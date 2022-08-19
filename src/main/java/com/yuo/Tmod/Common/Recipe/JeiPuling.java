package com.yuo.Tmod.Common.Recipe;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Gui.ContainerDemo;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPuling implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new PowerRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(PowerRecipeManager.getPowerRecipes(), PowerRecipeCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ItemLoader.powerExtractor), PowerRecipeCategory.UID);
        registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerDemo.class, PowerRecipeCategory.UID, 0, 2, 3,36);
    }
}
