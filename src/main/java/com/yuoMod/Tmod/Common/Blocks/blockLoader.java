package com.yuoMod.Tmod.Common.Blocks;

import com.yuoMod.Tmod.Common.Blocks.Crops.StemCrop;
import com.yuoMod.Tmod.Common.Blocks.Crops.AllReeds;
import com.yuoMod.Tmod.Common.Blocks.Crops.EmeraldCrop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
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
	public static final BlockCrops emerald_crop=new EmeraldCrop("emerald_crop");
	public static final Block boss_block=new BossBlock("boss_block");
	public static final Block emerald_chest=new EmeraldChest("emerald_chest");
	public static final Block space_ore=new SpaceOre("space_ore");
	public static final Block space_block = new SpaceBlock("space_block");
	public static final Block salt_ore=new SaltOre("salt_ore");
	public static final Block diamond_crop=new StemCrop("diamond_crop", Blocks.DIAMOND_ORE);
	public static final Block gold_crop=new StemCrop("gold_crop", Blocks.GOLD_ORE);
	public static final Block iron_crop=new StemCrop("iron_crop", Blocks.IRON_ORE);
	public static final Block emerald_stem_crop=new StemCrop("emerald_stem_crop", Blocks.EMERALD_ORE);
	public static final Block coal_crop=new StemCrop("coal_crop", Blocks.COAL_ORE);//煤
	public static final Block lapis_crop=new StemCrop("coal_crop", Blocks.LAPIS_ORE);//青金石
	public static final Block quartz_crop=new StemCrop("coal_crop", Blocks.QUARTZ_ORE);//石英
	public static final Block redstone_crop=new StemCrop("coal_crop", Blocks.REDSTONE_ORE);//红石
	public static final Block apple_reeds=new AllReeds("apple_reeds");
	public static final Block lucky_block=new LuckyBlock("lucky_block");
	public static final Block unlucky_block=new UnLuckyBlock("unlucky_block");
	
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
        register(boss_block, "boss_block");
        register(emerald_chest, "emerald_chest");
        register(space_ore, "space_ore");
        register(space_block, "space_block");
        register(salt_ore, "salt_ore");
        register(diamond_crop, "diamond_crop");
        register(gold_crop, "gold_crop");
        register(iron_crop, "iron_crop");
        register(emerald_stem_crop, "emerald_stem_crop");
        register(lapis_crop, "lapis_crop");
        register(coal_crop, "coal_crop");
        register(quartz_crop, "quartz_crop");
        register(redstone_crop, "redstone_crop");
        register(apple_reeds, "apple_reeds");
        register(lucky_block, "lucky_block");
        register(unlucky_block, "unlucky_block");
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
        registerStateMapper(power_extractor, new StateMap.Builder().build());
        registerRender(emerald_crop,0);
        registerRender(boss_block,0);
        registerRender(emerald_chest,0);
        registerRender(space_ore,0);
        registerRender(space_block,0);
        registerRender(salt_ore,0);
        registerRender(diamond_crop,0);
        registerRender(gold_crop,0);
        registerRender(iron_crop,0);
        registerRender(emerald_stem_crop,0);
        registerRender(coal_crop,0);
        registerRender(lapis_crop,0);
        registerRender(quartz_crop,0);
        registerRender(redstone_crop,0);
        registerRender(apple_reeds,0);
        registerRender(lucky_block,0);
        registerRender(unlucky_block,0);
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
