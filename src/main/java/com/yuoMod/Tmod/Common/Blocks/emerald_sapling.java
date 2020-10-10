package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.WorldCreate.WorldTreeCreate;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class emerald_sapling extends BlockBush implements IGrowable
{
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
	//绿宝石树苗
	public emerald_sapling(String name) 
	{
		super(Material.LEAVES);
		this.setUnlocalizedName(name);
	    this.setHardness(0.01f);
	    this.setResistance(0.3f);
	    this.setCreativeTab(CreativeTabsLoader.TMOD);
	    this.setSoundType(SoundType.PLANT);
//	    this.setTickRandomly(true);
	}
	//获取边界框
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }
	//是否可以成长
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}
    //是否可以使用骨粉
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO 自动生成的方法存根
		return true;
	}
	//生长
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO 自动生成的方法存根
		WorldTreeCreate tree=new WorldTreeCreate();
		tree.generate(worldIn, rand, pos);
	}
}
