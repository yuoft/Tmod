package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Tmod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

//武器和工具材料
public class TmodMaterial {
    public static ItemArmor.ArmorMaterial EMERALD_ARMOR = addArmorMaterial("emerald", 35, new int[]{5, 8, 10, 5}, 13, 3.0F, ItemLoader.emeraldIngot);
    public static ItemArmor.ArmorMaterial OP = addArmorMaterial("op", -1, new int[]{3, 8, 6, 3}, 0, 0, Item.getItemFromBlock(Blocks.BEDROCK));
    public static ItemArmor.ArmorMaterial SPACE_ARMOR = addArmorMaterial("space", 50, new int[]{17, 20, 25, 17}, 30, 5, ItemLoader.spaceIngot);
    public static ItemArmor.ArmorMaterial RUBY_ARMOR = addArmorMaterial("ruby", 25, new int[]{3, 6, 8, 3}, 11, 2.5f, ItemLoader.ruby);
    public static ItemArmor.ArmorMaterial DRAGON_ARMOR = addArmorMaterial("dragon", 40, new int[]{12, 14, 17, 12}, 22, 3.5f, ItemLoader.dragonCrystal);
    public static ItemArmor.ArmorMaterial BEACON_ARMOR = addArmorMaterial("beacon", 28, new int[]{6, 9, 11, 6}, 18, 1.5f, Item.getItemFromBlock(Blocks.BEACON));
    public static ItemArmor.ArmorMaterial GLOWSTONE_ARMOR = addArmorMaterial("glowstone", 18, new int[]{2, 5, 6, 2}, 8, 0.5f, Item.getItemFromBlock(Blocks.GLOWSTONE));
    public static ItemArmor.ArmorMaterial OBSIDIAN_ARMOR = addArmorMaterial("obsidian", 37, new int[]{5, 8, 10, 5}, 13, 4, Item.getItemFromBlock(Blocks.OBSIDIAN));
    public static ItemArmor.ArmorMaterial NETHERITE_ARMOR = addArmorMaterial("netherite", 36, new int[]{8, 10, 13, 8}, 19, 3.0f, ItemLoader.netheriteIngot);
    public static ItemArmor.ArmorMaterial TOTEM_ARMOR = addArmorMaterial("totem", 23, new int[]{4, 7, 9, 4}, 15, 1.0f, Items.TOTEM_OF_UNDYING);
    public static ItemArmor.ArmorMaterial XRAY_ARMOR = addArmorMaterial("xray", 33, new int[]{7, 9, 12, 7}, 17, 2.5f, Items.TOTEM_OF_UNDYING);
    public static ItemArmor.ArmorMaterial SUPER_XRAY_ARMOR = addArmorMaterial("super_xray", 42, new int[]{11, 13, 16, 11}, 20, 3.5f, Items.TOTEM_OF_UNDYING);
    public static ItemArmor.ArmorMaterial SUPER_ARMOR = addArmorMaterial("super", 39, new int[]{9, 11, 14, 9}, 18, 3.0f, Items.TOTEM_OF_UNDYING);
    public static ItemArmor.ArmorMaterial ULTRA_ARMOR = addArmorMaterial("ultra", 45, new int[]{15, 17, 20, 15}, 25, 4.0f, Items.TOTEM_OF_UNDYING);

    public static Item.ToolMaterial SPACE_TOOL = addToolMaterial("space", 7, 3115, 30, 25, 30, ItemLoader.spaceIngot);
    public static Item.ToolMaterial EMERALD_TOOL = addToolMaterial("emerald", 3, 1634, 10.0f, 5.0f, 12, ItemLoader.emeraldIngot);
    public static Item.ToolMaterial BH3 = addToolMaterial("bh3", 4, 233, 10.0f, 15.0f, 15, Items.NETHER_STAR);
    public static Item.ToolMaterial RUBY_TOOL = addToolMaterial("ruby", 3, 1123, 8.0f, 3.0f, 10, ItemLoader.ruby);
    public static Item.ToolMaterial DRAGON_TOOL = addToolMaterial("dragon", 5, 2123, 20.0f, 15.0f, 25, ItemLoader.dragonCrystal);
    public static Item.ToolMaterial GLOWSTONE_TOOL = addToolMaterial("glowstone", 1, 356, 6.0f, 1.5f, 13, Item.getItemFromBlock(Blocks.GLOWSTONE));
    public static Item.ToolMaterial BEACON_TOOL = addToolMaterial("beacon", 2, 556, 8.0f, 7.0f, 18, Item.getItemFromBlock(Blocks.BEACON));
    public static Item.ToolMaterial OBSIDIAN_TOOL = addToolMaterial("obsidian", 3, 978, 10.0f, 5.0f, 13, Item.getItemFromBlock(Blocks.OBSIDIAN));
    public static Item.ToolMaterial NETHERITE_TOOL = addToolMaterial("netherite", 4, 1789, 15.0f, 10.0f, 20, ItemLoader.netheriteIngot);
    public static Item.ToolMaterial TOTEM_TOOL = addToolMaterial("totem", 2, 469, 8.0f, 6.5f, 15, Items.TOTEM_OF_UNDYING);
    public static Item.ToolMaterial XRAY_TOOL = addToolMaterial("xray", 3, 1548, 12.0f, 8f, 16, Items.TOTEM_OF_UNDYING);
    public static Item.ToolMaterial SUPER_XRAY_TOOL = addToolMaterial("super_xray", 5, 2547, 21.0f, 17f, 21, Items.TOTEM_OF_UNDYING);
    public static Item.ToolMaterial SUPER_TOOL = addToolMaterial("super", 4, 2134, 17.0f, 12f, 17, Items.TOTEM_OF_UNDYING);
    public static Item.ToolMaterial ULTRA_TOOL = addToolMaterial("ultra", 6, 2789, 25.0f, 20f, 25, Items.TOTEM_OF_UNDYING);

    private static ItemArmor.ArmorMaterial addArmorMaterial(String name, int damage, int[] armor, int enchant, float toughness, Item item) {
        return EnumHelper.addArmorMaterial(name, Tmod.MOD_ID + ":" + name, damage, armor, enchant, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughness).setRepairItem(new ItemStack(item));
    }

    private static Item.ToolMaterial addToolMaterial(String name, int level, int maxUses, float efficiency, float damage, int enchant, Item item) {
        return EnumHelper.addToolMaterial(name, level, maxUses, efficiency, damage, enchant).setRepairItem(new ItemStack(item));
    }

}
