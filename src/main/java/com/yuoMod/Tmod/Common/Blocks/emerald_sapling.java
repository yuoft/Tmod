package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.WorldCreate.WorldTreeCreate;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class emerald_sapling extends BlockBush implements IGrowable
{
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
	//�̱�ʯ����
	public emerald_sapling(String name) 
	{
		super(Material.LEAVES);
		this.setUnlocalizedName(name);
	    this.setHardness(0.01f);
	    this.setResistance(0.3f);
	    this.setCreativeTab(CreativeTabsLoader.TMOD);
	    this.setSoundType(SoundType.PLANT);
//	    this.setTickRandomly(true);
	}
	//��ȡ�߽��
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return SAPLING_AABB;
    }
	//�Ƿ���Գɳ�
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}
    //�Ƿ����ʹ�ùǷ�
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO �Զ����ɵķ������
		return true;
	}
	//����
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO �Զ����ɵķ������
		WorldTreeCreate tree=new WorldTreeCreate();
		tree.generate(worldIn, rand, pos);
	}
}
