package com.yuoMod.Tmod.Common.Blocks;

import java.util.List;

import com.google.common.collect.Lists;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class emerald_leaf extends BlockLeaves
{
    //绿宝石树叶
	public emerald_leaf(String name) 
	{
		super();
		this.setUnlocalizedName(name);
	    this.setHardness(0.5f);
	    this.setResistance(0.5f);
	    this.setCreativeTab(CreativeTabsLoader.TMOD);
	    this.setSoundType(SoundType.PLANT);
	    this.setHarvestLevel(Items.SHEARS.toString(), 2);
	    this.setTickRandomly(false);
	}
    //使用剪刀得到什么
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		ItemStack stack=new ItemStack(this);
		return Lists.newArrayList(stack);
	}

	@Override
	public EnumType getWoodType(int meta) {
		return EnumType.BIRCH;
	}

}
