package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.ItemLoader;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRedCreeper extends EntityCreeper {
    // ���һ�λ��ʱ��
    @SuppressWarnings("unused")
    private int lastActiveTime;
    // ������Һ���ʱ��
    private int timeSinceIgnited;
    // ��ըʱ��
    private final int fuseTime = 20;

    public EntityRedCreeper(World worldIn) {
        super(worldIn);
        this.SpwanMob();
    }

    // ʵ�����
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    // ������(�Ƿ����ɱ��������ȼ�)
    @Override
    protected void dropFewItems(boolean arg1, int arg2) {
        super.dropFewItems(arg1, arg2);
    }

    //wasRecentlyHit ����Ƿ񱻻���
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (EntityHelper.randomDrop(wasRecentlyHit, lootingModifier)) {
            EntityHelper.setMobDrops(this, 1, lootingModifier);
            if (rand.nextInt(100) > 50) {
                ItemStack stack4 = new ItemStack(Items.TOTEM_OF_UNDYING, 1);
                this.entityDropItem(stack4, 0.0f);
            }
        }
        super.dropLoot(wasRecentlyHit, lootingModifier, source);
    }

    // ʵ����������
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);//����
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);//�ٶ�
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);//�����˺�
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3D);//���˿���
    }

    private void explode() {
        if (!this.world.isRemote) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
            this.dead = true;
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 100.0f, flag);
            this.setDead();
        }
    }

    public void onUpdate() {
        if (this.isEntityAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;

            if (this.hasIgnited()) {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();

            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            }

            this.timeSinceIgnited += i;

            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.onUpdate();
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
            if (rand.nextInt(10000) == 0)//0.01%�����̱�ʯ����
            {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemLoader.emerald_sword));
                this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemLoader.emerald_helmet));
                this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemLoader.emerald_breastplate));
                this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemLoader.emerald_shoes));
                this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemLoader.emerald_leggings));
            }
        }
    }
}
