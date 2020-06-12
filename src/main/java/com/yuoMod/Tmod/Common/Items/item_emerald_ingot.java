package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.Item;

public class item_emerald_ingot extends Item
{
	public item_emerald_ingot(String name)
	{
		super();
		this.setUnlocalizedName(name);//item本地化名称
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
	}
}
