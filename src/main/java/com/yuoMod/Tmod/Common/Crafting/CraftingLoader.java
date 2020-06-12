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
//	//�ϳɱ�
//	private static void registerRecipe()
//	{
//		GameRegistry.addShapedRecipe(null,null,new ItemStack(itemLoader.emerald_pickaxe), new Object[]
//		{
//			    "###", " * ", " * ", '#', itemLoader.emerald_ingot, '*', Items.DIAMOND
//		});
//	}
	//��¯�䷽
	private static void registerSmelting()
	{
		//Input item ID, output item ID, get experience value
	    GameRegistry.addSmelting(itemLoader.emerald_powder,new ItemStack(itemLoader.emerald_ingot), 100.0f);
	    GameRegistry.addSmelting(itemLoader.bad_apple,new ItemStack(Items.APPLE), 1000.0f);
	}
	//���ȼ��
	private static void registerFuel()
	{
		GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
                return itemLoader.emerald_powder != fuel.getItem() ? 0 : 128000;//ȼ��ʱ��
            }
        });
	}
	//����̨�䷽
	private static void registerBrewing()
	{
		// ��һ�������ǡ�ҩˮ����Ҳ��������ҩˮ����Ҫ�ŵĶ������ڶ��������ǡ����ϡ���Ҳ���ǵ�������һ����Ҫ�ŵ���Ʒ��
		// �����������������
//		BrewingRecipeRegistry.addRecipe(new ItemStack(Items.POTIONITEM,1,1), new ItemStack(itemLoader.emerald_powder), new ItemStack(Items.POTIONITEM.setRegistryName(tmod.MODID, "potionFall_type"),1));

		// ��һ�����صİ汾�����еڶ���������һ����Ч�Ŀ���ʵ�����
//		BrewingRecipeRegistry.addRecipe(new ItemStack(Blocks.DIRT), "cobblestone", new ItemStack(Items.DIAMOND));
	}
}
