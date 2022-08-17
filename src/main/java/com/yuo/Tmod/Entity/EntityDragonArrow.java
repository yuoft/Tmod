package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Common.Items.ItemLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDragonArrow extends EntityArrow {
    public EntityDragonArrow(World worldIn) {
        super(worldIn);
        this.setDamage(4.5f);
    }

    public EntityDragonArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setDamage(4.5f);
    }

    public EntityDragonArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setDamage(4.5f);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemLoader.dragonArrow);
    }


    //空间弓 攻击怪物造成10伤害并且产生爆炸 否则将目标实体传送
    @Override
    protected void arrowHit(EntityLivingBase living) {
        living.attackEntityFrom(DamageSource.causeThornsDamage(shootingEntity), 5.0f);
    }
}
