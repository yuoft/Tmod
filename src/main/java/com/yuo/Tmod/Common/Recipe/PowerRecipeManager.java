package com.yuo.Tmod.Common.Recipe;

import com.yuo.Tmod.TileEntity.PowerTileEntity;
import com.yuo.Tmod.Tmod;
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
import java.util.Objects;

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
            if (powerRecipe.inputUp.test(inputUp) && powerRecipe.inputDown.test(inputDown))
                return powerRecipe.recipeOutput;
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

        //配方返回
        public ItemStack getCraftingResult(InventoryCrafting inv) {
            return this.recipeOutput.copy();
        }

        //配方输出
        public ItemStack getRecipeOutput() {
            return this.recipeOutput;
        }
    }
}
