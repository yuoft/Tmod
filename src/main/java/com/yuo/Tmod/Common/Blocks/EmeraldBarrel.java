package com.yuo.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.yuo.Tmod.Tmod;
import com.yuo.Tmod.Tab.TmodGroup;
import com.yuo.Tmod.Gui.GuiLoader;
import com.yuo.Tmod.TileEntity.NineTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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

public class EmeraldBarrel extends BlockContainer {
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);

    public EmeraldBarrel(String name) {
        super(Material.WOOD);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(5);
        this.setResistance(50);
        this.setHarvestLevel("axe", 2);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, false).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, OPEN, LOG_AXIS);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
    }
    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (te != null) {
            IItemHandler inv = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            return ItemHandlerHelper.calcRedstoneFromInventory(inv);
        }

        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new NineTileEntity();
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockLoader.emeraldBarrel);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        final TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof NineTileEntity) {
            for (int i = 0; i < ((NineTileEntity) tile).items.size(); i++)
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((NineTileEntity) tile).items.get(i));
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
            int id = GuiLoader.CHEST_GUI;
            playerIn.openGui(Tmod.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
            worldIn.setBlockState(pos, state.withProperty(OPEN, true));
        }
        worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 3.0f, 1.0f);
//        playerIn.playSound(SoundEvents.BLOCK_CHEST_OPEN, 3.0f, 1.0f);
        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        BlockLog.EnumAxis axis = BlockLog.EnumAxis.Y;
        boolean open = false;
        switch (meta) {
            case 0:
                break;
            case 1:
                axis = BlockLog.EnumAxis.X;
                break;
            case 2:
                axis = BlockLog.EnumAxis.Z;
                break;
            case 3:
                open = true;
                break;
            case 4:
                axis = BlockLog.EnumAxis.X;
                open = true;
                break;
            case 5:
                axis = BlockLog.EnumAxis.Z;
                open = true;
                break;
        }
        return this.getDefaultState().withProperty(LOG_AXIS, axis).withProperty(OPEN, open);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (state.getValue(LOG_AXIS) == BlockLog.EnumAxis.X)
            meta = 1;
        if (state.getValue(LOG_AXIS) == BlockLog.EnumAxis.Z)
            meta = 2;
        if (state.getValue(OPEN))
            meta += 3;
        return meta;
    }
}
