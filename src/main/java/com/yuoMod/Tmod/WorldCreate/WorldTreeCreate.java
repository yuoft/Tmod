package com.yuoMod.Tmod.WorldCreate;

import java.util.Random;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Blocks.emerald_sapling;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//��������
public class WorldTreeCreate extends WorldGenAbstractTree
{
	public static final IBlockState tree=blockLoader.emerald_tree.getDefaultState();
	public static final IBlockState leaf=blockLoader.emerald_leaf.getDefaultState()
			.withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockLeaves.DECAYABLE, false);
	private int minHeigth=4;
	public WorldTreeCreate() {
		super(false);
	}
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) 
	{
		//minHeigth ��ľ��С�߶ȣ�tree ��ľ���ɣ�leaf ��ľ��Ҷ��growVines �Ƿ���������
//		WorldGenTrees trees=new WorldGenTrees(false, minHeigth, tree, leaf, false);
//		trees.generate(world, rand, pos);
//		return true;
//	}
		int height = rand.nextInt(3) + this.minHeigth;
        boolean isReplaceable = true;
        //������
        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256)
        {
            // ������з�����滻
            for (int y = pos.getY(); y <= pos.getY() + 1 + height; ++y)
            {
                int xzSize = 1;
                // �׶�
                if (y == pos.getY())
                {
                    xzSize = 0;
                }
                // ����
                if (y >= pos.getY() + height - 1)
                {
                    xzSize = 2;
                }
                // ������ƽ�����з�����滻
                BlockPos.MutableBlockPos tmpPos = new BlockPos.MutableBlockPos();
                for (int x = pos.getX() - xzSize; x <= pos.getX() + xzSize && isReplaceable; ++x)
                {
                    for (int z = pos.getZ() - xzSize; z <= pos.getZ() + xzSize && isReplaceable; ++z)
                    {
                        if (y >= 0 && y < 256)
                        {
                            if (!this.isReplaceable(world, tmpPos.setPos(x, y, z)))
                            {
                                isReplaceable = false;
                            }
                        }
                        else
                        {
                            isReplaceable = false;
                        }
                    }
                }
            }
            if (!isReplaceable)//���з��鶼���滻������
            {
                return false;
            }
            else
            {
                BlockPos downPos = pos.down();
                Block downBlock = world.getBlockState(downPos).getBlock();
                IBlockState state=world.getBlockState(downPos);
                // �ǿ�������������
                boolean isSoil = downBlock.canSustainPlant(state, world, downPos, EnumFacing.UP, (emerald_sapling) blockLoader.emerald_sapling);
                //�ռ��Ƿ��㹻
                if (isSoil && pos.getY() < 256 - height - 1)
                {
                    downBlock.onPlantGrow(state,world, downPos, pos);
                    // ����Ҷ��
                    for (int y = pos.getY() + height - 3; y <= pos.getY() + height; ++y)
                    {
                        int restHeight = y - (pos.getY() + height);
                        int xzSize = 1 - restHeight / 2;

                        for (int x = pos.getX() - xzSize; x <= pos.getX() + xzSize; ++x)
                        {
                            int xOffset = x - pos.getX();

                            for (int z = pos.getZ() - xzSize; z <= pos.getZ() + xzSize; ++z)
                            {
                                int zOffset = z - pos.getZ();

                                if (   Math.abs(xOffset) != xzSize
                                    || Math.abs(zOffset) != xzSize // ���ڱ�Ե4����
                                    || rand.nextInt(2) != 0
                                    && restHeight != 0)
                                {
                                    BlockPos blockpos = new BlockPos(x, y, z);
                                    state = world.getBlockState(blockpos);
                                    Block block=state.getBlock();

                                    if (block.isAir(state,world, blockpos) || block.isLeaves(state,world, blockpos))
                                    {
                                        this.setBlockAndNotifyAdequately(world, blockpos, leaf);
                                    }
                                }
                            }
                        }
                    }
                    // ����ľͷ
                    for (int y = 0; y < height; ++y)
                    {
                        BlockPos upPos = pos.up(y);
                        state = world.getBlockState(upPos);
                        Block upBlock = world.getBlockState(upPos).getBlock();
                        //�ǿ�������Ҷ������
                        if (upBlock.isAir(state,world, upPos) || upBlock.isLeaves(state,world, upPos))
                        {
                            this.setBlockAndNotifyAdequately(world, pos.up(y), tree);
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
	}
}
