package com.yuoMod.Tmod.Common.ItemBlock;

import com.yuoMod.Tmod.Common.Blocks.Mine;
import com.yuoMod.Tmod.Common.Blocks.SpaceBlock;
import com.yuoMod.Tmod.Common.Blocks.SpaceOre;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class item_block extends ItemBlock
{

	public item_block(Block block) 
	{
		super(block);
		if(block instanceof Mine || block instanceof SpaceBlock || block instanceof SpaceOre) {
			this.setMaxStackSize(16);
		}
	}
}
