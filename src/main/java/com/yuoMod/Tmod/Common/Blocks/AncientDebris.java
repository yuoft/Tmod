package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.block.BlockHay;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class AncientDebris extends BlockHay {

    public AncientDebris(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(22);
        this.setResistance(55);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("pickaxe", 3);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemLoader.ancientDebris;
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
