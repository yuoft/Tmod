package com.yuo.Tmod.Entity.AI;

import java.util.List;

import com.yuo.Tmod.Capability.EventMobLv;
import com.yuo.Tmod.Entity.EntityKiana;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class AIKianaAttackRange extends EntityAIBase {
    private final EntityKiana entity;
    private int tick = 0;
    private final int speed;
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
        AxisAlignedBB aabb = new AxisAlignedBB(x - distance, y - distance, z - distance,
                x + distance, y + distance, z + distance); // 得到周围16格的玩家或调零
        List<EntityLiving> entitiesWithinAABB = entity.world.getEntitiesWithinAABB(EntityLiving.class, aabb);
        if (entitiesWithinAABB.size() == 0) return false;
        for (EntityLiving living : entitiesWithinAABB) {
            if (living == null || living.isDead) {
                return false;
            }
            if (living instanceof EntityWither) {
                entity.setAttackTarget(living); //设置最近实体为攻击目标
            }
            BlockPos playerPos = living.getPosition();
            int i = (int) Math.ceil(Math.sqrt((Math.pow(Math.abs(x - playerPos.getX()), 2)
                    + Math.pow(Math.abs(z - playerPos.getZ()), 2))));
            return i > 8 && i < 32;
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
        if (entityLivingBase instanceof EntityMob || entityLivingBase instanceof EntityAnimal) {
            this.rangedAttackEntityHost.attackEntityWithRangedAttack(entityLivingBase,
                    (float) (20.0f + this.entity.getEntityAttribute(EventMobLv.LIVING_LEVEL).getBaseValue() / 2));
//			this.entity.attackEntityWithRangedAttack(entityLivingBase, (float) (20.0f + this.entity.getEntityAttribute(EventMobLv.LIVING_LEVEL).getBaseValue() / 2));
            tick = 0;
        }
    }
}
