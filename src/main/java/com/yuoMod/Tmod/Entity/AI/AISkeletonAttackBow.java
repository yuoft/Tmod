package com.yuoMod.Tmod.Entity.AI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class AISkeletonAttackBow extends EntityAIBase
{
	private EntityCreature entity;
	
	public AISkeletonAttackBow(EntityCreature entity) 
	{
		this.entity=entity;
	}
	//是否执行ai任务
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entityLivingBase=entity.world.getNearestPlayerNotCreative(entity, 16.0D);
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
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	//ai任务
	@Override
	public void updateTask() {
		EntityLivingBase entityLivingBase=this.entity.world.getNearestPlayerNotCreative(entity, 16.0D);
    	if(entityLivingBase instanceof EntityPlayer)
    	{
    		BlockPos entityPos=entity.getPosition();
			BlockPos playerPos=entityLivingBase.getPosition();
			int i= (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(entityPos.getX()-playerPos.getX()),2) + Math.pow(Math.abs(entityPos.getZ()-playerPos.getZ()),2))));
			if(i > 5)
			{
				this.entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
			}
    	}
	}
}

