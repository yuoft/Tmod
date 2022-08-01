package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.ItemLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityRedSpider extends EntitySpider {
    private int tick = 160;

    public EntityRedSpider(World worldIn) {
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

    // ÊµÌåÉú³ÉÊôÐÔ
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(64.0D);//ÉúÃü
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);//ËÙ¶È
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);//¹¥»÷ÉËº¦
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);//»÷ÍË¿¹ÐÔ
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);//¿ø¼×·ÀÓù
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0D);//¿ø¼×ÈÍÐÔ
    }

    public void onUpdate() {
        super.onUpdate();
        this.tick++;
        if (!this.world.isRemote) {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }

        if (this.isEntityAlive())//±»¹¥»÷Ê±ÓÐ30%ÍÂÖëÍø
        {
            EntityPlayer player = this.attackingPlayer;
            if (player != null && rand.nextInt(100) > 69 && this.tick >= 160) {
                BlockPos pos = player.getPosition();
                IBlockState state = Blocks.WEB.getDefaultState();
                world.setBlockState(pos, state);
                this.tick = 0;
            }
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
            if (rand.nextInt(10000) == 0)//0.01%¸ÅÂÊÂÌ±¦Ê¯¶§Ì×
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
