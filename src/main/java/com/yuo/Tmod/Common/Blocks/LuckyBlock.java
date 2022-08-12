package com.yuo.Tmod.Common.Blocks;

import java.util.Random;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class LuckyBlock extends Block {
    public LuckyBlock(String name) {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(3);
        this.setResistance(50);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setHarvestLevel("pickaxe", 1);
    }

    //破坏不掉落
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.AIR);
    }
}
