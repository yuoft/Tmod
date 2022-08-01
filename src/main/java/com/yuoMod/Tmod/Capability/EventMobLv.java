package com.yuoMod.Tmod.Capability;

import com.yuoMod.Tmod.Client.ConfigLoader;
import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Network.MessagePlayerLevel;
import com.yuoMod.Tmod.Network.NetworkLoader;
import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.World.MobLevelInfoDataSave;
import com.yuoMod.Tmod.World.PlayerLevelInfoDataSave;
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
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
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
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventMobLv {
    // ��һ������ָ��һ����� IAttribute ��Ϊ���� parent��һ�㶼���� null��
    // ����������������Ǵӱ���������������Ļ���������ϡ�ԭ���ͨ��
    // IAttribute.getParent() ������ parent ����һ�����뵽Ŀ��ʵ���ϡ�
    // �ڶ��������� id����ְ translation key����Ҫ�ͱ�����������ˡ�
    // �����������Ǹ����Եĳ�ʼֵ��
    // ���ĸ�����ָ�����ޣ���������ͱ��������ֵ��
    // ���������ָ�����ޣ����������ֻ�������ֵ��
    //
    // setShouldWatch �����˸����Ե�ֵ�ı仯�Ƿ���Ҫͬ�����߼��ͻ��ˡ�
    // setShouldWatch(false) �����߼��ͻ��˿����������Եı仯��
    // ����㲻ϣ���ͻ��˿������ֵ�����߿ͻ��˲���Ҫ֪�����ֵ����������
    // ����΢��Լһ������
    public static final IAttribute LIVING_LEVEL = new RangedAttribute(null, "tmod.level", 0, 0, ConfigLoader.level).setDescription("Tmod_Level").setShouldWatch(true);

    /*
     * ����ȼ�
     * �����¼����������¼������ݾ�������жϵȼ�
     */
    public EventMobLv() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    //���������¼�
    @SubscribeEvent
    public void mobSpawnLv(EntityJoinWorldEvent event) throws Exception {
        Entity entityLiving = event.getEntity();
        if (entityLiving instanceof EntityLiving && !event.getWorld().isRemote) {
            BlockPos pos = entityLiving.getPosition();//��������
            int lv = getMobLv(pos, event.getWorld());//��ȡ����ȼ�
            EntityLiving living = (EntityLiving) entityLiving;
            int attrLv = getAttrLevelValue(living);
            if (attrLv > 0) return; //ʵ��û�еȼ�ʱ���Ż�Ϊʵ����������
            World world = entityLiving.world;
            if (living instanceof EntityKiana) {
                lv = ConfigLoader.level; //ģ��boss�ȼ��̶�Ϊ100��
            } else if (living instanceof EntityDragon) {
                lv = (int) (ConfigLoader.level * 0.6); //ĩӰ��Ϊ 60
            } else if (living instanceof EntityWither || !living.isNonBoss()) {
                lv = (int) (ConfigLoader.level * 0.5); //����̶�Ϊ50��
            }
            if (world.provider.getDimensionType().equals(DimensionType.NETHER)) {
                lv += 10; //����������������10��
            } else if (world.provider.getDimensionType().equals(DimensionType.THE_END)) {
                lv += 20; //����������������20��
            }
            lv = lv < ConfigLoader.level ? lv : ConfigLoader.level;
            setMobAttr(living, lv);
        }
        //��ҽ�������ʱ��������
        if (!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            if (isPlayerHasLv(player)) {
                MessagePlayerLevel message = new MessagePlayerLevel();

                IPlayerLevel tmodLv = player.getCapability(CapabilityLoader.tmodLv, null);
                IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();

                message.nbt = new NBTTagCompound();
                message.nbt.setTag("tmod_level", storage.writeNBT(CapabilityLoader.tmodLv, tmodLv, null));

                NetworkLoader.instance.sendTo(message, player);
            }
        }
    }

    //Ϊ������Capaility
    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof EntityPlayer) {
            /*
             * ��һ��������Ҫ����һ��ResourceLocation����ΪICapabilitySerializable��Ψһ��ʶ������������ʹ�õ���"tmod:player_level"��
             * �ڶ�������������Ҫ�����ICapabilitySerializable��
             */
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityPlayerLevel.ProviderPlayer();
            event.addCapability(new ResourceLocation(Tmod.MOD_ID + ":" + "tmod_level"), provider);
        }
    }

    //��Ҵ�������ʱ��������
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        Capability<IPlayerLevel> capability = CapabilityLoader.tmodLv;

        if (event.getOriginal().hasCapability(capability, null)) {
            IPlayerLevel capability1 = event.getOriginal().getCapability(capability, null);
            EntityPlayer player = event.getEntityPlayer();
            IPlayerLevel capability2 = player.getCapability(capability, null);
            capability2.setPlayerLevel(capability1.getPlayerLevel());
            capability2.setPlayerExp(capability1.getPlayerExp());
        }
    }

    //��Һϳ���Ʒʱ��������Ʒ�ȼ�
    @SubscribeEvent
    public void craftItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
        ItemStack crafting = event.crafting;
        EntityPlayer player = event.player;
        if (player.world.isRemote) return;
        setItemLv(crafting, player, true);
    }

    //��Ҽ�����Ʒʱ����ӵȼ�
    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItem().getItem();
        if (player.world.isRemote) return;
        setItemLv(stack, player, false);
        if (stack.getItem() == ItemLoader.op_sword) {
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack);
            if (level == 0) stack.addEnchantment(Enchantments.LOOTING, 10);
        }
        if (stack.getItem() == ItemLoader.op_pickaxe) {
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            if (level == 0) stack.addEnchantment(Enchantments.FORTUNE, 10);
        }
    }

    //������ҵȼ���ʼ����Ʒ�ȼ�
    private void setItemLv(ItemStack stack, EntityPlayer player, boolean flag) {
        if (stack.isEmpty()) return;
        Item item = stack.getItem();
        if (item instanceof ItemSword || item instanceof ItemArmor || item instanceof ItemTool || item instanceof ItemBow) {
            if (isOpItem(item)) return; //opװ������ӵȼ� û�еȼ�
            if (isPlayerHasLv(player) && (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("tmod_level"))) {
                Random random = new Random();
                int lv = 0;
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                Integer playerLevel = capability.getPlayerLevel();
                if (flag) {
                    int i = playerLevel - 10 < 0 ? 0 : playerLevel - 10; // (lv - 10) ~ lv
                    lv = random.nextInt(10) + i + 1;
                } else {
                    int i = playerLevel - 15 < 0 ? 0 : playerLevel - 15;
                    lv = random.nextInt(25) + i + 1; // (lv - 15) ~ (lv + 10)
                }
                lv = lv < ConfigLoader.level ? lv : ConfigLoader.level;
                //���õȼ���Ϣnbt����,�ȼ�Ϊ��ҵȼ�����
                stack.setTagInfo("tmod_level", new NBTTagInt(lv));
            }
        }
    }

    //�Ƿ���opװ��
    public static boolean isOpItem(Item item) {
        return item == ItemLoader.op_sword || item == ItemLoader.op_pickaxe || item == ItemLoader.op_helmet ||
                item == ItemLoader.op_chestplate || item == ItemLoader.op_leggings || item == ItemLoader.op_boots;
    }

    //����޷�ʹ�øߵȼ�����
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
            entityLiving.attackEntityFrom(DamageSource.causeMobDamage((EntityDragon) trueSource), value / 2);
        }
    }

    //��ҽ�ս�����ȼ������˺�����
    @SubscribeEvent
    public void attackEntity(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        Entity target = event.getTarget();
        if (target instanceof EntityLiving) {
            EntityLiving living = (EntityLiving) target; //����ҹ���ʵ��
            ItemStack stack = player.getHeldItemMainhand();
            //���ʹ�������еȼ��ͱ�����ʵ���еȼ�
            if (isItemHasLv(stack) && getAttrLevelValue(living) > 0) {
                Integer lv = stack.getTagCompound().getInteger("tmod_level");
                Item item = stack.getItem();
                int amount = 0;
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
                target.attackEntityFrom(DamageSource.causePlayerDamage(player), capability.getPlayerLevel() / 4);
            }
            //op��ֱ��ɱ��Kiana
//            if (stack.getItem().equals(itemLoader.op_sword) && living instanceof EntityKiana) {
//                living.setHealth(0.1f);
//            }
        }
    }

    //���ʹ�õȼ����� Ч�ʼӳ�
    @SubscribeEvent
    public void toolEfficiency(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack mainhand = player.getHeldItemMainhand();
        if (mainhand.isEmpty()) return;
        if (isItemHasLv(mainhand)) {
            int level = mainhand.getTagCompound().getInteger("tmod_level");
            float originalSpeed = event.getOriginalSpeed();
            float newSpeed = originalSpeed * (1 + level / (float) ConfigLoader.level);
            event.setNewSpeed(newSpeed);
        }
        if (mainhand.getItem() == ItemLoader.op_pickaxe) {
            if (!event.getEntityLiving().onGround || event.getEntityLiving().isInWater()) { //δվ��״̬,��ˮ�� �ƻ��ٶȼӱ�
                event.setNewSpeed(event.getOriginalSpeed() * 2);
            }
        }
    }

    //���浯�����˺�
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
                    entityHit.attackEntityFrom(DamageSource.causeMobDamage(living), (int) Math.ceil(lv / 2));
                }
            }
        }
    }

    //��ҹ����˺��ӳ�
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

    //��ȡ��
    private ItemStack getPlayerBow(EntityPlayer player) {
        ItemStack mainhand = player.getHeldItemMainhand();
        ItemStack offhand = player.getHeldItemOffhand();
        if (!mainhand.isEmpty() && mainhand.getItem() instanceof ItemBow) { //�����й�
            return mainhand;
        } else if ((mainhand.isEmpty() || !(mainhand.getItem() instanceof ItemBow))  //�����޹� �����й�
                && !offhand.isEmpty() && offhand.getItem() instanceof ItemBow) {
            return offhand;
        }
        return ItemStack.EMPTY;
    }

    //���﹥����Ҽ������
    @SubscribeEvent
    public void attackPlayer(LivingDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof EntityPlayer && entity.hasCapability(CapabilityLoader.tmodLv, null)) {
            EntityPlayer player = (EntityPlayer) entity;
            int opNum = getOpNum(player); //�����opװ���򲻼���ȼ�����
            if (opNum == 4) {
                event.setAmount(0);
            } else if (opNum != 0) {
                event.setAmount(0.1f - (0.03f * opNum));
            } else {
                int allLv = getPlayerArmorLv(player);
                //���˼���
                float amount = event.getAmount();
                if (amount <= 0.25f) return;
                amount = (float) (amount - (allLv / 8.0)) < 0.25 ? 0.25f : (float) (amount - (allLv / 8.0));
                event.setAmount(amount);
            }
        }
    }

    //��ȡ���opװ������
    public static int getOpNum(EntityPlayer player) {
        boolean hasChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemLoader.op_chestplate;
        boolean hasLeg = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemLoader.op_leggings;
        boolean hasHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemLoader.op_helmet;
        boolean hasFeet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemLoader.op_boots;
        boolean[] bools = new boolean[]{hasHead, hasChest, hasLeg, hasFeet};
        int num = 0;
        for (int i = 0; i < 4; i++) {
            if (bools[i]) num++;
        }
        return num;
    }

    private int getPlayerArmorLv(EntityPlayer player) {
        //��ȡʵ��װ��
        ItemStack StackChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
        ItemStack StackFeet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        ItemStack StackHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
        ItemStack StackLegs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty() ? ItemStack.EMPTY : player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
        //��ȡ����tag
        NBTTagCompound tagNull = new NBTTagCompound();
        tagNull.setInteger("tmod_level", 0);
        NBTTagCompound tagCheat = StackChest.getTagCompound() == null ? tagNull : StackChest.getTagCompound();
        NBTTagCompound tagFeet = StackFeet.getTagCompound() == null ? tagNull : StackFeet.getTagCompound();
        NBTTagCompound tagHead = StackHead.getTagCompound() == null ? tagNull : StackHead.getTagCompound();
        NBTTagCompound tagLegs = StackLegs.getTagCompound() == null ? tagNull : StackLegs.getTagCompound();
        //��ȡװ���ܵȼ�
        Integer chestLv = tagCheat.getInteger("tmod_level");// == 0 ? 0:StackChest.getTagCompound().getInteger("level");
        Integer feetLv = tagFeet.getInteger("tmod_level");
        Integer headLv = tagHead.getInteger("tmod_level");
        Integer legsLv = tagLegs.getInteger("tmod_level");
        Integer allLv = chestLv + feetLv + headLv + legsLv;
        return allLv;
    }

    //��Ҳ���ʹ�øߵȼ���Ʒ
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void useRightClickItem(PlayerInteractEvent.RightClickItem event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (!player.world.isRemote && !canPlayerUseItem(player, stack)) {
            tellPlayer(player, event);
        }
    }

    //֪ͨ����޷�ʹ����Ʒ ��ȡ���¼�
    private void tellPlayer(EntityPlayer player, Event event) {
        if (!player.world.isRemote)
            player.sendStatusMessage(new TextComponentTranslation("tmod.message.can_not_use"), true);
        if (event.isCancelable()) event.setCanceled(true);
    }

    //�����������
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void useLeftClickItem(PlayerInteractEvent.LeftClickEmpty event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (!player.world.isRemote && !canPlayerUseItem(player, stack)) {
            tellPlayer(player, event);
        }
    }

    //�����������
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void useLeftClickItemBlock(PlayerInteractEvent.LeftClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (!player.world.isRemote && !canPlayerUseItem(player, stack)) {
            tellPlayer(player, event);
        }
    }

    //����ܷ�ʹ����Ʒ
    private boolean canPlayerUseItem(EntityPlayer player, ItemStack stack) {
        //��ƷΪ�� op��Ʒ �޵ȼ� ����ģʽ ����޵ȼ� true
        if (stack.isEmpty() || isOpItem(stack.getItem()) || !isItemHasLv(stack) || player.isCreative() || !isPlayerHasLv(player))
            return true;
        IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
        if (capability == null) return false;
        Integer playerLevel = capability.getPlayerLevel();
        int level = stack.getTagCompound().getInteger("tmod_level");
        return level <= playerLevel;
    }

    //����޷�װ���ߵȼ�װ��
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

    //����Ƿ��еȼ�
    private boolean isPlayerHasLv(EntityPlayer player) {
        return player.hasCapability(CapabilityLoader.tmodLv, null) && player.getCapability(CapabilityLoader.tmodLv, null) != null;
    }

    //��Ʒ�Ƿ��еȼ�
    public static boolean isItemHasLv(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.getTagCompound() != null && stack.getTagCompound().hasKey("tmod_level");
    }

    //ѱ������
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
            playTameEffect(wolf, true);
            wolf.world.setEntityState(wolf, (byte) 7);
            event.setCanceled(true);
        }
    }

    //ѱ��Ч��
    private void playTameEffect(EntityWolf wolf, boolean play) {
        EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

        Random rand = wolf.world.rand;

        if (!play) {
            enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            wolf.world.spawnParticle(enumparticletypes, wolf.posX + (double) (rand.nextFloat() * wolf.width * 2.0F) -
                            (double) wolf.width, wolf.posY + 0.5D + (double) (rand.nextFloat() * wolf.height),
                    wolf.posZ + (double) (rand.nextFloat() * wolf.width * 2.0F) - (double) wolf.width, d0, d1, d2);
        }
    }

    //��һ�ɱ�ȼ�����,������ڵȼ�С�;�����Ƭ
    @SubscribeEvent
    public void playerKillLiving(LivingDeathEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();
        Entity trueSource = event.getSource().getTrueSource();
        if (entityLiving.world.isRemote) return;
        if (entityLiving instanceof EntityLiving && trueSource instanceof EntityPlayer) {
            EntityLiving living = (EntityLiving) entityLiving;
            EntityPlayer player = (EntityPlayer) trueSource;
            if (isPlayerHasLv(player) && !player.world.isRemote) { //��־����ȡ
                int mobLv = getAttrLevelValue(living);
                //��ȡ�������ϵͳ
                IPlayerLevel playerCap = player.getCapability(CapabilityLoader.tmodLv, null);
                Integer exp = playerCap.getPlayerExp();//��ȡ����ֵ
                Integer lv = playerCap.getPlayerLevel();
                playerCap.setPlayerExp(exp + mobLv);//���þ���ֵ
                playerCap.setPlayerLevel(setPlayerLv(playerCap, lv, playerCap.getPlayerExp(), player)); //���õȼ�

                //ͬ���������
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

    //Ϊԭ��ʵ���������
    @SubscribeEvent
    public void entityInit(EntityEvent.EntityConstructing event) {
        final Entity e = event.getEntity();
        if (e instanceof EntityLivingBase) {
            final EntityLivingBase living = (EntityLivingBase) e;
            living.getAttributeMap().registerAttribute(LIVING_LEVEL);
        }
    }

    //������Ʒ��Ϣ
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTooltip(ItemTooltipEvent event) {
        ArrayList<String> tooltip = (ArrayList<String>) event.getToolTip();
        ItemStack stack = event.getItemStack();
        if (isItemHasLv(stack)) {
            Item item = stack.getItem();
            //�еȼ��� ���� ���� ����
            if (item instanceof ItemTool || item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemBow) {
                tooltip.add(I18n.format("tmod.item.level") + stack.getTagCompound().getInteger("tmod_level")); //������Ʒ��Ϣ��ʾ
            }
        }
    }

    /**
     * ������ҵĵȼ�
     *
     * @param cap ����
     * @param lv  ��ǰ�ȼ�
     * @param exp ��ǰ�ȼ�������������
     * @return �ȼ�
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

    //��������
    private static int[] expCeil(int exp, int lv, int LvNum, int expDown) {
        int expCeil = (int) Math.pow(lv, 2) + 2 * lv;
        if (exp < expCeil) return new int[]{LvNum, expDown};
        else return expCeil(exp - expCeil, lv + 1, LvNum + 1, expDown + expCeil);
    }

    //��������ȼ���ʱ��Ӱ������
    @SubscribeEvent
    public void tickWorld(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (!world.isRemote && event.side == Side.SERVER && event.phase == TickEvent.Phase.END) {
            long worldTime = event.world.getTotalWorldTime();
            if (world instanceof WorldServerMulti) return; //��ֹ�������ά���ظ�����
            MobLevelInfoDataSave dataSave = MobLevelInfoDataSave.get(world);
            int i = 1200 * dataSave.getMobLevelUpSpeed(); //�����ٶ�
            if (i > 0 && worldTime % i == 0) {
                dataSave.addBase();
                EntityPlayerSP player = Minecraft.getMinecraft().player;
                player.sendMessage(new TextComponentTranslation("tmod.text.mobLevelUp", dataSave.getMobLevelBase()));
            }
        }
    }

    //�ı���ʽ
    private static final Style style = new Style()
            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("tmod.message.login0")))
            .setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://space.bilibili.com/21854371"));

    //����ҵ�¼ʱͬ���ȼ�����
    @SubscribeEvent
    public void playerLogin(PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        if (!player.world.isRemote) {
            MobLevelInfoDataSave dataSave = MobLevelInfoDataSave.get(player.world);
            if (dataSave.isEmpty()) dataSave.initData(); //����Ϊ��ʱ��ʼ��Ĭ������
            PlayerLevelInfoDataSave infoDataSave = PlayerLevelInfoDataSave.get(player.world);
            if (!infoDataSave.isEmpty() && infoDataSave.isName(player.getName())) { //�������籣���������������ҵȼ���Ϣ
                PlayerLevelInfoDataSave.LevelInfo playerInfo = infoDataSave.getPlayerInfo(player.getName());
                Integer level = playerInfo.getLevel() > 0 ? playerInfo.getLevel() : 1;
                Integer exp = playerInfo.getExp();
                if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                    IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                    capability.setPlayerLevel(level);
                    capability.setPlayerExp(exp);
                }
            }
        }
        //������Ϣ
        player.sendMessage(new TextComponentTranslation("tmod.message.login").setStyle(style));
    }

    //����ҵǳ�ʱ����ȼ�����
    @SubscribeEvent
    public void playerLoginOut(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;
        if (!player.world.isRemote) {
            PlayerLevelInfoDataSave dataSave = PlayerLevelInfoDataSave.get(player.world);
            String name = player.getName();
            if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                if (dataSave.isName(name))
                    dataSave.change(name, capability.getPlayerLevel(), capability.getPlayerExp());
                else dataSave.add(name, capability.getPlayerLevel(), capability.getPlayerExp());
            }
        }
    }

    //��ȡ����ȼ�����ʱ�����ҵȼ��仯��
    private int getMobLv(BlockPos pos, World world) {
        int baseLv = MobLevelInfoDataSave.get(world).getMobLevelBase();
        int playerLv = playerLv(pos, world, baseLv);
        if (playerLv > 0) baseLv += playerLv;
        int posLv = mobPosAndZeroDistance(pos);
        if (posLv > 0) baseLv += posLv;
        //��ʱ��Ӱ��
        return mobLvRange(baseLv < 1 ? 1 : baseLv, world.rand);
    }

    //������ҵȼ�Ӱ��̶�
    private int playerLv(BlockPos pos, World world, int baseLv) {
        int lv = 0;
        //���ɾ�����ҽϽ�ʱ����ҵȼ�Ӱ��
        //�������ҵľ���
        int mobPlayerRange = 16;
        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class,
                new AxisAlignedBB(pos.add(-mobPlayerRange, -mobPlayerRange, -mobPlayerRange), pos.add(mobPlayerRange, mobPlayerRange, mobPlayerRange)));
        if (players.size() == 0) return 0;
        for (EntityPlayer player : players) {
            if (isPlayerHasLv(player)) {
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                lv += capability.getPlayerLevel();
            }
        }
        int avgLv = (int) Math.floor(lv / (players.size() * 1.0d));
        if (avgLv > baseLv + 10) {
            return (int) Math.floor((avgLv - baseLv + 10) / 2d); //���ӵȼ�
        } else if (avgLv < baseLv - 10) {
            return -(int) Math.floor((baseLv - avgLv - 10) / 2d); //���ٵȼ�
        } else return 0;
    }

    //���ݻ����ȼ���������ȼ�
    private int mobLvRange(int level, Random random) {
        int timeRange = level - 10 < 1 ? 1 : level - 10;
        int lv;
        if (level <= 10) {
            lv = random.nextInt(level) + 1;
        } else lv = random.nextInt(16) + timeRange; //��-10 ~ +5��Χ�ڱ仯
        return lv < ConfigLoader.level ? lv : ConfigLoader.level;
    }

    //������������������ľ��룬����Ӱ������ȼ���ֵ
    private int mobPosAndZeroDistance(BlockPos pos) {
        double distance = pos.getDistance(0, 64, 0);
        return (int) Math.floor(distance / 500);
    }

    //���ù�������
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
            AttributeModifier modifier = ModAttributeModifier.getModifier(ModAttributeModifier.ATTR_TYPE.MOVE_SPEED, lv * 0.001 < 0.2 ? lv * 0.001 : 0.2);
            moveSpeed.applyModifier(modifier);
        }
        entityLiving.addTag("Lv:" + lv);
        if (ConfigLoader.mobInfo) {
            setLivingInfo(entityLiving, lv);
        }
    }

    //��������ȼ���ʾ
    private void setLivingInfo(EntityLiving entityLiving, int lv) {
        if (entityLiving instanceof EntityVillager) { //����
            EntityVillager villager = (EntityVillager) entityLiving;
            ResourceLocation profession = villager.getProfessionForge().getRegistryName();
            if (!entityLiving.getCustomNameTag().contains("Lv:")) { //�����ڵȼ���Ϣ
                entityLiving.setCustomNameTag(entityLiving.getCustomNameTag() + "Lv:" + lv + " " + entityLiving.getName() + ":" +
                        I18n.format("entity.Villager." + getVillagerName(profession.toString())));
            }
        } else {
            if (!entityLiving.getCustomNameTag().contains("Lv:")) {
                entityLiving.setCustomNameTag(entityLiving.getCustomNameTag() + "Lv:" + lv + " " + entityLiving.getName()); //����ʵ������
            }
        }
    }

    //��ȡ����ְҵ string
    private String getVillagerName(String str) {
        String[] split = str.split(":");
        if (split[0].equals("minecraft")) {
            String replace = str.replace(split[0], "");
            return replace.replace(":", "");
        } else {
            return str.replace(":", ".");
        }
    }

    //��ȡ����ȼ�
    public static int getAttrLevelValue(EntityLiving living) {
        if (living == null) return 0;
        return (int) living.getAttributeMap().getAttributeInstance(LIVING_LEVEL).getAttributeValue();
    }
    //���������Ϳ�������
//	private void setItemAttr(int lv, Item item) {
//		if(item instanceof ItemSword) {
//			ItemSword sword = (ItemSword) item;
//			sword.getAttackDamage();
//		}
//	}
}
