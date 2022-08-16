package com.yuo.Tmod.Common.Recipe;

import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Fluid.FluidLoader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class ModRecipeManager {

    //加载配方
    public static void initRecipes(){
        FluidStack fluidStack = FluidRegistry.getFluidStack(FluidLoader.emerald_fluid.getName(), 0);
        if (fluidStack != null) {
            ItemStack bucket = FluidUtil.getFilledBucket(fluidStack);
            PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(ItemLoader.emeraldIngotBlock)),
                    Ingredient.fromStacks(new ItemStack(Items.LAVA_BUCKET)), bucket, 200);
        }
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.COAL_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.COAL_ORE, 5), 140);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.DIAMOND_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.DIAMOND_ORE, 2), 200);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.EMERALD_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.EMERALD_ORE, 2), 200);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.GOLD_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.GOLD_ORE, 3), 180);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.IRON_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.IRON_ORE, 3), 180);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.LAPIS_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.LAPIS_ORE, 4), 160);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.QUARTZ_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.QUARTZ_ORE, 5), 140);
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(Blocks.REDSTONE_ORE)),
                Ingredient.fromStacks(new ItemStack(BlockLoader.emeraldTree)), new ItemStack(Blocks.REDSTONE_ORE, 4), 160);
    }

}
