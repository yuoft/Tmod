package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.BlockTorch;

//���ٻ��
public class SpeedTorch extends BlockTorch{

	public SpeedTorch(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setLightLevel(10);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
}
