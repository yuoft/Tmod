package com.yuo.Tmod.Common.Items;


import com.yuo.Tmod.Common.Blocks.BlockLoader;
import com.yuo.Tmod.Common.Blocks.ModItemBlock;
import com.yuo.Tmod.Common.Items.Armor.*;
import com.yuo.Tmod.Common.Items.Crops.AllStemCropSeed;
import com.yuo.Tmod.Common.Items.Crops.EmeraldCropSeeds;
import com.yuo.Tmod.Common.Items.Crops.ModXCropSeed;
import com.yuo.Tmod.Common.Items.Food.BadApple;
import com.yuo.Tmod.Common.Items.Food.BuffApple;
import com.yuo.Tmod.Common.Items.Food.GoldDiamondBread;
import com.yuo.Tmod.Common.Items.Food.SaltMeat;
import com.yuo.Tmod.Common.Items.Tool.*;
import com.yuo.Tmod.Client.SoundLoader;
import com.yuo.Tmod.Tmod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
    //食物
    public static final Item badApple = new BadApple("bad_apple", 6, 0.4f, false);
    public static final Item goldDiamondBread = new GoldDiamondBread("gold_diamond_bread", 6, 0.5f, false);
    public static final Item emeraldApple = new BuffApple("emerald_apple", 6, 0.5f, false);
    public static final Item diamondApple = new BuffApple("diamond_apple", 6, 0.5f, false);
    public static final Item ironApple = new BuffApple("iron_apple", 6, 0.5f, false);
    public static final Item coalApple = new BuffApple("coal_apple", 6, 0.5f, false);
    public static final Item quartzApple = new BuffApple("quartz_apple", 6, 0.5f, false);
    public static final Item lapisApple = new BuffApple("lapis_apple", 6, 0.5f, false);
    public static final Item redstoneApple = new BuffApple("redstone_apple", 6, 0.5f, false);
    public static final Item glowstoneApple = new BuffApple("glowstone_apple", 6, 0.5f, false);
    public static final Item deBuffApple = new BuffApple("debuff_apple", 16, 0.8f, false);
    public static final Item saltPigMeat = new SaltMeat("salt_pig_meat", 5, 0.8f, true);
    public static final Item saltCattleMeat = new SaltMeat("salt_cattle_meat", 5, 0.8f, true);
    public static final Item saltChickenMeat = new SaltMeat("salt_chicken_meat", 4, 0.8f, true);
    public static final Item saltSheepMeat = new SaltMeat("salt_sheep_meat", 4, 0.8f, true);
    public static final Item saltFishMeat = new SaltMeat("salt_fish_meat", 4, 0.4f, true);
    public static final Item saltRottenMeat = new SaltMeat("salt_rotten_meat", 6, 0.4f, true);
    public static final Item saltMeat = new SaltMeat("salt_meat", 8, 0.8f, true);
    public static final Item cookingSaltMeat = new SaltMeat("cooking_salt_meat", 10, 1.0f, true);
    //绿宝石锭盔甲材料
    //--------------------------------------------------------------------材料名------盔甲模型前缀--------耐久值---------各部位护甲值------附魔能力---音效---------------------------盔甲韧性
    public static final Item emeraldIngot = new ModItem("emerald_ingot");
    public static final Item netheriteIngot = new ModItem("netherite_ingot");
    public static final Item ruby = new ModItem("ruby");
    public static final Item dragonCrystal = new ModItem("dragon_crystal");
    public static final Item spaceIngot = new ModItem("space_ingot");
    public static final ItemArmor emeraldHead = new ModArmor("emerald_helmet", TmodMaterial.EMERALD_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor emeraldChest = new ModArmor("emerald_breastplate", TmodMaterial.EMERALD_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor emeraldLegs = new ModArmor("emerald_leggings", TmodMaterial.EMERALD_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor emeraldFeet = new ModArmor("emerald_shoes", TmodMaterial.EMERALD_ARMOR, 3, EntityEquipmentSlot.FEET);
    //op套
    public static final ItemArmor opHead = new OPArmor("op_helmet", TmodMaterial.OP, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor opChest = new OPArmor("op_chestplate", TmodMaterial.OP, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor opLegs = new OPArmor("op_leggings", TmodMaterial.OP, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor opFeet = new OPArmor("op_boots", TmodMaterial.OP, 3, EntityEquipmentSlot.FEET);
    public static final OPSword opSword = new OPSword("op_sword");
    public static final OPPickaxe opPickaxe = new OPPickaxe("op_pickaxe");
    //空间套装
    public static final ItemArmor spaceHead = new SpaceArmor("space_helmet", TmodMaterial.SPACE_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor spaceChest = new SpaceArmor("space_chestplate", TmodMaterial.SPACE_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor spaceLegs = new SpaceArmor("space_leggings", TmodMaterial.SPACE_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor spaceFeet = new SpaceArmor("space_boots", TmodMaterial.SPACE_ARMOR, 3, EntityEquipmentSlot.FEET);
    public static final SpaceSword spaceSword = new SpaceSword("space_sword", TmodMaterial.SPACE_TOOL);
    public static final SpaceBow spaceBow = new SpaceBow("space_bow");
    public static final DragonBow dragonBow = new DragonBow("dragon_bow");
    public static final SpacePickaxe spacePickaxe = new SpacePickaxe("space_pickaxe", TmodMaterial.SPACE_TOOL);
    //绿宝石锭工具材料
    public static final Item complexPowder = new ModItem("complex_powder");
    public static final Item changePowder = new ChangePowder("change_powder");
    //--------------------------------------------------------------------------名字------------------------挖掘等级--耐久--使用效率-攻击力-附魔能力
    public static final ToolAxe emeraldAxe = new ToolAxe("emerald_axe", TmodMaterial.EMERALD_TOOL);
    public static final ToolHoe emeraldHoe = new ToolHoe("emerald_hoe", TmodMaterial.EMERALD_TOOL);
    public static final ToolPickaxe emeraldPickaxe = new ToolPickaxe("emerald_pickaxe", TmodMaterial.EMERALD_TOOL);
    public static final BH3Sword hengShuang = new BH3Sword("hengshuang", TmodMaterial.BH3);
    public static final ToolShovel emeraldShovel = new ToolShovel("emerald_shovel", TmodMaterial.EMERALD_TOOL);
    public static final ToolSword emeraldSword = new ToolSword("emerald_sword", TmodMaterial.EMERALD_TOOL);
    public static final BH3Sword tianHuo = new BH3Sword("tianhuo", TmodMaterial.BH3);
    //红宝石套装
    public static final ToolAxe rubyAxe = new ToolAxe("ruby_axe", TmodMaterial.RUBY_TOOL);
    public static final ToolHoe rubyHoe = new ToolHoe("ruby_hoe", TmodMaterial.RUBY_TOOL);
    public static final ToolPickaxe rubyPickaxe = new ToolPickaxe("ruby_pickaxe", TmodMaterial.RUBY_TOOL);
    public static final ToolShovel rubyShovel = new ToolShovel("ruby_shovel", TmodMaterial.RUBY_TOOL);
    public static final ToolSword rubySword = new ToolSword("ruby_sword", TmodMaterial.RUBY_TOOL);
    public static final ItemArmor rubyHead = new ModArmor("ruby_helmet", TmodMaterial.RUBY_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor rubyChest = new ModArmor("ruby_chestplate", TmodMaterial.RUBY_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor rubyLegs = new ModArmor("ruby_leggings", TmodMaterial.RUBY_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor rubyFeet = new ModArmor("ruby_boots", TmodMaterial.RUBY_ARMOR, 3, EntityEquipmentSlot.FEET);
    //龙晶套装
    public static final ToolPickaxe dragonPickaxe = new ToolPickaxe("dragon_pickaxe", TmodMaterial.DRAGON_TOOL);
    public static final ToolSword dragonSword = new ToolSword("dragon_sword", TmodMaterial.DRAGON_TOOL);
    public static final DragonArmor dragonHead = new DragonArmor("dragon_head", TmodMaterial.DRAGON_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final DragonArmor dragonChest = new DragonArmor("dragon_chest", TmodMaterial.DRAGON_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final DragonArmor dragonLegs = new DragonArmor("dragon_chest", TmodMaterial.DRAGON_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final DragonArmor dragonFeet = new DragonArmor("dragon_feet", TmodMaterial.DRAGON_ARMOR, 3, EntityEquipmentSlot.FEET);

    public static final Item blazeBone = new ModItem("blaze_bone");
    public static final Item netheriteScrap = new ModItem("netherite_scrap");
    public static final Item witherBone = new ModItem("wither_bone");
    public static final Item yuanshi = new ModItem("yuanshi");
    public static final Item jiejing = new ModItem("jiejing");
    //信标套装
    public static final ToolSword beaconSword = new ToolSword("beacon_sword", TmodMaterial.BEACON_TOOL);
    public static final BeaconArmor beaconHead = new BeaconArmor("beacon_head", TmodMaterial.BEACON_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final BeaconArmor beaconChest = new BeaconArmor("beacon_chest", TmodMaterial.BEACON_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final BeaconArmor beaconLegs = new BeaconArmor("beacon_legs", TmodMaterial.BEACON_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final BeaconArmor beaconFeet = new BeaconArmor("beacon_feet", TmodMaterial.BEACON_ARMOR, 3, EntityEquipmentSlot.FEET);
    //荧石套
    public static final ToolSword glowstoneSword = new ToolSword("glowstone_sword", TmodMaterial.GLOWSTONE_TOOL);
    public static final GlowstoneArmor glowstoneHead = new GlowstoneArmor("glowstone_head", TmodMaterial.GLOWSTONE_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final GlowstoneArmor glowstoneChest = new GlowstoneArmor("glowstone_chest", TmodMaterial.GLOWSTONE_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final GlowstoneArmor glowstoneLegs = new GlowstoneArmor("glowstone_legs", TmodMaterial.GLOWSTONE_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final GlowstoneArmor glowstoneFeet = new GlowstoneArmor("glowstone_feet", TmodMaterial.GLOWSTONE_ARMOR, 3, EntityEquipmentSlot.FEET);
    //玄铁套
    public static final ToolSword netheriteSword = new ToolSword("netherite_sword", TmodMaterial.NETHERITE_TOOL);
    public static final ToolPickaxe netheritePickaxe = new ToolPickaxe("netherite_pickaxe", TmodMaterial.NETHERITE_TOOL);
    public static final ToolAxe netheriteAxe = new ToolAxe("netherite_axe", TmodMaterial.NETHERITE_TOOL);
    public static final ToolShovel netheriteShovel = new ToolShovel("netherite_shovel", TmodMaterial.NETHERITE_TOOL);
    public static final ToolHoe netheriteHoe = new ToolHoe("netherite_hoe", TmodMaterial.NETHERITE_TOOL);
    public static final NetheriteArmor netheriteHead = new NetheriteArmor("netherite_head", TmodMaterial.NETHERITE_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final NetheriteArmor netheriteChest = new NetheriteArmor("netherite_chest", TmodMaterial.NETHERITE_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final NetheriteArmor netheriteLegs = new NetheriteArmor("netherite_legs", TmodMaterial.NETHERITE_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final NetheriteArmor netheriteFeet = new NetheriteArmor("netherite_feet", TmodMaterial.NETHERITE_ARMOR, 3, EntityEquipmentSlot.FEET);
    //不死图腾套装
    public static final ToolSword totemSword = new ToolSword("totem_sword", TmodMaterial.TOTEM_TOOL);
    public static final TotemArmor totemHead = new TotemArmor("totem_head", TmodMaterial.TOTEM_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final TotemArmor totemChest = new TotemArmor("totem_chest", TmodMaterial.TOTEM_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final TotemArmor totemLegs = new TotemArmor("totem_legs", TmodMaterial.TOTEM_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final TotemArmor totemFeet = new TotemArmor("totem_feet", TmodMaterial.TOTEM_ARMOR, 3, EntityEquipmentSlot.FEET);
    //黑曜石套
    public static final ToolSword beheadSword = new ToolSword("behead_sword", TmodMaterial.OBSIDIAN_TOOL);
    public static final ToolSword obsidianSword = new ToolSword("obsidian_sword", TmodMaterial.OBSIDIAN_TOOL);
    public static final ObsidianArmor obsidianHead = new ObsidianArmor("obsidian_head", TmodMaterial.OBSIDIAN_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ObsidianArmor obsidianChest = new ObsidianArmor("obsidian_chest", TmodMaterial.OBSIDIAN_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ObsidianArmor obsidianLegs = new ObsidianArmor("obsidian_legs", TmodMaterial.OBSIDIAN_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ObsidianArmor obsidianFeet = new ObsidianArmor("obsidian_feet", TmodMaterial.OBSIDIAN_ARMOR, 3, EntityEquipmentSlot.FEET);

    public static final ModItemBlock emeraldIngotBlock = new ModItemBlock(BlockLoader.emeraldIngotBlock);
    public static final ModItemBlock emeraldIngotOre = new ModItemBlock(BlockLoader.emeraldIngotOre);
    public static final ModItemBlock powerExtractor = new ModItemBlock(BlockLoader.powerExtractor);
    public static final ModItemBlock bossBlock = new ModItemBlock(BlockLoader.bossBlock);
    public static final ModItemBlock bossBlock1 = new ModItemBlock(BlockLoader.bossBlock1);
    public static final ModItemBlock emeraldBarrel = new ModItemBlock(BlockLoader.emeraldBarrel);
    public static final ModItemBlock spaceOre = new ModItemBlock(BlockLoader.spaceOre);
    public static final ModItemBlock dragonOre = new ModItemBlock(BlockLoader.dragonOre);
    public static final ModItemBlock endSpaceOre = new ModItemBlock(BlockLoader.endSpaceOre);
    public static final ModItemBlock spaceBlock = new ModItemBlock(BlockLoader.spaceBlock);
    public static final ModItemBlock saltOre = new ModItemBlock(BlockLoader.saltOre);
    public static final ModItemBlock luckyBlock = new ModItemBlock(BlockLoader.luckyBlock);
    public static final ModItemBlock unluckyBlock = new ModItemBlock(BlockLoader.unluckyBlock);
    public static final ModItemBlock rubyOre = new ModItemBlock(BlockLoader.rubyOre);
    public static final ModItemBlock netherRubyOre = new ModItemBlock(BlockLoader.netherRubyOre);
    public static final ModItemBlock mine = new ModItemBlock(BlockLoader.mine);
    public static final ModItemBlock speedTorch = new ModItemBlock(BlockLoader.speedTorch);
    public static final ModItemBlock elevator = new ModItemBlock(BlockLoader.elevator);
    public static final ModItemBlock rubyBlock = new ModItemBlock(BlockLoader.rubyBlock);
    public static final ModItemBlock dragonBlock = new ModItemBlock(BlockLoader.dragonBlock);
    public static final ModItemBlock netheriteBlock = new ModItemBlock(BlockLoader.netheriteBlock);
    public static final ModItemBlock ancientDebris = new ModItemBlock(BlockLoader.ancientDebris);
    //特殊物品
    public static final GoldenTNT goldenTnt = new GoldenTNT("golden_tnt");
    public static final TmodMusicRecord tmodMusic1 = new TmodMusicRecord("tmod_music1", SoundLoader.LTY_JueTiJueMimg);
    public static final TmodMusicRecord tmodMusic2 = new TmodMusicRecord("tmod_music2", SoundLoader.HY_MingJi);
    public static final TmodMusicRecord tmodMusic3 = new TmodMusicRecord("tmod_music3", SoundLoader.CD_Op);
    public static final Item expSmall = new ExpWaterDrop("exp_small");
    public static final Item expBig = new ExpWaterDrop("exp_big");
    public static final Item storageRingBig = new StorageRing("storage_ring_big");
    public static final Item storageRingIn = new StorageRing("storage_ring_in");
    public static final Item storageRingSmall = new StorageRing("storage_ring_small");
    public static final Item spaceVillagerEgg = new SpaceVillagerEgg("space_villager_egg");
    public static final Item playerLevel = new LevelViewer("player_level");
    public static final Item upgradeGem = new UpGradeGem("upgrade_gem");
    public static final Item bombRemover = new DombRemover("bomb_remover");
    //多功能工具
    public static final ManyFunTool diamondTool = new ManyFunTool("diamond_tool", 4.0f, -2.8f, ToolMaterial.DIAMOND);
    public static final ManyFunTool goldTool = new ManyFunTool("gold_tool", 5.0f, -2.8f, ToolMaterial.GOLD);
    public static final ManyFunTool ironTool = new ManyFunTool("iron_tool", 5.0f, -2.8f, ToolMaterial.IRON);
    public static final ManyFunTool stoneTool = new ManyFunTool("stone_tool", 6.0f, -2.8f, ToolMaterial.STONE);
    public static final ManyFunTool woodTool = new ManyFunTool("wood_tool", 5.0f, -2.8f, ToolMaterial.WOOD);
    //作物 普通
    public static final EmeraldCropSeeds emeraldCropSeeds = new EmeraldCropSeeds("emerald_crop_seeds");
    //作物 甘蔗
    public static final ModItemBlock appleReeds = new ModItemBlock(BlockLoader.appleReeds);
    //树
    public static final ModItemBlock emeraldTree = new ModItemBlock(BlockLoader.emeraldTree);
    public static final ModItemBlock emeraldLeaf = new ModItemBlock(BlockLoader.emeraldLeaf);
    public static final ModItemBlock emeraldSapling = new ModItemBlock(BlockLoader.emeraldSapling);
    public static final ModItemBlock coalLeaf = new ModItemBlock(BlockLoader.coalLeaf);
    public static final ModItemBlock coalSapling = new ModItemBlock(BlockLoader.coalSapling);
    public static final ModItemBlock diamondLeaf = new ModItemBlock(BlockLoader.diamondLeaf);
    public static final ModItemBlock diamondSapling = new ModItemBlock(BlockLoader.diamondSapling);
    public static final ModItemBlock goldLeaf = new ModItemBlock(BlockLoader.goldLeaf);
    public static final ModItemBlock goldSapling = new ModItemBlock(BlockLoader.goldSapling);
    public static final ModItemBlock ironLeaf = new ModItemBlock(BlockLoader.ironLeaf);
    public static final ModItemBlock ironSapling = new ModItemBlock(BlockLoader.ironSapling);
    public static final ModItemBlock lapisLeaf = new ModItemBlock(BlockLoader.lapisLeaf);
    public static final ModItemBlock lapisSapling = new ModItemBlock(BlockLoader.lapisSapling);
    public static final ModItemBlock quartzLeaf = new ModItemBlock(BlockLoader.quartzLeaf);
    public static final ModItemBlock quartzSapling = new ModItemBlock(BlockLoader.quartzSapling);
    public static final ModItemBlock redstoneLeaf = new ModItemBlock(BlockLoader.redstoneLeaf);
    public static final ModItemBlock redstoneSapling = new ModItemBlock(BlockLoader.redstoneSapling);
    public static final ModItemBlock neteriteLeaf = new ModItemBlock(BlockLoader.netheriteLeaf);
    public static final ModItemBlock neteriteSapling = new ModItemBlock(BlockLoader.netheriteSapling);
    //作物 茎
    public static final AllStemCropSeed coalStemSeed = new AllStemCropSeed("coal_stem_seed", BlockLoader.coalStem);
    public static final AllStemCropSeed diamondStemSeed = new AllStemCropSeed("diamond_stem_seed", BlockLoader.diamondStem);
    public static final AllStemCropSeed emeraldStemSeed = new AllStemCropSeed("emerald_stem_seed", BlockLoader.emeraldStem);
    public static final AllStemCropSeed goldStemSeed = new AllStemCropSeed("gold_stem_seed", BlockLoader.goldStem);
    public static final AllStemCropSeed ironStemSeed = new AllStemCropSeed("iron_stem_seed", BlockLoader.ironStem);
    public static final AllStemCropSeed lapisStemSeed = new AllStemCropSeed("lapis_stem_seed", BlockLoader.lapisStem);
    public static final AllStemCropSeed quartzStemSeed = new AllStemCropSeed("quartz_stem_seed", BlockLoader.quartzStem);
    public static final AllStemCropSeed redstoneStemSeed = new AllStemCropSeed("redstone_stem_seed", BlockLoader.redstoneStem);
    public static final AllStemCropSeed netheriteStemSeed = new AllStemCropSeed("netherite_stem_seed", BlockLoader.netheriteStem);
    //作物 x型
    public static final Item coalCropSeed = new ModXCropSeed("coal_crop_seed", BlockLoader.coalCrop);
    public static final Item diamondCropSeed = new ModXCropSeed("diamond_crop_seed", BlockLoader.diamondCrop);
    public static final Item emeraldCropSeed = new ModXCropSeed("emerald_crop_seed", BlockLoader.emeraldCrop);
    public static final Item goldCropSeed = new ModXCropSeed("gold_crop_seed", BlockLoader.goldCrop);
    public static final Item ironCropSeed = new ModXCropSeed("iron_crop_seed", BlockLoader.ironCrop);
    public static final Item lapisCropSeed = new ModXCropSeed("lapis_crop_seed", BlockLoader.lapisCrop);
    public static final Item netheriteCropSeed = new ModXCropSeed("netherite_crop_seed", BlockLoader.netheriteCrop);
    public static final Item quartzCropSeed = new ModXCropSeed("quartz_crop_seed", BlockLoader.quartzCrop);
    public static final Item redstoneCropSeed = new ModXCropSeed("redstone_crop_seed", BlockLoader.redstoneCrop);
    public static final Item coalFruit = new ModCropItem("coal_fruit");
    public static final Item coalNugget = new ModCropItem("coal_nugget");
    public static final Item diamondFruit = new ModCropItem("diamond_fruit");
    public static final Item diamondNugget = new ModCropItem("diamond_nugget");
    public static final Item emeraldFruit = new ModCropItem("emerald_fruit");
    public static final Item emeraldNugget = new ModCropItem("emerald_nugget");
    public static final Item goldFruit = new ModCropItem("gold_fruit");
    public static final Item ironFruit = new ModCropItem("iron_fruit");
    public static final Item lapisFruit = new ModCropItem("lapis_fruit");
    public static final Item lapisNugget = new ModCropItem("lapis_nugget");
    public static final Item netheriteFruit = new ModCropItem("netherite_fruit");
    public static final Item netheriteNugget = new ModCropItem("netherite_nugget");
    public static final Item quartzFruit = new ModCropItem("quartz_fruit");
    public static final Item quartzNugget = new ModCropItem("quartz_nugget");
    public static final Item redstoneFruit = new ModCropItem("redstone_fruit");
    public static final Item redstoneNugget = new ModCropItem("redstone_nugget");
//    public static final Item dragonString = new ModCropItem("xray_crop_seed");
    //碎片物品 or 通用物品注册
    public static final Item emeraldPowder = new ModItem("emerald_powder");
    public static final Item elytraLeft = new ModItem("elytra_left");
    public static final Item elytraRight = new ModItem("elytra_right");
    public static final Item netherStarSmall = new ModItem("nether_star_small");
    public static final Item totemSmall = new ModItem("totem_small");
    public static final Item spacePatch = new ModItem("space_patch");
    public static final Item spaceCore = new ModItem("space_core");
    public static final Item spaceLine = new ModItem("space_line");
    public static final Item salt = new ModItem("salt");
    public static final Item saltWash = new ModItem("salt_wash");
    public static final Item dragonString = new ModItem("dragon_string");
    public static final Item bedrockIngot = new ModItem("bedrock_ingot");
    public static final Item bedrockNugget = new ModItem("bedrock_nugget");
    public static final Item bedrockPowder = new ModItem("bedrock_powder");
    public static final Item diamondStick = new ModItem("diamond_stick");
    public static final Item goldStick = new ModItem("gold_stick");
    public static final Item ironStick = new ModItem("iron_stick");
    public static final Item netheriteStick = new ModItem("netherite_stick");

    //注册item的id
    public static void init() {
        ForgeRegistries.ITEMS.register(badApple.setRegistryName("bad_apple"));
        ForgeRegistries.ITEMS.register(goldDiamondBread.setRegistryName("gold_diamond_bread"));
        ForgeRegistries.ITEMS.register(emeraldApple.setRegistryName("emerald_apple"));
        ForgeRegistries.ITEMS.register(diamondApple.setRegistryName("diamond_apple"));
        ForgeRegistries.ITEMS.register(ironApple.setRegistryName("iron_apple"));
        ForgeRegistries.ITEMS.register(coalApple.setRegistryName("coal_apple"));
        ForgeRegistries.ITEMS.register(quartzApple.setRegistryName("quartz_apple"));
        ForgeRegistries.ITEMS.register(lapisApple.setRegistryName("lapis_apple"));
        ForgeRegistries.ITEMS.register(redstoneApple.setRegistryName("redstone_apple"));
        ForgeRegistries.ITEMS.register(glowstoneApple.setRegistryName("glowstone_apple"));
        ForgeRegistries.ITEMS.register(deBuffApple.setRegistryName("debuff_apple"));
        ForgeRegistries.ITEMS.register(saltCattleMeat.setRegistryName("salt_cattle_meat"));
        ForgeRegistries.ITEMS.register(saltChickenMeat.setRegistryName("salt_chicken_meat"));
        ForgeRegistries.ITEMS.register(saltFishMeat.setRegistryName("salt_fish_meat"));
        ForgeRegistries.ITEMS.register(saltMeat.setRegistryName("salt_meat"));
        ForgeRegistries.ITEMS.register(cookingSaltMeat.setRegistryName("cooking_salt_meat"));
        ForgeRegistries.ITEMS.register(saltPigMeat.setRegistryName("salt_pig_meat"));
        ForgeRegistries.ITEMS.register(saltRottenMeat.setRegistryName("salt_rotten_meat"));
        ForgeRegistries.ITEMS.register(saltSheepMeat.setRegistryName("salt_sheep_meat"));

        ForgeRegistries.ITEMS.register(emeraldHead.setRegistryName("emerald_helmet"));
        ForgeRegistries.ITEMS.register(emeraldChest.setRegistryName("emerald_breastplate"));
        ForgeRegistries.ITEMS.register(emeraldLegs.setRegistryName("emerald_leggings"));
        ForgeRegistries.ITEMS.register(emeraldFeet.setRegistryName("emerald_shoes"));
        ForgeRegistries.ITEMS.register(emeraldIngot.setRegistryName("emerald_ingot"));
        ForgeRegistries.ITEMS.register(opHead.setRegistryName("op_helmet"));
        ForgeRegistries.ITEMS.register(opChest.setRegistryName("op_chestplate"));
        ForgeRegistries.ITEMS.register(opLegs.setRegistryName("op_leggings"));
        ForgeRegistries.ITEMS.register(opFeet.setRegistryName("op_boots"));
        ForgeRegistries.ITEMS.register(opSword.setRegistryName("op_sword"));
        ForgeRegistries.ITEMS.register(opPickaxe.setRegistryName("op_pickaxe"));
        ForgeRegistries.ITEMS.register(dragonCrystal.setRegistryName("dragon_crystal"));
        ForgeRegistries.ITEMS.register(dragonHead.setRegistryName("dragon_head"));
        ForgeRegistries.ITEMS.register(dragonChest.setRegistryName("dragon_chest"));
        ForgeRegistries.ITEMS.register(dragonLegs.setRegistryName("dragon_legs"));
        ForgeRegistries.ITEMS.register(dragonFeet.setRegistryName("dragon_feet"));
        ForgeRegistries.ITEMS.register(dragonSword.setRegistryName("dragon_sword"));
        ForgeRegistries.ITEMS.register(dragonBow.setRegistryName("dragon_bow"));
        ForgeRegistries.ITEMS.register(dragonPickaxe.setRegistryName("dragon_pickaxe"));
        ForgeRegistries.ITEMS.register(spaceHead.setRegistryName("space_helmet"));
        ForgeRegistries.ITEMS.register(spaceChest.setRegistryName("space_chestplate"));
        ForgeRegistries.ITEMS.register(spaceLegs.setRegistryName("space_leggings"));
        ForgeRegistries.ITEMS.register(spaceFeet.setRegistryName("space_boots"));
        ForgeRegistries.ITEMS.register(spaceSword.setRegistryName("space_sword"));
        ForgeRegistries.ITEMS.register(spaceBow.setRegistryName("space_bow"));
        ForgeRegistries.ITEMS.register(spacePickaxe.setRegistryName("space_pickaxe"));

        ForgeRegistries.ITEMS.register(emeraldAxe.setRegistryName("emerald_axe"));
        ForgeRegistries.ITEMS.register(emeraldHoe.setRegistryName("emerald_hoe"));
        ForgeRegistries.ITEMS.register(emeraldPickaxe.setRegistryName("emerald_pickaxe"));
        ForgeRegistries.ITEMS.register(emeraldShovel.setRegistryName("emerald_shovel"));
        ForgeRegistries.ITEMS.register(emeraldSword.setRegistryName("emerald_sword"));
        ForgeRegistries.ITEMS.register(hengShuang.setRegistryName("hengshuang"));
        ForgeRegistries.ITEMS.register(tianHuo.setRegistryName("tianhuo"));
        ForgeRegistries.ITEMS.register(complexPowder.setRegistryName("complex_powder"));
        ForgeRegistries.ITEMS.register(changePowder.setRegistryName("change_powder"));

        ForgeRegistries.ITEMS.register(ruby.setRegistryName("ruby"));
        ForgeRegistries.ITEMS.register(rubyAxe.setRegistryName("ruby_axe"));
        ForgeRegistries.ITEMS.register(rubyFeet.setRegistryName("ruby_boots"));
        ForgeRegistries.ITEMS.register(rubyChest.setRegistryName("ruby_chestplate"));
        ForgeRegistries.ITEMS.register(rubyHead.setRegistryName("ruby_helmet"));
        ForgeRegistries.ITEMS.register(rubyHoe.setRegistryName("ruby_hoe"));
        ForgeRegistries.ITEMS.register(rubyLegs.setRegistryName("ruby_leggings"));
        ForgeRegistries.ITEMS.register(rubyPickaxe.setRegistryName("ruby_pickaxe"));
        ForgeRegistries.ITEMS.register(rubyShovel.setRegistryName("ruby_shovel"));
        ForgeRegistries.ITEMS.register(rubySword.setRegistryName("ruby_sword"));

        ForgeRegistries.ITEMS.register(blazeBone.setRegistryName("blaze_bone"));
        ForgeRegistries.ITEMS.register(netheriteIngot.setRegistryName("netherite_ingot"));
        ForgeRegistries.ITEMS.register(netheriteScrap.setRegistryName("netherite_scrap"));
        ForgeRegistries.ITEMS.register(witherBone.setRegistryName("wither_bone"));
        ForgeRegistries.ITEMS.register(yuanshi.setRegistryName("yuanshi"));
        ForgeRegistries.ITEMS.register(jiejing.setRegistryName("jiejing"));
        ForgeRegistries.ITEMS.register(beaconSword.setRegistryName("beacon_sword"));
        ForgeRegistries.ITEMS.register(beaconHead.setRegistryName("beacon_head"));
        ForgeRegistries.ITEMS.register(beaconChest.setRegistryName("beacon_chest"));
        ForgeRegistries.ITEMS.register(beaconLegs.setRegistryName("beacon_legs"));
        ForgeRegistries.ITEMS.register(beaconFeet.setRegistryName("beacon_feet"));
        ForgeRegistries.ITEMS.register(glowstoneSword.setRegistryName("glowstone_sword"));
        ForgeRegistries.ITEMS.register(glowstoneHead.setRegistryName("glowstone_head"));
        ForgeRegistries.ITEMS.register(glowstoneChest.setRegistryName("glowstone_chest"));
        ForgeRegistries.ITEMS.register(glowstoneLegs.setRegistryName("glowstone_legs"));
        ForgeRegistries.ITEMS.register(glowstoneFeet.setRegistryName("glowstone_feet"));
        ForgeRegistries.ITEMS.register(netheriteSword.setRegistryName("netherite_sword"));
        ForgeRegistries.ITEMS.register(netheritePickaxe.setRegistryName("netherite_pickaxe"));
        ForgeRegistries.ITEMS.register(netheriteAxe.setRegistryName("netherite_axe"));
        ForgeRegistries.ITEMS.register(netheriteShovel.setRegistryName("netherite_shovel"));
        ForgeRegistries.ITEMS.register(netheriteHoe.setRegistryName("netherite_hoe"));
        ForgeRegistries.ITEMS.register(netheriteHead.setRegistryName("netherite_head"));
        ForgeRegistries.ITEMS.register(netheriteChest.setRegistryName("netherite_chest"));
        ForgeRegistries.ITEMS.register(netheriteLegs.setRegistryName("netherite_legs"));
        ForgeRegistries.ITEMS.register(netheriteFeet.setRegistryName("netherite_feet"));
        ForgeRegistries.ITEMS.register(totemSword.setRegistryName("totem_sword"));
        ForgeRegistries.ITEMS.register(totemHead.setRegistryName("totem_head"));
        ForgeRegistries.ITEMS.register(totemChest.setRegistryName("totem_chest"));
        ForgeRegistries.ITEMS.register(totemLegs.setRegistryName("totem_legs"));
        ForgeRegistries.ITEMS.register(totemFeet.setRegistryName("totem_feet"));
        ForgeRegistries.ITEMS.register(beheadSword.setRegistryName("behead_sword"));
        ForgeRegistries.ITEMS.register(obsidianSword.setRegistryName("obsidian_sword"));
        ForgeRegistries.ITEMS.register(obsidianHead.setRegistryName("obsidian_head"));
        ForgeRegistries.ITEMS.register(obsidianChest.setRegistryName("obsidian_chest"));
        ForgeRegistries.ITEMS.register(obsidianLegs.setRegistryName("obsidian_legs"));
        ForgeRegistries.ITEMS.register(obsidianFeet.setRegistryName("obsidian_feet"));

        ForgeRegistries.ITEMS.register(emeraldIngotBlock.setRegistryName("emerald_ingot_block"));
        ForgeRegistries.ITEMS.register(emeraldIngotOre.setRegistryName("emerald_ingot_ore"));
        ForgeRegistries.ITEMS.register(emeraldTree.setRegistryName("emerald_tree"));
        ForgeRegistries.ITEMS.register(emeraldLeaf.setRegistryName("emerald_leaf"));
        ForgeRegistries.ITEMS.register(emeraldSapling.setRegistryName("emerald_sapling"));
        ForgeRegistries.ITEMS.register(powerExtractor.setRegistryName("power_extractor"));
        ForgeRegistries.ITEMS.register(bossBlock.setRegistryName("boss_block"));
        ForgeRegistries.ITEMS.register(bossBlock1.setRegistryName("boss_block1"));
        ForgeRegistries.ITEMS.register(emeraldBarrel.setRegistryName("emerald_barrel"));
        ForgeRegistries.ITEMS.register(spaceOre.setRegistryName("space_ore"));
        ForgeRegistries.ITEMS.register(dragonOre.setRegistryName("dragon_ore"));
        ForgeRegistries.ITEMS.register(endSpaceOre.setRegistryName("end_space_ore"));
        ForgeRegistries.ITEMS.register(spaceBlock.setRegistryName("space_block"));
        ForgeRegistries.ITEMS.register(saltOre.setRegistryName("salt_ore"));
        ForgeRegistries.ITEMS.register(luckyBlock.setRegistryName("lucky_block"));
        ForgeRegistries.ITEMS.register(unluckyBlock.setRegistryName("unlucky_block"));
        ForgeRegistries.ITEMS.register(rubyOre.setRegistryName("ruby_ore"));
        ForgeRegistries.ITEMS.register(netherRubyOre.setRegistryName("nether_ruby_ore"));
        ForgeRegistries.ITEMS.register(mine.setRegistryName("mine"));
        ForgeRegistries.ITEMS.register(speedTorch.setRegistryName("speed_torch"));
        ForgeRegistries.ITEMS.register(elevator.setRegistryName("elevator"));
        ForgeRegistries.ITEMS.register(rubyBlock.setRegistryName("ruby_block"));
        ForgeRegistries.ITEMS.register(dragonBlock.setRegistryName("dragon_block"));
        ForgeRegistries.ITEMS.register(netheriteBlock.setRegistryName("netherite_block"));
        ForgeRegistries.ITEMS.register(ancientDebris.setRegistryName("ancient_debris"));

        ForgeRegistries.ITEMS.register(goldenTnt.setRegistryName("golden_tnt"));
        ForgeRegistries.ITEMS.register(tmodMusic1.setRegistryName("tmod_music1"));
        ForgeRegistries.ITEMS.register(tmodMusic2.setRegistryName("tmod_music2"));
        ForgeRegistries.ITEMS.register(tmodMusic3.setRegistryName("tmod_music3"));
        ForgeRegistries.ITEMS.register(expSmall.setRegistryName("exp_small"));
        ForgeRegistries.ITEMS.register(expBig.setRegistryName("exp_big"));
        ForgeRegistries.ITEMS.register(storageRingBig.setRegistryName("storage_ring_big"));
        ForgeRegistries.ITEMS.register(storageRingIn.setRegistryName("storage_ring_in"));
        ForgeRegistries.ITEMS.register(storageRingSmall.setRegistryName("storage_ring_small"));
        ForgeRegistries.ITEMS.register(spaceVillagerEgg.setRegistryName("space_villager_egg"));
        ForgeRegistries.ITEMS.register(playerLevel.setRegistryName("player_level"));
        ForgeRegistries.ITEMS.register(upgradeGem.setRegistryName("upgrade_gem"));
        ForgeRegistries.ITEMS.register(bombRemover.setRegistryName("domb_remover"));

        ForgeRegistries.ITEMS.register(diamondTool.setRegistryName("diamond_tool"));
        ForgeRegistries.ITEMS.register(goldTool.setRegistryName("gold_tool"));
        ForgeRegistries.ITEMS.register(ironTool.setRegistryName("iron_tool"));
        ForgeRegistries.ITEMS.register(stoneTool.setRegistryName("stone_tool"));
        ForgeRegistries.ITEMS.register(woodTool.setRegistryName("wood_tool"));

        ForgeRegistries.ITEMS.register(emeraldCropSeeds.setRegistryName("emerald_crop_seeds"));
        ForgeRegistries.ITEMS.register(appleReeds.setRegistryName("apple_reeds"));
        ForgeRegistries.ITEMS.register(diamondStemSeed.setRegistryName("diamond_stem_seed"));
        ForgeRegistries.ITEMS.register(goldStemSeed.setRegistryName("gold_stem_seed"));
        ForgeRegistries.ITEMS.register(ironStemSeed.setRegistryName("iron_stem_seed"));
        ForgeRegistries.ITEMS.register(emeraldStemSeed.setRegistryName("emerald_stem_seed"));
        ForgeRegistries.ITEMS.register(coalStemSeed.setRegistryName("coal_stem_seed"));
        ForgeRegistries.ITEMS.register(quartzStemSeed.setRegistryName("quartz_stem_seed"));
        ForgeRegistries.ITEMS.register(lapisStemSeed.setRegistryName("lapis_stem_seed"));
        ForgeRegistries.ITEMS.register(redstoneStemSeed.setRegistryName("redstone_stem_seed"));
        ForgeRegistries.ITEMS.register(netheriteStemSeed.setRegistryName("netherite_stem_seed"));

        ForgeRegistries.ITEMS.register(emeraldPowder.setRegistryName("emerald_powder"));
        ForgeRegistries.ITEMS.register(elytraLeft.setRegistryName("elytra_left"));
        ForgeRegistries.ITEMS.register(elytraRight.setRegistryName("elytra_right"));
        ForgeRegistries.ITEMS.register(netherStarSmall.setRegistryName("nether_star_small"));
        ForgeRegistries.ITEMS.register(totemSmall.setRegistryName("totem_small"));
        ForgeRegistries.ITEMS.register(spacePatch.setRegistryName("space_patch"));
        ForgeRegistries.ITEMS.register(spaceIngot.setRegistryName("space_ingot"));
        ForgeRegistries.ITEMS.register(spaceCore.setRegistryName("space_core"));
        ForgeRegistries.ITEMS.register(spaceLine.setRegistryName("space_line"));
        ForgeRegistries.ITEMS.register(salt.setRegistryName("salt"));
        ForgeRegistries.ITEMS.register(saltWash.setRegistryName("salt_wash"));
        ForgeRegistries.ITEMS.register(dragonString.setRegistryName("dragon_string"));
        ForgeRegistries.ITEMS.register(bedrockIngot.setRegistryName("bedrock_ingot"));
        ForgeRegistries.ITEMS.register(bedrockNugget.setRegistryName("bedrock_nugget"));
        ForgeRegistries.ITEMS.register(bedrockPowder.setRegistryName("bedrock_powder"));
        ForgeRegistries.ITEMS.register(diamondStick.setRegistryName("diamond_stick"));
        ForgeRegistries.ITEMS.register(goldStick.setRegistryName("gold_stick"));
        ForgeRegistries.ITEMS.register(ironStick.setRegistryName("iron_stick"));
        ForgeRegistries.ITEMS.register(netheriteStick.setRegistryName("netherite_stick"));

        ForgeRegistries.ITEMS.register(coalLeaf.setRegistryName("coal_leaf"));
        ForgeRegistries.ITEMS.register(coalSapling.setRegistryName("coal_sapling"));
        ForgeRegistries.ITEMS.register(diamondLeaf.setRegistryName("diamond_leaf"));
        ForgeRegistries.ITEMS.register(diamondSapling.setRegistryName("diamond_sapling"));
        ForgeRegistries.ITEMS.register(goldLeaf.setRegistryName("gold_leaf"));
        ForgeRegistries.ITEMS.register(goldSapling.setRegistryName("gold_sapling"));
        ForgeRegistries.ITEMS.register(ironLeaf.setRegistryName("iron_leaf"));
        ForgeRegistries.ITEMS.register(ironSapling.setRegistryName("iron_sapling"));
        ForgeRegistries.ITEMS.register(lapisLeaf.setRegistryName("lapis_leaf"));
        ForgeRegistries.ITEMS.register(lapisSapling.setRegistryName("lapis_sapling"));
        ForgeRegistries.ITEMS.register(quartzLeaf.setRegistryName("quartz_leaf"));
        ForgeRegistries.ITEMS.register(quartzSapling.setRegistryName("quartz_sapling"));
        ForgeRegistries.ITEMS.register(redstoneLeaf.setRegistryName("redstone_leaf"));
        ForgeRegistries.ITEMS.register(redstoneSapling.setRegistryName("redstone_sapling"));
        ForgeRegistries.ITEMS.register(neteriteLeaf.setRegistryName("netherite_leaf"));
        ForgeRegistries.ITEMS.register(neteriteSapling.setRegistryName("netherite_sapling"));

        ForgeRegistries.ITEMS.register(coalCropSeed.setRegistryName("coal_crop_seed"));
        ForgeRegistries.ITEMS.register(diamondCropSeed.setRegistryName("diamond_crop_seed"));
        ForgeRegistries.ITEMS.register(emeraldCropSeed.setRegistryName("emerald_crop_seed"));
        ForgeRegistries.ITEMS.register(goldCropSeed.setRegistryName("gold_crop_seed"));
        ForgeRegistries.ITEMS.register(ironCropSeed.setRegistryName("iron_crop_seed"));
        ForgeRegistries.ITEMS.register(lapisCropSeed.setRegistryName("lapis_crop_seed"));
        ForgeRegistries.ITEMS.register(quartzCropSeed.setRegistryName("quartz_crop_seed"));
        ForgeRegistries.ITEMS.register(redstoneCropSeed.setRegistryName("redstone_crop_seed"));
        ForgeRegistries.ITEMS.register(netheriteCropSeed.setRegistryName("netherite_crop_seed"));

        ForgeRegistries.ITEMS.register(coalFruit.setRegistryName("coal_fruit"));
        ForgeRegistries.ITEMS.register(coalNugget.setRegistryName("coal_nugget"));
        ForgeRegistries.ITEMS.register(diamondFruit.setRegistryName("diamond_fruit"));
        ForgeRegistries.ITEMS.register(diamondNugget.setRegistryName("diamond_nugget"));
        ForgeRegistries.ITEMS.register(emeraldFruit.setRegistryName("emerald_fruit"));
        ForgeRegistries.ITEMS.register(emeraldNugget.setRegistryName("emerald_nugget"));
        ForgeRegistries.ITEMS.register(goldFruit.setRegistryName("gold_fruit"));
        ForgeRegistries.ITEMS.register(ironFruit.setRegistryName("iron_fruit"));
        ForgeRegistries.ITEMS.register(lapisFruit.setRegistryName("lapis_fruit"));
        ForgeRegistries.ITEMS.register(lapisNugget.setRegistryName("lapis_nugget"));
        ForgeRegistries.ITEMS.register(quartzFruit.setRegistryName("quartz_fruit"));
        ForgeRegistries.ITEMS.register(quartzNugget.setRegistryName("quartz_nugget"));
        ForgeRegistries.ITEMS.register(redstoneFruit.setRegistryName("redstone_fruit"));
        ForgeRegistries.ITEMS.register(redstoneNugget.setRegistryName("redstone_nugget"));
        ForgeRegistries.ITEMS.register(netheriteFruit.setRegistryName("netherite_fruit"));
        ForgeRegistries.ITEMS.register(netheriteNugget.setRegistryName("netherite_nugget"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()//zhucecaizhi
    {
        OBJLoader.INSTANCE.addDomain(Tmod.MOD_ID);

        registerRender(badApple);
        registerRender(goldDiamondBread);
        registerRender(emeraldApple, 0);
        registerRender(emeraldApple, 1);
        registerRender(diamondApple, 0);
        registerRender(diamondApple, 1);
        registerRender(ironApple, 0);
        registerRender(ironApple, 1);
        registerRender(coalApple, 0);
        registerRender(coalApple, 1);
        registerRender(quartzApple, 0);
        registerRender(quartzApple, 1);
        registerRender(lapisApple, 0);
        registerRender(lapisApple, 1);
        registerRender(redstoneApple, 0);
        registerRender(redstoneApple, 1);
        registerRender(glowstoneApple, 0);
        registerRender(glowstoneApple, 1);
        registerRender(deBuffApple, 0);
        registerRender(deBuffApple, 1);
        registerRender(saltCattleMeat);
        registerRender(saltChickenMeat);
        registerRender(saltFishMeat);
        registerRender(saltMeat);
        registerRender(cookingSaltMeat);
        registerRender(saltPigMeat);
        registerRender(saltRottenMeat);
        registerRender(saltSheepMeat);

        registerRender(emeraldHead);
        registerRender(emeraldChest);
        registerRender(emeraldLegs);
        registerRender(emeraldFeet);
        registerRender(emeraldIngot);
        registerRender(opHead);
        registerRender(opChest);
        registerRender(opLegs);
        registerRender(opFeet);
        registerRender(opSword);
        registerRender(opPickaxe);
        registerRender(dragonCrystal);
        registerRender(dragonHead);
        registerRender(dragonChest);
        registerRender(dragonLegs);
        registerRender(dragonFeet);
        registerRender(dragonSword);
        registerRender(dragonPickaxe);
        registerRender(dragonBow);
        registerRender(spaceHead);
        registerRender(spaceChest);
        registerRender(spaceLegs);
        registerRender(spaceFeet);
        registerRender(spaceSword);
        registerRender(spaceBow);
        registerRender(spacePickaxe);

        registerRender(emeraldAxe);
        registerRender(emeraldHoe);
        registerRender(emeraldPickaxe);
        registerRender(emeraldShovel);
        registerRender(emeraldSword);
        registerRender(hengShuang);
        registerRender(tianHuo);
        registerRender(complexPowder);
        registerRender(changePowder);

        registerRender(ruby);
        registerRender(rubyAxe);
        registerRender(rubyFeet);
        registerRender(rubyChest);
        registerRender(rubyHead);
        registerRender(rubyHoe);
        registerRender(rubyLegs);
        registerRender(rubyPickaxe);
        registerRender(rubyShovel);
        registerRender(rubySword);

        registerRender(blazeBone);
        registerRender(netheriteIngot);
        registerRender(netheriteScrap);
        registerRender(witherBone);
        registerRender(yuanshi);
        registerRender(jiejing);
        registerRender(beaconSword);
        registerRender(beaconHead);
        registerRender(beaconChest);
        registerRender(beaconLegs);
        registerRender(beaconFeet);
        registerRender(glowstoneSword);
        registerRender(glowstoneHead);
        registerRender(glowstoneChest);
        registerRender(glowstoneLegs);
        registerRender(glowstoneFeet);
        registerRender(netheriteSword);
        registerRender(netheritePickaxe);
        registerRender(netheriteAxe);
        registerRender(netheriteShovel);
        registerRender(netheriteHoe);
        registerRender(netheriteHead);
        registerRender(netheriteChest);
        registerRender(netheriteLegs);
        registerRender(netheriteFeet);
        registerRender(totemSword);
        registerRender(totemHead);
        registerRender(totemChest);
        registerRender(totemLegs);
        registerRender(totemFeet);
        registerRender(beheadSword);
        registerRender(obsidianSword);
        registerRender(obsidianHead);
        registerRender(obsidianChest);
        registerRender(obsidianLegs);
        registerRender(obsidianFeet);

        registerRender(emeraldIngotBlock);
        registerRender(emeraldIngotOre);
        registerRender(emeraldTree, 0);
        registerRender(emeraldLeaf, 0);
        registerRender(emeraldSapling);
        registerRender(powerExtractor, 0);
        registerRender(bossBlock);
        registerRender(bossBlock1);
        registerRender(emeraldBarrel);
        registerRender(spaceOre);
        registerRender(dragonOre);
        registerRender(endSpaceOre);
        registerRender(spaceBlock);
        registerRender(saltOre);
        registerRender(luckyBlock);
        registerRender(unluckyBlock);
        registerRender(rubyOre);
        registerRender(netherRubyOre);
        registerRender(mine);
        registerRender(speedTorch);
        registerRender(elevator);
        registerRender(netheriteBlock);
        registerRender(ancientDebris);

        registerRender(goldenTnt);
        registerRender(tmodMusic1);
        registerRender(tmodMusic2);
        registerRender(tmodMusic3);
        registerRender(expSmall);
        registerRender(expBig);
        registerRender(storageRingBig);
        registerRender(storageRingIn);
        registerRender(storageRingSmall);
        registerRender(spaceVillagerEgg);
        registerRender(playerLevel);
        registerRender(upgradeGem);
        registerRender(bombRemover);

        registerRender(diamondTool);
        registerRender(goldTool);
        registerRender(ironTool);
        registerRender(stoneTool);
        registerRender(woodTool);

        registerRender(emeraldCropSeeds);
        registerRender(appleReeds);
        registerRender(diamondStemSeed);
        registerRender(goldStemSeed);
        registerRender(ironStemSeed);
        registerRender(emeraldStemSeed);
        registerRender(quartzStemSeed);
        registerRender(coalStemSeed);
        registerRender(lapisStemSeed);
        registerRender(redstoneStemSeed);

        registerRender(emeraldPowder);
        registerRender(elytraLeft);
        registerRender(elytraRight);
        registerRender(netherStarSmall);
        registerRender(totemSmall);
        registerRender(spacePatch);
        registerRender(spaceIngot);
        registerRender(spaceCore);
        registerRender(spaceLine);
        registerRender(salt);
        registerRender(saltWash);
        registerRender(dragonString);
        registerRender(bedrockIngot);
        registerRender(bedrockNugget);
        registerRender(bedrockPowder);
        registerRender(diamondStick);
        registerRender(goldStick);
        registerRender(ironStick);
        registerRender(netheriteStick);

        registerRender(coalLeaf);
        registerRender(coalSapling);
        registerRender(diamondLeaf);
        registerRender(diamondSapling);
        registerRender(goldLeaf);
        registerRender(goldSapling);
        registerRender(ironLeaf);
        registerRender(ironSapling);
        registerRender(lapisLeaf);
        registerRender(lapisSapling);
        registerRender(quartzLeaf);
        registerRender(quartzSapling);
        registerRender(redstoneLeaf);
        registerRender(redstoneSapling);
        registerRender(neteriteLeaf);
        registerRender(neteriteSapling);

        registerRender(coalCropSeed);
        registerRender(diamondCropSeed);
        registerRender(emeraldCropSeed);
        registerRender(goldCropSeed);
        registerRender(ironCropSeed);
        registerRender(lapisCropSeed);
        registerRender(quartzCropSeed);
        registerRender(redstoneCropSeed);
        registerRender(netheriteCropSeed);

        registerRender(coalFruit);
        registerRender(coalNugget);
        registerRender(diamondFruit);
        registerRender(diamondNugget);
        registerRender(emeraldFruit);
        registerRender(emeraldNugget);
        registerRender(goldFruit);
        registerRender(ironFruit);
        registerRender(lapisFruit);
        registerRender(lapisNugget);
        registerRender(quartzFruit);
        registerRender(quartzNugget);
        registerRender(redstoneFruit);
        registerRender(redstoneNugget);
        registerRender(netheriteFruit);
        registerRender(netheriteNugget);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Item item, int meta)//多状态物品注册
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");//模型路径
        ModelLoader.setCustomModelResourceLocation(item, meta, model);//物品id，物品状态，物品模型
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Item item) {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

}