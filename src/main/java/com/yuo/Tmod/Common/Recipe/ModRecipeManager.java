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

    public static void initRecipes(){
        PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(ItemLoader.emeraldTree)),
                Ingredient.fromStacks(new ItemStack(Items.DIAMOND)), new ItemStack(Items.EMERALD));
        FluidStack fluidStack = FluidRegistry.getFluidStack(FluidLoader.emerald_fluid.getName(), 0);
        if (fluidStack != null) {
            ItemStack bucket = new ItemStack(ForgeModContainer.getInstance().universalBucket);
            NBTTagCompound nbt = new NBTTagCompound();
            fluidStack.writeToNBT(nbt);
            bucket.setTagCompound(nbt);
            PowerRecipeManager.addRecipe(Ingredient.fromStacks(new ItemStack(ItemLoader.emeraldIngotBlock)),
                    Ingredient.fromStacks(new ItemStack(Items.LAVA_BUCKET)), bucket);
        }
    }

}
