package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class tallgrass_block extends Block
{
	//Çà²Ý¿é
	public tallgrass_block(String name) 
	{
		super(Material.GRASS);
		this.setUnlocalizedName(name);
        this.setHardness(0.5f);
        this.setResistance(2);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setSoundType(SoundType.PLANT);
        this.setResistance(0.5f);
        this.setHarvestLevel("¼ôµ¶", 1);
	}
}
