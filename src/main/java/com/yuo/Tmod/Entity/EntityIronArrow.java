package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Common.Items.ItemLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIronArrow extends EntityArrow {
    public EntityIronArrow(World worldIn) {
        super(worldIn);
        this.setDamage(2.5f);
    }

    public EntityIronArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setDamage(2.5f);
    }

    public EntityIronArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setDamage(2.5f);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemLoader.ironArrow);
    }

}
