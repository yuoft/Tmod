package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import javax.annotation.Nonnull;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Gui.GuiLoader;
import com.yuoMod.Tmod.TileEntity.MyTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PowerExtractor extends BlockContainer {
    //����
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    //ȼ��
    public static final PropertyBool BURNING = PropertyBool.create("burning");

    public PowerExtractor(String name) {
        super(Material.ROCK);
        this.setUnlocalizedName(name);
        this.setHardness(5);
        this.setResistance(50);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(BURNING, false));
    }

    //��֪���ǵķ���ʹ������2��IProperty
    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, BURNING);//����״̬����
    }

    //BlockState��Metadata���໥��Ӧ
    @Override
    public IBlockState getStateFromMeta(int meta)//��ֵ�õ�״̬
    {
        EnumFacing facing = EnumFacing.NORTH;
        Boolean burning = false;
        switch (meta) {
            case 0:
                facing = EnumFacing.NORTH;
                burning = false;
                break;
            case 1:
                facing = EnumFacing.SOUTH;
                burning = false;
                break;
            case 2:
                facing = EnumFacing.EAST;
                burning = false;
                break;
            case 3:
                facing = EnumFacing.WEST;
                burning = false;
                break;
            case 4:
                facing = EnumFacing.NORTH;
                burning = true;
                break;
            case 5:
                facing = EnumFacing.SOUTH;
                burning = true;
                break;
            case 6:
                facing = EnumFacing.EAST;
                burning = true;
                break;
            case 7:
                facing = EnumFacing.WEST;
                burning = true;
                break;
        }
        return this.getDefaultState().withProperty(FACING, facing).withProperty(BURNING, burning);
    }

    @Override
    public int getMetaFromState(IBlockState state)//��״̬�õ�ֵ
    {
        int meta = 0;
        if (state.getValue(FACING) == EnumFacing.NORTH && state.getValue(BURNING) == false)
            meta = 0;
        if (state.getValue(FACING) == EnumFacing.SOUTH && state.getValue(BURNING) == false)
            meta = 1;
        if (state.getValue(FACING) == EnumFacing.EAST && state.getValue(BURNING) == false)
            meta = 2;
        if (state.getValue(FACING) == EnumFacing.WEST && state.getValue(BURNING) == false)
            meta = 3;
        if (state.getValue(FACING) == EnumFacing.NORTH && state.getValue(BURNING) == true)
            meta = 4;
        if (state.getValue(FACING) == EnumFacing.SOUTH && state.getValue(BURNING) == true)
            meta = 5;
        if (state.getValue(FACING) == EnumFacing.EAST && state.getValue(BURNING) == true)
            meta = 6;
        if (state.getValue(FACING) == EnumFacing.WEST && state.getValue(BURNING) == true)
            meta = 7;
        return meta;
    }

    public int getMetadata(int damage) {
        return 0;
    }

    //��ȡλ��״̬
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    //���÷���ʱ���÷���״̬
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (placer != null) {
            worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        }
    }

    //������
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockLoader.powerExtractor);
    }

    //ȼ��ʱ�����������
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(BURNING)) {
            EnumFacing enumfacing = stateIn.getValue(FACING);
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound((double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            switch (enumfacing) {
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    //�ƻ���������Ʒ����
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        final TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof MyTileEntity) {
            for (int i = 0; i < ((MyTileEntity) tile).getSizeInventory(); i++) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((MyTileEntity) tile).getStackInSlot(i));
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    //������Ⱦ��ʽ
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//		int id = guiLoader.GUI_DEMO;
//		// playerIn�Ǹ�EntityPlayer
//		// 5����arbitrary number��������IGuiHandler�е�id�����庬����ʵ�־���
//		// worldIn�Ǹ�World
//		// ��������xyzû��ǿ��Ҫ�������꣬�����ڴ���������
//		playerIn.openGui(tmod.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
//		return true;
        if (!worldIn.isRemote) {
            int id = GuiLoader.GUI_DEMO;
            playerIn.openGui(Tmod.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    //����TileEntity
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new MyTileEntity();
    }

}



