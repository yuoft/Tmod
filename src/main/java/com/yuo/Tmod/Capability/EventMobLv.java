package com.yuo.Tmod.Capability;

import com.yuo.Tmod.Client.ConfigLoader;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Entity.EntityKiana;
import com.yuo.Tmod.Network.MessagePlayerLevel;
import com.yuo.Tmod.Network.NetworkLoader;
import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.World.MobLevelInfoDataSave;
import com.yuo.Tmod.World.PlayerLevelInfoDataSave;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServerMulti;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventMobLv {
    // 第一个参数指定一个别的 IAttribute 作为它的 parent。一般都是填 null，
    // 但如果你的属性真的是从别的属性衍生而来的话，建议加上。原版会通过
    // IAttribute.getParent() 把所有 parent 属性一并加入到目标实体上。
    // 第二个参数是 id，兼职 translation key。不要和别的属性重名了。
    // 第三个参数是该属性的初始值。
    // 第四个参数指定下限：该属性最低必须是这个值。
    // 第五个参数指定上限：该属性最高只能是这个值。
    //
    // setShouldWatch 决定了该属性的值的变化是否需要同步至逻辑客户端。
    // setShouldWatch(false) 会令逻辑客户端看不到该属性的变化。
    // 如果你不希望客户端看到这个值，或者客户端不需要知道这个值，可以用它
    // 来稍微节约一点点带宽。
    public static final IAttribute LIVING_LEVEL = new RangedAttribute(null, "tmod.level", 0, 0, ConfigLoader.level).setDescription("Tmod_Level").setShouldWatch(true);

    /*
     * 怪物等级
     * 监听事件生物生成事件，根据距离零点判断等级
     */
    public EventMobLv() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    //生物生成事件
    @SubscribeEvent
    public void mobSpawnLv(EntityJoinWorldEvent event) throws Exception {
        Entity entityLiving = event.getEntity();
        if (entityLiving instanceof EntityLiving && !event.getWorld().isRemote) {
            BlockPos pos = entityLiving.getPosition();//生成坐标
            int lv = getMobLv(pos, event.getWorld());//获取怪物等级
            EntityLiving living = (EntityLiving) entityLiving;
            int attrLv = getAttrLevelValue(living);
            if (attrLv > 0) return; //实体没有等级时，才会为实体设置属性
            World world = entityLiving.world;
            if (living instanceof EntityKiana) {
                lv = ConfigLoader.level; //模组boss等级固定为100级
            } else if (living instanceof EntityDragon) {
                lv = (int) (ConfigLoader.level * 0.6); //末影龙为 60
            } else if (living instanceof EntityWither || !living.isNonBoss()) {
                lv = (int) (ConfigLoader.level * 0.5); //凋零固定为50级
            }
            if (world.provider.getDimensionType().equals(DimensionType.NETHER)) {
                lv += 10; //地狱怪物比主世界高10级
            } else if (world.provider.getDimensionType().equals(DimensionType.THE_END)) {
                lv += 20; //地狱怪物比主世界高20级
            }
            lv = lv < ConfigLoader.level ? lv : ConfigLoader.level;
            setMobAttr(living, lv);
        }
        //玩家进入世界时发送数据
        if (!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            if (isPlayerHasLv(player)) {
                MessagePlayerLevel message = new MessagePlayerLevel();

                IPlayerLevel tmodLv = player.getCapability(CapabilityLoader.tmodLv, null);
                IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();
                NBTBase nbt = storage.writeNBT(CapabilityLoader.tmodLv, tmodLv, null);
                if (nbt != null){
                    message.nbt = new NBTTagCompound();
                    message.nbt.setTag("tmod_level", nbt);
                }

                NetworkLoader.instance.sendTo(message, player);
            }
        }
    }

    //为玩家添加Capaility
    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof EntityPlayer) {
            /*
             * 第一个参数需要传入一个ResourceLocation，作为ICapabilitySerializable的唯一标识符，这里我们使用的是"tmod:player_level"，
             * 第二个参数就是需要传入的ICapabilitySerializable。
             */
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityPlayerLevel.ProviderPlayer();
            event.addCapability(new ResourceLocation(Tmod.MOD_ID + ":" + "tmod_level"), provider);
        }
    }

    //玩家传送世界时复制数据
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        Capability<IPlayerLevel> capability = CapabilityLoader.tmodLv;

        if (event.getOriginal().hasCapability(capability, null)) {
            IPlayerLevel capability1 = event.getOriginal().getCapability(capability, null);
            EntityPlayer player = event.getEntityPlayer();
            IPlayerLevel capability2 = player.getCapability(capability, null);
            if (capability2 != null && capability1 != null){
                capability2.setPlayerLevel(capability1.getPlayerLevel());
                capability2.setPlayerExp(capability1.getPlayerExp());
            }
        }
    }

    //玩家合成物品时，设置物品等级
    @SubscribeEvent
    public void craftItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
        ItemStack crafting = event.crafting;
        EntityPlayer player = event.player;
        if (player.world.isRemote) return;
        setItemLv(crafting, player, true);
    }

    //玩家捡起物品时，添加等级
    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItem().getItem();
        if (player.world.isRemote) return;
        setItemLv(stack, player, false);
        if (stack.getItem() == ItemLoader.opSword) {
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack);
            if (level == 0) stack.addEnchantment(Enchantments.LOOTING, 10);
        }
        if (stack.getItem() == ItemLoader.opPickaxe) {
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            if (level == 0) stack.addEnchantment(Enchantments.FORTUNE, 10);
        }
    }

    //根据玩家等级初始化物品等级
    private void setItemLv(ItemStack stack, EntityPlayer player, boolean flag) {
        if (stack.isEmpty()) return;
        Item item = stack.getItem();
        if (item instanceof ItemSword || item instanceof ItemArmor || item instanceof ItemTool || item instanceof ItemBow) {
            if (isOpItem(item)) return; //op装备不添加等级 没有等级
            if (isPlayerHasLv(player) && (!stack.hasTagCompound() || (stack.getTagCompound() != null && !stack.getTagCompound().hasKey("tmod_level")))) {
                Random random = new Random();
                int lv;
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                Integer playerLevel = 0;
                if (capability != null) {
                    playerLevel = capability.getPlayerLevel();
                }
                if (flag) {
                    int i = Math.max(playerLevel - 10, 0); // (lv - 10) ~ lv
                    lv = random.nextInt(10) + i + 1;
                } else {
                    int i = Math.max(playerLevel - 15, 0);
                    lv = random.nextInt(25) + i + 1; // (lv - 15) ~ (lv + 10)
                }
                lv = lv < ConfigLoader.level ? lv : ConfigLoader.level;
                //设置等级信息nbt数据,等级为玩家等级以下
                stack.setTagInfo("tmod_level", new NBTTagInt(lv));
            }
        }
    }

    //是否是op装备
    public static boolean isOpItem(Item item) {
        return item == ItemLoader.opSword || item == ItemLoader.opPickaxe || item == ItemLoader.opHelmet ||
                item == ItemLoader.opChest || item == ItemLoader.opLegs || item == ItemLoader.opBoots;
    }

    //玩家无法使用高等级武器
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void useSword(LivingAttackEvent event) {
        Entity trueSource = event.getSource().getTrueSource();
        if (trueSource instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) trueSource;
            ItemStack stack = player.getHeldItemMainhand();
            if (!canPlayerUseItem(player, stack)) {
                tellPlayer(player, event);
            }
        }
        if (trueSource instanceof EntityDragon) {
            EntityLivingBase entityLiving = event.getEntityLiving();
            int value = getAttrLevelValue((EntityLiving) trueSource);
            entityLiving.attackEntityFrom(DamageSource.causeMobDamage((EntityDragon) trueSource), value / 2f);
        }
    }

    //玩家近战攻击等级生物伤害计算
    @SubscribeEvent
    public void attackEntity(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        Entity target = event.getTarget();
        if (target instanceof EntityLiving) {
            EntityLiving living = (EntityLiving) target; //被玩家攻击实体
            ItemStack stack = player.getHeldItemMainhand();
            //玩家使用武器有等级和被攻击实体有等级
            if (isItemHasLv(stack) && getAttrLevelValue(living) > 0) {
                int lv = 0;
                if (stack.getTagCompound() != null) {
                    lv = stack.getTagCompound().getInteger("tmod_level");
                }
                Item item = stack.getItem();
                int amount;
                if (item instanceof ItemSword) {
                    ItemSword sword = (ItemSword) item;
                    int damage = (int) Math.floor(sword.getAttackDamage() * 1.5);
                    amount = damage + lv;
                } else if (item instanceof ItemTool) {
                    ItemTool tool = (ItemTool) item;
                    Item.ToolMaterial material = Item.ToolMaterial.valueOf(tool.getToolMaterialName());
                    int damage = (int) Math.floor(material.getAttackDamage() * 1.5);
                    amount = damage + lv / 2;
                } else amount = lv;
                target.attackEntityFrom(DamageSource.causePlayerDamage(player), amount);
            }
            if (stack.isEmpty() && isPlayerHasLv(player)) {
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                if (capability != null) {
                    target.attackEntityFrom(DamageSource.causePlayerDamage(player), capability.getPlayerLevel() / 4f);
                }
            }
        }
    }

    //玩家使用等级工具 效率加成
    @SubscribeEvent
    public void toolEfficiency(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack mainhand = player.getHeldItemMainhand();
        if (mainhand.isEmpty()) return;
        if (isItemHasLv(mainhand)) {
            int level = 0;
            if (mainhand.getTagCompound() != null) {
                level = mainhand.getTagCompound().getInteger("tmod_level");
            }
            float originalSpeed = event.getOriginalSpeed();
            float newSpeed = originalSpeed * (1 + level / (float) ConfigLoader.level);
            event.setNewSpeed(newSpeed);
        }
        if (mainhand.getItem() == ItemLoader.opPickaxe) {
            if (!event.getEntityLiving().onGround || event.getEntityLiving().isInWater()) { //未站立状态,在水中 破坏速度加倍
                event.setNewSpeed(event.getOriginalSpeed() * 2);
            }
        }
    }

    //火焰弹增加伤害
    @SubscribeEvent
    public void dragonBall(ProjectileImpactEvent.Fireball event) {
        EntityFireball fireball = event.getFireball();
        EntityLivingBase shootingEntity = fireball.shootingEntity;
        if (shootingEntity instanceof EntityLiving && !fireball.world.isRemote) {
            EntityLiving living = (EntityLiving) shootingEntity;
            int lv = getAttrLevelValue(living);
            if (lv > 0) {
                RayTraceResult result = event.getRayTraceResult();
                Entity entityHit = result.entityHit;
                if (entityHit instanceof EntityLiving) {
                    entityHit.attackEntityFrom(DamageSource.causeMobDamage(living), (int) Math.ceil(lv / 2f));
                }
            }
        }
    }

    //玩家弓箭伤害加成
    @SubscribeEvent
    public void bowArrow(ProjectileImpactEvent.Arrow event) {
        EntityArrow arrow = event.getArrow();
        Entity shootingEntity = arrow.shootingEntity;
        if (shootingEntity instanceof EntityPlayer && !arrow.world.isRemote) {
            EntityPlayer player = (EntityPlayer) shootingEntity;
            ItemStack bow = getPlayerBow(player);
            if (!bow.isEmpty()) {
                if (bow.getTagCompound() != null && bow.getTagCompound().hasKey("tmod_level")) {
                    int level = bow.getTagCompound().getInteger("tmod_level");
                    arrow.setDamage(arrow.getDamage() + level / 4.5d);
                }
            }
        }
        if (shootingEntity instanceof EntityLiving) {
            EntityLiving living = (EntityLiving) shootingEntity;
            int value = getAttrLevelValue(living);
            if (value > 0) {
                arrow.setDamage(arrow.getDamage() + value / 5d);
            }
        }
    }

    //获取弓
    private ItemStack getPlayerBow(EntityPlayer player) {
        ItemStack mainhand = player.getHeldItemMainhand();
        ItemStack offhand = player.getHeldItemOffhand();
        if (!mainhand.isEmpty() && mainhand.getItem() instanceof ItemBow) { //主手有弓
            return mainhand;
        } else if ((mainhand.isEmpty() || !(mainhand.getItem() instanceof ItemBow))  //主手无弓 副手有弓
                && !offhand.isEmpty() && offhand.getItem() instanceof ItemBow) {
            return offhand;
        }
        return ItemStack.EMPTY;
    }

    //怪物攻击玩家计算减伤
    @SubscribeEvent
    public void attackPlayer(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer && entity.hasCapability(CapabilityLoader.tmodLv, null)) {
            EntityPlayer player = (EntityPlayer) entity;
            int opNum = getOpNum(player); //如果有op装备则不计算等级减伤
            if (opNum == 4) {
                event.setAmount(0);
            } else if (opNum != 0) {
                event.setAmount(0.1f - (0.03f * opNum));
            } else {
                int allLv = getPlayerArmorLv(player);
                //减伤计算
                float amount = event.getAmount();
                if (amount <= 0.25f) return;
                amount = (float) (amount - (allLv / 8.0)) < 0.25 ? 0.25f : (float) (amount - (allLv / 8.0));
                event.setAmount(amount);
            }
        }
    }

    //获取玩家op装备件数
    public static int getOpNum(EntityPlayer player) {
        boolean hasChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemLoader.opChest;
        boolean hasLeg = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemLoader.opLegs;
        boolean hasHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemLoader.opHelmet;
        boolean hasFeet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemLoader.opBoots;
        boolean[] bools = new boolean[]{hasHead, hasChest, hasLeg, hasFeet};
        int num = 0;
        for (int i = 0; i < 4; i++) {
            if (bools[i]) num++;
        }
        return num;
    }

    private int getPlayerArmorLv(EntityPlayer player) {
        //获取实体装备
        ItemStack StackChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack StackFeet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        ItemStack StackHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack StackLegs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        //获取整备tag
        NBTTagCompound tagNull = new NBTTagCompound();
        tagNull.setInteger("tmod_level", 0);
        NBTTagCompound tagCheat = StackChest.getTagCompound() == null ? tagNull : StackChest.getTagCompound();
        NBTTagCompound tagFeet = StackFeet.getTagCompound() == null ? tagNull : StackFeet.getTagCompound();
        NBTTagCompound tagHead = StackHead.getTagCompound() == null ? tagNull : StackHead.getTagCompound();
        NBTTagCompound tagLegs = StackLegs.getTagCompound() == null ? tagNull : StackLegs.getTagCompound();
        //获取装备总等级
        Integer chestLv = tagCheat.getInteger("tmod_level");// == 0 ? 0:StackChest.getTagCompound().getInteger("level");
        Integer feetLv = tagFeet.getInteger("tmod_level");
        Integer headLv = tagHead.getInteger("tmod_level");
        Integer legsLv = tagLegs.getInteger("tmod_level");
        return chestLv + feetLv + headLv + legsLv;
    }

    //玩家不能使用高等级物品
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void useRightClickItem(PlayerInteractEvent.RightClickItem event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (!player.world.isRemote && !canPlayerUseItem(player, stack)) {
            tellPlayer(player, event);
        }
    }

    //通知玩家无法使用物品 并取消事件
    private void tellPlayer(EntityPlayer player, Event event) {
        if (!player.world.isRemote)
            player.sendStatusMessage(new TextComponentTranslation("tmod.message.can_not_use"), true);
        if (event.isCancelable()) event.setCanceled(true);
    }

    //不能左键空气
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void useLeftClickItem(PlayerInteractEvent.LeftClickEmpty event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (!player.world.isRemote && !canPlayerUseItem(player, stack)) {
            tellPlayer(player, event);
        }
    }

    //不能左键方块
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void useLeftClickItemBlock(PlayerInteractEvent.LeftClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (!player.world.isRemote && !canPlayerUseItem(player, stack)) {
            tellPlayer(player, event);
        }
    }

    //玩家能否使用物品
    private boolean canPlayerUseItem(EntityPlayer player, ItemStack stack) {
        //物品为空 op物品 无等级 创造模式 玩家无等级 true
        if (stack.isEmpty() || isOpItem(stack.getItem()) || !isItemHasLv(stack) || player.isCreative() || !isPlayerHasLv(player))
            return true;
        IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
        if (capability == null) return false;
        Integer playerLevel = capability.getPlayerLevel();
        int level = 0;
        if (stack.getTagCompound() != null) {
            level = stack.getTagCompound().getInteger("tmod_level");
        }
        return level <= playerLevel;
    }

    //玩家无法装备高等级装备
    @SubscribeEvent
    public void onArmorUse(LivingEquipmentChangeEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if (!player.isCreative() && isPlayerHasLv(player)) {
                EntityEquipmentSlot slot = event.getSlot();
                if (slot.getSlotType().equals(EntityEquipmentSlot.Type.ARMOR)) {
                    ItemStack stack = player.inventory.armorInventory.get(slot.getIndex());
                    if (isItemHasLv(stack) && !canPlayerUseItem(player, stack)) {
                        if (!player.inventory.addItemStackToInventory(stack)) {
                            player.dropItem(stack, false);
                        }
                        player.inventory.armorInventory.set(slot.getIndex(), ItemStack.EMPTY);
                        tellPlayer(player, event);
                    }
                }
            }
        }
    }

    //玩家是否有等级
    private boolean isPlayerHasLv(EntityPlayer player) {
        return player.hasCapability(CapabilityLoader.tmodLv, null) && player.getCapability(CapabilityLoader.tmodLv, null) != null;
    }

    //物品是否有等级
    public static boolean isItemHasLv(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.getTagCompound() != null && stack.getTagCompound().hasKey("tmod_level");
    }

    //驯服动物
    @SubscribeEvent
    public void animalTame(AnimalTameEvent event) {
        EntityAnimal animal = event.getAnimal();
        if (animal instanceof EntityWolf && !event.getAnimal().world.isRemote) {
            EntityWolf wolf = (EntityWolf) animal;
            wolf.setTamedBy(event.getTamer());
            wolf.getNavigator().clearPath();
            wolf.setAttackTarget(null);
            wolf.getAISit().setSitting(true);
            wolf.setHealth(20.0F + getAttrLevelValue(wolf) * 5);
            playTameEffect(wolf);
            wolf.world.setEntityState(wolf, (byte) 7);
            event.setCanceled(true);
        }
    }

    //驯服效果
    private void playTameEffect(EntityWolf wolf) {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
        Random rand = wolf.world.rand;
        for (int i = 0; i < 7; ++i) {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            wolf.world.spawnParticle(enumparticletypes, wolf.posX + (double) (rand.nextFloat() * wolf.width * 2.0F) -
                            (double) wolf.width, wolf.posY + 0.5D + (double) (rand.nextFloat() * wolf.height),
                    wolf.posZ + (double) (rand.nextFloat() * wolf.width * 2.0F) - (double) wolf.width, d0, d1, d2);
        }
    }

    //玩家击杀等级生物,掉落对于等级小型经验碎片
    @SubscribeEvent
    public void playerKillLiving(LivingDeathEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();
        Entity trueSource = event.getSource().getTrueSource();
        if (entityLiving.world.isRemote) return;
        if (entityLiving instanceof EntityLiving && trueSource instanceof EntityPlayer) {
            EntityLiving living = (EntityLiving) entityLiving;
            EntityPlayer player = (EntityPlayer) trueSource;
            if (isPlayerHasLv(player) && !player.world.isRemote) { //打怪经验获取
                int mobLv = getAttrLevelValue(living);
                //获取玩家能力系统
                IPlayerLevel playerCap = player.getCapability(CapabilityLoader.tmodLv, null);
                if (playerCap != null){
                    Integer exp = playerCap.getPlayerExp();//获取经验值
                    Integer lv = playerCap.getPlayerLevel();
                    playerCap.setPlayerExp(exp + mobLv);//设置经验值
                    playerCap.setPlayerLevel(setPlayerLv(playerCap, lv, playerCap.getPlayerExp(), player)); //设置等级
                    //同步玩家数据
                    MessagePlayerLevel msg = new MessagePlayerLevel();
                    msg.nbt = new NBTTagCompound();
                    msg.nbt.setInteger("player_exp", playerCap.getPlayerExp());
                    msg.nbt.setInteger("player_level", playerCap.getPlayerLevel());
                    if (player instanceof EntityPlayerMP) {
                        NetworkLoader.instance.sendTo(msg, (EntityPlayerMP) player);
                    }
                }

            }
        }
    }

    //为原版实体添加属性
    @SubscribeEvent
    public void entityInit(EntityEvent.EntityConstructing event) {
        final Entity e = event.getEntity();
        if (e instanceof EntityLivingBase) {
            final EntityLivingBase living = (EntityLivingBase) e;
            living.getAttributeMap().registerAttribute(LIVING_LEVEL);
        }
    }

    //设置物品信息
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTooltip(ItemTooltipEvent event) {
        ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
        ItemStack stack = event.getItemStack();
        if (isItemHasLv(stack)) {
            Item item = stack.getItem();
            //有等级的 工具 盔甲 武器
            if (item instanceof ItemTool || item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemBow) {
                if (stack.getTagCompound() != null) {
                    tooltip.add(I18n.format("tmod.item.level") + stack.getTagCompound().getInteger("tmod_level")); //设置物品信息显示
                }
            }
        }
    }

    /**
     * 返回玩家的等级
     *
     * @param cap 能力
     * @param lv  当前等级
     * @param exp 当前等级所需升级经验
     * @return 等级
     */
    public static int setPlayerLv(IPlayerLevel cap, int lv, int exp, EntityPlayer player) {
        if (lv < 0) return 0;
        if (lv >= ConfigLoader.level) {
            lv = ConfigLoader.level;
            cap.setPlayerExp(0);
            return lv;
        }
        int[] num = expCeil(exp, lv, 0, 0);
        lv += num[0];
        exp -= num[1];
        if (lv == 100) player.sendMessage(new TextComponentTranslation("tmod.text.level_ceil"));
        if (num[0] > 0) player.sendMessage(new TextComponentTranslation("tmod.text.level_up"));
        cap.setPlayerExp(exp);
        return lv;
    }

    //经验升级
    private static int[] expCeil(int exp, int lv, int LvNum, int expDown) {
        int expCeil = (int) Math.pow(lv, 2) + 2 * lv;
        if (exp < expCeil) return new int[]{LvNum, expDown};
        else return expCeil(exp - expCeil, lv + 1, LvNum + 1, expDown + expCeil);
    }

    //怪物基础等级受时间影响增加
    @SubscribeEvent
    public void tickWorld(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (!world.isRemote && event.side == Side.SERVER && event.phase == TickEvent.Phase.END) {
            long worldTime = event.world.getTotalWorldTime();
            if (world instanceof WorldServerMulti) return; //防止多个世界维度重复触发
            MobLevelInfoDataSave dataSave = MobLevelInfoDataSave.get(world);
            int i = 1200 * dataSave.getMobLevelUpSpeed(); //增长速度
            if (i > 0 && worldTime % i == 0) {
                dataSave.addBase();
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                player.sendMessage(new TextComponentTranslation("tmod.text.mobLevelUp", dataSave.getMobLevelBase()));
            }
        }
    }

    //文本样式
    private static final Style style = new Style()
            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("tmod.message.login0")))
            .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://space.bilibili.com/21854371"));

    //在玩家登录时同步等级数据
    @SubscribeEvent
    public void playerLogin(PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        if (!player.world.isRemote) {
            MobLevelInfoDataSave dataSave = MobLevelInfoDataSave.get(player.world);
            if (dataSave.isEmpty()) dataSave.initData(); //数据为空时初始化默认数据
            PlayerLevelInfoDataSave infoDataSave = PlayerLevelInfoDataSave.get(player.world);
            if (!infoDataSave.isEmpty() && infoDataSave.isName(player.getName())) { //根据世界保存的数据来设置玩家等级信息
                PlayerLevelInfoDataSave.LevelInfo playerInfo = infoDataSave.getPlayerInfo(player.getName());
                Integer level = playerInfo.getLevel() > 0 ? playerInfo.getLevel() : 1;
                Integer exp = playerInfo.getExp();
                if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                    IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                    if (capability != null) {
                        capability.setPlayerLevel(level);
                        capability.setPlayerExp(exp);
                    }
                }
            }
        }
        //发送消息
        player.sendMessage(new TextComponentTranslation("tmod.message.login").setStyle(style));
    }

    //在玩家登出时保存等级数据
    @SubscribeEvent
    public void playerLoginOut(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;
        if (!player.world.isRemote) {
            PlayerLevelInfoDataSave dataSave = PlayerLevelInfoDataSave.get(player.world);
            String name = player.getName();
            if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                if (capability != null) {
                if (dataSave.isName(name))
                    dataSave.change(name, capability.getPlayerLevel(), capability.getPlayerExp());
                else dataSave.add(name, capability.getPlayerLevel(), capability.getPlayerExp());
                }
            }
        }
    }

    //获取怪物等级（随时间和玩家等级变化）
    private int getMobLv(BlockPos pos, World world) {
        int baseLv = MobLevelInfoDataSave.get(world).getMobLevelBase();
        int playerLv = playerLv(pos, world, baseLv);
        if (playerLv > 0) baseLv += playerLv;
        int posLv = mobPosAndZeroDistance(pos);
        if (posLv > 0) baseLv += posLv;
        //受时间影响
        return mobLvRange(Math.max(baseLv, 1), world.rand);
    }

    //返回玩家等级影响程度
    private int playerLv(BlockPos pos, World world, int baseLv) {
        int lv = 0;
        //生成距离玩家较近时受玩家等级影响
        //怪物和玩家的距离
        int mobPlayerRange = 16;
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class,
                new AxisAlignedBB(pos.add(-mobPlayerRange, -mobPlayerRange, -mobPlayerRange), pos.add(mobPlayerRange, mobPlayerRange, mobPlayerRange)));
        if (players.size() == 0) return 0;
        for (EntityPlayer player : players) {
            if (isPlayerHasLv(player)) {
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                if (capability != null) {
                    lv += capability.getPlayerLevel();
                }
            }
        }
        int avgLv = (int) Math.floor(lv / (players.size() * 1.0d));
        if (avgLv > baseLv + 10) {
            return (int) Math.floor((avgLv - baseLv + 10) / 2d); //增加等级
        } else if (avgLv < baseLv - 10) {
            return -(int) Math.floor((baseLv - avgLv - 10) / 2d); //减少等级
        } else return 0;
    }

    //根据基础等级返回随机等级
    private int mobLvRange(int level, Random random) {
        int timeRange = Math.max(level - 10, 1);
        int lv;
        if (level <= 10) {
            lv = random.nextInt(level) + 1;
        } else lv = random.nextInt(16) + timeRange; //在-10 ~ +5范围内变化
        return lv < ConfigLoader.level ? lv : ConfigLoader.level;
    }

    //根据生物距离零点坐标的距离，返回影响生物等级数值
    private int mobPosAndZeroDistance(BlockPos pos) {
        double distance = pos.getDistance(0, 64, 0);
        return (int) Math.floor(distance / 500);
    }

    //设置怪物属性
    private void setMobAttr(EntityLiving entityLiving, int lv) throws Exception {
        float health = entityLiving.getHealth() + lv * 5;
        try {
            if (entityLiving instanceof EntityMob || entityLiving instanceof EntityWolf) {
                IAttributeInstance attackDamage = entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
                AttributeModifier modifier = ModAttributeModifier.getModifier(ModAttributeModifier.ATTR_TYPE.DAMAGE, lv * 0.5);
                attackDamage.applyModifier(modifier);
            }
        } catch (Exception e) {
            throw new Exception(entityLiving.getName() + " <AttackDamage> Attr Is Null");
        }
        try {
            IAttributeInstance armor = entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ARMOR);
            AttributeModifier modifier = ModAttributeModifier.getModifier(ModAttributeModifier.ATTR_TYPE.ARMOR, lv * 0.5);
            armor.applyModifier(modifier);
        } catch (Exception e) {
            throw new Exception(entityLiving.getName() + " <Armor> Attr Is Null");
        }
        try {
            IAttributeInstance maxHealth = entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
            AttributeModifier modifier = ModAttributeModifier.getModifier(ModAttributeModifier.ATTR_TYPE.HEALTH, lv * 5);
            maxHealth.applyModifier(modifier);
            entityLiving.setHealth(health);
        } catch (Exception e) {
            throw new Exception(entityLiving.getName() + " <MaxHealth> Attr Is Null");
        }
        try {
            IAttributeInstance level = entityLiving.getAttributeMap().getAttributeInstance(LIVING_LEVEL);
            AttributeModifier modifier = ModAttributeModifier.getModifier(ModAttributeModifier.ATTR_TYPE.LEVEL, lv);
            level.applyModifier(modifier);
        } catch (Exception e) {
            throw new Exception(entityLiving.getName() + " <Level> Attr Is Null");
        }
        if (entityLiving instanceof EntityIronGolem) {
            IAttributeInstance moveSpeed = entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED);
            AttributeModifier modifier = ModAttributeModifier.getModifier(ModAttributeModifier.ATTR_TYPE.MOVE_SPEED, Math.min(lv * 0.001, 0.2));
            moveSpeed.applyModifier(modifier);
        }
        entityLiving.addTag("Lv:" + lv);
        if (ConfigLoader.mobInfo) {
            setLivingInfo(entityLiving, lv);
        }
    }

    //设置生物等级显示
    private void setLivingInfo(EntityLiving entityLiving, int lv) {
        if (entityLiving instanceof EntityVillager) { //村民
            EntityVillager villager = (EntityVillager) entityLiving;
            ResourceLocation profession = villager.getProfessionForge().getRegistryName();
            if (!entityLiving.getCustomNameTag().contains("Lv:")) { //不存在等级信息
                entityLiving.setCustomNameTag(entityLiving.getCustomNameTag() + "Lv:" + lv + " " + entityLiving.getName() + ":" +
                        I18n.format("entity.Villager." + getVillagerName(profession != null ? profession.toString() : "")));
            }
        } else {
            if (!entityLiving.getCustomNameTag().contains("Lv:")) {
                entityLiving.setCustomNameTag(entityLiving.getCustomNameTag() + "Lv:" + lv + " " + entityLiving.getName()); //重设实体名称
            }
        }
    }

    //获取村民职业 string
    private String getVillagerName(String str) {
        String[] split = str.split(":");
        if (split[0].equals("minecraft")) {
            String replace = str.replace(split[0], "");
            return replace.replace(":", "");
        } else {
            return str.replace(":", ".");
        }
    }

    //获取生物等级
    public static int getAttrLevelValue(EntityLiving living) {
        if (living == null) return 0;
        return (int) living.getAttributeMap().getAttributeInstance(LIVING_LEVEL).getAttributeValue();
    }
}
