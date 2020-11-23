package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemAxe;

public class emerald_axe extends ItemAxe
{

	public emerald_axe(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial, 10.0f, -3.0f);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("axe", 3);
	}
}
