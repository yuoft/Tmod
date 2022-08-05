package com.yuo.Tmod.Entity.AI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class AISkeletonAttackSword extends EntityAIBase {
    private final EntityCreature entity;

    public AISkeletonAttackSword(EntityCreature entity) {
        this.entity = entity;
    }

    //是否执行ai任务
    @Override
    public boolean shouldExecute() {
        EntityLivingBase entityLivingBase = entity.world.getNearestPlayerNotCreative(entity, 3.0D);
        if (entityLivingBase == null) {
            return false;
        } else if (!entityLivingBase.isEntityAlive()) {
            return false;
        } else {
            ItemStack stack = entity.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            return stack == null || !stack.getItem().equals(Items.IRON_SWORD);
        }
    }

    //ai任务
    @Override
    public void updateTask() {//当玩家靠近骷髅3格时，切换武器为剑
        EntityLivingBase entityLivingBase = this.entity.world.getNearestPlayerNotCreative(entity, 3.0D);
        if (entityLivingBase instanceof EntityPlayer) {
            this.entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        }
    }
}

