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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpaceBlock extends Block
{
	//绿宝石锭块
	public SpaceBlock(String name)
	{
        super(Material.ROCK);//放置音效
        this.setUnlocalizedName(name);
        this.setHardness(30);//硬度
        this.setHarvestLevel("pickaxe", 5);//采集工具,等级
        this.setResistance(500);//爆炸抗性
        this.setCreativeTab(CreativeTabsLoader.TMOD);
        this.setSoundType(SoundType.STONE);//破坏音效
    }
	//实体在方块上走时
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
		if(entityIn instanceof EntityLivingBase)
		{
			//原版紫颂果效果
			EntityLivingBase entityLiving=(EntityLivingBase) entityIn;
			Helper.TP(entityLiving, worldIn);
		}
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.block.space_block", ""));
    }
	//方块被放置
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) 
	{
		if(!worldIn.isRemote)
		{
			//检查方块结构
			int x=pos.getX();
			int y=pos.getY();
			int z=pos.getZ();
			BlockPos posCenter=new BlockPos(x, y-1, z);
			IBlockState stateCenter=worldIn.getBlockState(posCenter);
			if(stateCenter.getBlock().equals(blockLoader.emerald_ingot_block))
			{
				EntityIronGolem ironGolem=new EntityIronGolem(worldIn);
				ironGolem.setPosition(x, y-1, z);
				//设置实体属性
				ironGolem.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);//生命
				ironGolem.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);//速度
				ironGolem.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50.0D);//攻击伤害
				ironGolem.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0D);//盔甲防御
				ironGolem.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10.0D);//盔甲韧性
				ironGolem.setPlayerCreated(true);
				ironGolem.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY() + 0.05D, (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
				ironGolem.setHealth(300.0F);
				worldIn.spawnEntity(ironGolem);
				//删除方块
				placer.sendMessage(new TextComponentTranslation("坐标："+pos.toString()+" " + posCenter.toString()));
				worldIn.setBlockState(posCenter, Blocks.AIR.getDefaultState(), 2);
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
			}
			else placer.sendMessage(new TextComponentTranslation("error"));
		}
	}
}
