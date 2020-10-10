package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Entity.EntityGoldenTNT;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GoldenTNT extends Item
{
	public GoldenTNT(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(16);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemStack=playerIn.getHeldItem(handIn);
		if (!playerIn.capabilities.isCreativeMode) {
			itemStack.setCount(itemStack.getCount()-1);
		}
		if (!worldIn.isRemote) {
			EntityGoldenTNT egg=new EntityGoldenTNT(worldIn, playerIn);
//			egg.shoot(playerIn.posX, playerIn.posY, playerIn.posZ, 3.0f, 0f);
			//ȷ��Ͷ����ĳ��ٶȷ���rotationYaw:ʵ��Χ��Y����ת�̶ȣ�rotationPitch��ʵ��Χ��X����ת�ĳ̶�;render:��Ⱦƫ��
			egg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead,0f, 2.0f, 0.1f);
			worldIn.spawnEntity(egg);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
}
