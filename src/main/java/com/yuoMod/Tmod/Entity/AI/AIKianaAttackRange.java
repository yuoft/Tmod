package com.yuoMod.Tmod.Entity.AI;

import com.yuoMod.Tmod.Entity.EntityKiana;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class AIKianaAttackRange extends EntityAIBase
{
	private EntityKiana entity;
	private int tick=0;
	private int speed;
	private final IRangedAttackMob rangedAttackEntityHost;
	
	public AIKianaAttackRange(IRangedAttackMob attacker,int speed) 
	{
		this.rangedAttackEntityHost=attacker;
		this.entity=(EntityKiana) attacker;
		this.speed=speed;
	}
	//是否执行ai任务
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entityLivingBase=entity.world.getNearestPlayerNotCreative(entity, 32.0D);//发现可攻击的非创造玩家
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
			BlockPos entityPos=entity.getPosition();
			BlockPos playerPos=entityLivingBase.getPosition();
			int i= (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(entityPos.getX()-playerPos.getX()),2) + Math.pow(Math.abs(entityPos.getZ()-playerPos.getZ()),2))));
			if(i > 5 && i < 16 )
			{
				return true;
			}
			else return false;
		}
	}
	//ai任务
	@Override
	public void updateTask() {
		this.tick++;
		if(tick < speed)
		{
			return ;
		}
		EntityLivingBase entityLivingBase=this.entity.world.getNearestPlayerNotCreative(entity, 16.0D);
    	if(entityLivingBase instanceof EntityPlayer)
    	{
    		this.rangedAttackEntityHost.attackEntityWithRangedAttack(entityLivingBase, 20.0f);
    		tick=0;
    	}
	}
}

