package com.yuo.Tmod.Common.Recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

//配方
public class PowerRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
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

    public int getTime() {
        return time;
    }

    public int getExp() {
        return exp;
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        ItemStack stackUp = inv.getStackInSlot(0);
        ItemStack stackDown = inv.getStackInSlot(1);
        return inputUp.isItemEqual(stackUp) && inputDown.isItemEqual(stackDown);
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
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