package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Common.Items.Tool.ModHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySpaceArrow extends EntityArrow {
    public EntitySpaceArrow(World worldIn) {
        super(worldIn);
        this.setDamage(6.0f);
    }

    public EntitySpaceArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setDamage(6.0f);
    }

    public EntitySpaceArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setDamage(6.0f);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemLoader.spaceArrow);
    }


    //空间弓 攻击怪物造成10伤害并且产生爆炸 否则将目标实体传送
    @Override
    protected void arrowHit(EntityLivingBase living) {
        if (!(living instanceof IMob)) {
            ModHelper.TP(living, world);
        } else living.attackEntityFrom(DamageSource.causeThornsDamage(shootingEntity), 10.f);
    }
}
