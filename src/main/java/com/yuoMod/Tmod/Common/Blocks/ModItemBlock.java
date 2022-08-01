package com.yuoMod.Tmod.Common.Blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ModItemBlock extends ItemBlock {

    public ModItemBlock(Block block) {
        super(block);
        if (block instanceof Mine || block instanceof SpaceBlock || block instanceof SpaceOre) {
            this.setMaxStackSize(16);
        }
    }
}
