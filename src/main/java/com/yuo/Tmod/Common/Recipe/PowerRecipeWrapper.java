package com.yuo.Tmod.Common.Recipe;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PowerRecipeWrapper implements IRecipeWrapper {
    private final List<ItemStack> input = new ArrayList<>();
    private final ItemStack output;

    public PowerRecipeWrapper(PowerRecipe powerRecipe) {
        output = powerRecipe.getRecipeOutput();
        input.add(powerRecipe.getInputUp());
        input.add(powerRecipe.getInputDown());
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, input);
        ingredients.setOutput(VanillaTypes.ITEM, output);
    }

}
