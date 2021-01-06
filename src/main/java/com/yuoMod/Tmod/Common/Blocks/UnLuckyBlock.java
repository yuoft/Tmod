package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class UnLuckyBlock extends Block
{
	public UnLuckyBlock(String name) {
		super(Material.PLANTS);
		this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(3);
        this.setResistance(50);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("pickaxe", 1);
	}
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.AIR);
    }
}
