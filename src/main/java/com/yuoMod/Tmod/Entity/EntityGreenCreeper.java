package com.yuoMod.Tmod.Entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGreenCreeper extends EntityCreeper {
    //最后一次活动的时间
    private int lastActiveTime;
    //靠近玩家后点火时间
    private int timeSinceIgnited;
    //爆炸时间
    private final int fuseTime = 20;

    public EntityGreenCreeper(World worldIn) {
        super(worldIn);
        this.SpwanMob();
    }

    // 实体更新
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    // 掉落物(是否被玩家杀死，抢夺等级)
    @Override
    protected void dropFewItems(boolean arg1, int arg2) {
        super.dropFewItems(arg1, arg2);
    }

    //wasRecentlyHit 最近是否被击中
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (EntityHelper.randomDrop(wasRecentlyHit, lootingModifier)) {
            EntityHelper.setMobDrops(this, 0, lootingModifier);
        }
        super.dropLoot(wasRecentlyHit, lootingModifier, source);
    }

    // 实体生成属性
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);//生命
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);//速度
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);//攻击伤害
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);//盔甲防御
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2.0D);//盔甲韧性
    }

    private void explode()//苦力怕爆炸
    {
        if (!this.world.isRemote) {
            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this);
            this.dead = true;
            this.world.createExplosion(this, this.posX, this.posY, this.posZ, 6.0f, flag);
            this.setDead();
        }
    }

    public void onUpdate()//爆炸逻辑
    {
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

            if (this.timeSinceIgnited >= this.fuseTime)//时间大于爆炸时间就爆炸(秒炸)
            {
                this.timeSinceIgnited = this.fuseTime;
                this.explode();
            }
        }

        super.onUpdate();
    }

    private void SpwanMob() {
        int i = rand.nextInt(100);
        if (i < 70 && i >= 30) {
            if (rand.nextInt(100) > 50)//实体生成 物品槽物品
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            else this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
        } else if (i < 85 && i >= 70) {
            if (rand.nextInt(100) > 50)
                this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            else this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
        } else if (i < 95 && i >= 85) {
            if (rand.nextInt(100) > 50)
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            else this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        } else if (i >= 95)//极小概率生成全套铁装
        {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
            this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
        }
    }
}
