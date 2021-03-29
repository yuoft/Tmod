package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Entity.AI.AIKianaAttackRange;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class EntityKiana extends EntityMob implements IEntityMultiPart, IMob,IRangedAttackMob
{
	private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    
	public EntityKiana(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 1.8f);
		this.experienceValue=2000;
		this.isImmuneToFire = true;//�Ի�����
	}
	public void onLivingUpdate()
    {
		super.onLivingUpdate();
    }
	//��Ϊai
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(2, new AIKianaAttackRange(this, 40));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
	//����ai
    protected void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityIronGolem>(this, EntityIronGolem.class, true));
    }
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);//����
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);//�ٶ�
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(30.0D);//�����˺�
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0D);//���׷���
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(10.0D);//��������
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);//���˿���
		this.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 100000, 0));
	}
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		if(wasRecentlyHit == true)
		{
			ItemStack stack=new ItemStack(itemLoader.space_core,rand.nextInt(2)+lootingModifier);
			ItemStack stack1=new ItemStack(itemLoader.exp_big,rand.nextInt(10)+lootingModifier+1);
			ItemStack stack2=new ItemStack(Items.TOTEM_OF_UNDYING,rand.nextInt(1));
			this.entityDropItem(stack, 0.0f);
			this.entityDropItem(stack1, 0.0f);
			this.entityDropItem(stack2, 0.0f);
		}
	}
	public void setCustomNameTag(String name) {
		super.setCustomNameTag(name);
		this.bossInfo.setName(this.getDisplayName());
	}
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }
    //��������
    protected void despawnEntity()
    {
        this.idleTime = 0;
    }
	@Override
	public World getWorld() {
		// TODO �Զ����ɵķ������
		return this.getWorld();
	}
	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		// TODO �Զ����ɵķ������
		return false;
	}
	//��boss����false
	public boolean isNonBoss()
    {
        return false;
    }
	public void onUpdate()
    {
		EntityLivingBase livingBase=this.world.getNearestPlayerNotCreative(this, 16.0D);
		if(livingBase !=null && livingBase.isEntityAlive() && livingBase instanceof EntityPlayer)//��һ���ֵ����15�㣬��ɱ���
		{
			int armor=((EntityPlayer) livingBase).getTotalArmorValue();
        	if(armor > 15)
        	{
        		livingBase.sendMessage(new TextComponentTranslation("tmod.text.boss"));
        		livingBase.attackEntityFrom(DamageSource.causeMobDamage(this), 1000.0F);
        	}
		}
        super.onUpdate();
    }
	//�ܵ��˺�������ĳ��ֵ�����ˣ�
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (amount > 30) {
			amount = 30;
		}
		return super.attackEntityFrom(source, amount);
	}
	//��ʲô�˺�(ֻ�������ɵ��˺�)
	public boolean isEntityInvulnerable(DamageSource source)
    {
		if( !source.isCreativePlayer() || source.equals(DamageSource.IN_FIRE) || source.equals(DamageSource.LIGHTNING_BOLT)
				|| source.equals(DamageSource.ON_FIRE) || source.equals(DamageSource.LAVA) || source.equals(DamageSource.HOT_FLOOR)
				|| source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.CRAMMING) || source.equals(DamageSource.DROWN)
				|| source.equals(DamageSource.STARVE) || source.equals(DamageSource.CACTUS) || source.equals(DamageSource.FALL)
				|| source.equals(DamageSource.MAGIC) || source.equals(DamageSource.WITHER) || source.equals(DamageSource.ANVIL)
				|| source.equals(DamageSource.FALLING_BLOCK) || source.equals(DamageSource.DRAGON_BREATH) || source.equals(DamageSource.FIREWORKS)
				|| source.isProjectile() || source.isExplosion()){
			return false;
		}
		else return true;
    }
	//Զ�̹���
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		switch(rand.nextInt(3))
		{
		case 0:attackArrow(target, distanceFactor);break;
		case 1:attackSnowball(target, distanceFactor);break;
		case 2:attackFireCharge(target, distanceFactor);break;
		default : return ;
		}
	}
	@Override
	public void setSwingingArms(boolean swingingArms) {
		
	}
	//�õ���ʵ��
	protected EntityArrow getArrow(float p_190726_1_) {
		EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
		entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
		return entitytippedarrow;
	}
	//������
	private void attackArrow(EntityLivingBase target, float distanceFactor)
	{
		for(int i=0; i < 10; i++)
		{
			EntityArrow entityarrow = this.getArrow(distanceFactor);
	        double d0 = target.posX - this.posX;
	        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
	        double d2 = target.posZ - this.posZ;
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
	        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	        this.world.spawnEntity(entityarrow);
		}
	}
	//ѩ�򹥻�
	private void attackSnowball(EntityLivingBase target, float distanceFactor)
	{
		for(int i=0; i < 10 ; i++)
		{
			EntitySnowball snowball=new EntitySnowball(world, this);
			double d0 = target.posX - this.posX;
	        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - snowball.posY;
	        double d2 = target.posZ - this.posZ;
	        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	        snowball.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
	        this.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	        this.world.spawnEntity(snowball);
		}
	}
	//���浯
	private void attackFireCharge(EntityLivingBase target, float distanceFactor)
	{
		for(int i=0; i < 10 ; i++)
		{
            Vec3d vec3d = this.getLook(1.0F);
            double d2 = target.posX - (this.posX + vec3d.x * 4.0D);
            double d3 = target.getEntityBoundingBox().minY + (double)(target.height / 2.0F) - (0.5D + this.posY + (double)(this.height / 2.0F));
            double d4 = target.posZ - (this.posZ + vec3d.z * 4.0D);
            world.playEvent((EntityPlayer)null, 1016, new BlockPos(this), 0);
            EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this, d2, d3, d4);
            entitylargefireball.explosionPower = 1;
            entitylargefireball.posX = this.posX + vec3d.x * 4.0D;
            entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
            entitylargefireball.posZ = this.posZ + vec3d.z * 4.0D;
            world.spawnEntity(entitylargefireball);
		}
	}
}
