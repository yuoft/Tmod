package com.yuo.Tmod.Common.Blocks.Crops;

import java.util.Random;

import javax.annotation.Nonnull;

import com.yuo.Tmod.Tab.TmodGroup;

import com.yuo.Tmod.Tmod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class OreTree extends Block {
    public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

    //绿宝石树木
    public OreTree(String name) {
        super(Material.WOOD);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName(name);
        this.setHardness(5.0f);
        this.setResistance(5.0f);
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setSoundType(SoundType.WOOD);
        this.setHarvestLevel("axe", 2);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        BlockLog.EnumAxis axis = BlockLog.EnumAxis.Y;
        switch (meta) {
            case 0:
                break;
            case 1:
                axis = BlockLog.EnumAxis.X;
                break;
            case 2:
                axis = BlockLog.EnumAxis.Z;
                break;
        }
        return this.getDefaultState().withProperty(LOG_AXIS, axis);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (state.getValue(LOG_AXIS) == BlockLog.EnumAxis.X)
            meta = 1;
        if (state.getValue(LOG_AXIS) == BlockLog.EnumAxis.Z)
            meta = 2;
        return meta;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
    }

}
