package com.yuoMod.Tmod.Entity.AI;

import java.util.List;

import com.yuoMod.Tmod.Capability.EventMobLv;
import com.yuoMod.Tmod.Entity.EntityKiana;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class AIKianaAttackRange extends EntityAIBase {
	private EntityKiana entity;
	private int tick = 0;
	private int speed;
	private final IRangedAttackMob rangedAttackEntityHost;

	public AIKianaAttackRange(IRangedAttackMob attacker, int speed) {
		this.rangedAttackEntityHost = attacker;
		this.entity = (EntityKiana) attacker;
		this.speed = speed;
	}

	// 是否执行ai任务
	@Override
	public boolean shouldExecute() {
		BlockPos pos = entity.getPosition();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int distance = 16;
		AxisAlignedBB aabb = new AxisAlignedBB(x -distance , y - distance, z - distance, 
				x + distance, y + distance, z + distance); // 得到周围16格的玩家或调零
		List<EntityPlayer> entitiesWithinAABB = entity.world.getEntitiesWithinAABB(EntityPlayer.class, aabb);
		List<EntityWither> witerWithinAABB = entity.world.getEntitiesWithinAABB(EntityWither.class, aabb);
		if(witerWithinAABB.size() == 0 && entitiesWithinAABB.size() == 0) {
			return false;
		}else if(witerWithinAABB.size() != 0) {
			entity.setAttackTarget(witerWithinAABB.get(0)); //设置调零为攻击目标
			return true;
		}else {
			for(EntityPlayer player : entitiesWithinAABB) {
				if(player == null || player.isDead) {
					return false;
				}
				BlockPos playerPos = player.getPosition();
				int i = (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(x - playerPos.getX()), 2)
						+ Math.pow(Math.abs(z - playerPos.getZ()), 2))));
				if (i > 8 && i < 32)
					return true;
				else return false;
			}
		}
		return false;
	}

	// ai任务
	@Override
	public void updateTask() {
		this.tick++;
		if (tick < speed) {
			return;
		}
		EntityLivingBase entityLivingBase = this.entity.getAttackTarget();
		if (entityLivingBase instanceof EntityPlayer || entityLivingBase instanceof EntityWither
				|| entityLivingBase instanceof EntityDragon) {
			this.rangedAttackEntityHost.attackEntityWithRangedAttack(entityLivingBase, 
					(float) (20.0f + this.entity.getEntityAttribute(EventMobLv.LIVING_LEVEL).getBaseValue()));
			tick = 0;
		}
	}
}
