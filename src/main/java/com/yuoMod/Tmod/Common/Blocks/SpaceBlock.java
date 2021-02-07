package com.yuoMod.Tmod.Common.Blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Util.Helper;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpaceBlock extends Block {
	// �̱�ʯ����
	public SpaceBlock(String name) {
		super(Material.ROCK);// ������Ч
		this.setUnlocalizedName(name);
		this.setHardness(30);// Ӳ��
		this.setHarvestLevel("pickaxe", 5);// �ɼ�����,�ȼ�
		this.setResistance(500);// ��ը����
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setSoundType(SoundType.STONE);// �ƻ���Ч
	}

	// ʵ���ڷ�������ʱ
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof EntityLivingBase) {
			// ԭ�����̹�Ч��
			EntityLivingBase entityLiving = (EntityLivingBase) entityIn;
			Helper.TP(entityLiving, worldIn);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("tmod.block.space_block", ""));
	}
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
		//ֹͣ��Ϸ������
//		Minecraft mc = Minecraft.getMinecraft();
//		mc.stopIntegratedServer();
    }
	// ���鱻����
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		// ��鷽��ṹ
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		BlockPos posCenter = new BlockPos(x, y - 1, z);
		BlockPos posBottom = new BlockPos(x, y - 2, z);
		IBlockState stateCenter = worldIn.getBlockState(posCenter);
		IBlockState stateBottom = worldIn.getBlockState(posBottom);
		if (stateCenter.getBlock().equals(blockLoader.emerald_ingot_block)
				|| stateBottom.getBlock().equals(blockLoader.emerald_ingot_block)) {
			EntityIronGolem ironGolem = new EntityIronGolem(worldIn);
			ironGolem.setPosition(x, y - 2, z);
			// ����ʵ������
			ironGolem.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);// ����
			ironGolem.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);// �ٶ�
			ironGolem.setHealth(300.0F);
			// ��ʾ��Ϣ������ʵ��
			if (!worldIn.isRemote) {
				placer.sendMessage(new TextComponentTranslation("tmod.text.spuerIronGolem"));
				worldIn.spawnEntity(ironGolem);
			}
			// ɾ������
			worldIn.setBlockState(posCenter, Blocks.AIR.getDefaultState());
			worldIn.setBlockState(posBottom, Blocks.AIR.getDefaultState());
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		} else {
			if (!worldIn.isRemote)
				placer.sendMessage(new TextComponentTranslation("error"));
		}
	}
}
