package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class emerald_sword extends ItemSword
{
	protected emerald_sword(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
       tooltip.add(I18n.format("tmod.item.emerald_sword", ""));
    }
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(playerIn.getHealth() > 4 && playerIn.experienceLevel >= 10)
		{
//			playerIn.setHealth(4.0f);
			playerIn.experienceLevel-=10;
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 1200, 2));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 1200, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 1200, 1));
		}
		else playerIn.sendMessage(new TextComponentTranslation("tmod.text.key_event1"));
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
}
