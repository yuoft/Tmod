package com.yuoMod.Tmod.Common.Items.Food;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//—ŒÎÁ»‚
public class SaltMeat extends ItemFood
{
	public SaltMeat(String name,int hungerHeal, float saturation, boolean isWolfFood)
	{
		super(hungerHeal,saturation,isWolfFood);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(32);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.item.salt_meat", ""));
    }
}
