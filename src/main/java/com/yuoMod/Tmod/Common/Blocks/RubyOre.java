package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class RubyOre extends BlockOre {
    public RubyOre(String name) {
        super(MapColor.STONE);
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(10);
        this.setResistance(20);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("pickaxe", 2);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemLoader.ruby_ore;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }
}
