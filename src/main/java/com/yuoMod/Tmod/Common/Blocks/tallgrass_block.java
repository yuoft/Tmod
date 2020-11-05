package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.BlockHay;
import net.minecraft.block.SoundType;

public class tallgrass_block extends BlockHay
{
	//Çà²Ý¿é
	public tallgrass_block(String name) 
	{
		super();
		this.setUnlocalizedName(name);
        this.setHardness(0.5f);
        this.setResistance(2);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setSoundType(SoundType.PLANT);
        this.setResistance(0.5f);
        this.setHarvestLevel("¼ôµ¶", 1);
	}
}
