package com.yuoMod.Tmod.Common.Blocks.Crops;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;

public class StemCrop extends BlockStem {
    public StemCrop(String name, Block block) {
        super(block);//���뷽�顰��ʵ��
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }
}
