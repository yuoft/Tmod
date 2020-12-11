package com.yuoMod.Tmod.Common.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Entity.EntityKiana;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
//ÕÙ»½boss
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
		if(playerIn.isSneaking())
		{
			EntityKiana kiana=new EntityKiana(worldIn);
			kiana.setPosition(pos.getX(), pos.getY(), pos.getZ());
			EntityLightningBolt lightningBolt=new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			worldIn.spawnEntity(lightningBolt);
			if(!worldIn.isRemote)
			{
				playerIn.sendMessage(new TextComponentTranslation("tmod.text.boss_spawn"));
				Random random=new Random();
				if(random.nextInt(100) > 66)
				{
					kiana.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(itemLoader.emerald_sword));
					kiana.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
					kiana.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(itemLoader.emerald_helmet));
					kiana.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(itemLoader.emerald_breastplate));
					kiana.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(itemLoader.emerald_shoes));
					kiana.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(itemLoader.emerald_leggings));
					playerIn.sendMessage(new TextComponentTranslation("tmod.text.boss_strong"));
				}
				worldIn.spawnEntity(kiana);
				worldIn.setBlockToAir(pos);
			}
		}
		else// if(!worldIn.isRemote)
		{
			EntityZombie steve=new EntityZombie(worldIn);
			steve.setPosition(pos.getX(), pos.getY(), pos.getZ());
			steve.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(itemLoader.emerald_sword));
			steve.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
			steve.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(itemLoader.emerald_helmet));
			steve.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(itemLoader.emerald_breastplate));
			steve.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(itemLoader.emerald_shoes));
			steve.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(itemLoader.emerald_leggings));
			steve.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			steve.setHealth(100.0f);
			steve.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
			steve.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
			steve.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
			if(!worldIn.isRemote)
			{
				playerIn.sendMessage(new TextComponentTranslation("tmod.text.boss_spawn"));
				worldIn.spawnEntity(steve);
				worldIn.setBlockToAir(pos);
			}
		}
		return true;
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.block.boss_block1", ""));
       tooltip.add(I18n.format("tmod.block.boss_block2", ""));
    }
}
