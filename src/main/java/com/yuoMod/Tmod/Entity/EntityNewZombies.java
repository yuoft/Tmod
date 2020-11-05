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
	// ʵ�����
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	// ������(�Ƿ����ɱ��������ȼ�)
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
	// ʵ����������
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);//����
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);//�ٶ�
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);//�����˺�
//		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);//���˿���
//		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);//���׷���
//		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(1.0D);//��������
	}
}
