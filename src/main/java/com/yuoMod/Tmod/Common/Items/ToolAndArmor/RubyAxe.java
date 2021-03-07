package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemAxe;

public class RubyAxe extends ItemAxe
{

	public RubyAxe(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial, 8.0f, -3.0f);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("axe", 2);
	}
}
