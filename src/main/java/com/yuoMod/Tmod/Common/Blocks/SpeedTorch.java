package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.BlockTorch;

//¼ÓËÙ»ð°Ñ
public class SpeedTorch extends BlockTorch{

	public SpeedTorch(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setLightLevel(10);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
}
