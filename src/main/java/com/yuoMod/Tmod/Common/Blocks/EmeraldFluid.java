package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Fluid.FluidLoader;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class EmeraldFluid extends BlockFluidClassic {
    public EmeraldFluid(String name) {
        super(FluidLoader.emerald_fluid, Material.WATER);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setTickRandomly(true);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.MAGIC, 2);
    }
}