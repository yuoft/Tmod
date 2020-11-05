package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityNewZombies extends EntityZombie
{
	public EntityNewZombies(World worldIn) {
		super(worldIn);
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
//		if (this.rand.nextInt(3) == 0) {
//			this.dropItem(Items.EMERALD, 12);
//		}
		super.dropFewItems(arg1, arg2);
	}
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		if(wasRecentlyHit == true )
		{
			ItemStack stack=new ItemStack(Items.DIAMOND,rand.nextInt(2)+lootingModifier);
			this.entityDropItem(stack, 0.0f);
		}
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}
	// 实体生成属性
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);//生命
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);//速度
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);//攻击伤害
//		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);//击退抗性
//		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);//盔甲防御
//		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(1.0D);//盔甲韧性
	}
}
