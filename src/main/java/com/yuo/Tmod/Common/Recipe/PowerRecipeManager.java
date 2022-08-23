package com.yuo.Tmod.Common.Recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
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
                return powerRecipe.getCraftingResult(null);
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
                return new int[]{powerRecipe.getInputUp().getCount(), powerRecipe.getInputDown().getCount()};
            }
        }
        return new int[]{1, 1};
    }

    public static int getRecipeExp(ItemStack output){
        for (PowerRecipe powerRecipe : powerRecipes) {
            if (powerRecipe.hasOutput(output)){
                return powerRecipe.getExp();
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

}
