package com.yuoMod.Tmod.Common.Items.Food;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BuffApple extends ItemFood {
    public BuffApple(String name, int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(64);
        this.setAlwaysEdible();
        this.setHasSubtypes(true);
    }

    //是否有附魔效果
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || stack.getMetadata() > 0;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            Item item = stack.getItem();
            if (item.equals(ItemLoader.diamond_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 2000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 4000, 2));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 4000, 2));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 2000, 1));
                }
            } else if (item.equals(ItemLoader.emerald_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 4000, 2));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 4000, 2));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 2000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 2000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 2000, 1));
                }
            } else if (item.equals(ItemLoader.iron_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 4000, 2));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 4000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2000, 0));
                }
            } else if (item.equals(ItemLoader.coal_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(6), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 3000, 0));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 2000, 0));
                }
            } else if (item.equals(ItemLoader.lapis_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 4000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 2000, 0));
                }
            } else if (item.equals(ItemLoader.redstone_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 4000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 4000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 2000, 0));
                }
            } else if (item.equals(ItemLoader.quartz_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 4000, 0));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 2000, 0));
                }
            } else if (item.equals(ItemLoader.glowstone_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 4000, 0));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 2000, 0));
                }
            } else if (item.equals(ItemLoader.debuff_apple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 4000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(4), 4000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(7), 1000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 4000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 4000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 4000, 1));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(18), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 4000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 2000, 5));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(4), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 2000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 2000, 0));
                }
            } else {
            }
        }
        super.onFoodEaten(stack, world, player);
    }

    //返回id相同但是meta不同的项的列表
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
            items.add(new ItemStack(this, 1, 1));
        }
    }
}
