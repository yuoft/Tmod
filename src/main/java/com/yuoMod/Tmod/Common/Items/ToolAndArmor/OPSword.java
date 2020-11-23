package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.util.List;
import javax.annotation.Nullable;

import com.yuoMod.Tmod.Common.ConfigLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Entity.EntityGoldenTNT;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPSword extends ItemSword
{
	/*
	 * harvestLevel������ʾ�������Ĺ��ߵȼ���
maxUses������ʾ�������Ĺ��߶�Ӧ�;á�
efficiency������ʾ�������Ĺ���ʹ��Ч�ʡ�
damageVsEntity������ʾ�����˺����ȡ�
enchantability�����븽ħ�ȼ����
	 */
	private static final ToolMaterial OPSWORD=EnumHelper.addToolMaterial("op_sword", 9, -1, 99.0f, -3.0f, 0);
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
	//����
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(playerIn.isSneaking())
		{
			float range=ConfigLoader.range * 1.0f;
			attackAOE(playerIn, range, 10000.0f, ConfigLoader.opSwordType);
			playerIn.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 3.0f);
		}
		else
		{
			EntityGoldenTNT goldenTNT=new EntityGoldenTNT(worldIn, playerIn);
			goldenTNT.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead,0f, 2.0f, 0.1f);
			worldIn.spawnEntity(goldenTNT);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	//����ʵ��
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
				target.setDead();
			}
			attacker.sendMessage(new TextComponentTranslation(target.getName()+"OnKill"));
		}
		else
		{
			target.attackEntityFrom(DamageSource.GENERIC, 10000.0f);
		}
        return super.hitEntity(stack, target, attacker);
    }
	//aoe�˺�
	protected void attackAOE(EntityPlayer player,float range, float damage,boolean type)
	{
		if (player.getEntityWorld().isRemote)
		{
			return;
		}
		AxisAlignedBB aabb = player.getEntityBoundingBox().grow(range);//��Χ
		List<Entity> toAttack = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);//�����б�
		DamageSource src = DamageSource.GENERIC;//�˺�����
		for (Entity entity : toAttack)//ѭ������
		{
			if(type)
			{
				if(entity instanceof EntityLivingBase)
				{
					entity.attackEntityFrom(src, damage);//����ʵ���˺�
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
	}
}
