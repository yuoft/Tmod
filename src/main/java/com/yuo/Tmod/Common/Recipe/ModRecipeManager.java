package com.yuo.Tmod.Common.Recipe;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Fluid.FluidLoader;
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
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(ItemLoader.emeraldTree)),
                Ingredient.fromStacks(new ItemStack(Items.DIAMOND)), new ItemStack(Items.EMERALD), 100);
        FluidStack fluidStack = FluidRegistry.getFluidStack(FluidLoader.emerald_fluid.getName(), 0);
        if (fluidStack != null) {
            ItemStack bucket = FluidUtil.getFilledBucket(fluidStack);
            PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(ItemLoader.emeraldIngotBlock)),
                    Ingredient.fromStacks(new ItemStack(Items.LAVA_BUCKET)), bucket, 200);
        }
    }

}
