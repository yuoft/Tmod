package com.yuoMod.Tmod.Client;

import com.yuoMod.Tmod.Common.Blocks.BlockLoader;
import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Potion.PotionLoader;
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

    //	//�ϳɱ�
//	private static void registerRecipe()
//	{
//		GameRegistry.addShapedRecipe(null,null,new ItemStack(itemLoader.emerald_pickaxe), new Object[]
//		{
//			    "###", " * ", " * ", '#', itemLoader.emerald_ingot, '*', Items.DIAMOND
//		});
//	}
    //��¯�䷽
    private static void registerSmelting() {
        //Input item ID, output item ID, get experience value
//	    GameRegistry.addSmelting(itemLoader.emerald_powder,new ItemStack(itemLoader.emerald_ingot), 20.0f);
//	    GameRegistry.addSmelting(Items.EMERALD,new ItemStack(itemLoader.emerald_ingot), 20.0f);
        GameRegistry.addSmelting(ItemLoader.badApple, new ItemStack(Items.APPLE), 0.3f);
        GameRegistry.addSmelting(ItemLoader.saltMeat, new ItemStack(ItemLoader.cookingSaltMeat), 0.8f);
        GameRegistry.addSmelting(BlockLoader.saltOre, new ItemStack(ItemLoader.saltWash), 0.5f);
        GameRegistry.addSmelting(ItemLoader.complexPowder, new ItemStack(ItemLoader.emeraldIngot), 1.0f);
        GameRegistry.addSmelting(BlockLoader.ancientDebris, new ItemStack(ItemLoader.netheriteScrap), 2.0f);
    }

    //���ȼ��
    private static void registerFuel() {
        GameRegistry.registerFuelHandler(fuel -> {
            return ItemLoader.emeraldPowder != fuel.getItem() ? 0 : 6400;//ȼ��ʱ��
        });
    }

    //����̨�䷽
    private static void registerBrewing() {

        ItemStack potionInput = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK); //����ҩˮ
        ItemStack potionOutput = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionLoader.potionFall_Type);
        ItemStack potionOutputSplash = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionLoader.potionFall_Type);
        ItemStack potionOutputLingerIng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionLoader.potionFall_Type);
        ItemStack potionOutputLong = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionLoader.potionFall_TypeLong);
        ItemStack potionOutputLongSplash = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionLoader.potionFall_TypeLong);
        ItemStack potionOutputLongLingering = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionLoader.potionFall_TypeLong);
        ItemStack potionOutputStrong = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionLoader.potionFall_TypeStrong);
        ItemStack potionOutputStrongSplash = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionLoader.potionFall_TypeStrong);
        ItemStack potionOutputStrongLingerIng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionLoader.potionFall_TypeStrong);
        // ��һ�������ǡ�ҩˮ����Ҳ��������ҩˮ����Ҫ�ŵĶ������ڶ��������ǡ����ϡ���Ҳ���ǵ�������һ����Ҫ�ŵ���Ʒ��
        // �����������������
        BrewingRecipeRegistry.addRecipe(potionInput, new ItemStack(Items.SHULKER_SHELL), potionOutput);
        BrewingRecipeRegistry.addRecipe(potionOutput, new ItemStack(Items.GUNPOWDER), potionOutputSplash);
        BrewingRecipeRegistry.addRecipe(potionOutputSplash, new ItemStack(Items.DRAGON_BREATH), potionOutputLingerIng);
        BrewingRecipeRegistry.addRecipe(potionOutput, new ItemStack(Items.GLOWSTONE_DUST), potionOutputLong);
        BrewingRecipeRegistry.addRecipe(potionOutputLong, new ItemStack(Items.GUNPOWDER), potionOutputLongSplash);
        BrewingRecipeRegistry.addRecipe(potionOutputLongSplash, new ItemStack(Items.DRAGON_BREATH), potionOutputLongLingering);
        BrewingRecipeRegistry.addRecipe(potionOutput, new ItemStack(Items.REDSTONE), potionOutputStrong);
        BrewingRecipeRegistry.addRecipe(potionOutputStrong, new ItemStack(Items.GUNPOWDER), potionOutputStrongSplash);
        BrewingRecipeRegistry.addRecipe(potionOutputStrongSplash, new ItemStack(Items.DRAGON_BREATH), potionOutputStrongLingerIng);

        // ��һ�����صİ汾�����еڶ���������һ����Ч�Ŀ���ʵ�����
//		BrewingRecipeRegistry.addRecipe(new ItemStack(Blocks.DIRT), "cobblestone", new ItemStack(Items.DIAMOND));
    }
}
