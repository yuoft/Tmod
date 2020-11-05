package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Common.Blocks.Crops.emerald_crop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
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
	public static final BlockFluidClassic emerald_fluid=new block_Emerald_Fluid("emerald_fluid");
	public static final BlockCrops emerald_crop=new emerald_crop("emerald_crop");

    public blockLoader(FMLPreInitializationEvent event)
    {
        register(emerald_ingot_block, "emerald_ingot_block");
        register(tallgrass_block, "tallgrass_block");
        register(emerald_ingot_ore, "emerald_ingot_ore");
        register(emerald_tree, "emerald_tree");
        register(emerald_leaf, "emerald_leaf");
        register(emerald_sapling, "emerald_sapling");
        register(power_extractor, "power_extractor");
        register(emerald_fluid, "emerald_fluid");
        register(emerald_crop, "emerald_crop");
    }
    private static void register(Block block, String name)
    {
    	ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
    }
    //材质
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(emerald_ingot_block,0);
        registerRender(tallgrass_block,0);
        registerRender(emerald_ingot_ore,0);
        registerRender(emerald_tree,0);
        registerStateMapper(emerald_tree,
                new StateMap.Builder().build());
        registerRender(emerald_leaf,0);
        registerStateMapper(emerald_leaf,//重定位模型资源
                new StateMap.Builder().build());
        registerRender(emerald_sapling,0);
        registerRender(power_extractor,0);
        registerStateMapper(power_extractor,
                new StateMap.Builder().build());
        registerRender(emerald_crop,0);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block,int meta)
    {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
    }
    @SideOnly(Side.CLIENT)
    private static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
}
