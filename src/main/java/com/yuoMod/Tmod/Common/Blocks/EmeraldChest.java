package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nonnull;
import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Gui.guiLoader;
import com.yuoMod.Tmod.TileEntity.NineTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class EmeraldChest extends BlockContainer
{
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		
	protected EmeraldChest(String name) {
//		super(BlockChest.Type.TRAP);
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setSoundType(SoundType.STONE);
		this.setHardness(5);
		this.setResistance(50);
		this.setHarvestLevel("pickaxe", 3);
	}
	@Nonnull
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return AABB;
	}
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
	{
		TileEntity te = world.getTileEntity(pos);
		if (te != null)
		{
			IItemHandler inv = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			return ItemHandlerHelper.calcRedstoneFromInventory(inv);
		}

		return 0;
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new NineTileEntity();
	}
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(blockLoader.emerald_chest);
    }
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		final TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof NineTileEntity) {
			final ItemStackHandler inv = ((NineTileEntity) tile).getInventory();
			for (int i = 0; i < inv.getSlots(); i++) {
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inv.getStackInSlot(i));
			}
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
		EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			int id = guiLoader.CHEST_GUI;
			playerIn.openGui(tmod.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 20.0f, 1.0f);
		playerIn.playSound(SoundEvents.BLOCK_CHEST_OPEN, 20.0f, 1.0f);
		return true;
	}
}
