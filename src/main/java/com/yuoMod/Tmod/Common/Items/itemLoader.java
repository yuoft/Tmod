package com.yuoMod.Tmod.Common.Items;


import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.ItemBlock.item_block;
import com.yuoMod.Tmod.Common.Items.Crops.AllStemCropSeed;
import com.yuoMod.Tmod.Common.Items.Crops.EmeraldCropSeeds;
import com.yuoMod.Tmod.Common.Items.Food.SaltMeat;
import com.yuoMod.Tmod.Common.Items.Food.BuffApple;
import com.yuoMod.Tmod.Common.Items.Food.item_food_Bad;
import com.yuoMod.Tmod.Common.Items.Food.item_food_Good;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.ManyFunTool;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.OPArmor;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.OPSword;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.SpaceArmor;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.SpaceBow;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.SpacePickaxe;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.SpaceSword;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.emerald_axe;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.emerald_hoe;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.emerald_pickaxe;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.emerald_shovel;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.emerald_sword;
import com.yuoMod.Tmod.Common.Items.ToolAndArmor.item_EmeraldArmor;
import com.yuoMod.Tmod.Sound.soundLoader;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class itemLoader 
{
	//食物
	public static final Item bad_apple=new item_food_Bad("bad_apple",6, 0.5f, false);
	public static final Item gold_diamond_bread=new item_food_Good("gold_diamond_bread",6, 0.5f, false);
	public static final Item emerald_apple=new BuffApple("emerald_apple", 6, 0.5f, false);
	public static final Item diamond_apple=new BuffApple("diamond_apple", 6, 0.5f, false);
	public static final Item iron_apple=new BuffApple("iron_apple", 6, 0.5f, false);
	public static final Item coal_apple=new BuffApple("coal_apple", 6, 0.5f, false);
	public static final Item quartz_apple=new BuffApple("quartz_apple", 6, 0.5f, false);
	public static final Item lapis_apple=new BuffApple("lapis_apple", 6, 0.5f, false);
	public static final Item redstone_apple=new BuffApple("redstone_apple", 6, 0.5f, false);
	public static final Item glowstone_apple=new BuffApple("glowstone_apple", 6, 0.5f, false);
	public static final Item debuff_apple=new BuffApple("debuff_apple", 16, 0.8f, false);
	public static final Item salt_pig_meat=new SaltMeat("salt_pig_meat", 5, 0.8f, false);
	public static final Item salt_cattle_meat=new SaltMeat("salt_cattle_meat", 5, 0.8f, false);
	public static final Item salt_chicken_meat=new SaltMeat("salt_chicken_meat", 4, 0.8f, false);
	public static final Item salt_sheep_meat=new SaltMeat("salt_sheep_meat", 4, 0.8f, false);
	public static final Item salt_fish_meat=new SaltMeat("salt_fish_meat", 4, 0.4f, false);
	public static final Item salt_rotten_meat=new SaltMeat("salt_rotten_meat", 6, 0.4f, false);
	public static final Item salt_meat=new SaltMeat("salt_meat", 10, 1.0f, false);
	//绿宝石锭盔甲材料
	//--------------------------------------------------------------------材料名------盔甲模型前缀--------耐久值---------各部位护甲值------附魔能力---音效---------------------------盔甲韧性
	public static final ArmorMaterial EMERALD=EnumHelper.addArmorMaterial("emerald", tmod.MODID+":emerald",300, new int[]{10, 12, 15, 10}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 5.0F);
	public static final Item emerald_ingot=new item_emerald_ingot("emerald_ingot");
	public static final ItemArmor emerald_helmet=new item_EmeraldArmor("emerald_helmet", EMERALD, 3, EntityEquipmentSlot.HEAD);
	public static final ItemArmor emerald_breastplate=new item_EmeraldArmor("emerald_breastplate", EMERALD, 8, EntityEquipmentSlot.CHEST);
	public static final ItemArmor emerald_leggings=new item_EmeraldArmor("emerald_leggings", EMERALD, 6, EntityEquipmentSlot.LEGS);
	public static final ItemArmor emerald_shoes=new item_EmeraldArmor("emerald_shoes", EMERALD, 3, EntityEquipmentSlot.FEET);
	//op套
	public static final ArmorMaterial OP=EnumHelper.addArmorMaterial("op", tmod.MODID+":op",-1, new int[]{0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
	public static final ItemArmor op_helmet=new OPArmor("op_helmet", OP, 3, EntityEquipmentSlot.HEAD);
	public static final ItemArmor op_chestplate=new OPArmor("op_chestplate", OP, 8, EntityEquipmentSlot.CHEST);
	public static final ItemArmor op_leggings=new OPArmor("op_leggings", OP, 6, EntityEquipmentSlot.LEGS);
	public static final ItemArmor op_boots=new OPArmor("op_boots", OP, 3, EntityEquipmentSlot.FEET);
	public static final OPSword op_sword=new OPSword("op_sword");
	//空间套装
	private static final ToolMaterial SPACE=EnumHelper.addToolMaterial("space", 4, 5000, 20.0f, 30.0f, 10);
	public static final ArmorMaterial SPACE_ARMOR=EnumHelper.addArmorMaterial("space_armor", tmod.MODID+":space_armor",500, new int[]{15, 17, 20, 15}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10.0F);
	public static final ItemArmor space_helmet=new SpaceArmor("space_helmet", SPACE_ARMOR, 3, EntityEquipmentSlot.HEAD);
	public static final ItemArmor space_chestplate=new SpaceArmor("space_chestplate", SPACE_ARMOR, 8, EntityEquipmentSlot.CHEST);
	public static final ItemArmor space_leggings=new SpaceArmor("space_leggings", SPACE_ARMOR, 6, EntityEquipmentSlot.LEGS);
	public static final ItemArmor space_boots=new SpaceArmor("space_boots", SPACE_ARMOR, 3, EntityEquipmentSlot.FEET);
	public static final SpaceSword space_sword=new SpaceSword("space_sword",SPACE);
	public static final SpaceBow space_bow=new SpaceBow("space_bow");
	public static final SpacePickaxe space_pickaxe=new SpacePickaxe("space_pickaxe",SPACE);
	//绿宝石锭工具材料
	public static final Item change_powder=new change_powder("change_powder");
	//--------------------------------------------------------------------------名字------------------------挖掘等级--耐久--使用效率-攻击力-附魔能力
	public static final ToolMaterial EMERALD_INGOT=EnumHelper.addToolMaterial(itemLoader.emerald_ingot.toString(), 4, 2500, 15.0f, 20.0f, 15);
	public static final emerald_axe emerald_axe=new emerald_axe("emerald_axe",EMERALD_INGOT);
	public static final emerald_hoe emerald_hoe=new emerald_hoe("emerald_hoe",EMERALD_INGOT);
	public static final emerald_pickaxe emerald_pickaxe=new emerald_pickaxe("emerald_pickaxe",EMERALD_INGOT);
	public static final emerald_shovel emerald_shovel=new emerald_shovel("emerald_shovel",EMERALD_INGOT);
	public static final emerald_sword emerald_sword=new emerald_sword("emerald_sword",EMERALD_INGOT);
	//方块
	public static final item_block emerald_ingot_block=new item_block(blockLoader.emerald_ingot_block);
	public static final item_block tallgrass_block = new item_block(blockLoader.tallgrass_block);
	public static final item_block emerald_ingot_ore = new item_block(blockLoader.emerald_ingot_ore);
	public static final item_block emerald_tree = new item_block(blockLoader.emerald_tree);
	public static final item_block emerald_leaf = new item_block(blockLoader.emerald_leaf);
	public static final item_block emerald_sapling = new item_block(blockLoader.emerald_sapling);
	public static final item_block power_extractor = new item_block(blockLoader.power_extractor);
	public static final item_block boss_block = new item_block(blockLoader.boss_block);
	public static final item_block emerald_chest = new item_block(blockLoader.emerald_chest);
	public static final item_block space_ore = new item_block(blockLoader.space_ore);
	public static final item_block space_block = new item_block(blockLoader.space_block);
	public static final item_block salt_ore = new item_block(blockLoader.salt_ore);
	//特殊物品
	public static final GoldenTNT golden_tnt=new GoldenTNT("golden_tnt");
	public static final PotionFallUse potion_fall_use=new PotionFallUse("potion_fall_use");
	public static final tmodMusicRecord tmod_music1=new tmodMusicRecord("tmod_music1", soundLoader.LTY_JueTiJueMimg);
	public static final tmodMusicRecord tmod_music2=new tmodMusicRecord("tmod_music2", soundLoader.HY_MingJi);
	public static final tmodMusicRecord tmod_music3=new tmodMusicRecord("tmod_music3", soundLoader.CD_Op);
	public static final Item exp_small=new ExpWaterDrop("exp_small");
	public static final Item exp_big=new ExpWaterDrop("exp_big");
	public static final Item storage_ring_big=new StorageRing("storage_ring_big");
	public static final Item storage_ring_in=new StorageRing("storage_ring_in");
	public static final Item storage_ring_small=new StorageRing("storage_ring_small");
	//多功能工具
	public static final ManyFunTool diamond_tool=new ManyFunTool("diamond_tool",-10.0f,-2.0f,EMERALD_INGOT,4);
	public static final ManyFunTool gold_tool=new ManyFunTool("gold_tool",3.0f,-2.2f,ToolMaterial.IRON,2);
	public static final ManyFunTool iron_tool=new ManyFunTool("iron_tool",5.0f,-2.1f,ToolMaterial.DIAMOND,3);
	public static final ManyFunTool stone_tool=new ManyFunTool("stone_tool",3.0f,-2.2f,ToolMaterial.IRON,2);
	public static final ManyFunTool wood_tool=new ManyFunTool("wood_tool",2.0f,-2.3f,ToolMaterial.STONE,1);
	//作物 普通
	public static final EmeraldCropSeeds emerald_crop_seeds=new EmeraldCropSeeds("emerald_crop_seeds");
	//作物 甘蔗
	public static final item_block apple_reeds=new item_block(blockLoader.apple_reeds);
	//作物 茎
	public static final AllStemCropSeed diamond_crop_seed=new AllStemCropSeed("diamond_crop_seed",blockLoader.diamond_crop);
	public static final AllStemCropSeed gold_crop_seed=new AllStemCropSeed("gold_crop_seed",blockLoader.gold_crop);
	public static final AllStemCropSeed iron_crop_seed=new AllStemCropSeed("iron_crop_seed",blockLoader.iron_crop);
	public static final AllStemCropSeed emerald_stem_crop_seed=new AllStemCropSeed("emerald_stem_crop_seed",blockLoader.emerald_stem_crop);
	public static final AllStemCropSeed coal_crop_seed=new AllStemCropSeed("coal_crop_seed",blockLoader.coal_crop);
	public static final AllStemCropSeed quartz_crop_seed=new AllStemCropSeed("quartz_crop_seed",blockLoader.quartz_crop);
	public static final AllStemCropSeed lapis_crop_seed=new AllStemCropSeed("lapis_crop_seed",blockLoader.lapis_crop);
	public static final AllStemCropSeed redstone_crop_seed=new AllStemCropSeed("redstone_crop_seed",blockLoader.redstone_crop);
	//碎片物品 or 通用物品注册
	public static final Item emerald_powder=new ItemsAll("emerald_powder");
	public static final Item elytra_left=new ItemsAll("elytra_left");
	public static final Item elytra_right=new ItemsAll("elytra_right");
	public static final Item nether_star_small=new ItemsAll("nether_star_small");
	public static final Item totem_small=new ItemsAll("totem_small");
	public static final Item space_patch=new ItemsAll("space_patch");
	public static final Item space_ingot=new ItemsAll("space_ingot");
	public static final Item space_core=new ItemsAll("space_core");
	public static final Item space_line=new ItemsAll("space_line");
	public static final Item salt=new ItemsAll("salt");
	public static final Item salt_wash=new ItemsAll("salt_wash");
	//注册item的id
	public static void init(FMLPreInitializationEvent event)
	{
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
		ForgeRegistries.ITEMS.register(change_powder.setRegistryName("change_powder"));
		
		ForgeRegistries.ITEMS.register(emerald_ingot_block.setRegistryName("emerald_ingot_block"));
		ForgeRegistries.ITEMS.register(tallgrass_block.setRegistryName("tallgrass_block"));
		ForgeRegistries.ITEMS.register(emerald_ingot_ore.setRegistryName("emerald_ingot_ore"));
		ForgeRegistries.ITEMS.register(emerald_tree.setRegistryName("emerald_tree"));
		ForgeRegistries.ITEMS.register(emerald_leaf.setRegistryName("emerald_leaf"));
		ForgeRegistries.ITEMS.register(emerald_sapling.setRegistryName("emerald_sapling"));
		ForgeRegistries.ITEMS.register(power_extractor.setRegistryName("power_extractor"));
		ForgeRegistries.ITEMS.register(boss_block.setRegistryName("boss_block"));
		ForgeRegistries.ITEMS.register(emerald_chest.setRegistryName("emerald_chest"));
		ForgeRegistries.ITEMS.register(space_ore.setRegistryName("space_ore"));
		ForgeRegistries.ITEMS.register(space_block.setRegistryName("space_block"));
		ForgeRegistries.ITEMS.register(salt_ore.setRegistryName("salt_ore"));
		
		ForgeRegistries.ITEMS.register(golden_tnt.setRegistryName("golden_tnt"));
		ForgeRegistries.ITEMS.register(potion_fall_use.setRegistryName("potion_fall_use"));
		ForgeRegistries.ITEMS.register(tmod_music1.setRegistryName("tmod_music1"));
		ForgeRegistries.ITEMS.register(tmod_music2.setRegistryName("tmod_music2"));
		ForgeRegistries.ITEMS.register(tmod_music3.setRegistryName("tmod_music3"));
		ForgeRegistries.ITEMS.register(exp_small.setRegistryName("exp_small"));
		ForgeRegistries.ITEMS.register(exp_big.setRegistryName("exp_big"));
		ForgeRegistries.ITEMS.register(storage_ring_big.setRegistryName("storage_ring_big"));
		ForgeRegistries.ITEMS.register(storage_ring_in.setRegistryName("storage_ring_in"));
		ForgeRegistries.ITEMS.register(storage_ring_small.setRegistryName("storage_ring_small"));
		
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
	}
//	private static void register(Item items, String name)
//	{
//	    ForgeRegistries.ITEMS.register(items.setRegistryName(name));
//	}
	@SideOnly(Side.CLIENT)
	public static void registerRenders()//zhucecaizhi
	{
		OBJLoader.INSTANCE.addDomain(tmod.MODID);
		
		registerRender(bad_apple);
		registerRender(gold_diamond_bread);
		registerRender(emerald_apple,0);
		registerRender(emerald_apple,1);
		registerRender(diamond_apple,0);
		registerRender(diamond_apple,1);
		registerRender(iron_apple,0);
		registerRender(iron_apple,1);
		registerRender(coal_apple,0);
		registerRender(coal_apple,1);
		registerRender(quartz_apple,0);
		registerRender(quartz_apple,1);
		registerRender(lapis_apple,0);
		registerRender(lapis_apple,1);
		registerRender(redstone_apple,0);
		registerRender(redstone_apple,1);
		registerRender(glowstone_apple,0);
		registerRender(glowstone_apple,1);
		registerRender(debuff_apple,0);
		registerRender(debuff_apple,1);
		registerRender(salt_cattle_meat);
		registerRender(salt_chicken_meat);
		registerRender(salt_fish_meat);
		registerRender(salt_meat);
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
		registerRender(change_powder);
		
		registerRender(emerald_ingot_block);
		registerRender(tallgrass_block);
		registerRender(emerald_ingot_ore);
		registerRender(emerald_tree,0);
		registerRender(emerald_leaf,0);
		registerRender(emerald_sapling);
		registerRender(power_extractor,0);
		registerRender(boss_block);
		registerRender(emerald_chest);
		registerRender(space_ore);
		registerRender(space_block);
		registerRender(salt_ore);
		
		registerRender(golden_tnt);
		registerRender(potion_fall_use);
		registerRender(tmod_music1);
		registerRender(tmod_music2);
		registerRender(tmod_music3);
		registerRender(exp_small);
		registerRender(exp_big);
		registerRender(storage_ring_big);
		registerRender(storage_ring_in);
		registerRender(storage_ring_small);
		
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
	}
	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item,int meta)//多状态物品注册
	{
		ModelResourceLocation model=new ModelResourceLocation(item.getRegistryName(),"inventory");//模型路径
		ModelLoader.setCustomModelResourceLocation(item, meta, model);//物品id，物品状态，物品模型
	}
	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item)
	{
		ModelResourceLocation model=new ModelResourceLocation(item.getRegistryName(),"inventory");
		ModelLoader.setCustomModelResourceLocation(item, 0, model);
	}
	
}
