package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.block.BlockHay;
import net.minecraft.block.SoundType;

public class TallgrassBlock extends BlockHay {
    //ЗаІЭїй
    public TallgrassBlock(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setHardness(0.5f);
        this.setResistance(2);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setSoundType(SoundType.PLANT);
        this.setResistance(0.5f);
    }
}
