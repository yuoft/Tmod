package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Capability.EventMobLv;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
/**
 * 升级宝石 提示一级
 * @author yuo
 *
 */
public class UpGradeGem extends Item{

	public UpGradeGem(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(16);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItemOffhand(); //获取玩家副手物品
		if(!worldIn.isRemote) {
			if(stack.getTagCompound() != null) {
				int level = stack.getTagCompound().getInteger("level"); // 获取物品等级
				double value = playerIn.getEntityAttribute(EventMobLv.LIVING_LEVEL).getBaseValue();
				if((level + 1) <= value) {
					stack.setTagInfo("level", new NBTTagInt(level + 1)); // 物品等级加一
					playerIn.getHeldItemMainhand().setCount(playerIn.getHeldItemMainhand().getCount() - 1);
					playerIn.sendMessage(new TextComponentTranslation(I18n.format("tmod.text.upgrade_gem1") + (level + 1)));
				}else {
					playerIn.sendMessage(new TextComponentTranslation("tmod.text.upgrade_gem3"));
				}
			}else {
				playerIn.sendMessage(new TextComponentTranslation("tmod.text.upgrade_gem2"));
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
}
