package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.util.List;
import javax.annotation.Nullable;

import com.yuoMod.Tmod.Common.ConfigLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Entity.EntityLightningDiamond;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPSword extends ItemSword
{
	/*
	 * harvestLevel参数表示制作出的工具等级。
maxUses参数表示制作出的工具对应耐久。
efficiency参数表示制作出的工具使用效率。
damageVsEntity参数表示攻击伤害力度。
enchantability参数与附魔等级相关
	 */
	public static final ToolMaterial OPSWORD=EnumHelper.addToolMaterial("op_sword", 99, -1, 999.0f, -3.0f, 0);
	public OPSword(String name) 
	{
		super(OPSWORD);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		 tooltip.add(I18n.format("tmod.item.op_sword1", ""));
	     tooltip.add(I18n.format("tmod.item.op_sword2", ""));
    }
	//主动
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote)
		{
			if(playerIn.isSneaking())
			{
				float range=ConfigLoader.range * 1.0f;
				attackAOE(playerIn, range, 10000.0f, ConfigLoader.opSwordType);
			}
			else
			{
				EntityLightningDiamond lightningDiamond=new EntityLightningDiamond(worldIn,playerIn);
				lightningDiamond.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead,0, 2.0f, 0.1f);
				worldIn.spawnEntity(lightningDiamond);
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
		else return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	//攻击实体
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(attacker.isSneaking())
		{
			if(target instanceof EntityPlayer)
			{
				target.setHealth(0.0f);
			}
			else
			{
				if(target instanceof EntityDragon)
				{
					target.setHealth(0.0f);
				}
				else target.setDead();
			}
			attacker.sendMessage(new TextComponentTranslation(target.getName()+"OnKill"));
		}
		else
		{
			if(target instanceof EntityDragon)//对末影龙伤害
			{
				((EntityDragon) target).attackEntityFromPart(((EntityDragon) target).dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) attacker), 10000.0f);
			}
			else
			target.attackEntityFrom(DamageSource.GENERIC, 10000.0f);
		}
        return super.hitEntity(stack, target, attacker);
    }
	//aoe伤害
	protected void attackAOE(EntityPlayer player,float range, float damage,boolean type)
	{
		if (player.getEntityWorld().isRemote)
		{
			return;
		}
		AxisAlignedBB aabb = player.getEntityBoundingBox().grow(range);//范围
		List<Entity> toAttack = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);//生物列表
		DamageSource src = DamageSource.GENERIC;//伤害类型
		for (Entity entity : toAttack)//循环遍历
		{
			if(type)
			{
				if(entity instanceof EntityLivingBase)
				{
					entity.attackEntityFrom(src, damage);//给与实体伤害
				}
			}
			else
			{
				if (entity instanceof IMob || entity instanceof MultiPartEntityPart)
				{
					entity.attackEntityFrom(src, damage);
				}
			}
		}
		player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 3.0f);
	}
}
