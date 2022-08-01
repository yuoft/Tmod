package com.yuoMod.Tmod.Common.Items;


import com.yuoMod.Tmod.Common.Blocks.BlockLoader;
import com.yuoMod.Tmod.Common.Blocks.ModItemBlock;
import com.yuoMod.Tmod.Common.Items.Armor.*;
import com.yuoMod.Tmod.Common.Items.Crops.AllStemCropSeed;
import com.yuoMod.Tmod.Common.Items.Crops.EmeraldCropSeeds;
import com.yuoMod.Tmod.Common.Items.Food.BadApple;
import com.yuoMod.Tmod.Common.Items.Food.BuffApple;
import com.yuoMod.Tmod.Common.Items.Food.GoldDiamondBread;
import com.yuoMod.Tmod.Common.Items.Food.SaltMeat;
import com.yuoMod.Tmod.Common.Items.Tool.*;
import com.yuoMod.Tmod.Client.SoundLoader;
import com.yuoMod.Tmod.Tmod;
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
    public static final Item bad_apple = new BadApple("bad_apple", 6, 0.4f, false);
    public static final Item gold_diamond_bread = new GoldDiamondBread("gold_diamond_bread", 6, 0.5f, false);
    public static final Item emerald_apple = new BuffApple("emerald_apple", 6, 0.5f, false);
    public static final Item diamond_apple = new BuffApple("diamond_apple", 6, 0.5f, false);
    public static final Item iron_apple = new BuffApple("iron_apple", 6, 0.5f, false);
    public static final Item coal_apple = new BuffApple("coal_apple", 6, 0.5f, false);
    public static final Item quartz_apple = new BuffApple("quartz_apple", 6, 0.5f, false);
    public static final Item lapis_apple = new BuffApple("lapis_apple", 6, 0.5f, false);
    public static final Item redstone_apple = new BuffApple("redstone_apple", 6, 0.5f, false);
    public static final Item glowstone_apple = new BuffApple("glowstone_apple", 6, 0.5f, false);
    public static final Item debuff_apple = new BuffApple("debuff_apple", 16, 0.8f, false);
    public static final Item salt_pig_meat = new SaltMeat("salt_pig_meat", 5, 0.8f, true);
    public static final Item salt_cattle_meat = new SaltMeat("salt_cattle_meat", 5, 0.8f, true);
    public static final Item salt_chicken_meat = new SaltMeat("salt_chicken_meat", 4, 0.8f, true);
    public static final Item salt_sheep_meat = new SaltMeat("salt_sheep_meat", 4, 0.8f, true);
    public static final Item salt_fish_meat = new SaltMeat("salt_fish_meat", 4, 0.4f, true);
    public static final Item salt_rotten_meat = new SaltMeat("salt_rotten_meat", 6, 0.4f, true);
    public static final Item salt_meat = new SaltMeat("salt_meat", 8, 0.8f, true);
    public static final Item cooking_salt_meat = new SaltMeat("cooking_salt_meat", 10, 1.0f, true);
    //绿宝石锭盔甲材料
    //--------------------------------------------------------------------材料名------盔甲模型前缀--------耐久值---------各部位护甲值------附魔能力---音效---------------------------盔甲韧性
    public static final Item emerald_ingot = new ModItem("emerald_ingot");
    public static final Item netheriteIngot = new ModItem("netherite_ingot");
    public static final Item ruby = new ModItem("ruby");
    public static final Item dragonCrystal = new ModItem("dragon_crystal");
    public static final Item space_ingot = new ModItem("space_ingot");
    public static final ItemArmor emerald_helmet = new ModArmor("emerald_helmet", TmodMaterial.EMERALD_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor emerald_breastplate = new ModArmor("emerald_breastplate", TmodMaterial.EMERALD_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor emerald_leggings = new ModArmor("emerald_leggings", TmodMaterial.EMERALD_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor emerald_shoes = new ModArmor("emerald_shoes", TmodMaterial.EMERALD_ARMOR, 3, EntityEquipmentSlot.FEET);
    //op套
    public static final ItemArmor op_helmet = new OPArmor("op_helmet", TmodMaterial.OP, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor op_chestplate = new OPArmor("op_chestplate", TmodMaterial.OP, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor op_leggings = new OPArmor("op_leggings", TmodMaterial.OP, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor op_boots = new OPArmor("op_boots", TmodMaterial.OP, 3, EntityEquipmentSlot.FEET);
    public static final OPSword op_sword = new OPSword("op_sword");
    public static final OPPickaxe op_pickaxe = new OPPickaxe("op_pickaxe");
    //空间套装
    public static final ItemArmor space_helmet = new SpaceArmor("space_helmet", TmodMaterial.SPACE_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor space_chestplate = new SpaceArmor("space_chestplate", TmodMaterial.SPACE_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor space_leggings = new SpaceArmor("space_leggings", TmodMaterial.SPACE_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor space_boots = new SpaceArmor("space_boots", TmodMaterial.SPACE_ARMOR, 3, EntityEquipmentSlot.FEET);
    public static final SpaceSword space_sword = new SpaceSword("space_sword", TmodMaterial.SPACE_TOOL);
    public static final SpaceBow space_bow = new SpaceBow("space_bow");
    public static final DragonBow dragonBow = new DragonBow("dragon_bow");
    public static final SpacePickaxe space_pickaxe = new SpacePickaxe("space_pickaxe", TmodMaterial.SPACE_TOOL);
    //绿宝石锭工具材料
    public static final Item complexPowder = new ModItem("complex_powder");
    public static final Item change_powder = new ChangePowder("change_powder");
    //--------------------------------------------------------------------------名字------------------------挖掘等级--耐久--使用效率-攻击力-附魔能力
    public static final ToolAxe emerald_axe = new ToolAxe("emerald_axe", TmodMaterial.EMERALD_TOOL);
    public static final ToolHoe emerald_hoe = new ToolHoe("emerald_hoe", TmodMaterial.EMERALD_TOOL);
    public static final ToolPickaxe emerald_pickaxe = new ToolPickaxe("emerald_pickaxe", TmodMaterial.EMERALD_TOOL);
    public static final BH3Sword hengShuang = new BH3Sword("hengshuang", TmodMaterial.BH3);
    public static final ToolShovel emerald_shovel = new ToolShovel("emerald_shovel", TmodMaterial.EMERALD_TOOL);
    public static final ToolSword emerald_sword = new ToolSword("emerald_sword", TmodMaterial.EMERALD_TOOL);
    public static final BH3Sword tianHuo = new BH3Sword("tianhuo", TmodMaterial.BH3);
    //红宝石套装
    public static final ToolAxe ruby_axe = new ToolAxe("ruby_axe", TmodMaterial.RUBY_TOOL);
    public static final ToolHoe ruby_hoe = new ToolHoe("ruby_hoe", TmodMaterial.RUBY_TOOL);
    public static final ToolPickaxe ruby_pickaxe = new ToolPickaxe("ruby_pickaxe", TmodMaterial.RUBY_TOOL);
    public static final ToolShovel ruby_shovel = new ToolShovel("ruby_shovel", TmodMaterial.RUBY_TOOL);
    public static final ToolSword ruby_sword = new ToolSword("ruby_sword", TmodMaterial.RUBY_TOOL);
    public static final ItemArmor ruby_helmet = new ModArmor("ruby_helmet", TmodMaterial.RUBY_ARMOR, 3, EntityEquipmentSlot.HEAD);
    public static final ItemArmor ruby_chestplate = new ModArmor("ruby_chestplate", TmodMaterial.RUBY_ARMOR, 8, EntityEquipmentSlot.CHEST);
    public static final ItemArmor ruby_leggings = new ModArmor("ruby_leggings", TmodMaterial.RUBY_ARMOR, 6, EntityEquipmentSlot.LEGS);
    public static final ItemArmor ruby_boots = new ModArmor("ruby_boots", TmodMaterial.RUBY_ARMOR, 3, EntityEquipmentSlot.FEET);
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

    public static final ModItemBlock emerald_ingot_block = new ModItemBlock(BlockLoader.emerald_ingot_block);
    public static final ModItemBlock tallgrass_block = new ModItemBlock(BlockLoader.tallgrass_block);
    public static final ModItemBlock emerald_ingot_ore = new ModItemBlock(BlockLoader.emerald_ingot_ore);
    public static final ModItemBlock emerald_tree = new ModItemBlock(BlockLoader.emerald_tree);
    public static final ModItemBlock emerald_leaf = new ModItemBlock(BlockLoader.emerald_leaf);
    public static final ModItemBlock emerald_sapling = new ModItemBlock(BlockLoader.emerald_sapling);
    public static final ModItemBlock power_extractor = new ModItemBlock(BlockLoader.power_extractor);
    public static final ModItemBlock boss_block = new ModItemBlock(BlockLoader.boss_block);
    public static final ModItemBlock bossBlock1 = new ModItemBlock(BlockLoader.bossBlock1);
    public static final ModItemBlock emerald_chest = new ModItemBlock(BlockLoader.emerald_chest);
    public static final ModItemBlock space_ore = new ModItemBlock(BlockLoader.space_ore);
    public static final ModItemBlock space_block = new ModItemBlock(BlockLoader.space_block);
    public static final ModItemBlock salt_ore = new ModItemBlock(BlockLoader.salt_ore);
    public static final ModItemBlock lucky_block = new ModItemBlock(BlockLoader.lucky_block);
    public static final ModItemBlock unlucky_block = new ModItemBlock(BlockLoader.unlucky_block);
    public static final ModItemBlock ruby_ore = new ModItemBlock(BlockLoader.ruby_ore);
    public static final ModItemBlock mine = new ModItemBlock(BlockLoader.mine);
    public static final ModItemBlock speed_torch = new ModItemBlock(BlockLoader.speed_torch);
    public static final ModItemBlock elevator = new ModItemBlock(BlockLoader.elevator);
    public static final ModItemBlock rubyBlock = new ModItemBlock(BlockLoader.rubyBlock);
    public static final ModItemBlock dragonBlock = new ModItemBlock(BlockLoader.dragonBlock);
    public static final ModItemBlock netheriteBlock = new ModItemBlock(BlockLoader.netheriteBlock);
    public static final ModItemBlock ancientDebris = new ModItemBlock(BlockLoader.ancientDebris);
    //特殊物品
    public static final GoldenTNT golden_tnt = new GoldenTNT("golden_tnt");
    public static final tmodMusicRecord tmod_music1 = new tmodMusicRecord("tmod_music1", SoundLoader.LTY_JueTiJueMimg);
    public static final tmodMusicRecord tmod_music2 = new tmodMusicRecord("tmod_music2", SoundLoader.HY_MingJi);
    public static final tmodMusicRecord tmod_music3 = new tmodMusicRecord("tmod_music3", SoundLoader.CD_Op);
    public static final Item exp_small = new ExpWaterDrop("exp_small");
    public static final Item exp_big = new ExpWaterDrop("exp_big");
    public static final Item storage_ring_big = new StorageRing("storage_ring_big");
    public static final Item storage_ring_in = new StorageRing("storage_ring_in");
    public static final Item storage_ring_small = new StorageRing("storage_ring_small");
    public static final Item space_villager_egg = new SpaceVillagerEgg("space_villager_egg");
    public static final Item player_level = new LevelViewer("player_level");
    public static final Item upgrade_gem = new UpGradeGem("upgrade_gem");
    public static final Item domb_remover = new DombRemover("domb_remover");
    //多功能工具
    public static final ManyFunTool diamond_tool = new ManyFunTool("diamond_tool", 4.0f, -2.8f, ToolMaterial.DIAMOND);
    public static final ManyFunTool gold_tool = new ManyFunTool("gold_tool", 5.0f, -2.8f, ToolMaterial.GOLD);
    public static final ManyFunTool iron_tool = new ManyFunTool("iron_tool", 5.0f, -2.8f, ToolMaterial.IRON);
    public static final ManyFunTool stone_tool = new ManyFunTool("stone_tool", 6.0f, -2.8f, ToolMaterial.STONE);
    public static final ManyFunTool wood_tool = new ManyFunTool("wood_tool", 5.0f, -2.8f, ToolMaterial.WOOD);
    //作物 普通
    public static final EmeraldCropSeeds emerald_crop_seeds = new EmeraldCropSeeds("emerald_crop_seeds");
    //作物 甘蔗
    public static final ModItemBlock apple_reeds = new ModItemBlock(BlockLoader.apple_reeds);
    //作物 茎
    public static final AllStemCropSeed diamond_crop_seed = new AllStemCropSeed("diamond_crop_seed", BlockLoader.diamond_crop);
    public static final AllStemCropSeed gold_crop_seed = new AllStemCropSeed("gold_crop_seed", BlockLoader.gold_crop);
    public static final AllStemCropSeed iron_crop_seed = new AllStemCropSeed("iron_crop_seed", BlockLoader.iron_crop);
    public static final AllStemCropSeed emerald_stem_crop_seed = new AllStemCropSeed("emerald_stem_crop_seed", BlockLoader.emerald_stem_crop);
    public static final AllStemCropSeed coal_crop_seed = new AllStemCropSeed("coal_crop_seed", BlockLoader.coal_crop);
    public static final AllStemCropSeed quartz_crop_seed = new AllStemCropSeed("quartz_crop_seed", BlockLoader.quartz_crop);
    public static final AllStemCropSeed lapis_crop_seed = new AllStemCropSeed("lapis_crop_seed", BlockLoader.lapis_crop);
    public static final AllStemCropSeed redstone_crop_seed = new AllStemCropSeed("redstone_crop_seed", BlockLoader.redstone_crop);
    //碎片物品 or 通用物品注册
    public static final Item emerald_powder = new ModItem("emerald_powder");
    public static final Item elytra_left = new ModItem("elytra_left");
    public static final Item elytra_right = new ModItem("elytra_right");
    public static final Item nether_star_small = new ModItem("nether_star_small");
    public static final Item totem_small = new ModItem("totem_small");
    public static final Item space_patch = new ModItem("space_patch");
    public static final Item space_core = new ModItem("space_core");
    public static final Item space_line = new ModItem("space_line");
    public static final Item salt = new ModItem("salt");
    public static final Item salt_wash = new ModItem("salt_wash");
    public static final Item dragonString = new ModItem("dragon_string");

    //注册item的id
    public static void init(FMLPreInitializationEvent event) {
        ForgeRegistries.ITEMS.register(bad_apple.setRegistryName("bad_apple"));
        ForgeRegistries.ITEMS.register(gold_diamond_bread.setRegistryName("gold_diamond_bread"));
        ForgeRegistries.ITEMS.register(emerald_apple.setRegistryName("emerald_apple"));
        ForgeRegistries.ITEMS.register(diamond_apple.setRegistryName("diamond_apple"));
        ForgeRegistries.ITEMS.register(iron_apple.setRegistryName("iron_apple"));
        ForgeRegistries.ITEMS.register(coal_apple.setRegistryName("coal_apple"));
        ForgeRegistries.ITEMS.register(quartz_apple.setRegistryName("quartz_apple"));
        ForgeRegistries.ITEMS.register(lapis_apple.setRegistryName("lapis_apple"));
        ForgeRegistries.ITEMS.register(redstone_apple.setRegistryName("redstone_apple"));
        ForgeRegistries.ITEMS.register(glowstone_apple.setRegistryName("glowstone_apple"));
        ForgeRegistries.ITEMS.register(debuff_apple.setRegistryName("debuff_apple"));
        ForgeRegistries.ITEMS.register(salt_cattle_meat.setRegistryName("salt_cattle_meat"));
        ForgeRegistries.ITEMS.register(salt_chicken_meat.setRegistryName("salt_chicken_meat"));
        ForgeRegistries.ITEMS.register(salt_fish_meat.setRegistryName("salt_fish_meat"));
        ForgeRegistries.ITEMS.register(salt_meat.setRegistryName("salt_meat"));
        ForgeRegistries.ITEMS.register(cooking_salt_meat.setRegistryName("cooking_salt_meat"));
        ForgeRegistries.ITEMS.register(salt_pig_meat.setRegistryName("salt_pig_meat"));
        ForgeRegistries.ITEMS.register(salt_rotten_meat.setRegistryName("salt_rotten_meat"));
        ForgeRegistries.ITEMS.register(salt_sheep_meat.setRegistryName("salt_sheep_meat"));

        ForgeRegistries.ITEMS.register(emerald_helmet.setRegistryName("emerald_helmet"));
        ForgeRegistries.ITEMS.register(emerald_breastplate.setRegistryName("emerald_breastplate"));
        ForgeRegistries.ITEMS.register(emerald_leggings.setRegistryName("emerald_leggings"));
        ForgeRegistries.ITEMS.register(emerald_shoes.setRegistryName("emerald_shoes"));
        ForgeRegistries.ITEMS.register(emerald_ingot.setRegistryName("emerald_ingot"));
        ForgeRegistries.ITEMS.register(op_helmet.setRegistryName("op_helmet"));
        ForgeRegistries.ITEMS.register(op_chestplate.setRegistryName("op_chestplate"));
        ForgeRegistries.ITEMS.register(op_leggings.setRegistryName("op_leggings"));
        ForgeRegistries.ITEMS.register(op_boots.setRegistryName("op_boots"));
        ForgeRegistries.ITEMS.register(op_sword.setRegistryName("op_sword"));
        ForgeRegistries.ITEMS.register(op_pickaxe.setRegistryName("op_pickaxe"));
        ForgeRegistries.ITEMS.register(dragonCrystal.setRegistryName("dragon_crystal"));
        ForgeRegistries.ITEMS.register(dragonHead.setRegistryName("dragon_head"));
        ForgeRegistries.ITEMS.register(dragonChest.setRegistryName("dragon_chest"));
        ForgeRegistries.ITEMS.register(dragonLegs.setRegistryName("dragon_legs"));
        ForgeRegistries.ITEMS.register(dragonFeet.setRegistryName("dragon_feet"));
        ForgeRegistries.ITEMS.register(dragonSword.setRegistryName("dragon_sword"));
        ForgeRegistries.ITEMS.register(dragonBow.setRegistryName("dragon_bow"));
        ForgeRegistries.ITEMS.register(dragonPickaxe.setRegistryName("dragon_pickaxe"));
        ForgeRegistries.ITEMS.register(space_helmet.setRegistryName("space_helmet"));
        ForgeRegistries.ITEMS.register(space_chestplate.setRegistryName("space_chestplate"));
        ForgeRegistries.ITEMS.register(space_leggings.setRegistryName("space_leggings"));
        ForgeRegistries.ITEMS.register(space_boots.setRegistryName("space_boots"));
        ForgeRegistries.ITEMS.register(space_sword.setRegistryName("space_sword"));
        ForgeRegistries.ITEMS.register(space_bow.setRegistryName("space_bow"));
        ForgeRegistries.ITEMS.register(space_pickaxe.setRegistryName("space_pickaxe"));

        ForgeRegistries.ITEMS.register(emerald_axe.setRegistryName("emerald_axe"));
        ForgeRegistries.ITEMS.register(emerald_hoe.setRegistryName("emerald_hoe"));
        ForgeRegistries.ITEMS.register(emerald_pickaxe.setRegistryName("emerald_pickaxe"));
        ForgeRegistries.ITEMS.register(emerald_shovel.setRegistryName("emerald_shovel"));
        ForgeRegistries.ITEMS.register(emerald_sword.setRegistryName("emerald_sword"));
        ForgeRegistries.ITEMS.register(hengShuang.setRegistryName("hengshuang"));
        ForgeRegistries.ITEMS.register(tianHuo.setRegistryName("tianhuo"));
        ForgeRegistries.ITEMS.register(complexPowder.setRegistryName("complex_powder"));
        ForgeRegistries.ITEMS.register(change_powder.setRegistryName("change_powder"));

        ForgeRegistries.ITEMS.register(ruby.setRegistryName("ruby"));
        ForgeRegistries.ITEMS.register(ruby_axe.setRegistryName("ruby_axe"));
        ForgeRegistries.ITEMS.register(ruby_boots.setRegistryName("ruby_boots"));
        ForgeRegistries.ITEMS.register(ruby_chestplate.setRegistryName("ruby_chestplate"));
        ForgeRegistries.ITEMS.register(ruby_helmet.setRegistryName("ruby_helmet"));
        ForgeRegistries.ITEMS.register(ruby_hoe.setRegistryName("ruby_hoe"));
        ForgeRegistries.ITEMS.register(ruby_leggings.setRegistryName("ruby_leggings"));
        ForgeRegistries.ITEMS.register(ruby_pickaxe.setRegistryName("ruby_pickaxe"));
        ForgeRegistries.ITEMS.register(ruby_shovel.setRegistryName("ruby_shovel"));
        ForgeRegistries.ITEMS.register(ruby_sword.setRegistryName("ruby_sword"));

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

        ForgeRegistries.ITEMS.register(emerald_ingot_block.setRegistryName("emerald_ingot_block"));
        ForgeRegistries.ITEMS.register(tallgrass_block.setRegistryName("tallgrass_block"));
        ForgeRegistries.ITEMS.register(emerald_ingot_ore.setRegistryName("emerald_ingot_ore"));
        ForgeRegistries.ITEMS.register(emerald_tree.setRegistryName("emerald_tree"));
        ForgeRegistries.ITEMS.register(emerald_leaf.setRegistryName("emerald_leaf"));
        ForgeRegistries.ITEMS.register(emerald_sapling.setRegistryName("emerald_sapling"));
        ForgeRegistries.ITEMS.register(power_extractor.setRegistryName("power_extractor"));
        ForgeRegistries.ITEMS.register(boss_block.setRegistryName("boss_block"));
        ForgeRegistries.ITEMS.register(bossBlock1.setRegistryName("boss_block1"));
        ForgeRegistries.ITEMS.register(emerald_chest.setRegistryName("emerald_chest"));
        ForgeRegistries.ITEMS.register(space_ore.setRegistryName("space_ore"));
        ForgeRegistries.ITEMS.register(space_block.setRegistryName("space_block"));
        ForgeRegistries.ITEMS.register(salt_ore.setRegistryName("salt_ore"));
        ForgeRegistries.ITEMS.register(lucky_block.setRegistryName("lucky_block"));
        ForgeRegistries.ITEMS.register(unlucky_block.setRegistryName("unlucky_block"));
        ForgeRegistries.ITEMS.register(ruby_ore.setRegistryName("ruby_ore"));
        ForgeRegistries.ITEMS.register(mine.setRegistryName("mine"));
        ForgeRegistries.ITEMS.register(speed_torch.setRegistryName("speed_torch"));
        ForgeRegistries.ITEMS.register(elevator.setRegistryName("elevator"));
        ForgeRegistries.ITEMS.register(rubyBlock.setRegistryName("ruby_block"));
        ForgeRegistries.ITEMS.register(dragonBlock.setRegistryName("dragon_block"));
        ForgeRegistries.ITEMS.register(netheriteBlock.setRegistryName("netherite_block"));
        ForgeRegistries.ITEMS.register(ancientDebris.setRegistryName("ancient_debris"));

        ForgeRegistries.ITEMS.register(golden_tnt.setRegistryName("golden_tnt"));
        ForgeRegistries.ITEMS.register(tmod_music1.setRegistryName("tmod_music1"));
        ForgeRegistries.ITEMS.register(tmod_music2.setRegistryName("tmod_music2"));
        ForgeRegistries.ITEMS.register(tmod_music3.setRegistryName("tmod_music3"));
        ForgeRegistries.ITEMS.register(exp_small.setRegistryName("exp_small"));
        ForgeRegistries.ITEMS.register(exp_big.setRegistryName("exp_big"));
        ForgeRegistries.ITEMS.register(storage_ring_big.setRegistryName("storage_ring_big"));
        ForgeRegistries.ITEMS.register(storage_ring_in.setRegistryName("storage_ring_in"));
        ForgeRegistries.ITEMS.register(storage_ring_small.setRegistryName("storage_ring_small"));
        ForgeRegistries.ITEMS.register(space_villager_egg.setRegistryName("space_villager_egg"));
        ForgeRegistries.ITEMS.register(player_level.setRegistryName("player_level"));
        ForgeRegistries.ITEMS.register(upgrade_gem.setRegistryName("upgrade_gem"));
        ForgeRegistries.ITEMS.register(domb_remover.setRegistryName("domb_remover"));

        ForgeRegistries.ITEMS.register(diamond_tool.setRegistryName("diamond_tool"));
        ForgeRegistries.ITEMS.register(gold_tool.setRegistryName("gold_tool"));
        ForgeRegistries.ITEMS.register(iron_tool.setRegistryName("iron_tool"));
        ForgeRegistries.ITEMS.register(stone_tool.setRegistryName("stone_tool"));
        ForgeRegistries.ITEMS.register(wood_tool.setRegistryName("wood_tool"));

        ForgeRegistries.ITEMS.register(emerald_crop_seeds.setRegistryName("emerald_crop_seeds"));
        ForgeRegistries.ITEMS.register(apple_reeds.setRegistryName("apple_reeds"));
        ForgeRegistries.ITEMS.register(diamond_crop_seed.setRegistryName("diamond_crop_seed"));
        ForgeRegistries.ITEMS.register(gold_crop_seed.setRegistryName("gold_crop_seed"));
        ForgeRegistries.ITEMS.register(iron_crop_seed.setRegistryName("iron_crop_seed"));
        ForgeRegistries.ITEMS.register(emerald_stem_crop_seed.setRegistryName("emerald_stem_crop_seed"));
        ForgeRegistries.ITEMS.register(coal_crop_seed.setRegistryName("coal_crop_seed"));
        ForgeRegistries.ITEMS.register(quartz_crop_seed.setRegistryName("quartz_crop_seed"));
        ForgeRegistries.ITEMS.register(lapis_crop_seed.setRegistryName("lapis_crop_seed"));
        ForgeRegistries.ITEMS.register(redstone_crop_seed.setRegistryName("redstone_crop_seed"));

        ForgeRegistries.ITEMS.register(emerald_powder.setRegistryName("emerald_powder"));
        ForgeRegistries.ITEMS.register(elytra_left.setRegistryName("elytra_left"));
        ForgeRegistries.ITEMS.register(elytra_right.setRegistryName("elytra_right"));
        ForgeRegistries.ITEMS.register(nether_star_small.setRegistryName("nether_star_small"));
        ForgeRegistries.ITEMS.register(totem_small.setRegistryName("totem_small"));
        ForgeRegistries.ITEMS.register(space_patch.setRegistryName("space_patch"));
        ForgeRegistries.ITEMS.register(space_ingot.setRegistryName("space_ingot"));
        ForgeRegistries.ITEMS.register(space_core.setRegistryName("space_core"));
        ForgeRegistries.ITEMS.register(space_line.setRegistryName("space_line"));
        ForgeRegistries.ITEMS.register(salt.setRegistryName("salt"));
        ForgeRegistries.ITEMS.register(salt_wash.setRegistryName("salt_wash"));
        ForgeRegistries.ITEMS.register(dragonString.setRegistryName("dragon_string"));
    }

    //	private static void registerRegistryName(Item items) {
//	    ForgeRegistries.ITEMS.register(items.setRegistryName(items.getUnlocalizedName()));
//	}
    @SideOnly(Side.CLIENT)
    public static void registerRenders()//zhucecaizhi
    {
        OBJLoader.INSTANCE.addDomain(Tmod.MOD_ID);

        registerRender(bad_apple);
        registerRender(gold_diamond_bread);
        registerRender(emerald_apple, 0);
        registerRender(emerald_apple, 1);
        registerRender(diamond_apple, 0);
        registerRender(diamond_apple, 1);
        registerRender(iron_apple, 0);
        registerRender(iron_apple, 1);
        registerRender(coal_apple, 0);
        registerRender(coal_apple, 1);
        registerRender(quartz_apple, 0);
        registerRender(quartz_apple, 1);
        registerRender(lapis_apple, 0);
        registerRender(lapis_apple, 1);
        registerRender(redstone_apple, 0);
        registerRender(redstone_apple, 1);
        registerRender(glowstone_apple, 0);
        registerRender(glowstone_apple, 1);
        registerRender(debuff_apple, 0);
        registerRender(debuff_apple, 1);
        registerRender(salt_cattle_meat);
        registerRender(salt_chicken_meat);
        registerRender(salt_fish_meat);
        registerRender(salt_meat);
        registerRender(cooking_salt_meat);
        registerRender(salt_pig_meat);
        registerRender(salt_rotten_meat);
        registerRender(salt_sheep_meat);

        registerRender(emerald_helmet);
        registerRender(emerald_breastplate);
        registerRender(emerald_leggings);
        registerRender(emerald_shoes);
        registerRender(emerald_ingot);
        registerRender(op_helmet);
        registerRender(op_chestplate);
        registerRender(op_leggings);
        registerRender(op_boots);
        registerRender(op_sword);
        registerRender(op_pickaxe);
        registerRender(dragonCrystal);
        registerRender(dragonHead);
        registerRender(dragonChest);
        registerRender(dragonLegs);
        registerRender(dragonFeet);
        registerRender(dragonSword);
        registerRender(dragonPickaxe);
        registerRender(dragonBow);
        registerRender(space_helmet);
        registerRender(space_chestplate);
        registerRender(space_leggings);
        registerRender(space_boots);
        registerRender(space_sword);
        registerRender(space_bow);
        registerRender(space_pickaxe);

        registerRender(emerald_axe);
        registerRender(emerald_hoe);
        registerRender(emerald_pickaxe);
        registerRender(emerald_shovel);
        registerRender(emerald_sword);
        registerRender(hengShuang);
        registerRender(tianHuo);
        registerRender(complexPowder);
        registerRender(change_powder);

        registerRender(ruby);
        registerRender(ruby_axe);
        registerRender(ruby_boots);
        registerRender(ruby_chestplate);
        registerRender(ruby_helmet);
        registerRender(ruby_hoe);
        registerRender(ruby_leggings);
        registerRender(ruby_pickaxe);
        registerRender(ruby_shovel);
        registerRender(ruby_sword);

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

        registerRender(emerald_ingot_block);
        registerRender(tallgrass_block);
        registerRender(emerald_ingot_ore);
        registerRender(emerald_tree, 0);
        registerRender(emerald_leaf, 0);
        registerRender(emerald_sapling);
        registerRender(power_extractor, 0);
        registerRender(boss_block);
        registerRender(bossBlock1);
        registerRender(emerald_chest);
        registerRender(space_ore);
        registerRender(space_block);
        registerRender(salt_ore);
        registerRender(lucky_block);
        registerRender(unlucky_block);
        registerRender(ruby_ore);
        registerRender(mine);
        registerRender(speed_torch);
        registerRender(elevator);
        registerRender(netheriteBlock);
        registerRender(ancientDebris);

        registerRender(golden_tnt);
        registerRender(tmod_music1);
        registerRender(tmod_music2);
        registerRender(tmod_music3);
        registerRender(exp_small);
        registerRender(exp_big);
        registerRender(storage_ring_big);
        registerRender(storage_ring_in);
        registerRender(storage_ring_small);
        registerRender(space_villager_egg);
        registerRender(player_level);
        registerRender(upgrade_gem);
        registerRender(domb_remover);

        registerRender(diamond_tool);
        registerRender(gold_tool);
        registerRender(iron_tool);
        registerRender(stone_tool);
        registerRender(wood_tool);

        registerRender(emerald_crop_seeds);
        registerRender(apple_reeds);
        registerRender(diamond_crop_seed);
        registerRender(gold_crop_seed);
        registerRender(iron_crop_seed);
        registerRender(emerald_stem_crop_seed);
        registerRender(quartz_crop_seed);
        registerRender(coal_crop_seed);
        registerRender(lapis_crop_seed);
        registerRender(redstone_crop_seed);

        registerRender(emerald_powder);
        registerRender(elytra_left);
        registerRender(elytra_right);
        registerRender(nether_star_small);
        registerRender(totem_small);
        registerRender(space_patch);
        registerRender(space_ingot);
        registerRender(space_core);
        registerRender(space_line);
        registerRender(salt);
        registerRender(salt_wash);
        registerRender(dragonString);
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
