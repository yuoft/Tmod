package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Fluid.fluidLoader;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class block_Emerald_Fluid extends BlockFluidClassic
{
	public block_Emerald_Fluid(String name) 
    {
    	super(fluidLoader.emerald_fluid , Material.WATER);
    	this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
}
