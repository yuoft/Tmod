package com.yuoMod.Tmod.Common.Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class blockLoader 
{
	public static final Block emerald_ingot_block = new emerald_ingot_block("emerald_ingot_block");
	public static final Block tallgrass_block = new tallgrass_block("tallgrass_block");
	public static final Block emerald_ingot_ore=new emerald_ingot_ore("emerald_ingot_ore");
	public static final Block emerald_tree=new emerald_tree("emerald_tree");
	public static final Block emerald_leaf=new emerald_leaf("emerald_leaf");
	public static final Block emerald_sapling=new emerald_sapling("emerald_sapling");
	public static final Block power_extractor=new power_extractor("power_extractor");
	public static final BlockFluidClassic block_Emerald_Fluid=new block_Emerald_Fluid("block_Emerald_Fluid");

    public blockLoader(FMLPreInitializationEvent event)
    {
        register(emerald_ingot_block, "emerald_ingot_block");
        register(tallgrass_block, "tallgrass_block");
        register(emerald_ingot_ore, "emerald_ingot_ore");
        register(emerald_tree, "emerald_tree");
        register(emerald_leaf, "emerald_leaf");
        register(emerald_sapling, "emerald_sapling");
        register(power_extractor, "power_extractor");
        register(block_Emerald_Fluid, "block_Emerald_Fluid");
    }
    private static void register(Block block, String name)
    {
    	ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
    }
    //²ÄÖÊ
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(emerald_ingot_block);
        registerRender(tallgrass_block);
        registerRender(emerald_ingot_ore);
        registerRender(emerald_tree);
        registerRender(emerald_leaf);
        registerRender(emerald_sapling);
        registerRender(power_extractor);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
    }
}
