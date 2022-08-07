package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Common.Blocks.Crops.StemCrop;
import com.yuo.Tmod.Common.Blocks.Crops.AllReeds;
import com.yuo.Tmod.Common.Blocks.Crops.EmeraldCrop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
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

public class BlockLoader {
    //矿物
    public static final Block emeraldIngotOre = new EmeraldIngotOre("emerald_ingot_ore");
    public static final Block spaceOre = new SpaceOre("space_ore");
    public static final Block spaceBlock = new SpaceBlock("space_block");
    public static final Block saltOre = new SaltOre("salt_ore");
    public static final Block rubyOre = new ModBlock("ruby_ore", 10, 2, Material.ROCK);
    public static final Block ancientDebris = new ModBlock("ancient_debris", 22, 3, Material.IRON);
    //树木
    public static final Block emeraldTree = new EmeraldTree("emerald_tree");
    public static final Block emeraldLeaf = new EmeraldLeaf("emerald_leaf");
    public static final Block emeraldSapling = new EmeraldSapling("emerald_sapling");
    //普通方块
    public static final Block emeraldIngotBlock = new ModBlock("emerald_ingot_block", 10, 3, Material.ROCK);
    public static final Block rubyBlock = new ModBlock("ruby_block", 15, 3, Material.IRON);
    public static final Block dragonBlock = new ModBlock("dragon_block", 25, 4, Material.IRON);
    public static final Block netheriteBlock = new ModBlock("netherite_block", 23, 4, Material.IRON);
    //功能方块
    public static final Block powerExtractor = new PowerExtractor("power_extractor");
    public static final BlockFluidClassic emeraldFluid = new EmeraldFluid("emerald_fluid");
    public static final Block bossBlock = new BossBlock("boss_block");
    public static final Block bossBlock1 = new BossBlock("boss_block1");
    public static final Block emeraldBarrel = new EmeraldBarrel("emerald_barrel");
    public static final Block luckyBlock = new LuckyBlock("lucky_block");
    public static final Block unluckyBlock = new LuckyBlock("unlucky_block");
    public static final Block mine = new Mine("mine"); //地雷
    public static final Block speedTorch = new SpeedTorch("speed_torch"); //加速火把
    public static final Block elevator = new Elevator("elevator"); //电梯
    //作物
    public static final BlockCrops emeraldCrop = new EmeraldCrop("emerald_crop");
    public static final Block diamondCrop = new StemCrop("diamond_crop", Blocks.DIAMOND_ORE);
    public static final Block goldCrop = new StemCrop("gold_crop", Blocks.GOLD_ORE);
    public static final Block ironCrop = new StemCrop("iron_crop", Blocks.IRON_ORE);
    public static final Block emeraldStemCrop = new StemCrop("emerald_stem_crop", Blocks.EMERALD_ORE);
    public static final Block coalCrop = new StemCrop("coal_crop", Blocks.COAL_ORE);//煤
    public static final Block lapisCrop = new StemCrop("coal_crop", Blocks.LAPIS_ORE);//青金石
    public static final Block quartzCrop = new StemCrop("coal_crop", Blocks.QUARTZ_ORE);//石英
    public static final Block redstoneCrop = new StemCrop("coal_crop", Blocks.REDSTONE_ORE);//红石
    public static final Block appleReeds = new AllReeds("apple_reeds");

    public BlockLoader(FMLPreInitializationEvent event) {
        register(emeraldIngotBlock, "emerald_ingot_block");
        register(emeraldIngotOre, "emerald_ingot_ore");
        register(emeraldTree, "emerald_tree");
        register(emeraldLeaf, "emerald_leaf");
        register(emeraldSapling, "emerald_sapling");
        register(powerExtractor, "power_extractor");
        register(emeraldFluid, "emerald_fluid");
        register(emeraldCrop, "emerald_crop");
        register(bossBlock, "boss_block");
        register(bossBlock1, "boss_block1");
        register(emeraldBarrel, "emerald_barrel");
        register(spaceOre, "space_ore");
        register(spaceBlock, "space_block");
        register(saltOre, "salt_ore");
        register(diamondCrop, "diamond_crop");
        register(goldCrop, "gold_crop");
        register(ironCrop, "iron_crop");
        register(emeraldStemCrop, "emerald_stem_crop");
        register(lapisCrop, "lapis_crop");
        register(coalCrop, "coal_crop");
        register(quartzCrop, "quartz_crop");
        register(redstoneCrop, "redstone_crop");
        register(appleReeds, "apple_reeds");
        register(luckyBlock, "lucky_block");
        register(unluckyBlock, "unlucky_block");
        register(rubyOre, "ruby_ore");
        register(mine, "mine");
        register(speedTorch, "speed_torch");
        register(elevator, "elevator");
        register(rubyBlock, "ruby_block");
        register(dragonBlock, "dragon_block");
        register(netheriteBlock, "netherite_block");
        register(ancientDebris, "ancient_debris");
    }

    private static void register(Block block, String name) {
        ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
    }

    //材质
    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(emeraldIngotBlock, 0);
        registerRender(emeraldIngotOre, 0);
        registerRender(emeraldTree, 0);
        registerStateMapper(emeraldTree,
                new StateMap.Builder().build());
        registerRender(emeraldLeaf, 0);
        registerStateMapper(emeraldLeaf,//重定位模型资源
                new StateMap.Builder().build());
        registerRender(emeraldSapling, 0);
        registerRender(powerExtractor, 0);
        registerStateMapper(powerExtractor, new StateMap.Builder().build());
        registerRender(emeraldCrop, 0);
        registerRender(bossBlock, 0);
        registerRender(bossBlock1, 0);
        registerRender(emeraldBarrel, 0);
        registerRender(spaceOre, 0);
        registerRender(spaceBlock, 0);
        registerRender(saltOre, 0);
        registerRender(diamondCrop, 0);
        registerRender(goldCrop, 0);
        registerRender(ironCrop, 0);
        registerRender(emeraldStemCrop, 0);
        registerRender(coalCrop, 0);
        registerRender(lapisCrop, 0);
        registerRender(quartzCrop, 0);
        registerRender(redstoneCrop, 0);
        registerRender(appleReeds, 0);
        registerRender(luckyBlock, 0);
        registerRender(unluckyBlock, 0);
        registerRender(rubyOre, 0);
        registerRender(mine, 0);
        registerRender(speedTorch, 0);
        registerRender(elevator, 0);
        registerRender(rubyBlock, 0);
        registerRender(dragonBlock, 0);
        registerRender(netheriteBlock, 0);
        registerRender(ancientDebris, 0);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, int meta) {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerStateMapper(Block block, IStateMapper mapper) {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
}
