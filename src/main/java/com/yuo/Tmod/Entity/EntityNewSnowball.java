package com.yuo.Tmod.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityNewSnowball extends EntitySnowball {
    private float damage = 0;

    public EntityNewSnowball(World worldIn) {
        super(worldIn);
    }

    public EntityNewSnowball(World worldIn, EntityLivingBase throwerIn, float damage) {
        super(worldIn, throwerIn);
        this.damage = damage;
    }

    public EntityNewSnowball(World worldIn, double x, double y, double z, float damage) {
        super(worldIn, x, y, z);
        this.damage = damage;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            float i = this.damage;

            if (result.entityHit instanceof EntityBlaze) {
                i *= 1.3;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), i);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.setDead();
        }
    }
}
