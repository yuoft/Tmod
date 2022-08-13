package com.yuo.Tmod.Common.Blocks.Crops;

import java.util.Random;

import javax.annotation.Nullable;

import com.yuo.Tmod.Tab.TmodGroup;
import com.yuo.Tmod.World.WorldTreeGen;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class OreSapling extends BlockBush implements IGrowable {
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
    private final Block tree;
    private final Block leaf;
    //绿宝石树苗
    public OreSapling(String name, Block tree, Block leaf) {
        super(Material.PLANTS);
        this.setUnlocalizedName(name);
        this.setHardness(0.01f);
        this.setResistance(0.3f);
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setSoundType(SoundType.PLANT);
        this.setTickRandomly(true);
        this.tree = tree;
        this.leaf = leaf;
    }

    //获取边界框
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
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
        return true;
    }

    //生长
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
//        for (BlockPos allInBox : BlockPos.getAllInBox(pos.add(-1, 0, -1), pos.add(1, 1, 1))) {
//            if (!worldIn.isAirBlock(allInBox) && allInBox != pos) return;
//        }

        WorldTreeGen tree = new WorldTreeGen(this.tree, this.leaf);
        tree.generate(worldIn, rand, pos);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);
            if (!worldIn.isAreaLoaded(pos, 1)) return; //检查区块是否加载
            //光照等级大于9,1/7
            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }
}
