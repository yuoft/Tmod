package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//����
public class Mine extends BlockFalling{
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	private static final AxisAlignedBB MINE_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D);

	public Mine(String name) {
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setHardness(1.0f);
//		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(20.0f);
		this.setSoundType(SoundType.STONE);
	}
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if(!worldIn.isRemote) {
			worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 5.0f, true);
		}
	}
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return MINE_AABB;
	}
	//������Ⱦ��ʽ
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	//�ǲ�͸������
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	//����������
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	//ʵ���Ƿ���ͨ���˿�
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
	//����Ƿ����ڴ˿�������
	public boolean canSpawnInBlock()
    {
        return true;
    }
	//���ڷ�����º�
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!this.canBePlacedOn(worldIn, pos.down()))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
        }
        if (worldIn.isBlockPowered(pos))
        {
        	if(!worldIn.isRemote) {
				worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 5.0f, true);
			}
        }
    }
	//���Է���ʲô������
	private boolean canBePlacedOn(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).isSideSolid(worldIn, pos, EnumFacing.DOWN) || worldIn.getBlockState(pos).getBlock() instanceof BlockFence;
    }
	//��ʵ���뷽����ײ��
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
//		if (entityIn instanceof EntityLivingBase) { //ʵ����ײ������ը
			if(!worldIn.isRemote) {
				worldIn.createExplosion(entityIn, pos.getX(), pos.getY(), pos.getZ(), 5.0f, true);
			}
//		}
    }
	//����ը����ʱ
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        if (!worldIn.isRemote)
        {
        	worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 5.0f, true);
        }
    }
}
