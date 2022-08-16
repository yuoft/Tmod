package com.yuo.Tmod.Common.Items.Food;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GoldDiamondBread extends ItemFood {
    public GoldDiamondBread(String name, int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
        this.setUnlocalizedName(name);//item本地化名称
        this.setCreativeTab(TmodGroup.FOOD_TAB);
        this.setMaxStackSize(64);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 100, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 1));
//            player.addExperience(10);
        }
        super.onFoodEaten(stack, world, player);
    }
}
