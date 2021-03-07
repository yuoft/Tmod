package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpaceSword extends ItemSword
{
	private int tick=2400;
	public SpaceSword(String name,ToolMaterial material) 
	{
		super(material);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		 tooltip.add(I18n.format("tmod.item.space_sword1", ""));
	     tooltip.add(I18n.format("tmod.item.space_sword2", ""));
	     int i= (int) Math.ceil((this.tick/40));//冷却剩余时间
	     tooltip.add(I18n.format("tmod.item.space_sword3","")+i+"s");
    }
	//主动
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote)
		{
			if(playerIn.isSneaking() && this.tick == 0)
			{
				float range=5.0f;
				
				attackAOE(playerIn, range, 10.0f, false);
				this.tick=2400;
			}
			else if(playerIn.isSneaking() && this.tick > 0)
			{
				playerIn.sendMessage(new TextComponentTranslation("tmod.text.space_sword2"));
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
		else return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	//攻击实体
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		Random random=new Random();
		if(random.nextInt(100) > 89)
		{
			if(target instanceof EntityPlayer)
			{
				target.setHealth(0.0f);
			}
			else
			{
				if(target instanceof EntityDragon)//对末影龙暴击
				{
					((EntityDragon) target).attackEntityFromPart(((EntityDragon) target).dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) attacker), 100.0f);
				}
				else
				target.attackEntityFrom(DamageSource.GENERIC, 100.0f);
			}
			attacker.sendMessage(new TextComponentTranslation("tmod.text.space_sword1"));
		}
        return super.hitEntity(stack, target, attacker);
    }
	//更新
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(this.tick > 0)
		{
			this.tick--;
		}
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
		DamageSource src = DamageSource.causePlayerDamage(player);//伤害类型
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
				if (entity instanceof IMob)
				{
					entity.attackEntityFrom(src, damage);
				}
			}
		}
		player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
	}
}
