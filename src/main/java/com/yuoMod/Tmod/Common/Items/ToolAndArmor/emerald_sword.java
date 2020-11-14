package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
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
	public emerald_sword(String name,ToolMaterial toolmaterial) 
	{
		super(toolmaterial);
		this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TMOD);
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		 tooltip.add(I18n.format("tmod.item.emerald_sword1", ""));
	     tooltip.add(I18n.format("tmod.item.emerald_sword2", ""));
    }
	//主动
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(playerIn.getHealth() > 4 && playerIn.experienceLevel >= 15)
		{
			playerIn.experienceLevel-=15;
			playerIn.setFire(60);
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 1200, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 1200, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 1200, 1));
		}
		else playerIn.sendMessage(new TextComponentTranslation("tmod.text.emeraldSword"));
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	//攻击实体(天火被动
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		Random random=new Random();
		if(random.nextInt(100) > 89 && stack.getItem().equals(itemLoader.emerald_sword) && target instanceof IMob)
		{
			target.setFire(3);
		}
        return super.hitEntity(stack, target, attacker);
    }
}
