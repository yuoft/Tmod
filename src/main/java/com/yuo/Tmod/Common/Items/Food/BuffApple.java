package com.yuo.Tmod.Common.Items.Food;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BuffApple extends ItemFood {
    public BuffApple(String name, int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.FOOD_TAB);
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
            if (item.equals(ItemLoader.diamondApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 4000, 2));
                    player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 4000, 2));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 1));
                }
            } else if (item.equals(ItemLoader.emeraldApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 2));
                    player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 4000, 2));
                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2000, 1));
                }
            } else if (item.equals(ItemLoader.ironApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 2));
                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 4000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 0));
                }
            } else if (item.equals(ItemLoader.coalApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 3000, 0));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 2000, 0));
                }
            } else if (item.equals(ItemLoader.lapisApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
                }
            } else if (item.equals(ItemLoader.redstoneApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 4000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 4000, 1));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 2000, 0));
                }
            } else if (item.equals(ItemLoader.quartzApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 4000, 0));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2000, 0));
                }
            } else if (item.equals(ItemLoader.glowstoneApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 4000, 0));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 2000, 0));
                }
            } else if (item.equals(ItemLoader.deBuffApple)) {
                if (stack.getMetadata() > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 4000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 4000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 4000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 4000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 4000, 1));
                    player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.POISON, 4000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 5));
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 2000, 0));
                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 2000, 0));
                }
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
