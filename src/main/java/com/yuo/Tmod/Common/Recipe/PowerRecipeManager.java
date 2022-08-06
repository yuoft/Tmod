package com.yuo.Tmod.Common.Recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class PowerRecipeManager {
    private static final List<PowerRecipe> powerRecipes = new ArrayList<>();

    /**
     * 添加配方
     * @param inputUp
     * @param inputDown
     * @param output
     */
    public static void addRecipe(Ingredient inputUp, Ingredient inputDown, ItemStack output){
        PowerRecipe recipe = new PowerRecipe(output.getItem().getRegistryName(), inputUp, inputDown, output);
        powerRecipes.add(recipe);
    }

    /**
     * 获取配方输出
     * @param inputUp
     * @param inputDown
     * @return
     */
    public static ItemStack getRecipe(ItemStack inputUp, ItemStack inputDown){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.matches(inputUp, inputDown))
                return powerRecipe.getCraftingResult();
        }

        return ItemStack.EMPTY;
    }

    public static List<PowerRecipe> getPowerRecipes(){
        return powerRecipes;
    }


    //配方
    static class PowerRecipe {
        public final Ingredient inputUp; //输入 上
        public final Ingredient inputDown; //输入 下
        private final ItemStack recipeOutput; // 输出
        private final ResourceLocation id; //配方id

        public PowerRecipe(ResourceLocation idIn, Ingredient inputUp, Ingredient inputDown, ItemStack recipeOutput) {
            this.inputUp = inputUp;
            this.inputDown = inputDown;
            this.recipeOutput = recipeOutput;
            this.id = idIn;
        }

        //配方是否相同 判断id
        public boolean isEquals(PowerRecipe recipe){
            return this.id.compareTo(recipe.id) == 0;
        }

        //匹配配方
        public boolean matches(IInventory inv) {
            ItemStack stackUp = inv.getStackInSlot(0);
            ItemStack stackDown = inv.getStackInSlot(1);
            return inputUp.test(stackUp) && inputDown.test(stackDown);
        }

        //根据物品判断是否有配方
        public boolean matches(ItemStack stack, ItemStack itemStack){
            return inputUp.test(stack) && inputDown.test(itemStack);
        }

        //配方返回
        public ItemStack getCraftingResult() {
            return this.recipeOutput.copy();
        }

        //配方输出
        public ItemStack getRecipeOutput() {
            return this.recipeOutput;
        }
    }
}
