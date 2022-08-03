package com.yuoMod.Tmod.Common.Items.Food;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//—ŒÎÁ»‚
public class SaltMeat extends ItemFood {
    public SaltMeat(String name, int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
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
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(23), 600, 0));
            if (worldIn.rand.nextFloat() < 0.01f)
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 100, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 100, 0));
        }
        super.onFoodEaten(stack, worldIn, player);
    }
}
