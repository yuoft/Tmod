package com.yuoMod.Tmod.Common.Crop;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.item.Item;

public class emerald_crop_seeds extends Item
{
	public emerald_crop_seeds(String name) 
	{
		// TODO �Զ����ɵĹ��캯�����
		super();
		this.setUnlocalizedName(name);//�̱�ʯ��������
		this.setCreativeTab(CreativeTabsLoader.TMOD);
		this.setMaxStackSize(64);
	}
}
