package com.yuoMod.Tmod.Common.Items;


import com.yuoMod.Tmod.tmod;
import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Crop.emerald_crop_seeds;
import com.yuoMod.Tmod.Common.ItemBlock.item_block;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class itemLoader 
{
	public static final Item bad_apple=new item_food_Bad("bad_apple",6, 0.5f, false);
	public static final Item gold_diamond_bread=new item_food_Good("gold_diamond_bread",6, 0.5f, false);
	//绿宝石锭盔甲材料
	public static final ArmorMaterial EMERALD=EnumHelper.addArmorMaterial("emerald", tmod.MODID+":emerald",330, new int[]{40, 70, 90, 40}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 30.0F);
	public static final Item emerald_ingot=new item_emerald_ingot("emerald_ingot");
	public static final ItemArmor emerald_helmet=new item_EmeraldArmor("emerald_helmet", EMERALD, 1, EntityEquipmentSlot.HEAD);
	public static final ItemArmor emerald_breastplate=new item_EmeraldArmor("emerald_breastplate", EMERALD, 1, EntityEquipmentSlot.CHEST);
	public static final ItemArmor emerald_leggings=new item_EmeraldArmor("emerald_leggings", EMERALD, 2, EntityEquipmentSlot.LEGS);
	public static final ItemArmor emerald_shoes=new item_EmeraldArmor("emerald_shoes", EMERALD, 1, EntityEquipmentSlot.FEET);
	//绿宝石锭工具材料
	public static final Item emerald_powder=new emerald_powder("emerald_powder");
	public static final Item change_powder=new change_powder("change_powder");
	public static final ToolMaterial EMERALD_INGOT=EnumHelper.addToolMaterial(itemLoader.emerald_ingot.toString(), 4, 25000, 120.0f, 50.0f, 15);
	public static final emerald_axe emerald_axe=new emerald_axe("emerald_axe",EMERALD_INGOT);
	public static final emerald_hoe emerald_hoe=new emerald_hoe("emerald_hoe",EMERALD_INGOT);
	public static final emerald_pickaxe emerald_pickaxe=new emerald_pickaxe("emerald_pickaxe",EMERALD_INGOT);
	public static final emerald_shovel emerald_shovel=new emerald_shovel("emerald_shovel",EMERALD_INGOT);
	public static final emerald_sword emerald_sword=new emerald_sword("emerald_sword",EMERALD_INGOT);
	
	public static final item_block emerald_ingot_block=new item_block(blockLoader.emerald_ingot_block);
	public static final item_block tallgrass_block = new item_block(blockLoader.tallgrass_block);
	public static final item_block emerald_ingot_ore = new item_block(blockLoader.emerald_ingot_ore);
	public static final item_block emerald_tree = new item_block(blockLoader.emerald_tree);
	public static final item_block emerald_leaf = new item_block(blockLoader.emerald_leaf);
	public static final item_block emerald_sapling = new item_block(blockLoader.emerald_sapling);
	public static final item_block power_extractor = new item_block(blockLoader.power_extractor);
	//作物
	public static final emerald_crop_seeds emerald_crop_seeds=new emerald_crop_seeds("emerald_crop_seeds");
	//注册item的id
	public static void init(FMLPreInitializationEvent event)
	{
		ForgeRegistries.ITEMS.register(bad_apple.setRegistryName("bad_apple"));
		ForgeRegistries.ITEMS.register(gold_diamond_bread.setRegistryName("gold_diamond_bread"));
		
		ForgeRegistries.ITEMS.register(emerald_helmet.setRegistryName("emerald_helmet"));
		ForgeRegistries.ITEMS.register(emerald_breastplate.setRegistryName("emerald_breastplate"));
		ForgeRegistries.ITEMS.register(emerald_leggings.setRegistryName("emerald_leggings"));
		ForgeRegistries.ITEMS.register(emerald_shoes.setRegistryName("emerald_shoes"));
		ForgeRegistries.ITEMS.register(emerald_ingot.setRegistryName("emerald_ingot"));
		
		ForgeRegistries.ITEMS.register(emerald_axe.setRegistryName("emerald_axe"));
		ForgeRegistries.ITEMS.register(emerald_hoe.setRegistryName("emerald_hoe"));
		ForgeRegistries.ITEMS.register(emerald_pickaxe.setRegistryName("emerald_pickaxe"));
		ForgeRegistries.ITEMS.register(emerald_shovel.setRegistryName("emerald_shovel"));
		ForgeRegistries.ITEMS.register(emerald_sword.setRegistryName("emerald_sword"));
		ForgeRegistries.ITEMS.register(emerald_powder.setRegistryName("emerald_powder"));
		ForgeRegistries.ITEMS.register(change_powder.setRegistryName("change_powder"));
		
		ForgeRegistries.ITEMS.register(emerald_ingot_block.setRegistryName("emerald_ingot_block"));
		ForgeRegistries.ITEMS.register(tallgrass_block.setRegistryName("tallgrass_block"));
		ForgeRegistries.ITEMS.register(emerald_ingot_ore.setRegistryName("emerald_ingot_ore"));
		ForgeRegistries.ITEMS.register(emerald_tree.setRegistryName("emerald_tree"));
		ForgeRegistries.ITEMS.register(emerald_leaf.setRegistryName("emerald_leaf"));
		ForgeRegistries.ITEMS.register(emerald_sapling.setRegistryName("emerald_sapling"));
		ForgeRegistries.ITEMS.register(power_extractor.setRegistryName("power_extractor"));
		
		ForgeRegistries.ITEMS.register(emerald_crop_seeds.setRegistryName("emerald_crop_seeds"));
	}
//	private static void register(Item items, String name)
//	{
//	    ForgeRegistries.ITEMS.register(items.setRegistryName(name));
//	}
	@SideOnly(Side.CLIENT)
	public static void registerRenders()//zhucecaizhi
	{
		registerRender(bad_apple);
		registerRender(gold_diamond_bread);
		
		registerRender(emerald_helmet);
		registerRender(emerald_breastplate);
		registerRender(emerald_leggings);
		registerRender(emerald_shoes);
		registerRender(emerald_ingot);

		registerRender(emerald_axe);
		registerRender(emerald_hoe);
		registerRender(emerald_pickaxe);
		registerRender(emerald_shovel);
		registerRender(emerald_sword);
		registerRender(emerald_powder);
		registerRender(change_powder);
		
		registerRender(emerald_ingot_block);
		registerRender(tallgrass_block);
		registerRender(emerald_ingot_ore);
		registerRender(emerald_tree);
		registerRender(emerald_leaf);
		registerRender(emerald_sapling);
		registerRender(power_extractor);
		
		registerRender(emerald_crop_seeds);
	}
	@SideOnly(Side.CLIENT)
	public static void registerRender(Item item)
	{
		ModelResourceLocation model=new ModelResourceLocation(item.getRegistryName(),"inventory");//模型路径
		ModelLoader.setCustomModelResourceLocation(item, 0, model);//物品id，物品状态，物品模型
	}
//	@SideOnly(Side.CLIENT)
//	public static void registerRender02(Item item)
//	{
//		ModelResourceLocation model=new ModelResourceLocation(item.getRegistryName(),"inventory");
//		ModelLoader.setCustomModelResourceLocation(item, 0, model);
//	}
	/*
	register(bad_apple, "bad_apple");
	register(gold_diamond_bread, "gold_diamond_bread");
	
	register(emerald_helmet, "emerald_helmet");
	register(emerald_breastplate, "emerald_breastplate");
	register(emerald_leggings, "emerald_leggings");
	register(emerald_shoes, "emerald_shoes");
	register(emerald_ingot, "emerald_ingot");
	
	register(emerald_axe, "emerald_axe");
	register(emerald_hoe, "emerald_hoe");
	register(emerald_pickaxe, "emerald_pickaxe");
	register(emerald_shovel, "emerald_shovel");
	register(emerald_sword, "emerald_sword");
	register(emerald_sapling, "emerald_sapling");
	register(change_powder, "change_powder");
	
	register(emerald_ingot_block, "emerald_ingot_block");
	register(tallgrass_block, "tallgrass_block");
	register(emerald_ingot_ore, "emerald_ingot_ore");
	register(emerald_tree, "emerald_tree");
	register(emerald_leaf, "emerald_leaf");
	register(emerald_sapling, "emerald_sapling");
	register(power_extractor, "power_extractor");
	
	register(emerald_crop_seeds, "emerald_crop_seeds");
	*/
}
