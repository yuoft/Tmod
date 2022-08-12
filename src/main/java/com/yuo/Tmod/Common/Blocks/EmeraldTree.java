package com.yuo.Tmod.Common.Blocks;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EmeraldTree extends Block {
    public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

    //绿宝石树木
    public EmeraldTree(String name) {
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
                axis = BlockLog.EnumAxis.Y;
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
        if (state.getValue(LOG_AXIS) == BlockLog.EnumAxis.Y || state.getValue(LOG_AXIS) == BlockLog.EnumAxis.NONE)
            meta = 0;
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

    public int getMetadata(int damage) {
        return 0;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune) {
        // 把要掉落的物品塞进 drops 里即可。
        Random random = new Random();
        ItemStack stack = new ItemStack(this);
        ItemStack stack2 = new ItemStack(Items.EMERALD, 1);
        if (fortune != 0) {
            int number = random.nextInt(fortune + 1);
            ItemStack stack1 = new ItemStack(Items.EMERALD, number + 1);
            if (random.nextInt(10) > 4) {
//				drops.add(0, stack);
                drops.add(0, stack1);
            } else drops.add(0, stack);
        } else {
            if (random.nextInt(10) > 8) {
//				drops.add(0, stack);
                drops.add(0, stack2);
            } else drops.add(0, stack);
        }
    }
}
