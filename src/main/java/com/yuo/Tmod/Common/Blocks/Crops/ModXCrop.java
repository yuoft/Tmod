package com.yuo.Tmod.Common.Blocks.Crops;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import java.util.Random;

public class ModXCrop extends BlockCrops implements IGrowable {
    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[]{
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public ModXCrop(String name) {
        this.setUnlocalizedName(name);//X型作物
        this.setTickRandomly(true);//随机时间刻
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase) entityIn;
            living.setInWeb();
            if (!worldIn.isRemote && getAge(state) > 0 && (entityIn.lastTickPosX != entityIn.posX || entityIn.lastTickPosZ != entityIn.posZ)) {
                double d0 = Math.abs(entityIn.posX - entityIn.lastTickPosX);
                double d1 = Math.abs(entityIn.posZ - entityIn.lastTickPosZ);
                if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
                    living.attackEntityFrom(DamageSource.MAGIC, 1.0F);
                }
            }
        }
    }

    @Override
    public void grow(World worldIn, BlockPos pos, IBlockState state) {
        int i = this.getAge(state) + this.getGrowAge(state.getBlock());
        int j = this.getMaxAge();

        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    //生长速度
    private int getGrowAge(Block block) {
        Random random = new Random();
        if (block == BlockLoader.coalCrop) {
            return MathHelper.getInt(random, 2, 5);
        } else if (block == BlockLoader.diamondCrop) {
            return MathHelper.getInt(random, 1, 3);
        } else if (block == BlockLoader.emeraldCrop) {
            return MathHelper.getInt(random, 1, 3);
        } else if (block == BlockLoader.goldCrop) {
            return MathHelper.getInt(random, 1, 4);
        } else if (block == BlockLoader.ironCrop) {
            return MathHelper.getInt(random, 1, 4);
        } else if (block == BlockLoader.lapisCrop) {
            return MathHelper.getInt(random, 2, 4);
        } else if (block == BlockLoader.quartzCrop) {
            return MathHelper.getInt(random, 2, 4);
        } else if (block == BlockLoader.redstoneCrop) {
            return MathHelper.getInt(random, 2, 4);
        } else if (block == BlockLoader.netheriteCrop) {
            return MathHelper.getInt(random, 1, 2);
        }
        return 1;
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = getAge(state);
        Random rand = world instanceof World ? ((World) world).rand : new Random();

        if (age >= getMaxAge()) {
            drops.add(new ItemStack(getCropSeed(state.getBlock()), MathHelper.getInt(rand, 1, 1 + fortune), 0));
            drops.add(getCropFruit(state.getBlock(), rand, fortune));
        }
    }

    private Item getCropSeed(Block block){
        if (block == BlockLoader.coalCrop) {
            return ItemLoader.coalCropSeed;
        } else if (block == BlockLoader.diamondCrop) {
            return ItemLoader.diamondCropSeed;
        } else if (block == BlockLoader.emeraldCrop) {
            return ItemLoader.emeraldCropSeed;
        } else if (block == BlockLoader.goldCrop) {
            return ItemLoader.goldCropSeed;
        } else if (block == BlockLoader.ironCrop) {
            return ItemLoader.ironCropSeed;
        } else if (block == BlockLoader.lapisCrop) {
            return ItemLoader.lapisCropSeed;
        } else if (block == BlockLoader.quartzCrop) {
            return ItemLoader.quartzCropSeed;
        } else if (block == BlockLoader.redstoneCrop) {
            return ItemLoader.redstoneCropSeed;
        } else if (block == BlockLoader.netheriteCrop) {
            return ItemLoader.netheriteCropSeed;
        }
        return this.getSeed();
    }

    private ItemStack getCropFruit(Block block, Random random, int fortune){
        if (block == BlockLoader.coalCrop) {
            return new ItemStack(ItemLoader.coalFruit, MathHelper.getInt(random, 5, 10 + fortune * 5));
        } else if (block == BlockLoader.diamondCrop) {
            return new ItemStack(ItemLoader.diamondFruit, MathHelper.getInt(random, 2, 5 + fortune * 2));
        } else if (block == BlockLoader.emeraldCrop) {
            return new ItemStack(ItemLoader.emeraldFruit, MathHelper.getInt(random, 2, 5 + fortune * 2));
        } else if (block == BlockLoader.goldCrop) {
            return new ItemStack(ItemLoader.goldFruit, MathHelper.getInt(random, 3, 6 + fortune *3));
        } else if (block == BlockLoader.ironCrop) {
            return new ItemStack(ItemLoader.ironFruit, MathHelper.getInt(random, 3, 6 + fortune * 3));
        } else if (block == BlockLoader.lapisCrop) {
            return new ItemStack(ItemLoader.lapisFruit, MathHelper.getInt(random, 4, 8 + fortune * 4));
        } else if (block == BlockLoader.quartzCrop) {
            return new ItemStack(ItemLoader.quartzFruit, MathHelper.getInt(random, 4, 8 + fortune * 4));
        } else if (block == BlockLoader.redstoneCrop) {
            return new ItemStack(ItemLoader.redstoneFruit, MathHelper.getInt(random, 4, 8 + fortune * 4));
        } else if (block == BlockLoader.netheriteCrop) {
            return new ItemStack(ItemLoader.netheriteFruit, MathHelper.getInt(random, 1, 4 + fortune));
        }
        return new ItemStack(this.getCrop());
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROPS_AABB[state.getValue(this.getAgeProperty())];
    }

}
