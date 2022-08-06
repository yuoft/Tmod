package com.yuo.Tmod.Client;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Potion.PotionLoader;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("deprecation")
public class CraftingLoader {
    public CraftingLoader() {
//		registerRecipe();
        registerSmelting();
        registerBrewing();
        registerFuel();
    }

    //	//合成表
//	private static void registerRecipe()
//	{
//		GameRegistry.addShapedRecipe(null,null,new ItemStack(itemLoader.emerald_pickaxe), new Object[]
//		{
//			    "###", " * ", " * ", '#', itemLoader.emerald_ingot, '*', Items.DIAMOND
//		});
//	}
    //熔炉配方
    private static void registerSmelting() {
        //Input item ID, output item ID, get experience value
//	    GameRegistry.addSmelting(itemLoader.emerald_powder,new ItemStack(itemLoader.emerald_ingot), 20.0f);
//	    GameRegistry.addSmelting(Items.EMERALD,new ItemStack(itemLoader.emerald_ingot), 20.0f);
        GameRegistry.addSmelting(ItemLoader.badApple, new ItemStack(Items.APPLE), 0.3f);
        GameRegistry.addSmelting(ItemLoader.saltMeat, new ItemStack(ItemLoader.cookingSaltMeat), 0.8f);
        GameRegistry.addSmelting(ItemLoader.complexPowder, new ItemStack(ItemLoader.emeraldIngot), 1.0f);
        GameRegistry.addSmelting(BlockLoader.ancientDebris, new ItemStack(ItemLoader.netheriteScrap), 2.0f);
    }

    //添加燃料
    private static void registerFuel() {
        GameRegistry.registerFuelHandler(fuel -> {
            return ItemLoader.emeraldPowder != fuel.getItem() ? 0 : 6400;//燃烧时间
        });
    }

    //酿造台配方
    private static void registerBrewing() {

        ItemStack potionInput = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK); //粗制药水
        ItemStack potionOutput = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionLoader.potionFall_Type);
        ItemStack potionOutputSplash = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionLoader.potionFall_Type);
        ItemStack potionOutputLingerIng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionLoader.potionFall_Type);
        ItemStack potionOutputLong = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionLoader.potionFall_TypeLong);
        ItemStack potionOutputLongSplash = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionLoader.potionFall_TypeLong);
        ItemStack potionOutputLongLingering = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionLoader.potionFall_TypeLong);
        ItemStack potionOutputStrong = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionLoader.potionFall_TypeStrong);
        ItemStack potionOutputStrongSplash = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionLoader.potionFall_TypeStrong);
        ItemStack potionOutputStrongLingerIng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionLoader.potionFall_TypeStrong);
        // 第一个参数是“药水”，也就是三个药水格需要放的东西。第二个参数是“材料”，也就是地狱疣那一格需要放的物品。
        // 第三个参数是输出。
        BrewingRecipeRegistry.addRecipe(potionInput, new ItemStack(Items.SHULKER_SHELL), potionOutput);
        BrewingRecipeRegistry.addRecipe(potionOutput, new ItemStack(Items.GUNPOWDER), potionOutputSplash);
        BrewingRecipeRegistry.addRecipe(potionOutputSplash, new ItemStack(Items.DRAGON_BREATH), potionOutputLingerIng);
        BrewingRecipeRegistry.addRecipe(potionOutput, new ItemStack(Items.GLOWSTONE_DUST), potionOutputLong);
        BrewingRecipeRegistry.addRecipe(potionOutputLong, new ItemStack(Items.GUNPOWDER), potionOutputLongSplash);
        BrewingRecipeRegistry.addRecipe(potionOutputLongSplash, new ItemStack(Items.DRAGON_BREATH), potionOutputLongLingering);
        BrewingRecipeRegistry.addRecipe(potionOutput, new ItemStack(Items.REDSTONE), potionOutputStrong);
        BrewingRecipeRegistry.addRecipe(potionOutputStrong, new ItemStack(Items.GUNPOWDER), potionOutputStrongSplash);
        BrewingRecipeRegistry.addRecipe(potionOutputStrongSplash, new ItemStack(Items.DRAGON_BREATH), potionOutputStrongLingerIng);

        // 另一个重载的版本，其中第二个参数是一个有效的矿物词典名。
//		BrewingRecipeRegistry.addRecipe(new ItemStack(Blocks.DIRT), "cobblestone", new ItemStack(Items.DIAMOND));
    }
}
