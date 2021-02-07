package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//投掷物
public class EntityLightningDiamond extends EntityThrowable
{
	public EntityLightningDiamond(World worldIn) {
		super(worldIn);
	}

	public EntityLightningDiamond(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}

	public EntityLightningDiamond(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	//落地或碰撞后触发事件
	@Override
	protected void onImpact(RayTraceResult result) {
		if(!this.world.isRemote)
		{
			switch(result.typeOfHit) {
			case ENTITY: {
				if(result.entityHit instanceof EntityDragon)
				{
					((EntityDragon) result.entityHit).attackEntityFromPart(((EntityDragon) result.entityHit).dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) this.thrower), 100.0f);
				}
				else result.entityHit.attackEntityFrom(DamageSource.GENERIC, 100.0f);
			};break;
			case BLOCK: {
				BlockPos pos = result.getBlockPos();
				EntityLightningBolt lightningBolt=new EntityLightningBolt(this.world, pos.getX(), pos.getY(), pos.getZ(), false);
				this.world.createExplosion(this, this.posX, this.posY, this.posZ, 5.0f, true);
				this.world.spawnEntity(lightningBolt);
			};break;
			default : break;
			}
			// 删除实体
			this.setDead();
		}
	}
}
