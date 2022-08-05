package com.yuo.Tmod.Common.Items.Food;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GoldDiamondBread extends ItemFood {
    public GoldDiamondBread(String name, int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
        this.setUnlocalizedName(name);//item本地化名称
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(64);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 60, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(21), 100, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 60, 1));
//            player.addExperience(10);
        }
        super.onFoodEaten(stack, world, player);
    }
}
