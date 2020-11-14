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
//	��һ������amount��ʾ��ʳ�����ܻظ��ļ���ֵ�����ﱻ�趨�ɺ�ƻ����ͬ����4��
//	�ڶ�������saturation��ʾ��ʳ��������ӵ���Ա��Ͷȣ��������ڱ��ͶȺͼ���ֵ�ı�ֵ�������趨Ϊ0.6F��
//	���һ������isWolfFood��ʾ��ʳ���ܷ���ʳ�ã�����򵥵�����Ϊfalse�Ϳ����ˡ�
	public item_food_Bad(String name,int hungerHeal, float saturation, boolean isWolfFood) {
        super(hungerHeal, saturation, isWolfFood);
		this.setUnlocalizedName(name);//item���ػ�����
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
//           ItemFood ��Ĭ��ʵ���У�ֻ������Ҽ�����������ʱ����ܳԶ�����
//           ��������Խ�������ơ�
//           this.setAlwaysEdible();
    }
	@Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        // �������ʳ��֮�󱻵��ã�ԭ���ƻ��������׷�Ӷ���ҩˮЧ��
//		this.setPotionEffect(Potion.absorption.id, 10, 1, 1.0F);
		if (!world.isRemote)
        {
//			��һ��������ʾ��ӦҩˮЧ����id��
//			The second parameter represents the duration of the corresponding liquid medicine effect, in seconds,һ��Ϊ20��gametick
//			������������ʾ��ӦҩˮЧ���ĵȼ��������ԣ�0Ϊһ����1Ϊ������2Ϊ������
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 50, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 100, 0));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 100, 1));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 200, 1));
//            ʳ�ø�ʳ�ﻹ�����Ҵ���ʮ�㾭��
            player.addExperience(-10);
        }
        super.onFoodEaten(stack, world, player);
    }
}
