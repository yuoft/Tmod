package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.World.WorldTreeCreate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EmeraldSapling extends BlockBush implements IGrowable {
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    public EmeraldSapling() {

    }

    //�̱�ʯ����
    public EmeraldSapling(String name) {
        super(Material.LEAVES);
        this.setUnlocalizedName(name);
        this.setHardness(0.01f);
        this.setResistance(0.3f);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
    }

    //��ȡ�߽��
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    //�Ƿ���Գɳ�
    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    //�Ƿ����ʹ�ùǷ�
    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        // TODO �Զ����ɵķ������
        return true;
    }

    //����
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        // TODO �Զ����ɵķ������
        WorldTreeCreate tree = new WorldTreeCreate();
        tree.generate(worldIn, rand, pos);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);
            //���յȼ�����9,1/7
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }

    public static void worldGenTree(World world, Random random, int x, int z) {
        BlockPos pos = EmeraldSapling.getGroundPos(world, x, z);
        WorldTreeCreate tree = new WorldTreeCreate();
        tree.generate(world, random, pos);//world.getHeight(new BlockPos(x, 0, z)));
    }

    @Nullable
    public static BlockPos getGroundPos(World world, int x, int z) {
        final BlockPos topPos = world.getHeight(new BlockPos(x, 0, z));
        if (topPos.getY() == 0) {
            return null;
        }

        final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(topPos);

        IBlockState blockState = world.getBlockState(pos);
        while (isTreeBlock(blockState, world, pos) || canReplace(blockState, world, pos)) {
            pos.move(EnumFacing.DOWN);
            if (pos.getY() <= 0) {
                return null;
            }
            blockState = world.getBlockState(pos);
        }

        return pos.up();
    }

    public static boolean isTreeBlock(IBlockState blockState, World world, BlockPos pos) {
        Block block = blockState.getBlock();
        return block.isLeaves(blockState, world, pos) || block.isWood(world, pos);
    }

    public static boolean canReplace(IBlockState blockState, World world, BlockPos pos) {
        Block block = blockState.getBlock();
        return block.isReplaceable(world, pos) && !blockState.getMaterial().isLiquid();
    }
}
