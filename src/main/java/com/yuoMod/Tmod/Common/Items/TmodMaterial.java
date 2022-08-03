package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Tmod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

//武器和工具材料
public class TmodMaterial {
    public static ItemArmor.ArmorMaterial EMERALD_ARMOR = addArmorMaterial("emerald", 35, new int[]{5, 8, 10, 5}, 12, 5.0F, ItemLoader.emeraldIngot);
    public static ItemArmor.ArmorMaterial OP = addArmorMaterial("op", -1, new int[]{3, 8, 6, 3}, 0, 0, Item.getItemFromBlock(Blocks.BEDROCK));
    public static ItemArmor.ArmorMaterial SPACE_ARMOR = addArmorMaterial("space", 100, new int[]{15, 17, 20, 15}, 30, 10, ItemLoader.spaceIngot);
    public static ItemArmor.ArmorMaterial RUBY_ARMOR = addArmorMaterial("ruby", 30, new int[]{4, 7, 9, 4}, 10, 2.5f, ItemLoader.ruby);
    public static ItemArmor.ArmorMaterial DRAGON_ARMOR = addArmorMaterial("dragon", 55, new int[]{10, 12, 15, 10}, 20, 5, ItemLoader.dragonCrystal);
    public static ItemArmor.ArmorMaterial BEACON_ARMOR = addArmorMaterial("beacon", 29, new int[]{6, 8, 10, 6}, 13, 1.5f, Item.getItemFromBlock(Blocks.BEACON));
    public static ItemArmor.ArmorMaterial GLOWSTONE_ARMOR = addArmorMaterial("glowstone", 18, new int[]{2, 5, 6, 2}, 11, 0.5f, Item.getItemFromBlock(Blocks.GLOWSTONE));
    public static ItemArmor.ArmorMaterial OBSIDIAN_ARMOR = addArmorMaterial("obsidian", 36, new int[]{7, 9, 11, 7}, 18, 5, Item.getItemFromBlock(Blocks.OBSIDIAN));
    public static ItemArmor.ArmorMaterial NETHERITE_ARMOR = addArmorMaterial("netherite", 40, new int[]{9, 11, 14, 9}, 18, 3, ItemLoader.netheriteIngot);
    public static ItemArmor.ArmorMaterial TOTEM_ARMOR = addArmorMaterial("totem", 31, new int[]{6, 8, 10, 6}, 18, 0.5f, Items.TOTEM_OF_UNDYING);

    public static Item.ToolMaterial SPACE_TOOL = addToolMaterial("space", 5, 3315, 20, 30, 20, ItemLoader.spaceIngot);
    public static Item.ToolMaterial EMERALD_TOOL = addToolMaterial("emerald", 3, 1634, 10.0f, 5.0f, 12, ItemLoader.emeraldIngot);
    public static Item.ToolMaterial BH3 = addToolMaterial("bh3", 4, 666, 15.0f, 20.0f, 15, Items.NETHER_STAR);
    public static Item.ToolMaterial RUBY_TOOL = addToolMaterial("ruby", 3, 1123, 8.0f, 3.0f, 10, ItemLoader.ruby);
    public static Item.ToolMaterial DRAGON_TOOL = addToolMaterial("dragon", 4, 2123, 15.0f, 20.0f, 20, ItemLoader.dragonCrystal);
    public static Item.ToolMaterial GLOWSTONE_TOOL = addToolMaterial("glowstone", 2, 456, 11.0f, 1.5f, 13, Item.getItemFromBlock(Blocks.GLOWSTONE));
    public static Item.ToolMaterial BEACON_TOOL = addToolMaterial("beacon", 3, 556, 11.0f, 4.0f, 19, Item.getItemFromBlock(Blocks.BEACON));
    public static Item.ToolMaterial OBSIDIAN_TOOL = addToolMaterial("obsidian", 3, 978, 10.0f, 3.0f, 13, Item.getItemFromBlock(Blocks.OBSIDIAN));
    public static Item.ToolMaterial NETHERITE_TOOL = addToolMaterial("netherite", 3, 1789, 14.0f, 7.0f, 18, ItemLoader.netheriteIngot);
    public static Item.ToolMaterial TOTEM_TOOL = addToolMaterial("totem", 3, 996, 16.0f, 3.5f, 18, Items.TOTEM_OF_UNDYING);

    private static ItemArmor.ArmorMaterial addArmorMaterial(String name, int damage, int[] armor, int enchant, float toughness, Item item) {
        return EnumHelper.addArmorMaterial(name, Tmod.MOD_ID + ":" + name, damage, armor, enchant, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, toughness).setRepairItem(new ItemStack(item));
    }

    private static Item.ToolMaterial addToolMaterial(String name, int level, int maxUses, float efficiency, float damage, int enchant, Item item) {
        return EnumHelper.addToolMaterial(name, level, maxUses, efficiency, damage, enchant).setRepairItem(new ItemStack(item));
    }

}
