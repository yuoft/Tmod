package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ManyArrow extends Enchantment {
    ManyArrow(String name) {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 15;
    }

    //射出额外箭矢
    public static void manyArrow(int chargeIn, EntityLivingBase player, ItemStack bow, int manyArrow, World world){
        float charge = ItemBow.getArrowVelocity(chargeIn); //弓的状态
        ItemStack itemStack = findAmmo(player);
        if (itemStack.isEmpty()) return;
        int fireArrow = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bow);
        for (int i = 0; i < manyArrow; i++){
            ItemArrow arrow = (ItemArrow)(itemStack.getItem() instanceof ItemArrow ? itemStack.getItem() : Items.ARROW);
            EntityArrow entityarrow = arrow.createArrow(world, itemStack, player);
            entityarrow.setDamage(2);
            if (fireArrow > 0) entityarrow.setFire(100);
            if (charge == 1.0F) entityarrow.setIsCritical(true);
            entityarrow.shoot(player, player.rotationPitch, (float) (player.rotationYaw + world.rand.nextGaussian() / 10f), 0.0F, charge * 3.0F, 1.0F);
            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY; //万箭附魔的额外箭不可回收
            world.spawnEntity(entityarrow);
        }
    }

    private static ItemStack findAmmo(EntityLivingBase livingBase) {
        if (isArrow(livingBase.getHeldItem(EnumHand.OFF_HAND))) {
            return livingBase.getHeldItem(EnumHand.OFF_HAND);
        } else if (isArrow(livingBase.getHeldItem(EnumHand.MAIN_HAND))) {
            return livingBase.getHeldItem(EnumHand.MAIN_HAND);
        } else if (livingBase instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) livingBase;
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (isArrow(itemstack)) {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
        return ItemStack.EMPTY;
    }

    private static boolean isArrow(ItemStack stack) {
        return stack.getItem() instanceof ItemArrow;
    }
}
