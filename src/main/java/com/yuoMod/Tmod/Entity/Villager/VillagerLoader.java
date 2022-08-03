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
        // VillagerRegistry.VillagerProfession 的构造器接受三个 ResourceLocation 作为参数：
        // 第一个 ResourceLocation 是注册名。因为某些原因，它的构造器会调用 setRegistryName。
        // 第二个 ResourceLocation 指定了有这个职业的村民的纹理。必须指明 png 后缀。
        // 第三个 ResourceLocation 指定了有这个职业的僵尸村民的纹理。必须指明 png 后缀。
        //空间物资商
        SPACE = new VillagerProfession(Tmod.MOD_ID + ":space", "tmod:textures/entity/villager/space.png",
                "tmod:textures/entity/villager/zombie_space.png");
        ForgeRegistries.VILLAGER_PROFESSIONS.register(SPACE);
        VillagerCareer newCareer = new VillagerCareer(SPACE, Tmod.MOD_ID + ".space");
        newCareer.addTrade(4, new EntityVillager.ListEnchantedItemForEmeralds(ItemLoader.spaceCore, new PriceInfo(64, 1)));
        newCareer.addTrade(3, new TradeTmodTo(ItemLoader.emeraldIngotBlock, 1, 12));
        newCareer.addTrade(2, new TradeTmodTo(ItemLoader.emeraldIngotOre, 1, 4));
        newCareer.addTrade(3, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.GOLD_INGOT, 1), new PriceInfo(1, 4)));
        newCareer.addTrade(2, new TradeTmodForm(Items.DIAMOND, 1, ItemLoader.spacePatch, 3, 3));
        newCareer.addTrade(1, new EntityVillager.ListItemForEmeralds(new ItemStack(Items.DIAMOND, 1), new PriceInfo(2, 1)));
        newCareer.addTrade(1, new EntityVillager.EmeraldForItems(ItemLoader.emeraldIngot, new PriceInfo(1, 3)));
        //幸运商人
        LUCKY = new VillagerProfession(Tmod.MOD_ID + ":lucky", "tmod:textures/entity/villager/space.png",
                "tmod:textures/entity/villager/zombie_space.png");
        ForgeRegistries.VILLAGER_PROFESSIONS.register(LUCKY);
        VillagerCareer newLucky = new VillagerCareer(LUCKY, Tmod.MOD_ID + ".lucky");
        newLucky.addTrade(1, new EntityVillager.EmeraldForItems(ItemLoader.goldenTnt, new PriceInfo(9, 1)));
        newLucky.addTrade(1, new TradeTmodItemToItem(ItemLoader.spaceBoots, 1, ItemLoader.spaceBlock, 4, ItemLoader.opBoots, 1));
        newLucky.addTrade(2, new TradeTmodItemToItem(ItemLoader.spaceChest, 1, ItemLoader.spaceBlock, 8, ItemLoader.opChest, 1));
        newLucky.addTrade(1, new TradeTmodItemToItem(ItemLoader.spaceHelmet, 1, ItemLoader.spaceBlock, 5, ItemLoader.opHelmet, 1));
        newLucky.addTrade(2, new TradeTmodItemToItem(ItemLoader.spaceLeggings, 1, ItemLoader.spaceBlock, 7, ItemLoader.opLegs, 1));
        newLucky.addTrade(3, new TradeTmodItemToItem(ItemLoader.spaceSword, 1, ItemLoader.spaceBlock, 9, ItemLoader.opSword, 1));
        newLucky.addTrade(3, new TradeTmodItemToItem(ItemLoader.spacePickaxe, 1, ItemLoader.spaceBlock, 9, ItemLoader.opPickaxe, 1));
    }
}
