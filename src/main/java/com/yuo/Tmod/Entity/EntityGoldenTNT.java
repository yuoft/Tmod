package com.yuo.Tmod.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//投掷物
public class EntityGoldenTNT extends EntityThrowable {
    public EntityGoldenTNT(World worldIn) {
        super(worldIn);
    }

    public EntityGoldenTNT(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
//		this.shoot((Entity)throwerIn, 1.0f, 1.0f, 1.0f, 3.0f, 0.1f);
//		this.shoot(posX, posY, posZ, 3.0f, 0.1f);
    }

    public EntityGoldenTNT(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    //落地或碰撞后触发事件
    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            // createExplosion产生爆炸；entityIn 爆炸源，可以为 null，不参与爆炸伤害判定；x,y,z double，坐标；strength
            // 爆炸威力/范围，普通苦力怕为3.0，TNT为4.0；isSmoking 是否破坏地形和造成伤害
            this.world.createExplosion(this.thrower, this.posX, this.posY, this.posZ, 10.0f, true);
            // 删除实体
            this.setDead();
        }
    }
}
