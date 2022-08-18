package com.yuo.Tmod.Common.Items.Tool;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ModBow extends ItemBow {
    public ModBow(String name, int damage) {
        this.setUnlocalizedName(name);
        this.setMaxDamage(1);
        this.setMaxDamage(damage);
        this.setCreativeTab(TmodGroup.TOOL_TAB);
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    return !(entityIn.getActiveItemStack().getItem() instanceof ItemBow) ? 0.0F : (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    protected ItemStack findAmmo(EntityPlayer player) {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
            return player.getHeldItem(EnumHand.OFF_HAND);
        } else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        } else {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);
                if (this.isArrow(itemstack)) {
                    return itemstack;
                }
            }
            return ItemStack.EMPTY;
        }
    }

    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() instanceof ModArrow;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            //是否需要箭矢
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);
            if (itemstack.isEmpty() && flag){
                itemstack = ModArrow.getArrow(stack);
            }

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty()) {
                float f = getArrowVelocity(i);

                if ((double) f >= 0.1D) {
                    //是否消耗箭
                    boolean flag1 = entityplayer.capabilities.isCreativeMode || (itemstack.getItem() instanceof ModArrow && ((ModArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

                    if (!worldIn.isRemote) {
                        ModArrow modArrow = (ModArrow) itemstack.getItem();
                        EntityArrow entityarrow = modArrow.createArrow(worldIn, itemstack, entityplayer);
                        entityarrow = this.customizeArrow(entityarrow);
                        entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F) {
                            entityarrow.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0) {
                            entityarrow.setDamage(entityarrow.getDamage() + (double) j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0) {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            entityarrow.setFire(100);
                        }

                        stack.damageItem(1, entityplayer);

                        if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.spawnEntity(entityarrow);
                    }

                    worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1 && !entityplayer.capabilities.isCreativeMode) {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty()) {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(StatList.getObjectUseStats(this));
                }
            }
        }
    }

    public static float getArrowVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !this.findAmmo(playerIn).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;
        int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemstack);
        if (!playerIn.capabilities.isCreativeMode && !flag && level <= 0) {
            return flag ? new ActionResult<>(EnumActionResult.PASS, itemstack) : new ActionResult<>(EnumActionResult.FAIL, itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public EntityArrow customizeArrow(EntityArrow arrow) {
        return arrow;
    }
}
