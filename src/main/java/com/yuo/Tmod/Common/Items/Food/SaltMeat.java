package com.yuo.Tmod.Common.Items.Food;

import java.util.List;

import javax.annotation.Nullable;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Л╬ву╚Р
public class SaltMeat extends ItemFood {
    public SaltMeat(String name, int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.FOOD_TAB);
        this.setMaxStackSize(32);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.salt_meat", ""));
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote && stack.getItem() == ItemLoader.cookingSaltMeat) {
            if (worldIn.rand.nextFloat() < 0.05f)
                player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 600, 0));
            if (worldIn.rand.nextFloat() < 0.01f)
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 0));
        }
        super.onFoodEaten(stack, worldIn, player);
    }
}
