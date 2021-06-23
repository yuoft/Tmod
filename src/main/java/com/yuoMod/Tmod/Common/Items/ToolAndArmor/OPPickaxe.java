package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPPickaxe extends ItemTool{
	public static final ToolMaterial OPPICKAXE=EnumHelper.addToolMaterial("op_pickaxe", 99, -1, 999.0f, 0, 1);
	private static HashSet<Block> BLOCKS = Sets.newHashSet(
			Blocks.STONE,Blocks.GRASS,Blocks.DIRT,Blocks.COBBLESTONE,Blocks.PLANKS,Blocks.SAPLING,Blocks.BEDROCK,Blocks.SAND,Blocks.GRAVEL,
			Blocks.GOLD_ORE,Blocks.IRON_ORE,Blocks.COAL_ORE,Blocks.LOG,Blocks.LOG2,Blocks.LEAVES,Blocks.LEAVES2,Blocks.SPONGE,Blocks.GLASS,
			Blocks.LAPIS_ORE,Blocks.LAPIS_BLOCK,Blocks.DISPENSER,Blocks.SANDSTONE,Blocks.NOTEBLOCK,Blocks.BED,Blocks.GOLDEN_RAIL,Blocks.DETECTOR_RAIL,Blocks.STICKY_PISTON,
			Blocks.WEB,Blocks.TALLGRASS,Blocks.DEADBUSH,Blocks.PISTON,Blocks.PISTON_HEAD,Blocks.WOOL,Blocks.PISTON_EXTENSION,Blocks.YELLOW_FLOWER,Blocks.RED_FLOWER,
			Blocks.BROWN_MUSHROOM,Blocks.RED_MUSHROOM,Blocks.GOLD_BLOCK,Blocks.IRON_BLOCK,Blocks.DOUBLE_STONE_SLAB,Blocks.STONE_SLAB,Blocks.BRICK_BLOCK,Blocks.TNT,Blocks.BOOKSHELF,
			Blocks.MOSSY_COBBLESTONE,Blocks.OBSIDIAN,Blocks.TORCH,Blocks.FIRE,Blocks.MOB_SPAWNER,Blocks.OAK_STAIRS,Blocks.CHEST,Blocks.REDSTONE_WIRE,Blocks.DIAMOND_ORE,
			Blocks.DIAMOND_BLOCK,Blocks.CRAFTING_TABLE,Blocks.WHEAT,Blocks.FARMLAND,Blocks.FURNACE,Blocks.LIT_FURNACE,Blocks.STANDING_SIGN,Blocks.OAK_DOOR,Blocks.SPRUCE_DOOR,
			Blocks.BIRCH_DOOR,Blocks.JUNGLE_DOOR,Blocks.ACACIA_DOOR,Blocks.DARK_OAK_DOOR,Blocks.LADDER,Blocks.RAIL,Blocks.STONE_STAIRS,Blocks.WALL_SIGN,Blocks.LEVER,
			Blocks.STONE_PRESSURE_PLATE,Blocks.IRON_DOOR,Blocks.WOODEN_PRESSURE_PLATE,Blocks.REDSTONE_ORE,Blocks.LIT_REDSTONE_ORE,Blocks.UNLIT_REDSTONE_TORCH,Blocks.REDSTONE_TORCH,Blocks.STONE_BUTTON,Blocks.SNOW_LAYER,
			Blocks.ICE,Blocks.SNOW,Blocks.CACTUS,Blocks.CLAY,Blocks.REEDS,Blocks.JUKEBOX,Blocks.OAK_FENCE,Blocks.SPRUCE_FENCE,Blocks.BIRCH_FENCE,
			Blocks.JUNGLE_FENCE,Blocks.DARK_OAK_FENCE,Blocks.ACACIA_FENCE,Blocks.PUMPKIN,Blocks.NETHERRACK,Blocks.SOUL_SAND,Blocks.GLOWSTONE,Blocks.PORTAL,Blocks.LIT_PUMPKIN,
			Blocks.CAKE,Blocks.UNPOWERED_REPEATER,Blocks.POWERED_REPEATER,Blocks.TRAPDOOR,Blocks.MONSTER_EGG,Blocks.STONEBRICK,Blocks.BROWN_MUSHROOM_BLOCK,Blocks.RED_MUSHROOM_BLOCK,Blocks.IRON_BARS,
			Blocks.GLASS_PANE,Blocks.MELON_BLOCK,Blocks.PUMPKIN_STEM,Blocks.MELON_STEM,Blocks.VINE,Blocks.OAK_FENCE_GATE,Blocks.SPRUCE_FENCE_GATE,Blocks.BIRCH_FENCE_GATE,Blocks.JUNGLE_FENCE_GATE,
			Blocks.DARK_OAK_FENCE_GATE,Blocks.ACACIA_FENCE_GATE,Blocks.BRICK_STAIRS,Blocks.STONE_BRICK_STAIRS,Blocks.MYCELIUM,Blocks.WATERLILY,Blocks.NETHER_BRICK,Blocks.NETHER_BRICK_FENCE,Blocks.NETHER_BRICK_STAIRS,
			Blocks.NETHER_WART,Blocks.ENCHANTING_TABLE,Blocks.BREWING_STAND,Blocks.CAULDRON,Blocks.END_PORTAL,Blocks.END_PORTAL_FRAME,Blocks.END_STONE,Blocks.DRAGON_EGG,Blocks.REDSTONE_LAMP,
			Blocks.LIT_REDSTONE_LAMP,Blocks.DOUBLE_WOODEN_SLAB,Blocks.WOODEN_SLAB,Blocks.COCOA,Blocks.SANDSTONE_STAIRS,Blocks.EMERALD_ORE,Blocks.ENDER_CHEST,Blocks.TRIPWIRE_HOOK,Blocks.TRIPWIRE,
			Blocks.EMERALD_BLOCK,Blocks.SPRUCE_STAIRS,Blocks.BIRCH_STAIRS,Blocks.JUNGLE_STAIRS,Blocks.COMMAND_BLOCK,Blocks.BEACON,Blocks.COBBLESTONE_WALL,Blocks.FLOWER_POT,Blocks.CARROTS,
			Blocks.WOODEN_BUTTON,Blocks.POTATOES,Blocks.WOODEN_BUTTON,Blocks.SKULL,Blocks.ANVIL,Blocks.TRAPPED_CHEST,Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE,Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE,Blocks.UNPOWERED_COMPARATOR,
			Blocks.POWERED_COMPARATOR,Blocks.DAYLIGHT_DETECTOR,Blocks.DAYLIGHT_DETECTOR_INVERTED,Blocks.REDSTONE_BLOCK,Blocks.QUARTZ_ORE,Blocks.HOPPER,Blocks.QUARTZ_BLOCK,Blocks.QUARTZ_STAIRS,Blocks.ACTIVATOR_RAIL,
			Blocks.DROPPER,Blocks.STAINED_HARDENED_CLAY,Blocks.BARRIER,Blocks.IRON_TRAPDOOR,Blocks.HAY_BLOCK,Blocks.CARPET,Blocks.HARDENED_CLAY,Blocks.COAL_BLOCK,Blocks.PACKED_ICE,
			Blocks.DARK_OAK_STAIRS,Blocks.ACACIA_STAIRS,Blocks.SLIME_BLOCK,Blocks.DOUBLE_PLANT,Blocks.STAINED_GLASS,Blocks.STAINED_GLASS_PANE,Blocks.PRISMARINE,Blocks.SEA_LANTERN,Blocks.STANDING_BANNER,
			Blocks.WALL_BANNER,Blocks.RED_SANDSTONE,Blocks.RED_SANDSTONE_STAIRS,Blocks.DOUBLE_STONE_SLAB2,Blocks.STONE_SLAB2,Blocks.END_ROD,Blocks.CHORUS_PLANT,Blocks.CHORUS_FLOWER,Blocks.PURPUR_BLOCK,
			Blocks.PURPUR_PILLAR,Blocks.PURPUR_STAIRS,Blocks.PURPUR_DOUBLE_SLAB,Blocks.PURPUR_SLAB,Blocks.END_BRICKS,Blocks.BEETROOTS,Blocks.GRASS_PATH,Blocks.END_GATEWAY,Blocks.REPEATING_COMMAND_BLOCK,
			Blocks.CHAIN_COMMAND_BLOCK,Blocks.FROSTED_ICE,Blocks.MAGMA,Blocks.NETHER_WART_BLOCK,Blocks.RED_NETHER_BRICK,Blocks.BONE_BLOCK,Blocks.STRUCTURE_VOID,Blocks.OBSERVER,Blocks.WHITE_SHULKER_BOX,
			Blocks.ORANGE_SHULKER_BOX,Blocks.MAGENTA_SHULKER_BOX,Blocks.LIGHT_BLUE_SHULKER_BOX,Blocks.YELLOW_SHULKER_BOX,Blocks.LIME_SHULKER_BOX,Blocks.PINK_SHULKER_BOX,Blocks.GRAY_SHULKER_BOX,Blocks.SILVER_SHULKER_BOX,Blocks.CYAN_SHULKER_BOX,
			Blocks.PURPLE_SHULKER_BOX,Blocks.BLUE_SHULKER_BOX,Blocks.BROWN_SHULKER_BOX,Blocks.GREEN_SHULKER_BOX,Blocks.RED_SHULKER_BOX,Blocks.BLACK_SHULKER_BOX,Blocks.WHITE_GLAZED_TERRACOTTA,Blocks.ORANGE_GLAZED_TERRACOTTA,Blocks.MAGENTA_GLAZED_TERRACOTTA,
			Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA,Blocks.YELLOW_GLAZED_TERRACOTTA,Blocks.LIME_GLAZED_TERRACOTTA,Blocks.PINK_GLAZED_TERRACOTTA,Blocks.GRAY_GLAZED_TERRACOTTA,Blocks.SILVER_GLAZED_TERRACOTTA,Blocks.CYAN_GLAZED_TERRACOTTA,Blocks.PURPLE_GLAZED_TERRACOTTA,Blocks.BLUE_GLAZED_TERRACOTTA,
			Blocks.BROWN_GLAZED_TERRACOTTA,Blocks.GREEN_GLAZED_TERRACOTTA,Blocks.RED_GLAZED_TERRACOTTA,Blocks.BLACK_GLAZED_TERRACOTTA,Blocks.CONCRETE,Blocks.CONCRETE_POWDER,Blocks.STRUCTURE_BLOCK,
//			Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, 
//			Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE,
//			Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER,
//			Blocks.LEAVES, Blocks.LEAVES2, Blocks.WEB, Blocks.TALLGRASS, Blocks.VINE, Blocks.WOOL, Blocks.TRIPWIRE,
//			Blocks.COAL_BLOCK, Blocks.GOLD_BLOCK, Blocks.BED, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_STAIRS,
//			Blocks.NETHER_WART, Blocks.NETHER_WART_BLOCK, Blocks.NETHERRACK,
			blockLoader.salt_ore, blockLoader.emerald_ingot_ore, blockLoader.space_ore,
			blockLoader.boss_block, blockLoader.emerald_chest,blockLoader.emerald_ingot_block, blockLoader.emerald_leaf,
			blockLoader.emerald_tree, blockLoader.power_extractor, blockLoader.space_block, blockLoader.tallgrass_block,
			blockLoader.ruby_ore, Blocks.BEDROCK);
	private ItemHander hander;
	public OPPickaxe(String name) 
	{
		super(1.0f, -2.5f,OPPICKAXE, BLOCKS);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.hander = new ItemHander(this);
	}
	//添加附魔
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(this.isInCreativeTab(tab)) {
			Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
			map.put(Enchantment.getEnchantmentByID(35), 10);
			ItemStack stack = new ItemStack(this);
			EnchantmentHelper.setEnchantments(map, stack);
			items.add(stack);
		}
	}
	//设置挖掘等级
	public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
    {
        return 99;
    }
	//设置工具类型
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe", "shovel", "axe");
	}
	//附魔光效
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		 tooltip.add(I18n.format("tmod.item.op_pickaxe1", ""));
	     tooltip.add(I18n.format("tmod.item.op_pickaxe2", ""));
    }
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return BLOCKS.contains(blockIn.getBlock()) || super.canHarvestBlock(blockIn);
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
