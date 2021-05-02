package com.yuoMod.Tmod.Common.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Elevator extends Block{
	
	private static final AxisAlignedBB ELEVATOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 2.0D);

	public Elevator(String name) {
		super(Material.ROCK);
	}
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return ELEVATOR_AABB;
	}
}
