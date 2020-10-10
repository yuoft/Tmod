package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class tmodMusicRecord extends ItemRecord
{
	protected tmodMusicRecord(String name, SoundEvent soundIn) {
		super(name, soundIn);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setUnlocalizedName(name);
	}
	
}
