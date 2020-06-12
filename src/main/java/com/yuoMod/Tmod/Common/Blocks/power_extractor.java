package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class power_extractor extends Block
{

	public power_extractor(String name) 
	{
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setHardness(5);
		this.setResistance(50);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setSoundType(SoundType.STONE);
	}
}
