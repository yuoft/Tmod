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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SpaceOre extends BlockOre 
{
	//绿宝石锭矿
	public SpaceOre(String name) {
		super(MapColor.STONE);
		this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(10);
        this.setResistance(500);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("pickaxe", 3);
	}
	// SRG func_180660_a，用于决定掉落的物品种类
    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return itemLoader.space_patch;
    }

    // SRG func_149745_a，用于决定掉落的物品数量
    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(5)+1;
    }

    // SRG func_149679_a，用于决定受时运影响时掉落的物品数量
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            return this.quantityDropped(random) + (fortune + random.nextInt(fortune));
        } else {
            return this.quantityDropped(random);
        }
    }
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random random = world instanceof World ? ((World) world).rand : new Random();
        return MathHelper.getInt(random, 30, 70);
    }

    // Forge 的 patch，取代 getItem (func_185473_a)，用于创造模式下鼠标中键选取方块的功能。
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }
}
