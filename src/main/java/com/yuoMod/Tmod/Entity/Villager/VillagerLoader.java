package com.yuoMod.Tmod.Entity.Villager;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Common.Items.ItemLoader;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerLoader {
    public static VillagerRegistry.VillagerProfession SPACE;
    public static VillagerRegistry.VillagerProfession LUCKY;

    public VillagerLoader() {
        // VillagerRegistry.VillagerProfession �Ĺ������������� ResourceLocation ��Ϊ������
        // ��һ�� ResourceLocation ��ע��������ΪĳЩԭ�����Ĺ���������� setRegistryName��
        // �ڶ��� ResourceLocation ָ���������ְҵ�Ĵ������������ָ�� png ��׺��
        // ������ ResourceLocation ָ���������ְҵ�Ľ�ʬ�������������ָ�� png ��׺��
        //�ռ�������
        SPACE = new VillagerProfession(Tmod.MOD_ID + ":space", "tmod:textures/entity/villager/space.png",
                "tmod:textures/entity/villager/zombie_space.png");
        ForgeRegistries.VILLAGER_PROFESSIONS.register(SPACE);
        VillagerCareer newCareer = new VillagerCareer(SPACE, Tmod.MOD_ID + ".space");
        newCareer.addTrade(4, new EntityVillager.ListEnchantedItemForEmeralds(ItemLoader.space_core, new PriceInfo(64, 1)));
        newCareer.addTrade(3, new TradeTmodTo(ItemLoader.emerald_ingot_block, 1, 12));
        newCareer.addTrade(2, new TradeTmodTo(ItemLoader.emerald_ingot_ore, 1, 4));
        newCareer.addTrade(3, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.GOLD_INGOT, 1), new PriceInfo(1, 4)));
        newCareer.addTrade(2, new TradeTmodForm(Items.DIAMOND, 1, ItemLoader.space_patch, 3, 3));
        newCareer.addTrade(1, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.DIAMOND, 1), new PriceInfo(2, 1)));
        newCareer.addTrade(1, new EntityVillager.EmeraldForItems(ItemLoader.emerald_ingot, new PriceInfo(1, 3)));
        //��������
        LUCKY = new VillagerProfession(Tmod.MOD_ID + ":lucky", "tmod:textures/entity/villager/space.png",
                "tmod:textures/entity/villager/zombie_space.png");
        ForgeRegistries.VILLAGER_PROFESSIONS.register(LUCKY);
        VillagerCareer newLucky = new VillagerCareer(LUCKY, Tmod.MOD_ID + ".lucky");
        newLucky.addTrade(1, new EntityVillager.EmeraldForItems(ItemLoader.golden_tnt, new PriceInfo(9, 1)));
        newLucky.addTrade(1, new TradeTmodItemToItem(ItemLoader.space_boots, 1, ItemLoader.space_block, 4, ItemLoader.op_boots, 1));
        newLucky.addTrade(2, new TradeTmodItemToItem(ItemLoader.space_chestplate, 1, ItemLoader.space_block, 8, ItemLoader.op_chestplate, 1));
        newLucky.addTrade(1, new TradeTmodItemToItem(ItemLoader.space_helmet, 1, ItemLoader.space_block, 5, ItemLoader.op_helmet, 1));
        newLucky.addTrade(2, new TradeTmodItemToItem(ItemLoader.space_leggings, 1, ItemLoader.space_block, 7, ItemLoader.op_leggings, 1));
        newLucky.addTrade(3, new TradeTmodItemToItem(ItemLoader.space_sword, 1, ItemLoader.space_block, 9, ItemLoader.op_sword, 1));
        newLucky.addTrade(3, new TradeTmodItemToItem(ItemLoader.space_pickaxe, 1, ItemLoader.space_block, 9, ItemLoader.op_pickaxe, 1));
    }
}
