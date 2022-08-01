package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Entity.AI.AIKianaAttackRange;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityKiana extends EntityMob implements IMob, IRangedAttackMob {
    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    public EntityKiana(World worldIn) {
        super(worldIn);
        this.setSize(0.5f, 1.8f);
        this.experienceValue = 2000;
        this.isImmuneToFire = true;//对火免疫
        this.ignoreFrustumCheck = true;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    //ai
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(2, new AIKianaAttackRange(this, 40));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIMoveTowardsTarget(this, 1.0D, 64));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
//        this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.0D, 40, 20.0F));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);//生命
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);//速度
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(30.0D);//攻击伤害
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0D);//盔甲防御
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10.0D);//盔甲韧性
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);//击退抗性
        this.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 100000, 2));
    }

    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (wasRecentlyHit) {
            EntityHelper.setMobDrops(this, 3, lootingModifier);
        }
    }

    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    //死亡年龄
    protected void despawnEntity() {
        this.idleTime = 0;
    }

    //是boss返回false
    public boolean isNonBoss() {
        return false;
    }

    protected void updateAITasks() {
        if (!this.dead)
            this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    public void onUpdate() {
        EntityPlayer livingBase = this.world.getNearestPlayerNotCreative(this, 16.0D);
        if (livingBase != null && livingBase.isEntityAlive())//玩家护甲值大于20点，秒杀玩家
        {
            int armor = livingBase.getTotalArmorValue();
            if (armor > 20) {
                livingBase.sendMessage(new TextComponentTranslation("tmod.text.boss"));
                livingBase.attackEntityFrom(DamageSource.causeMobDamage(this), 10000.0F);
            }
        }
        super.onUpdate();
    }

    //受到伤害不超过某个值（锁伤）
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) source.getTrueSource();
            ItemStack mainhand = player.getHeldItemMainhand();
            if (mainhand.getItem() == ItemLoader.op_sword) {
                return super.attackEntityFrom(source, amount);
            }
        }
        if (amount > 50) {
            amount = 50;
        }
        return super.attackEntityFrom(source, amount);
    }

    //受什么伤害(只受玩家造成的伤害)
    public boolean isEntityInvulnerable(DamageSource source) {
        return !(source.getTrueSource() instanceof EntityPlayer) || source.isCreativePlayer();
    }

    //远程攻击 目标 攻击力
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        switch (rand.nextInt(3)) {
            case 0:
                attackArrow(target, distanceFactor);
                break;
            case 1:
                attackSnowball(target, distanceFactor);
                break;
            case 2:
                attackFireCharge(target, distanceFactor);
                break;
            default:
        }
    }

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(EntityKiana.class, DataSerializers.BOOLEAN);

    @Override
    public void setSwingingArms(boolean swingingArms) {
        this.dataManager.set(SWINGING_ARMS, swingingArms);
    }

    //得到箭实体
    private EntityArrow getArrow(float i) {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, i);
        entitytippedarrow.setDamage(i);
        return entitytippedarrow;
    }

    //箭攻击
    private void attackArrow(EntityLivingBase target, float distanceFactor) {
        for (int i = 0; i < 5; i++) {
            EntityArrow entityarrow = this.getArrow(distanceFactor);
            double d0 = target.posX - this.posX;
            double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - entityarrow.posY;
            double d2 = target.posZ - this.posZ;
            double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
            entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getDifficultyId() * 4));
            this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(entityarrow);
        }
    }

    //雪球攻击
    private void attackSnowball(EntityLivingBase target, float distanceFactor) {
        for (int i = 0; i < 5; i++) {
            EntityNewSnowball snowball = new EntityNewSnowball(world, this, distanceFactor);
            double d0 = target.posX - this.posX;
            double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - snowball.posY;
            double d2 = target.posZ - this.posZ;
            double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
            snowball.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getDifficultyId() * 4));
            this.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(snowball);
        }
    }

    //火焰弹
    private void attackFireCharge(EntityLivingBase target, float distanceFactor) {
        for (int i = 0; i < 5; i++) {
            Vec3d vec3d = this.getLook(1.0F);
            double d2 = target.posX - (this.posX + vec3d.x * 4.0D);
            double d3 = target.getEntityBoundingBox().minY + (double) (target.height / 2.0F) - (0.5D + this.posY + (double) (this.height / 2.0F));
            double d4 = target.posZ - (this.posZ + vec3d.z * 4.0D);
            world.playEvent(null, 1016, new BlockPos(this), 0);
            EntityNewFireball fireball = new EntityNewFireball(world, this, d2, d3, d4, distanceFactor);
            fireball.explosionPower = 1;
            fireball.posX = this.posX + vec3d.x * 4.0D;
            fireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
            fireball.posZ = this.posZ + vec3d.z * 4.0D;
            world.spawnEntity(fireball);
        }
    }
}
