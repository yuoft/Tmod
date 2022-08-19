package com.yuo.Tmod.Common.Recipe;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tmod;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class PowerRecipeCategory implements IRecipeCategory<PowerRecipeWrapper> {
    public static final String UID = "tmod.power";
    //合成配方背景
    public static final ResourceLocation TEXTURE = new ResourceLocation(Tmod.MOD_ID, "textures/gui/power_extractor.png");

    private final IDrawable background;
    private final IDrawable icon;
    public PowerRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 50,15,92,60); //绘制背景
        this.icon = helper.createDrawableIngredient(new ItemStack(ItemLoader.powerExtractor)); //绘制合成方块
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return ItemLoader.powerExtractor.getUnlocalizedName();
    }

    @Override
    public String getModName() {
        return Tmod.MOD_ID;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, PowerRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 3, 5);
        recipeLayout.getItemStacks().init(1, true, 3, 25);
        recipeLayout.getItemStacks().init(2, false, 63, 15);
        recipeLayout.getItemStacks().set(ingredients);
    }

}
