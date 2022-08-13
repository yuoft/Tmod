package com.yuo.Tmod.World;

import java.util.Random;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Blocks.Crops.OreSapling;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

//树苗生长
public class WorldTreeGen extends WorldGenAbstractTree {
    public final IBlockState tree;// 树干
    public final IBlockState leaf;//  树叶

    public WorldTreeGen(Block tree, Block leaf) {
        super(false);
        this.tree = tree.getDefaultState();
        this.leaf = leaf.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockLeaves.DECAYABLE, false);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        //minHeight 树木最小高度；tree 树木树干；leaf 树木树叶；growVines 是否有藤蔓；
        int minHeight = 4;
        int height = rand.nextInt(3) + minHeight;
        boolean isReplaceable = true;
        //坐标检查
        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256) {
            // 检查所有方块可替换
            for (int y = pos.getY(); y <= pos.getY() + 1 + height; ++y) {
                int xzSize = 1;
                // 底端
                if (y == pos.getY()) {
                    xzSize = 0;
                }
                // 顶端
                if (y >= pos.getY() + height - 1) {
                    xzSize = 2;
                }
                // 检查这个平面所有方块可替换
                BlockPos.MutableBlockPos tmpPos = new BlockPos.MutableBlockPos();
                for (int x = pos.getX() - xzSize; x <= pos.getX() + xzSize && isReplaceable; ++x) {
                    for (int z = pos.getZ() - xzSize; z <= pos.getZ() + xzSize && isReplaceable; ++z) {
                        if (y >= 0 && y < 256) {
                            if (!this.isReplaceable(world, tmpPos.setPos(x, y, z))) {
                                isReplaceable = false;
                            }
                        } else {
                            isReplaceable = false;
                        }
                    }
                }
            }
            if (!isReplaceable)//所有方块都可替换才生成
            {
                return false;
            } else {
                BlockPos downPos = pos.down();
                Block downBlock = world.getBlockState(downPos).getBlock();
                IBlockState state = world.getBlockState(downPos);
                // 是可生成树的土壤
                boolean isSoil = downBlock.canSustainPlant(state, world, downPos, EnumFacing.UP, (OreSapling) BlockLoader.emeraldSapling);
                //空间是否足够
                if (isSoil && pos.getY() < 256 - height - 1) {
                    downBlock.onPlantGrow(state, world, downPos, pos);
                    // 生成叶子
                    for (int y = pos.getY() + height - 3; y <= pos.getY() + height; ++y) {
                        int restHeight = y - (pos.getY() + height);
                        int xzSize = 1 - restHeight / 2;

                        for (int x = pos.getX() - xzSize; x <= pos.getX() + xzSize; ++x) {
                            int xOffset = x - pos.getX();

                            for (int z = pos.getZ() - xzSize; z <= pos.getZ() + xzSize; ++z) {
                                int zOffset = z - pos.getZ();

                                if (Math.abs(xOffset) != xzSize
                                        || Math.abs(zOffset) != xzSize // 不在边缘4个点
                                        || rand.nextInt(2) != 0
                                        && restHeight != 0) {
                                    BlockPos blockpos = new BlockPos(x, y, z);
                                    state = world.getBlockState(blockpos);
                                    Block block = state.getBlock();

                                    if (block.isAir(state, world, blockpos) || block.isLeaves(state, world, blockpos)) {
                                        this.setBlockAndNotifyAdequately(world, blockpos, leaf);
                                    }
                                }
                            }
                        }
                    }
                    // 生成木头
                    for (int y = 0; y < height; ++y) {
                        BlockPos upPos = pos.up(y);
                        state = world.getBlockState(upPos);
                        Block upBlock = world.getBlockState(upPos).getBlock();
                        //是空气和树叶才生成
                        if (upBlock.isAir(state, world, upPos) || upBlock.isLeaves(state, world, upPos)) {
                            this.setBlockAndNotifyAdequately(world, pos.up(y), tree);
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
