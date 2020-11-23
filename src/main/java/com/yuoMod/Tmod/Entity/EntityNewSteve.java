package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Entity.AI.AISetBlock;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityNewSteve extends EntityZombie
{
	public EntityNewSteve(World worldIn) {
		super(worldIn);
		this.tasks.addTask(3, new AISetBlock(this));
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
	}
	// 实体更新
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	// 掉落物(是否被玩家杀死，抢夺等级)
	@Override
	protected void dropFewItems(boolean arg1, int arg2) 
	{
		super.dropFewItems(arg1, arg2);
	}
	//wasRecentlyHit 最近是否被击中
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		if(wasRecentlyHit == true && rand.nextInt(100) < (69 + lootingModifier *3))
		{
			ItemStack stack=new ItemStack(itemLoader.totem_small,rand.nextInt(2));
			ItemStack stack1=new ItemStack(itemLoader.exp_small,rand.nextInt(3)+lootingModifier);
			ItemStack stack2=new ItemStack(itemLoader.exp_big,rand.nextInt(3)+lootingModifier);
			this.entityDropItem(stack, 0.0f);
			this.entityDropItem(stack1, 0.0f);
			this.entityDropItem(stack2, 0.0f);
		}
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}
	// 实体生成属性
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);//生命
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);//速度
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);//攻击伤害
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);//盔甲防御
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);//盔甲韧性
	}
}
