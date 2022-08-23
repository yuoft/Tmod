package com.yuo.Tmod.Common.Items.Food;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SuperFood extends ItemFood {
    public SuperFood(String name, int amount, float saturation) {
        super(amount, saturation, false);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.FOOD_TAB);
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote){
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 * 10, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20 * 10, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20 * 10, 0));
            if (worldIn.rand.nextDouble() < 0.05){
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20 * 20, 0));
            }
        }
    }
}
