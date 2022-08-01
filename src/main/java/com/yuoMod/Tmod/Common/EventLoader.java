package com.yuoMod.Tmod.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yuoMod.Tmod.Capability.EventMobLv;
import com.yuoMod.Tmod.Common.Items.Armor.OPArmor;
import com.yuoMod.Tmod.Common.Items.Armor.TotemArmor;
import com.yuoMod.Tmod.Common.Items.Tool.*;
import com.yuoMod.Tmod.Common.Items.UpGradeGem;
import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Enchantment.EnchantLoader;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Potion.PotionLoader;
import com.yuoMod.Tmod.TileEntity.TileTorcherino;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        if (tile == null || !(tile instanceof TileTorcherino)) return;
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

    //boss血条HUD显示
    @SideOnly(value = Side.CLIENT)
//    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Chat event) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;
        for (Entity entity : world.loadedEntityList) {
            if (entity instanceof EntityKiana) {
//                BossHealthHUD hud = new BossHealthHUD((EntityKiana) entity);
//                hud.drawHud();
            }
        }
//        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
//        }
    }

    //铁砧配方
    @SubscribeEvent
    public void NewRecipes(AnvilUpdateEvent event) {
        ItemStack stack = event.getLeft();
        ItemStack stack2 = event.getRight();
        int count1 = stack.getCount();
        int count2 = stack2.getCount();
        if (stack.getItem().equals(Items.STRING) && stack2.getItem().equals(ItemLoader.space_patch)) {
            if (count1 <= count2) {
                event.setCost(5 * count1);
                event.setMaterialCost(count1);
                event.setOutput(new ItemStack(ItemLoader.space_line, count1));
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
//			player.sendMessage(new TextComponentTranslation("其它 " +state.getBlock().getUnlocalizedName()+""+ event.getUseBlock() + " " + event.getUseItem()));
            if (heldItemMainhand.getItem().equals(ItemLoader.op_pickaxe) && state.getBlock().equals(Blocks.BEDROCK)) {
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
            int fireImmune = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.fireimmune, Stack_legs);
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
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 40 * 20, 0)); //防火
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 45 * 20, 0)); //生命恢复
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 5 * 20, 1)); //伤害吸收
                player.world.setEntityState(player, (byte) 35);

                //随机消耗一件装备大量耐久
                ItemStack stack = stacks.get(new Random().nextInt(4));
                stack.damageItem((int) Math.ceil(stack.getMaxDamage() / 4), player);
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
                    drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.nether_star_small, MathHelper.getInt(world.rand, 0, 2 + looting))));
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
                    drops.add(new EntityItem(world, posX, posY, posZ, new ItemStack(ItemLoader.totem_small, MathHelper.getInt(world.rand, 0, 2 + looting))));
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
            ItemStack stack = new ItemStack(ItemLoader.exp_small, world.rand.nextInt(i + looting * 3) + looting);
            EntityItem entityItem = new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, stack);
            items.add(entityItem);
            if (!entityLiving.isNonBoss()) { //boss级生物额外掉落
                ItemStack stack1 = new ItemStack(ItemLoader.exp_big, world.rand.nextInt(i + looting * 2) + 1 + looting);
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
            int i = EnchantmentHelper.getEnchantmentLevel(EnchantLoader.wartowar, stack);
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

    public static List<String> playersWithHat = new ArrayList<String>();
    public static List<String> playersWithChest = new ArrayList<String>();
    public static List<String> playersWithLeg = new ArrayList<String>();
    public static List<String> playersWithFoot = new ArrayList<String>();

    //op胸甲飞行 op鞋子增加行走速度
    @SubscribeEvent
    public void updatePlayerAbilityStatus(LivingUpdateEvent event) {
        EntityLivingBase living = event.getEntityLiving();
        if (living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) living;
            Boolean hasChest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemLoader.op_chestplate;
            Boolean hasLeg = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemLoader.op_leggings;
            Boolean hasHead = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemLoader.op_helmet;
            Boolean hasFoot = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemLoader.op_boots;
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
    //燃料
//	@SubscribeEvent
//	public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
//	    if (event.getItemStack().getItem().equals(itemLoader.salt_wash)) 
//	    {
//	        event.setBurnTime(10000);
//	        // 可以设定为 0。0 代表“这个物品不是燃料”，更准确地说是“这个物品燃烧时间是 0”。
//	        // 可以设定为 -1。-1 代表“由原版逻辑来决定”。
//	        // 可通过 event.getBurnTime() 获得当前决定的燃烧时间。
//	        // 这个事件可以取消。取消意味着后续的 Event listener 将不会收到这个事件，进而
//	        // 无法修改燃烧时间。
//	    }
//	}
}
