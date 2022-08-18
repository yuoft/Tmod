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
            PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.emeraldIngotBlock), new ItemStack(Items.LAVA_BUCKET), bucket, 160, 0);
        }
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.COAL_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.COAL_ORE, 5), 100, 0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.DIAMOND_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.DIAMOND_ORE, 2), 160, 0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.EMERALD_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.EMERALD_ORE, 2), 160,0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.GOLD_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.GOLD_ORE, 3), 140,0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.IRON_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.IRON_ORE, 3), 140,0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.LAPIS_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.LAPIS_ORE, 4), 120,0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.QUARTZ_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.QUARTZ_ORE, 5), 100,0);
        PowerRecipeManager.addRecipe(new ItemStack(Blocks.REDSTONE_ORE), new ItemStack(BlockLoader.emeraldTree),
                new ItemStack(Blocks.REDSTONE_ORE, 4), 120,0);

        PowerRecipeManager.addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT),
                new ItemStack(ItemLoader.ironStick), 60, 0);
        PowerRecipeManager.addRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.GOLD_INGOT),
                new ItemStack(ItemLoader.goldStick), 60, 0);
        PowerRecipeManager.addRecipe(new ItemStack(Items.DIAMOND), new ItemStack(Items.DIAMOND),
                new ItemStack(ItemLoader.diamondStick), 60, 0);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.netheriteIngot), new ItemStack(ItemLoader.netheriteIngot),
                new ItemStack(ItemLoader.netheriteStick), 60, 0);

        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal), new ItemStack(Items.STRING),
                new ItemStack(ItemLoader.dragonString), 40, 3);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.spacePatch), new ItemStack(Items.STRING),
                new ItemStack(ItemLoader.spaceLine), 40,5);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 4), new ItemStack(Items.DIAMOND_HELMET),
                new ItemStack(ItemLoader.dragonHead), 40,5);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 7), new ItemStack(Items.DIAMOND_CHESTPLATE),
                new ItemStack(ItemLoader.dragonChest), 40,7);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 6), new ItemStack(Items.DIAMOND_LEGGINGS),
                new ItemStack(ItemLoader.dragonLegs), 40,6);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 3), new ItemStack(Items.DIAMOND_BOOTS),
                new ItemStack(ItemLoader.dragonFeet), 40,3);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 2), new ItemStack(Items.DIAMOND_SWORD),
                new ItemStack(ItemLoader.dragonSword), 40,2);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 3), new ItemStack(Items.DIAMOND_PICKAXE),
                new ItemStack(ItemLoader.dragonPickaxe), 40,3);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 3), new ItemStack(Items.DIAMOND_AXE),
                new ItemStack(ItemLoader.dragonAxe), 40,3);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 1), new ItemStack(Items.DIAMOND_SHOVEL),
                new ItemStack(ItemLoader.dragonShovel), 40,1);
        PowerRecipeManager.addRecipe(new ItemStack(ItemLoader.dragonCrystal, 2), new ItemStack(Items.DIAMOND_HOE),
                new ItemStack(ItemLoader.dragonHoe), 40,2);
    }

}
