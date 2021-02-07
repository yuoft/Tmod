package com.yuoMod.Tmod.Entity.Villager;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
//村民交易
public class TradeTmodTo implements ITradeList //用物品换绿宝石
{
	private ItemStack item;
	private int formItem;
	public TradeTmodTo(Item item, int to, int form) {
		this.item=new ItemStack(item,to);
		this.formItem=form;
	}
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		ItemStack itemstack=this.item;
        ItemStack itemstack1=new ItemStack(Items.EMERALD, this.formItem);
        recipeList.add(new MerchantRecipe(itemstack, itemstack1));
	}
	
}
class TradeTmodForm implements ITradeList //用绿宝石+物品换物品
{
	private ItemStack item;
	private ItemStack item2;
	private int formItem;
	public TradeTmodForm(Item item, int to, Item item2, int to2, int form) {
		this.item=new ItemStack(item,to);
		this.item2=new ItemStack(item2,to2);
		this.formItem=form;
	}
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		ItemStack itemstack=this.item;
		ItemStack itemStack2=this.item2;
        ItemStack itemstack1=new ItemStack(Items.EMERALD, this.formItem);
        recipeList.add(new MerchantRecipe(itemstack, itemstack1, itemStack2));
	}
	
}
class TradeTmodItemToItem implements ITradeList //用物品+物品换物品
{
	private ItemStack item1;
	private ItemStack item2;
	private ItemStack item3;
	public TradeTmodItemToItem(Item item1, int to, Item item2, int to2, Item item3, int to3) {
		this.item1=new ItemStack(item1,to);
		this.item2=new ItemStack(item2,to2);
		this.item3=new ItemStack(item3, to3);
	}
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		ItemStack itemstack=this.item1;
		ItemStack itemStack2=this.item2;
        ItemStack itemstack1=this.item3;
        recipeList.add(new MerchantRecipe(itemstack, itemStack2, itemstack1));
	}
	
}
