package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpaceArmor extends ItemArmor
{
	public SpaceArmor(String name,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);//创造模式物品栏
	}
	// 穿在身上的时候的每时每刻都会调用的方法，可以用来追加药水效果什么的
	//套装效果
	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
		ItemStack stackHead=player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack stackChest=player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack stackLegs=player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack stackFeet=player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(stackHead !=null && stackChest !=null && stackLegs !=null && stackFeet !=null
			&& stackHead.getItem().equals(itemLoader.space_helmet) && stackChest.getItem().equals(itemLoader.space_chestplate)
				&& stackLegs.getItem().equals(itemLoader.space_leggings) && stackFeet.getItem().equals(itemLoader.space_boots))
		{
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 100000, 1));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 100000, 0));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 100000, 0));
		}
		else
		{
			player.removeActivePotionEffect(Potion.getPotionById(11));
			player.removeActivePotionEffect(Potion.getPotionById(8));
			player.removeActivePotionEffect(Potion.getPotionById(5));
		}
    }
}
