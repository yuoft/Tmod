package com.yuoMod.Tmod.Common.Blocks.Crops;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;

public class StemCrop extends BlockStem {
    public StemCrop(String name, Block block) {
        super(block);//传入方块“果实”
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }
}
