package com.yuo.Tmod.Common.Blocks;

import com.yuo.Tmod.Common.Blocks.Crops.*;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
    public static final Block endSpaceOre = new SpaceOre("end_space_ore");
    public static final Block spaceBlock = new SpaceBlock("space_block");
    public static final Block saltOre = new SaltOre("salt_ore");
    public static final Block dragonOre = new DragonOre("dragon_ore");
    public static final Block rubyOre = new ModBlock("ruby_ore", 8, 2, Material.ROCK);
    public static final Block netherRubyOre = new ModBlock("nether_ruby_ore", 10, 2, Material.ROCK);
    public static final Block ancientDebris = new ModBlock("ancient_debris", 12, 3, Material.IRON);

    public static final Block xrayBlock = new XrayBlock("xray_block", 2, 10);
    public static final Block superOre = new ModBlock("super_ore", 13, 3, Material.ROCK);
    public static final Block superXrayBlock = new XrayBlock("super_xray_block", 4, 15);
    public static final Block superBlock = new ModBlock("super_block", 15, 3, Material.IRON);
    public static final Block ultraBlock = new ModBlock("ultra_block", 20, 5, Material.IRON);
    public static final Block fragileBedrock = new FragileBedrock("fragile_bedrock");
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
    //树木
    public static final Block emeraldTree = new OreTree("emerald_tree");
    public static final Block emeraldLeaf = new OreLeaf("emerald_leaf");
    public static final Block emeraldSapling = new OreSapling("emerald_sapling", emeraldTree, emeraldLeaf);
    public static final Block coalLeaf = new OreLeaf("coal_leaf");
    public static final Block coalSapling = new OreSapling("coal_sapling", emeraldTree, coalLeaf);
    public static final Block diamondLeaf = new OreLeaf("diamond_leaf");
    public static final Block diamondSapling = new OreSapling("diamond_sapling", emeraldTree, diamondLeaf);
    public static final Block goldLeaf = new OreLeaf("gold_leaf");
    public static final Block goldSapling = new OreSapling("gold_sapling", emeraldTree, goldLeaf);
    public static final Block ironLeaf = new OreLeaf("iron_leaf");
    public static final Block ironSapling = new OreSapling("iron_sapling", emeraldTree, ironLeaf);
    public static final Block lapisLeaf = new OreLeaf("lapis_leaf");
    public static final Block lapisSapling = new OreSapling("lapis_sapling", emeraldTree, lapisLeaf);
    public static final Block quartzLeaf = new OreLeaf("quartz_leaf");
    public static final Block quartzSapling = new OreSapling("quartz_sapling", emeraldTree, quartzLeaf);
    public static final Block redstoneLeaf = new OreLeaf("redstone_leaf");
    public static final Block redstoneSapling = new OreSapling("redstone_sapling", emeraldTree, redstoneLeaf);
    public static final Block netheriteLeaf = new OreLeaf("netherite_leaf");
    public static final Block netheriteSapling = new OreSapling("netherite_sapling", emeraldTree, netheriteLeaf);
    //作物
    public static final BlockCrops emeraldCrops = new EmeraldCrop("emerald_crops");
    public static final Block diamondStem = new StemCrop("diamond_stem", Blocks.DIAMOND_ORE);
    public static final Block goldStem = new StemCrop("gold_stem", Blocks.GOLD_ORE);
    public static final Block ironStem = new StemCrop("iron_stem", Blocks.IRON_ORE);
    public static final Block emeraldStem = new StemCrop("emerald_stem", Blocks.EMERALD_ORE);
    public static final Block coalStem = new StemCrop("coal_stem", Blocks.COAL_ORE);//煤
    public static final Block lapisStem = new StemCrop("lapis_stem", Blocks.LAPIS_ORE);//青金石
    public static final Block quartzStem = new StemCrop("quartz_stem", Blocks.QUARTZ_ORE);//石英
    public static final Block redstoneStem = new StemCrop("redstone_stem", Blocks.REDSTONE_ORE);//红石
    public static final Block netheriteStem = new StemCrop("netherite_stem", BlockLoader.ancientDebris);//红石
    public static final Block appleReeds = new AllReeds("apple_reeds");

    public static final BlockCrops diamondCrop = new ModXCrop("diamond_crop");
    public static final BlockCrops goldCrop = new ModXCrop("gold_crop");
    public static final BlockCrops ironCrop = new ModXCrop("iron_crop");
    public static final BlockCrops emeraldCrop = new ModXCrop("emerald_crop");
    public static final BlockCrops coalCrop = new ModXCrop("coal_crop");
    public static final BlockCrops lapisCrop = new ModXCrop("lapis_crop");
    public static final BlockCrops quartzCrop = new ModXCrop("quartz_crop");
    public static final BlockCrops redstoneCrop = new ModXCrop("redstone_crop");
    public static final BlockCrops netheriteCrop = new ModXCrop("netherite_crop");

    public BlockLoader(FMLPreInitializationEvent event) {
        register(emeraldIngotBlock, "emerald_ingot_block");
        register(emeraldIngotOre, "emerald_ingot_ore");
        register(emeraldTree, "emerald_tree");
        register(emeraldLeaf, "emerald_leaf");
        register(emeraldSapling, "emerald_sapling");
        register(powerExtractor, "power_extractor");
        register(emeraldFluid, "emerald_fluid");
        register(emeraldCrops, "emerald_crops");
        register(bossBlock, "boss_block");
        register(bossBlock1, "boss_block1");
        register(emeraldBarrel, "emerald_barrel");
        register(spaceOre, "space_ore");
        register(endSpaceOre, "end_space_ore");
        register(spaceBlock, "space_block");
        register(saltOre, "salt_ore");
        register(dragonOre, "dragon_ore");

        register(xrayBlock, "xray_block");
        register(superOre, "super_ore");
        register(superXrayBlock, "super_xray_block");
        register(superBlock, "super_block");
        register(ultraBlock, "ultra_block");
        register(fragileBedrock, "fragile_bedrock");

        register(diamondStem, "diamond_stem");
        register(goldStem, "gold_stem");
        register(ironStem, "iron_stem");
        register(emeraldStem, "emerald_stem");
        register(lapisStem, "lapis_stem");
        register(coalStem, "coal_stem");
        register(quartzStem, "quartz_stem");
        register(redstoneStem, "redstone_stem");
        register(netheriteStem, "netherite_stem");
        register(appleReeds, "apple_reeds");
        register(luckyBlock, "lucky_block");
        register(unluckyBlock, "unlucky_block");
        register(rubyOre, "ruby_ore");
        register(netherRubyOre, "nether_ruby_ore");
        register(mine, "mine");
        register(speedTorch, "speed_torch");
        register(elevator, "elevator");
        register(rubyBlock, "ruby_block");
        register(dragonBlock, "dragon_block");
        register(netheriteBlock, "netherite_block");
        register(ancientDebris, "ancient_debris");

        register(coalLeaf, "coal_leaf");
        register(coalSapling, "coal_sapling");
        register(diamondLeaf, "diamond_leaf");
        register(diamondSapling, "diamond_sapling");
        register(goldLeaf, "gold_leaf");
        register(goldSapling, "gold_sapling");
        register(ironLeaf, "iron_leaf");
        register(ironSapling, "iron_sapling");
        register(lapisLeaf, "lapis_leaf");
        register(lapisSapling, "lapis_sapling");
        register(quartzLeaf, "quartz_leaf");
        register(quartzSapling, "quartz_sapling");
        register(redstoneLeaf, "redstone_leaf");
        register(redstoneSapling, "redstone_sapling");
        register(netheriteLeaf, "netherite_leaf");
        register(netheriteSapling, "netherite_sapling");

        register(coalCrop, "coal_crop");
        register(diamondCrop, "diamond_crop");
        register(emeraldCrop, "emerald_crop");
        register(goldCrop, "gold_crop");
        register(ironCrop, "iron_crop");
        register(lapisCrop, "lapis_crop");
        register(quartzCrop, "quartz_crop");
        register(redstoneCrop, "redstone_crop");
        register(netheriteCrop, "netherite_crop");
    }

    private static void register(Block block, String name) {
        ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
    }

    //材质
    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(emeraldIngotBlock);
        registerRender(emeraldIngotOre);
        registerRender(emeraldTree);
        registerStateMapper(emeraldTree);
        registerRender(emeraldLeaf);
        registerStateMapper(emeraldLeaf);//重定位模型资源
        registerRender(emeraldSapling);
        registerRender(powerExtractor);
        registerStateMapper(powerExtractor);
        registerRender(emeraldCrops);
        registerRender(bossBlock);
        registerRender(bossBlock1);
        registerRender(emeraldBarrel);
        registerRender(spaceOre);
        registerRender(endSpaceOre);
        registerRender(spaceBlock);
        registerRender(saltOre);
        registerRender(dragonOre);

        registerRender(xrayBlock);
        registerRender(superOre);
        registerRender(superXrayBlock);
        registerRender(superBlock);
        registerRender(ultraBlock);
        registerRender(fragileBedrock);

        registerRender(diamondStem);
        registerRender(goldStem);
        registerRender(ironStem);
        registerRender(emeraldStem);
        registerRender(coalStem);
        registerRender(lapisStem);
        registerRender(quartzStem);
        registerRender(redstoneStem);
        registerRender(netheriteStem);
        registerRender(appleReeds);
        registerRender(luckyBlock);
        registerRender(unluckyBlock);
        registerRender(rubyOre);
        registerRender(netherRubyOre);
        registerRender(mine);
        registerRender(speedTorch);
        registerRender(elevator);
        registerRender(rubyBlock);
        registerRender(dragonBlock);
        registerRender(netheriteBlock);
        registerRender(ancientDebris);

        registerRender(coalLeaf);
        registerRender(coalSapling);
        registerRender(diamondLeaf);
        registerRender(diamondSapling);
        registerRender(ironLeaf);
        registerRender(ironSapling);
        registerRender(goldLeaf);
        registerRender(goldSapling);
        registerRender(lapisLeaf);
        registerRender(lapisSapling);
        registerRender(quartzLeaf);
        registerRender(quartzSapling);
        registerRender(redstoneLeaf);
        registerRender(redstoneSapling);
        registerRender(netheriteLeaf);
        registerRender(netheriteSapling);

        registerRender(coalCrop);
        registerRender(diamondCrop);
        registerRender(emeraldCrop);
        registerRender(goldCrop);
        registerRender(ironCrop);
        registerRender(lapisCrop);
        registerRender(quartzCrop);
        registerRender(redstoneCrop);
        registerRender(netheriteCrop);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block) {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
    }

    @SideOnly(Side.CLIENT)
    private static void registerStateMapper(Block block) {
        ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
    }
}
