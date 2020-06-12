package com.yuoMod.Tmod.Common.Crafting;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("deprecation")
public class CraftingLoader 
{
	public CraftingLoader()
    {
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
	private static void registerSmelting()
	{
		//Input item ID, output item ID, get experience value
	    GameRegistry.addSmelting(itemLoader.emerald_powder,new ItemStack(itemLoader.emerald_ingot), 100.0f);
	    GameRegistry.addSmelting(itemLoader.bad_apple,new ItemStack(Items.APPLE), 1000.0f);
	}
	//添加燃料
	private static void registerFuel()
	{
		GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
                return itemLoader.emerald_powder != fuel.getItem() ? 0 : 128000;//燃烧时间
            }
        });
	}
	//酿造台配方
	private static void registerBrewing()
	{
		// 第一个参数是“药水”，也就是三个药水格需要放的东西。第二个参数是“材料”，也就是地狱疣那一格需要放的物品。
		// 第三个参数是输出。
//		BrewingRecipeRegistry.addRecipe(new ItemStack(Items.POTIONITEM,1,1), new ItemStack(itemLoader.emerald_powder), new ItemStack(Items.POTIONITEM.setRegistryName(tmod.MODID, "potionFall_type"),1));

		// 另一个重载的版本，其中第二个参数是一个有效的矿物词典名。
//		BrewingRecipeRegistry.addRecipe(new ItemStack(Blocks.DIRT), "cobblestone", new ItemStack(Items.DIAMOND));
	}
}
