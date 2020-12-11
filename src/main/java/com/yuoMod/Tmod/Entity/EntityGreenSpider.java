package com.yuoMod.Tmod.Entity;

import com.yuoMod.Tmod.Common.Items.itemLoader;

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

public class EntityGreenSpider extends EntitySpider
{
	private int tick=200;//技能冷却
	public EntityGreenSpider(World worldIn) {
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
	protected void dropFewItems(boolean arg1, int arg2) 
	{
//		if (this.rand.nextInt(3) == 0) {
//			this.dropItem(Items.EMERALD, 12);
//		}
		super.dropFewItems(arg1, arg2);
	}
	//wasRecentlyHit 最近是否被击中
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		if(wasRecentlyHit == true && rand.nextInt(100) < 69)
		{
			ItemStack stack=new ItemStack(Items.DIAMOND,rand.nextInt(2)+lootingModifier);
			ItemStack stack1=new ItemStack(itemLoader.exp_small,rand.nextInt(3)+lootingModifier);
			this.entityDropItem(stack, 0.0f);
			this.entityDropItem(stack1, 0.0f);
		}
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
	}
	// 实体生成属性
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0D);//生命
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);//速度
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);//攻击伤害
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);//盔甲防御
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2.0D);//盔甲韧性
	}
	public void onUpdate() 
	{
		super.onUpdate();
        this.tick++;
		if (!this.world.isRemote) {
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
		
		if(this.isEntityAlive())//被攻击时有20%吐蛛网
		{
			EntityPlayer player=this.attackingPlayer;
			if(player != null && rand.nextInt(100) > 79 && this.tick >= 200)//至少2秒触发一次
			{
				BlockPos pos=player.getPosition();
				IBlockState state=Blocks.WEB.getDefaultState();
				world.setBlockState(pos, state);
				this.tick=0;
			}
		}
	}
	private void SpwanMob()
	{
		int i=rand.nextInt(100);
		if(i < 70 && i >= 30)
		{
			if(rand.nextInt(100) >50)//实体生成 物品槽物品			
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			else this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
		}
		else if(i < 85 && i>= 70)
		{
			if(rand.nextInt(100) > 50)
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			else this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
		}
		else if(i < 95 && i >= 85)
		{
			if(rand.nextInt(100) >50)
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			else this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
		}
		else if(i >= 95)//极小概率生成全套铁装
		{
			this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
		}
	}
}
