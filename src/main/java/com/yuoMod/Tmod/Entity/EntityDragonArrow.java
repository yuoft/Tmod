package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDragonArrow extends EntityArrow {
    public EntityDragonArrow(World worldIn) {
        super(worldIn);
        this.setDamage(3.0f);
    }

    public EntityDragonArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setDamage(3.0f);
    }

    public EntityDragonArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setDamage(3.0f);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }


    //�ռ乭 �����������10�˺����Ҳ�����ը ����Ŀ��ʵ�崫��
    @Override
    protected void arrowHit(EntityLivingBase living) {
        living.attackEntityFrom(DamageSource.causeThornsDamage(shootingEntity), 5.f);
    }
}