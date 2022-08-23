package com.yuo.Tmod.Common;

import com.yuo.Tmod.Capability.EventMobLv;
import com.yuo.Tmod.Common.Items.Armor.OPArmor;
import com.yuo.Tmod.Common.Items.Armor.TotemArmor;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Common.Items.Tool.OPPickaxe;
import com.yuo.Tmod.Common.Items.Tool.OPSword;
import com.yuo.Tmod.Common.Items.UpGradeGem;
import com.yuo.Tmod.Enchantment.*;
import com.yuo.Tmod.Potion.PotionLoader;
import com.yuo.Tmod.TileEntity.TileTorcherino;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMagma;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventLoader {
    /*
     * 各种事件
     */
    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    //加速火把
    @SubscribeEvent
    public void speedTorch(RightClickBlock event) {
        if (event.getHand() == EnumHand.OFF_HAND) return;
        EntityPlayer player = event.getEntityPlayer();
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());
        if (!(tile instanceof TileTorcherino)) return;
        if (!event.getWorld().isRemote) {
            TileTorcherino torch = (TileTorcherino) tile;
            //非潜行切换速度
            torch.changeMode(!player.isSneaking()); //潜行切换范围
            player.sendStatusMessage(torch.getDescription(), true); //hud显示信息
        }
        event.setCanceled(true);
    }

    //升级宝石和op套 不会被岩浆烧毁
    @SideOnly(value = Side.CLIENT)
    @SubscribeEvent
    public void entityItemUnDeath(ItemEvent event) { //物品实体事件
        EntityItem entityItem = event.getEntityItem();
        Item item = entityItem.getItem().getItem();
        if (item instanceof UpGradeGem || item instanceof OPArmor || item instanceof OPSword
                || item instanceof OPPickaxe) {
            entityItem.setEntityInvulnerable(true); // 设置物品实体不会死亡
        }
    }

    //铁砧配方
