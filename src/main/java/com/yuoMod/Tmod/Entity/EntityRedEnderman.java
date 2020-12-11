package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityRedEnderman extends EntityEnderman
{
	private int tick=40;
	public EntityRedEnderman(World worldIn) {
		super(worldIn);
		this.SpwanMob();
	}
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	@Override
	protected void dropFewItems(boolean arg1, int arg2) 
	{
		super.dropFewItems(arg1, arg2);
	}
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		if(wasRecentlyHit == true && rand.nextInt(100) < 69)
		{
			ItemStack stack=new ItemStack(Items.DIAMOND,rand.nextInt(4)+lootingModifier);
			ItemStack stack1=new ItemStack(Items.EMERALD,rand.nextInt(2)+lootingModifier);
			ItemStack stack2=new ItemStack(itemLoader.exp_big,rand.nextInt(2)+lootingModifier);
			ItemStack stack3=new ItemStack(itemLoader.exp_small,rand.nextInt(4)+lootingModifier);
			this.entityDropItem(stack, 0.0f);
			this.entityDropItem(stack1, 0.0f);
			this.entityDropItem(stack2, 0.0f);
			this.entityDropItem(stack3, 0.0f);
		}
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}
	// ÊµÌåÉú³ÉÊôÐÔ
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);//ÉúÃü
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);//ËÙ¶È
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);//¹¥»÷ÉËº¦
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);//»÷ÍË¿¹ÐÔ
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);//¿ø¼×·ÀÓù
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);//¿ø¼×ÈÍÐÔ
	}
	public void onUpdate()
	{
		super.onUpdate();
		this.tick++;
		if(tick < 40)
		{
			return ;
		}
		EntityPlayer player=this.attackingPlayer;
		if(player !=null && rand.nextInt(100) > 50)
		{
			BlockPos pos=player.getPosition();
			BlockPos pos1=new BlockPos(pos.getX(), pos.getY()+2, pos.getZ());
			IBlockState state=world.getBlockState(pos1);
			if(state.getBlockHardness(world, pos1) < 5.0f)
			{
				this.setHeldBlockState(state);
				world.setBlockToAir(pos1);
			}
			tick=0;
		}
	}
	private void SpwanMob()
	{
		int i=rand.nextInt(100);
		if(i < 70 && i >= 30)
		{
			if(rand.nextInt(100) >50)
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
			else this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
		}
		else if(i < 85 && i>= 70)
		{
			if(rand.nextInt(100) > 50)
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			else this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
		}
		else if(i < 95 && i >= 85)
		{
			if(rand.nextInt(100) >50)
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
			else this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
		}
		else if(i >= 95)
		{
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
		}
		else
		{
			if(rand.nextInt(10000) == 0)//0.01%¸ÅÂÊÂÌ±¦Ê¯¶§Ì×
			{
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(itemLoader.emerald_sword));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(itemLoader.emerald_helmet));
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(itemLoader.emerald_breastplate));
				this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(itemLoader.emerald_shoes));
				this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(itemLoader.emerald_leggings));
			}
		}
	}
}
