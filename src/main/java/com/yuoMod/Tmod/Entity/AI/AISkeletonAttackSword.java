package com.yuoMod.Tmod.Entity.AI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class AISkeletonAttackSword extends EntityAIBase
{
	private EntityCreature entity;
	
	public AISkeletonAttackSword(EntityCreature entity) 
	{
		this.entity=entity;
	}
	//�Ƿ�ִ��ai����
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entityLivingBase=entity.world.getNearestPlayerNotCreative(entity, 3.0D);
		if(entityLivingBase == null)
		{
			return false;
		}
		else if(!entityLivingBase.isEntityAlive())
		{
			return false;
		}
		else
		{
			ItemStack stack=entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
			if(stack !=null && stack.getItem().equals(Items.IRON_SWORD))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	//ai����
	@Override
	public void updateTask() {//����ҿ�������3��ʱ���л�����Ϊ��
		EntityLivingBase entityLivingBase=this.entity.world.getNearestPlayerNotCreative(entity, 3.0D);
    	if(entityLivingBase instanceof EntityPlayer)
    	{
    		this.entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    	}
	}
}

