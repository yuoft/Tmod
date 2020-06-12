package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class emerald_tree extends Block
{
	//绿宝石树木
	public emerald_tree(String name) 
	{
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(name);
	    this.setHardness(2f);
	    this.setResistance(2f);
	    this.setCreativeTab(CreativeTabsLoader.TMOD);
	    this.setSoundType(SoundType.WOOD);
	    this.setHarvestLevel(itemLoader.emerald_axe.toString(), 4);
	}
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune) {
	    // 把要掉落的物品塞进 drops 里即可。
//		if(fortune>0)
//		{
//			Random random=new Random();
//			int number=(random.nextInt(fortune)+1)*fortune;
//			drops.add(number, new ItemStack(itemLoader.emerald_powder));
//			drops.add(1, new ItemStack(this));
//		}
//		else
//		{
//			drops.add(1, new ItemStack(this));
//		}
	}
}
