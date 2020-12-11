package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
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

public class SaltOre extends BlockOre 
{
	//ÂÌ±¦Ê¯¶§¿ó
	public SaltOre(String name) {
		super(MapColor.STONE);
		this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(8);
        this.setResistance(15);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("pickaxe", 1);
	}
    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return itemLoader.salt_ore;
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
