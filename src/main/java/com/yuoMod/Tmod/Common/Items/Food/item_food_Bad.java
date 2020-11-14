package com.yuoMod.Tmod.Common.Items.Food;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class item_food_Bad extends ItemFood
{
//	第一个参数amount表示该食物所能回复的饥饿值，这里被设定成和苹果相同，即4。
//	第二个参数saturation表示该食物所能添加的相对饱和度，其正比于饱和度和饥饿值的比值，这里设定为0.6F。
//	最后一个参数isWolfFood表示该食物能否被狼食用，这里简单地设置为false就可以了。
	public item_food_Bad(String name,int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
		this.setUnlocalizedName(name);//item本地化名称
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
//           ItemFood 的默认实现中，只有在玩家饥饿条不满的时候才能吃东西。
//           用这个可以解除此限制。
//           this.setAlwaysEdible();
    }
	@Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        // 会在玩家食用之后被调用，原版金苹果在这里追加多种药水效果
//		this.setPotionEffect(Potion.absorption.id, 10, 1, 1.0F);
		if (!world.isRemote)
        {
//			第一个参数表示对应药水效果的id。
//			The second parameter represents the duration of the corresponding liquid medicine effect, in seconds,一秒为20个gametick
//			第三个参数表示对应药水效果的等级，很明显，0为一级，1为二级，2为三级。
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 50, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 100, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 100, 1));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 200, 1));
//            食用该食物还会给玩家带来十点经验
            player.addExperience(-10);
        }
        super.onFoodEaten(stack, world, player);
    }
}
