package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPPickaxe extends ItemTool{
	private static Set<Block> BLOCKS = Sets.newHashSet(
			Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST,
			Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
			Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE,Blocks.ACTIVATOR_RAIL,
			Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK,
			Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK,
			Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK,
			Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK,
			Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE,
			Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY,
			Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND,
			Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WEB, Blocks.LEAVES, 
			Blocks.WOOL, Blocks.LEAVES2, blockLoader.salt_ore, blockLoader.emerald_ingot_ore, blockLoader.space_ore,
			blockLoader.boss_block, blockLoader.emerald_chest, blockLoader.emerald_ingot_block, blockLoader.emerald_leaf,
			blockLoader.emerald_tree, blockLoader.power_extractor, blockLoader.space_block, blockLoader.tallgrass_block,
			blockLoader.ruby_ore, Blocks.BEDROCK);
	private ItemHander hander;
	public OPPickaxe(String name) 
	{
		super(1.0f, 5.0f,OPSword.OPSWORD, BLOCKS);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setHarvestLevel("pickaxe", 99);
        this.hander = new ItemHander(this);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		 tooltip.add(I18n.format("tmod.item.op_pickaxe1", ""));
	     tooltip.add(I18n.format("tmod.item.op_pickaxe2", ""));
    }
	@Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
    {
		if(player.isSneaking())
		{
			Block blockDestroyed = player.getEntityWorld().getBlockState(pos).getBlock();
	        this.hander.onBlockStartBreak(stack, player.world, blockDestroyed, pos, player, 10, 20);
		}
        return false;
    }
}
