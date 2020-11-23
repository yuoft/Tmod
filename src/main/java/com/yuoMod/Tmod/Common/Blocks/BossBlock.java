package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Entity.EntityNewSteve;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
//’ŸªΩboss
public class BossBlock extends Block
{
	public BossBlock(String name) {
		super(Material.ROCK);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setHardness(10);
		this.setHarvestLevel("pickaxe", 3);
		this.setResistance(60);
		this.setSoundType(SoundType.STONE);
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote && playerIn.isSneaking())
		{
			EntityNewSteve steve=new EntityNewSteve(worldIn);
			steve.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.spawnEntity(steve);
			worldIn.setBlockToAir(pos);
		}
		return true;
    }
}
