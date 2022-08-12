package com.yuo.Tmod.Common.Blocks.Crops;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class StemCrop extends BlockStem {
    public StemCrop(String name, Block block) {
        super(block);//传入方块“果实”
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setTickRandomly(true);
    }

    //每次生长进度
    @Override
    public void growStem(World worldIn, BlockPos pos, IBlockState state) {
        int i = state.getValue(AGE);
        Block block = state.getBlock();
        if (block == BlockLoader.diamondStem || block == BlockLoader.emeraldStem){
            i += MathHelper.getInt(worldIn.rand, 1, 3);
        }else if (block == BlockLoader.lapisStem || block == BlockLoader.redstoneStem || block == BlockLoader.goldStem){
            i += MathHelper.getInt(worldIn.rand, 2, 4);
        }else i += MathHelper.getInt(worldIn.rand, 2, 5);;
        worldIn.setBlockState(pos, state.withProperty(AGE, Math.min(7, i)), 2);
    }
}
