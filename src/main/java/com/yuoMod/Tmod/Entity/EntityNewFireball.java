package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityNewFireball extends EntityLargeFireball {
    private float damage;

    public EntityNewFireball(World worldIn) {
        super(worldIn);
    }

    public EntityNewFireball(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ, float damage) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.damage = damage;
    }

    public EntityNewFireball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, float damage) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.damage = damage;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (result.entityHit != null) {
                result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), this.damage);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
            this.world.newExplosion(null, this.posX, this.posY, this.posZ, (float) this.explosionPower, flag, flag);
            this.setDead();
        }
    }
}
