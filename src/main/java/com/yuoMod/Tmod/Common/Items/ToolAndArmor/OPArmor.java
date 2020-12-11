package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class OPArmor extends ItemArmor implements ISpecialArmor
{
	/*
	 * name参数与该ArmorMaterial的材质所在位置有关。
maxDamage参数和该ArmorMaterial对应的盔甲的耐久成正比。
renderIndexIn参数的四个元素表示对应盔甲的头盔、胸甲、护腿、和靴子抵御伤害的能力，如皮甲分别为1，3，2，1，和为7，钻石甲分别为3，8，6，3，和为20，请不要让四个元素值的和超过这个值。
equipmentSlotIn参数和ToolMaterial一样，和对应盔甲的附魔能力正相关，同样，金盔甲的附魔能力最高。
	 */
	public OPArmor(String name,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
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
			&& stackHead.getItem().equals(itemLoader.op_helmet) && stackChest.getItem().equals(itemLoader.op_chestplate)
				&& stackLegs.getItem().equals(itemLoader.op_leggings) && stackFeet.getItem().equals(itemLoader.op_boots))
		{
			if(player.isBurning())
	             player.extinguish();//着火时熄灭
			 player.capabilities.allowFlying = true;//飞行
//			 player.capabilities.setFlySpeed(0.1f);
//			 player.capabilities.setPlayerWalkSpeed(0.3f);
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 100000, 0));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 100000, 0));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 100000, 10));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 100000, 2));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 100000, 2));
			 player.attackEntityAsMob(player);
		}
		else
		{
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;//取消飞行
//			player.capabilities.setFlySpeed(0.01f);
//			player.clearActivePotions();
			player.removeActivePotionEffect(Potion.getPotionById(16));
			player.removeActivePotionEffect(Potion.getPotionById(26));
			player.removeActivePotionEffect(Potion.getPotionById(11));
			player.removeActivePotionEffect(Potion.getPotionById(8));
			player.removeActivePotionEffect(Potion.getPotionById(1));
		}
    }
	//护甲属性(低于1000的伤害被免疫)
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
			int slot) {
		return new ArmorProperties(0, 1.0, 1000);//优先级，伤害吸收比，吸收上限
	}
	//获取护甲值
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}
	//护甲耐久消耗
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		
	}
}
