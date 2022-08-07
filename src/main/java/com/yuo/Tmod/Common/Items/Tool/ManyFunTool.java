package com.yuo.Tmod.Common.Items.Tool;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

//钻石多功能工具
public class ManyFunTool extends ItemTool {
    private static final HashSet<Block> blocks = Sets.newHashSet(
            Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST,
            Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
            Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.ACTIVATOR_RAIL,
            Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK,
            Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK,
            Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK,
            Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK,
            Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE,
            Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY,
            Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND,
            Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WEB, Blocks.LEAVES,
            Blocks.WOOL, Blocks.COAL_BLOCK, Blocks.NETHER_BRICK, Blocks.ANVIL,
            Blocks.LEAVES2, BlockLoader.saltOre, BlockLoader.emeraldIngotOre, BlockLoader.spaceOre,
            BlockLoader.bossBlock, BlockLoader.emeraldBarrel, BlockLoader.emeraldIngotBlock, BlockLoader.emeraldLeaf,
            BlockLoader.emeraldTree, BlockLoader.powerExtractor, BlockLoader.spaceBlock, BlockLoader.rubyOre);
    private final int level;

    public ManyFunTool(String name, float attack, float speed, ToolMaterial material) {
        super(attack, speed, material, blocks);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(1);
        this.level = material.getHarvestLevel();
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "shovel", "axe");
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
//        return blocks.contains(blockIn.getBlock()) || super.canHarvestBlock(blockIn);
        return true;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @Nullable EntityPlayer player, @Nullable IBlockState blockState) {
        return this.level;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return this.efficiency;
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (player.isSneaking()) {
            return this.UseHoe(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        } else {
            return this.UseShovel(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
    }

    //铲子右键方法（使用原版）
    public EnumActionResult UseShovel(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR && block == Blocks.GRASS) {
                IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
                worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!worldIn.isRemote) {
                    worldIn.setBlockState(pos, iblockstate1, 11);
                    itemstack.damageItem(1, player);
                }

                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.PASS;
            }
        }
    }

    //锄头右键方法（使用原版）
    @SuppressWarnings("incomplete-switch")
    public EnumActionResult UseHoe(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        } else {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT) {
                    switch (iblockstate.getValue(BlockDirt.VARIANT)) {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return EnumActionResult.SUCCESS;
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;
        }
    }

    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state) {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
}