//    @SubscribeEvent
    public void NewRecipes(AnvilUpdateEvent event) {
        ItemStack stack = event.getLeft();
        ItemStack stack2 = event.getRight();
        int count1 = stack.getCount();
        int count2 = stack2.getCount();
        if (stack.getItem().equals(Items.STRING) && stack2.getItem().equals(ItemLoader.spacePatch)) {
            if (count1 <= count2) {
                event.setCost(5 * count1);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemLoader.spaceLine, count1));
            }
        }
        if (stack.getItem().equals(Items.STRING) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count1 <= count2) {
                event.setCost(3 * count1);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemLoader.dragonString, count1));
            }
        }
        if (stack.getItem().equals(Items.DIAMOND_HELMET) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count2 >= 5) {
                event.setCost(10);
                event.setMaterialCost(5);
                event.setOutput(new ItemStack(ItemLoader.dragonHead));
            }
        }
        if (stack.getItem().equals(Items.DIAMOND_CHESTPLATE) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count2 >= 8) {
                event.setCost(15);
                event.setMaterialCost(8);
                event.setOutput(new ItemStack(ItemLoader.dragonChest));
            }
        }
        if (stack.getItem().equals(Items.DIAMOND_LEGGINGS) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count2 >= 7) {
                event.setCost(12);
                event.setMaterialCost(7);
                event.setOutput(new ItemStack(ItemLoader.dragonLegs));
            }
        }
        if (stack.getItem().equals(Items.DIAMOND_BOOTS) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count2 >= 5) {
                event.setCost(10);
                event.setMaterialCost(5);
                event.setOutput(new ItemStack(ItemLoader.dragonFeet));
            }
        }
        if (stack.getItem().equals(Items.DIAMOND_SWORD) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count2 > 2) {
                event.setCost(10);
                event.setMaterialCost(2);
                event.setOutput(new ItemStack(ItemLoader.dragonSword));
            }
        }
        if (stack.getItem().equals(Items.DIAMOND_PICKAXE) && stack2.getItem().equals(ItemLoader.dragonCrystal)) {
            if (count2 > 3) {
                event.setCost(10);
                event.setMaterialCost(3);
                event.setOutput(new ItemStack(ItemLoader.dragonPickaxe));
            }
        }
    }

    //挖掘基岩
    @SubscribeEvent
    public void breakJiYan(PlayerInteractEvent.LeftClickBlock event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            EntityPlayer player = event.getEntityPlayer();
            ItemStack heldItemMainhand = player.getHeldItemMainhand();
            BlockPos pos = event.getPos();
            IBlockState state = world.getBlockState(pos);
            if (heldItemMainhand.getItem().equals(ItemLoader.opPickaxe) && state.getBlock().equals(Blocks.BEDROCK)) {
                world.setBlockToAir(pos);
                world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.AMBIENT, 1.0f, 3.0f);
                EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BEDROCK));
                world.spawnEntity(entityItem);
            }
        }
    }

    //op鞋子 无摔落伤害
    @SubscribeEvent
    public void playerFall(LivingFallEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) living;
            String key = player.getGameProfile().getName() + ":" + player.world.isRemote;
            if (playersWithFoot.contains(key)) {
                event.setCanceled(true);
            }
        }
    }

    //药水效果，摔落减免 附魔，火焰免疫
    @SubscribeEvent
    public void potionFall(LivingHurtEvent event) {
        if (event.getSource() == DamageSource.FALL)// .getSource().getDamageType().equals("potionFall"))
        {
            PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionLoader.potionFall);
            if (effect != null) {
                if (effect.getAmplifier() == 0) {
                    event.setAmount(event.getAmount() / 2);
                } else {
                    event.setCanceled(true);
                }
            }
        }
        //伤害来源：火焰，岩浆，熔岩石，燃烧，烟花
        if ((event.getSource() == DamageSource.IN_FIRE) || (event.getSource() == DamageSource.ON_FIRE) ||
                (event.getSource() == DamageSource.LAVA) || (event.getSource() == DamageSource.HOT_FLOOR) ||
                (event.getSource() == DamageSource.LIGHTNING_BOLT) || (event.getSource() == DamageSource.FIREWORKS)) {
            EntityLivingBase player = event.getEntityLiving();//获取实体
            ItemStack Stack_legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);//获取实体装备槽
            //获取此装备上是否有指定附魔
            int fireImmune = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.fireImmune, Stack_legs);
            if (fireImmune > 0) {
                //耐久减少
                Stack_legs.setItemDamage(Math.max(Stack_legs.getItemDamage(), 1));
                event.setCanceled(true);
            }
            if (Stack_legs.getItem() instanceof OPArmor) {
                event.setCanceled(true);
            }
        }
        if (event.getSource().isMagicDamage() && event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int opNum = EventMobLv.getOpNum(player);
            if (opNum == 4) {
                event.setAmount(0);
            } else if (opNum != 0) {
                event.setAmount(0.1f - (0.03f * opNum));
            }
        }
        Entity trueSource = event.getSource().getTrueSource();
        if (trueSource instanceof EntityIronGolem || trueSource instanceof EntityDragon || trueSource instanceof EntityEvoker
                || trueSource instanceof EntityShulker || trueSource instanceof EntitySnowman || trueSource instanceof EntityLlama) {
            int level = EventMobLv.getAttrLevelValue((EntityLiving) trueSource);
            event.setAmount(event.getAmount() + (float) Math.ceil(level / 2.5));
        }
    }

    @SubscribeEvent
    public void totemArmor(LivingDeathEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();
        if (entityLiving instanceof EntityPlayer && !entityLiving.world.isRemote) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            NonNullList<ItemStack> stacks = player.inventory.armorInventory;
            boolean flag = stacks.size() >= 4;
            //未装备4件
            for (ItemStack itemStack : stacks) {
                if (itemStack.isEmpty() || !(itemStack.getItem() instanceof TotemArmor)) //不是当前盔甲
                    flag = false;
            }
            if (flag) {
                event.setCanceled(true);
                if (player instanceof EntityPlayerMP) {
                    EntityPlayerMP serverplayerentity = (EntityPlayerMP) player;
                    StatBase stats = StatList.getObjectUseStats(Items.TOTEM_OF_UNDYING);
                    if (stats != null)
                        serverplayerentity.addStat(stats);
                    CriteriaTriggers.USED_TOTEM.trigger(serverplayerentity, new ItemStack(Items.TOTEM_OF_UNDYING));
                }
                player.setHealth(4); //血量
                player.clearActivePotions(); //清除buff
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 40 * 20, 0)); //防火
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 45 * 20, 0)); //生命恢复
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 5 * 20, 1)); //伤害吸收
                player.world.setEntityState(player, (byte) 35);

                //随机消耗一件装备大量耐久
                ItemStack stack = stacks.get(new Random().nextInt(4));
                stack.damageItem((int) Math.ceil(stack.getMaxDamage() / 4f), player);
            }
        }
    }

    //原版额外掉落
    @SubscribeEvent
    public void livingDrops(LivingDropsEvent event) {
        EntityLivingBase entityLiving = event.getEntityLiving();
        World world = entityLiving.world;
        double posX = entityLiving.posX;
        double posY = entityLiving.posY;
        double posZ = entityLiving.posZ;
        Entity trueSource = event.getSource().getTrueSource();
        if (trueSource instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) trueSource;
            List<EntityItem> drops = event.getDrops();
            int looting = event.getLootingLevel();
            if (entityLiving instanceof EntityDragon) {
                drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.dragonCrystal, MathHelper.getInt(world.rand, 2, 5 + looting))));
                drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.jiejing, world.rand.nextInt(2 + looting))));
            }
            if (entityLiving instanceof EntityWitherSkeleton) {
                if (world.rand.nextInt(100) < 5 + 5 * looting) {
                    drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.netherStarSmall, MathHelper.getInt(world.rand, 0, 2 + looting))));
                    drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.witherBone, MathHelper.getInt(world.rand, 0, 2 + looting))));
                }
                ItemStack mainhand = player.getHeldItemMainhand();
                if (mainhand.getItem() == ItemLoader.beheadSword) {
                    if (world.rand.nextInt(100) > 50) {
                        drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(Blocks.SKULL, 1, 1)));
                    }
                }
            }
            if (entityLiving instanceof EntityWither) {
                drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.jiejing, world.rand.nextInt(2 + looting))));
            }
            if (entityLiving instanceof EntityBlaze) {
                if (world.rand.nextInt(100) < 5 + 5 * looting) {
                    drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.blazeBone, MathHelper.getInt(world.rand, 0, 2 + looting))));
                }
            }
            if (entityLiving instanceof EntityVindicator || entityLiving instanceof EntityEvoker) {
                if (world.rand.nextInt(100) < 5 + 5 * looting) {
                    drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.totemSmall, MathHelper.getInt(world.rand, 0, 2 + looting))));
                }
            }
            drops.addAll(getMobDrop((EntityLiving) entityLiving, looting));
        }
    }

    //怪物掉落
    private List<EntityItem> getMobDrop(EntityLiving entityLiving, int looting) {
        List<EntityItem> items = new ArrayList<>();
        int lv = EventMobLv.getAttrLevelValue(entityLiving);
        if (lv > 0) {
            World world = entityLiving.world;
            int i = (int) Math.ceil(lv / 4d);
            ItemStack stack = new ItemStack(ItemLoader.expSmall, world.rand.nextInt(i + looting * 3) + looting);
            EntityItem entityItem = new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, stack);
            items.add(entityItem);
            if (!entityLiving.isNonBoss()) { //boss级生物额外掉落
                ItemStack stack1 = new ItemStack(ItemLoader.expBig, world.rand.nextInt(i + looting * 2) + 1 + looting);
                EntityItem entityItem1 = new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, stack1);
                items.add(entityItem1);
            }
        }
        return items;
    }

    //附魔，以战养战
    @SubscribeEvent
    public void warToWar(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = player.world;
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItemMainhand();
            int i = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.warToWar, stack);
            if (i > 0) { //有附魔
                Random random = new Random();
//                player.sendMessage(new TextComponentTranslation("附魔:" + i));
                switch (i) {  //回血效果和概率与等级相关
                    case 1:
                        if (random.nextInt(100) > 90)
                            player.heal(0.5f);
                        break;
                    case 2:
                        if (random.nextInt(100) > 75)
                            player.heal(1.0f);
                        break;
                    case 3:
                        if (random.nextInt(100) > 60)
                            player.heal(1.5f);
                        break;
                    case 4:
                        if (random.nextInt(100) > 50)
                            player.heal(2.0f);
                        break;
                    default:
                        player.heal(i / 2.0f);
                        break;
                }
            }
        }
    }

    public static List<String> playersWithHat = new ArrayList<>();
    public static List<String> playersWithChest = new ArrayList<>();
    public static List<String> playersWithLeg = new ArrayList<>();
    public static List<String> playersWithFoot = new ArrayList<>();

    //op胸甲飞行 op鞋子增加行走速度
    @SubscribeEvent
    public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) living;
            boolean hasChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemLoader.opChest;
            boolean hasLeg = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemLoader.opLegs;
            boolean hasHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemLoader.opHead;
            boolean hasFoot = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemLoader.opFeet;
            //防止其它模组飞行装备无法使用
            String key = player.getGameProfile().getName() + ":" + player.world.isRemote;
            //head
            if (playersWithHat.contains(key)) {
                if (hasHead) {

                } else {
                    playersWithHat.remove(key);
                }
            } else if (hasHead) {
                playersWithHat.add(key);
            }
            //chest
            if (playersWithChest.contains(key)) {
                if (hasChest) {
                    player.capabilities.allowFlying = true;
                } else {
                    if (!player.isCreative()) {
                        player.capabilities.allowFlying = false;
                        player.capabilities.isFlying = false;
                    }
                    playersWithChest.remove(key);
                }
            } else if (hasChest) {
                playersWithChest.add(key);
            }
            //leg
            if (playersWithLeg.contains(key)) {
                if (hasLeg) {
                    player.capabilities.setPlayerWalkSpeed(0.3f); //行走速度
                } else {
                    player.capabilities.setPlayerWalkSpeed(0.1f);
                    playersWithLeg.remove(key);
                }
            } else if (hasLeg) {
                playersWithLeg.add(key);
            }
            //feet
            if (playersWithFoot.contains(key)) {
                if (hasFoot) {

                } else {
                    playersWithFoot.remove(key);
                }
            } else if (hasFoot) {
                playersWithFoot.add(key);
            }
        }
    }

    //op护腿 增加跳跃高度
    @SubscribeEvent
    public void jumpBoost(LivingJumpEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) living;
            String key = player.getGameProfile().getName() + ":" + player.world.isRemote;
            if (playersWithFoot.contains(key)) {
                player.motionY += 0.4f;
            }
        }
    }

    @SubscribeEvent
    public void blastArrow(ProjectileImpactEvent.Arrow event){
        EntityArrow arrow = event.getArrow();
        Entity entity = arrow.shootingEntity;
        if (entity instanceof EntityLivingBase){
            ItemStack stack = getUseItem((EntityLivingBase) entity);
            int blastArrow = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.blastArrow, stack);
            if (blastArrow > 0){
                BlastArrow.boom(arrow);
            }
        }
    }

    @SubscribeEvent
    public void insight(BlockEvent.BreakEvent event){
        EntityPlayer player = event.getPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        BlockPos pos = event.getPos();
        World world = event.getWorld();
        int exp = event.getExpToDrop();
        int insight = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.insight, stack);
        if (insight > 0 && exp > 0){
            Insight.addDropExp(event, insight);
        }
        int diamond = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.diamond, stack);
        int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
        Block block = event.getState().getBlock();
        if (diamond > 0 && block == Blocks.COAL_ORE){
            Diamond.dropDiamond(world, diamond, pos, fortune);
        }
        if (block == Blocks.TALLGRASS && world.rand.nextDouble() < 0.15 + fortune * 0.05){
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(ItemLoader.stemSeed, MathHelper.getInt(world.rand, 1, 1 + fortune))));
        }
    }

    @SubscribeEvent
    public void fish(ItemFishedEvent event){
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = getUseItem(player);
        int insight = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.insight, stack);
        if (insight > 0){
            Insight.addFishingExp(player, player.world, insight);
        }
    }

    @SubscribeEvent
    public void lastStand(LivingHurtEvent event){
        EntityLivingBase entityLiving = event.getEntityLiving();
        if (entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entityLiving;
            ItemStack feet = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            int lastStand = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.lastStand, feet);
            if (lastStand > 0 && !player.world.isRemote){
                LastStand.lastStand(player, event, feet);
            }
            int lavaWalker = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.lavaWalker, feet);
            if (lavaWalker > 0){
                if (event.getSource() == DamageSource.HOT_FLOOR){
                    if (lavaWalker >= 3)
                        event.setCanceled(true);
                    else event.setAmount(event.getAmount() - 0.3f * lavaWalker);
                }
            }
        }
    }

    @SubscribeEvent
    public void lavaWalker(LivingUpdateEvent event){
        EntityLivingBase entityLiving = event.getEntityLiving();
        ItemStack feet = entityLiving.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        int lavaWalker = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.lavaWalker, feet);
        if (lavaWalker > 0){
            LavaWalker.freezeLava(entityLiving, entityLiving.world, entityLiving.getPosition(), lavaWalker);
        }
    }

    @SubscribeEvent
    public void manyArrow(ArrowLooseEvent event){
        ItemStack bow = event.getBow();
        int manyArrow = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.manyArrow, bow);
        if (manyArrow > 0 && !event.getWorld().isRemote){
            ManyArrow.manyArrow(event.getCharge(), event.getEntityPlayer(), bow, manyArrow, event.getWorld());
        }
    }

    @SubscribeEvent
    public void slow(PlayerEvent.BreakSpeed event){
        EntityPlayer player = event.getEntityPlayer();
        int slow = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.slow, player.getHeldItemMainhand());
        if (slow > 0){
            event.setNewSpeed(event.getOriginalSpeed() * ( 1 - slow * 0.2f)); //挖掘速度变慢
        }
    }

    /**
     * 获取玩家使用的物品
     * @param player 玩家
     * @return 物品
     */
    public static ItemStack getUseItem(EntityLivingBase player){
        ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
        if (!heldItem.isEmpty()) return heldItem;
        ItemStack offHand = player.getHeldItem(EnumHand.OFF_HAND);
        if (!offHand.isEmpty()) return offHand;
        return ItemStack.EMPTY;
    }
}
