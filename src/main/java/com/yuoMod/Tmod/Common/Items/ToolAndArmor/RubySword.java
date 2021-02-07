package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import net.minecraft.item.ItemSword;

public class RubySword extends ItemSword
{
	public RubySword(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
}
