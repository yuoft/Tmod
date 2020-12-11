package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemsAll extends Item
{
	public ItemsAll(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if(stack.getItem().equals(itemLoader.space_line))
		{
			tooltip.add(I18n.format("tmod.item.space_line1", ""));
			tooltip.add(I18n.format("tmod.item.space_line2", ""));
		}
		if(stack.getItem().equals(itemLoader.salt))
		{
			tooltip.add(I18n.format("tmod.item.salt1", ""));
//			tooltip.add(I18n.format("tmod.item.salt2", ""));
		}
    }
}
