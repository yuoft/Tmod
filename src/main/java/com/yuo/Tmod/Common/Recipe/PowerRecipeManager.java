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
     * @param inputUp 输入上
     * @param inputDown 输入下
     * @param output 输出
     */
    public static void addRecipe(Ingredient inputUp, Ingredient inputDown, ItemStack output, int time){
        PowerRecipe recipe = new PowerRecipe(output.getItem().getRegistryName(), inputUp, inputDown, output, time);
        powerRecipes.add(recipe);
    }

    /**
     * 获取配方输出
     * @param inputUp 输入1
     * @param inputDown 输入2
     * @return 输出
     */
    public static ItemStack getRecipe(ItemStack inputUp, ItemStack inputDown){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.matches(inputUp, inputDown))
                return powerRecipe.getCraftingResult();
        }

        return ItemStack.EMPTY;
    }

    /**
     * 获取时间
     * @param inputUp 输入1
     * @param inputDown 输入2
     * @return 输出
     */
    public static int getTime(ItemStack inputUp, ItemStack inputDown){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.matches(inputUp, inputDown))
                return powerRecipe.getTime();
        }

        return 160;
    }

    /**
     * 判断物品是否在配方中
     * @param stack 物品
     * @return 是 true
     */
    public static boolean hasStack(ItemStack stack, boolean flag){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.hasStack(stack, flag)) return true;
        }
        return false;
    }

    public static List<PowerRecipe> getPowerRecipes(){
        return powerRecipes;
    }


    //配方
    static class PowerRecipe {
        private final Ingredient inputUp; //输入 上
        private final Ingredient inputDown; //输入 下
        private final ItemStack recipeOutput; // 输出
        private final int time; //配方时间
        private final ResourceLocation id; //配方id

        public PowerRecipe(ResourceLocation idIn, Ingredient inputUp, Ingredient inputDown, ItemStack recipeOutput, int timeIn) {
            this.inputUp = inputUp;
            this.inputDown = inputDown;
            this.recipeOutput = recipeOutput;
            this.time = timeIn;
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

        //是否在配方中
        public boolean hasStack(ItemStack stack, boolean flag){
            return flag ? inputUp.test(stack) : inputDown.test(stack);
        }

        //根据物品判断是否有配方
        public boolean matches(ItemStack stack, ItemStack itemStack){
            return inputUp.test(stack) && inputDown.test(itemStack);
        }

        //配方返回
        public ItemStack getCraftingResult() {
            return this.recipeOutput.copy();
        }

        public int getTime() {
            return time;
        }

        //配方输出
        public ItemStack getRecipeOutput() {
            return this.recipeOutput;
        }
    }
}
