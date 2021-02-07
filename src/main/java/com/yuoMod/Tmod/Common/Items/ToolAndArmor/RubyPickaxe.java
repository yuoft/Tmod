package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemPickaxe;

public class RubyPickaxe extends ItemPickaxe

{
	public RubyPickaxe(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("pickaxe", 3);
	}
}
