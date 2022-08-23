package com.yuo.Tmod.Common.Items.Food;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SuperMeat extends ItemFood {
    public SuperMeat(String name, int amount, float saturation) {
        super(amount, saturation, true);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.FOOD_TAB);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote){
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 20 * 10, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20 * 10, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20 * 10, 1));
            if (worldIn.rand.nextDouble() < 0.05){
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20 * 20, 1));
            }
        }
    }
}
