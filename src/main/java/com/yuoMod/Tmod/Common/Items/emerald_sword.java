package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemSword;

public class emerald_sword extends ItemSword
{
	protected emerald_sword(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
}
