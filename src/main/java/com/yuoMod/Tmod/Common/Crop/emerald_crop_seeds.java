package com.yuoMod.Tmod.Common.Crop;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.Item;

public class emerald_crop_seeds extends Item
{
	public emerald_crop_seeds(String name) 
	{
		// TODO 自动生成的构造函数存根
		super();
		this.setUnlocalizedName(name);//绿宝石作物种子
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
	}
}
