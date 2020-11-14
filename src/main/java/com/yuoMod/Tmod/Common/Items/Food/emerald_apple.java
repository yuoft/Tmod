package com.yuoMod.Tmod.Common.Items.Food;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class emerald_apple extends ItemFood 
{  
	public emerald_apple(String name,int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
		this.setAlwaysEdible();
        this.setHasSubtypes(true);
    }
	//是否有附魔效果
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) 
	{
		return super.hasEffect(stack) || stack.getMetadata() > 0;
	}
	public EnumRarity getRarity(ItemStack stack) 
	{
		return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
	}
	@Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote)
        {
			if(stack.getMetadata() > 0)
			{
				player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 4000, 2));
	            player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 4000, 2));
	            player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 2000, 1));
	            player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 2000, 1));
			}
			else
			{
				player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 2000, 1));
	            player.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 2000, 1));
			}
        }
        super.onFoodEaten(stack, world, player);
    }
	//返回id相同但是meta不同的项的列表
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));
            items.add(new ItemStack(this, 1, 1));
        }
    }
}
