package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Common.Items.ItemLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGoldArrow extends EntityArrow {
    public EntityGoldArrow(World worldIn) {
        super(worldIn);
        this.setDamage(3.0f);
    }

    public EntityGoldArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.setDamage(3.0f);
    }

    public EntityGoldArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
        this.setDamage(3.0f);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemLoader.goldArrow);
    }

}
