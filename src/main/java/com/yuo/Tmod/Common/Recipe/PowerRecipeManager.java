package com.yuo.Tmod.Common.Recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public class PowerRecipeManager {
    private static final List<PowerRecipe> powerRecipes = new ArrayList<>();

    /**
     * 添加配方
     * @param inputUp 输入上
     * @param inputDown 输入下
     * @param output 输出
     */
    public static void addRecipe(ItemStack inputUp, ItemStack inputDown, ItemStack output, int time, int exp){
        PowerRecipe recipe = new PowerRecipe(output.getItem().getRegistryName(), inputUp, inputDown, output, time, exp);
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
     * 获取配方的消耗
     * @param output 输出
     * @return 消耗
     */
    public static int[] getRecipeShrink(ItemStack output){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.hasOutput(output)){
                return new int[]{powerRecipe.inputUp.getCount(), powerRecipe.inputDown.getCount()};
            }
        }
        return new int[]{1, 1};
    }

    public static int getRecipeExp(ItemStack output){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.hasOutput(output)){
                return powerRecipe.exp;
            }
        }

        return 0;
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
        private final ItemStack inputUp; //输入 上
        private final ItemStack inputDown; //输入 下
        private final ItemStack recipeOutput; // 输出
        private final int exp; //消耗
        private final int time; //配方时间
        private final ResourceLocation id; //配方id

        public PowerRecipe(ResourceLocation idIn, ItemStack inputUp, ItemStack inputDown, ItemStack recipeOutput, int timeIn, int expLv) {
            this.inputUp = inputUp;
            this.inputDown = inputDown;
            this.recipeOutput = recipeOutput;
            this.exp = expLv;
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
            return inputUp.isItemEqual(stackUp) && inputDown.isItemEqual(stackDown);
        }

        //是否在配方中
        public boolean hasStack(ItemStack stack, boolean flag){
            return flag ? inputUp.isItemEqual(stack) : inputDown.isItemEqual(stack);
        }

        //输出是否相同
        public boolean hasOutput(ItemStack stack){
            return recipeOutput.isItemEqual(stack);
        }

        //根据物品判断是否有配方
        public boolean matches(ItemStack stack, ItemStack itemStack){
            return inputUp.isItemEqual(stack) && inputDown.isItemEqual(itemStack)
                    && stack.getCount() >= inputUp.getCount() && itemStack.getCount() >= inputDown.getCount();
        }

        //配方返回
        public ItemStack getCraftingResult() {
            return this.recipeOutput.copy();
        }

        public int getTime() {
            return time;
        }

        public int getExp() {
            return exp;
        }

        //配方输出
        public ItemStack getRecipeOutput() {
            return this.recipeOutput;
        }

        public ItemStack getInputUp() {
            return inputUp;
        }

        public ItemStack getInputDown() {
            return inputDown;
        }
    }
}
