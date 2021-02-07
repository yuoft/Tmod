package com.yuoMod.Tmod.Entity.Villager;

import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerLoader 
{
	public static VillagerRegistry.VillagerProfession SPACE;
	public static VillagerRegistry.VillagerProfession LUCKY;
	
    public VillagerLoader() {
    	// VillagerRegistry.VillagerProfession �Ĺ������������� ResourceLocation ��Ϊ������
	    // ��һ�� ResourceLocation ��ע��������ΪĳЩԭ�����Ĺ���������� setRegistryName��
	    // �ڶ��� ResourceLocation ָ���������ְҵ�Ĵ������������ָ�� png ��׺��
	    // ������ ResourceLocation ָ���������ְҵ�Ľ�ʬ�������������ָ�� png ��׺��
    	//�ռ�������
    	SPACE = new VillagerProfession(tmod.MODID+":space", "tmod:textures/entity/villager/space.png",
	    		"tmod:textures/entity/villager/zombie_space.png");
    	ForgeRegistries.VILLAGER_PROFESSIONS.register(SPACE);
    	VillagerCareer newCareer = new VillagerCareer(SPACE, tmod.MODID+".space");
    	newCareer.addTrade(3, new EntityVillager.ListEnchantedItemForEmeralds(itemLoader.space_core, new PriceInfo(64, 1)));
    	newCareer.addTrade(2, new TradeTmodTo(itemLoader.emerald_ingot_block, 1, 9));
    	newCareer.addTrade(2, new TradeTmodTo(itemLoader.emerald_ingot_ore, 1, 5));
    	newCareer.addTrade(2, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.GOLD_INGOT,1), new PriceInfo(2, 1)));
    	newCareer.addTrade(1, new TradeTmodForm(Items.DIAMOND, 1, itemLoader.space_patch, 3, 3));
    	newCareer.addTrade(1, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.DIAMOND,1), new PriceInfo(2, 1)));
    	newCareer.addTrade(1, new EntityVillager.EmeraldForItems(itemLoader.emerald_ingot, new PriceInfo(1, 1)));
    	//��������
    	LUCKY = new VillagerProfession(tmod.MODID+":lucky", "tmod:textures/entity/villager/space.png",
	    		"tmod:textures/entity/villager/zombie_space.png");
    	ForgeRegistries.VILLAGER_PROFESSIONS.register(LUCKY);
    	VillagerCareer newLucky = new VillagerCareer(LUCKY, tmod.MODID+".lucky");
    	newLucky.addTrade(1, new EntityVillager.EmeraldForItems(itemLoader.golden_tnt, new PriceInfo(9, 1)));
    	newLucky.addTrade(1, new TradeTmodItemToItem(itemLoader.space_boots, 1, itemLoader.space_block, 4, itemLoader.op_boots, 1));
    	newLucky.addTrade(1, new TradeTmodItemToItem(itemLoader.space_chestplate, 1, itemLoader.space_block, 8, itemLoader.op_chestplate, 1));
    	newLucky.addTrade(1, new TradeTmodItemToItem(itemLoader.space_helmet, 1, itemLoader.space_block, 5, itemLoader.op_helmet, 1));
    	newLucky.addTrade(1, new TradeTmodItemToItem(itemLoader.space_leggings, 1, itemLoader.space_block, 7, itemLoader.op_leggings, 1));
    	newLucky.addTrade(1, new TradeTmodItemToItem(itemLoader.space_leggings, 1, itemLoader.space_block, 9, itemLoader.op_sword, 1));
	}
}
