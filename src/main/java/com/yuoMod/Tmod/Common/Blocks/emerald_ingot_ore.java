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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class emerald_ingot_ore extends BlockOre 
{
	//绿宝石锭矿
	public emerald_ingot_ore(String name) {
		super(MapColor.STONE);
		this.setUnlocalizedName(name);
        this.setSoundType(SoundType.STONE);
        this.setHardness(10);
        this.setResistance(5);
        this.setLightLevel(1.0f);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        // Forge 的采掘等级机制，幻数 2 代表与钻石矿同级，magic string "pickaxe" 代表镐
        this.setHarvestLevel("pickaxe", 3);
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return false;
    }
	// SRG func_180660_a，用于决定掉落的物品种类
    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return itemLoader.emerald_powder;
    }

    // SRG func_149745_a，用于决定掉落的物品数量
    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(2)+1;
    }

    // SRG func_149679_a，用于决定受时运影响时掉落的物品数量
    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
//            int bonusFactor = Math.max(random.nextInt(fortune + 2) - 1, 0);
            return this.quantityDropped(random) * (fortune + 1);
        } else {
            return this.quantityDropped(random);
        }
//    	return 1;
    }

    // Forge 的 patch，用于决定掉落的经验数量（参考原版煤炭、红石、青金石、钻石、绿宝石、下界石英）
    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
       return 10;
    }

    // Forge 的 patch，取代 getItem (func_185473_a)，用于创造模式下鼠标中键选取方块的功能。
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }
}
