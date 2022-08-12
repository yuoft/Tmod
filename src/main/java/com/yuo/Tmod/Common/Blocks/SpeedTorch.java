package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Tab.TmodGroup;
import com.yuo.Tmod.TileEntity.TileTorcherino;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//加速火把
public class SpeedTorch extends BlockTorch {

    public SpeedTorch(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.WOOD);
        this.setLightLevel(10);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null && tile instanceof TileTorcherino)
                ((TileTorcherino) tile).setState(world.isBlockIndirectlyGettingPowered(pos) > 0);
        }
        super.onBlockAdded(world, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
        if (!world.isRemote) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile != null && tile instanceof TileTorcherino)
                ((TileTorcherino) tile).setState(world.isBlockIndirectlyGettingPowered(pos) > 0);
        }
        super.neighborChanged(state, world, pos, block, fromPos);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileTorcherino();
    }
}
