package com.yuo.Tmod.Entity;

import com.yuo.Tmod.Common.Items.ItemLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityRedEnderman extends EntityEnderman {
    private int tick = 40;

    public EntityRedEnderman(World worldIn) {
        super(worldIn);
        this.SpwanMob();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    protected void dropFewItems(boolean arg1, int arg2) {
        super.dropFewItems(arg1, arg2);
    }

    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (EntityHelper.randomDrop(wasRecentlyHit, lootingModifier)) {
            EntityHelper.setMobDrops(this, 1, lootingModifier);
        }
        super.dropLoot(wasRecentlyHit, lootingModifier, source);
    }

    // 实体生成属性
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);//生命
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);//速度
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);//攻击伤害
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);//击退抗性
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);//盔甲防御
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);//盔甲韧性
    }

    public void onUpdate() {
        super.onUpdate();
        this.tick++;
        if (tick < 40) {
            return;
        }
        EntityPlayer player = this.attackingPlayer;
        if (player != null && rand.nextInt(100) > 50) {
            BlockPos pos = player.getPosition();
            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ());
            IBlockState state = world.getBlockState(pos1);
            if (state.getBlockHardness(world, pos1) < 5.0f) {
                this.setHeldBlockState(state);
                world.setBlockToAir(pos1);
            }
            tick = 0;
        }
    }

    private void SpwanMob() {
        int i = rand.nextInt(100);
        if (i < 70 && i >= 30) {
            if (rand.nextInt(100) > 50)
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
            else this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        } else if (i < 85 && i >= 70) {
            if (rand.nextInt(100) > 50)
                this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            else this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
        } else if (i < 95 && i >= 85) {
            if (rand.nextInt(100) > 50)
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
            else this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        } else if (i >= 95) {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
            this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
            this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
            this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
        } else {
            if (rand.nextInt(10000) == 0)//0.01%概率绿宝石锭套
            {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.emeraldSword));
                this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.emeraldHead));
                this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.emeraldChest));
                this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.emeraldFeet));
                this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.emeraldLegs));
            }
        }
    }
}
