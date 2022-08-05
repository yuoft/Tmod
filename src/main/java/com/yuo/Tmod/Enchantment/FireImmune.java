package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class FireImmune extends Enchantment {
    private static final EntityEquipmentSlot[] SLOTS = {EntityEquipmentSlot.LEGS};//生效位置
    private static final EnumEnchantmentType TYPE = EnumEnchantmentType.ARMOR_LEGS; //装备位置

    public FireImmune(String name) {
        super(Rarity.RARE, TYPE, SLOTS);
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
        // rarity 代表了这个附魔的稀有程度，可以是 COMMON、UNCOMMON、RARE 或 VERY_RARE。
        // type 代表了这个附魔可以加在什么工具/武器/装备上。
        // slots 代表了“这个附魔加在什么格子里装的工具/武器/装备上才有效果”，例如荆棘只在盔甲四件套上有效。
        // slots 会影响 getEnchantedItem（func_92099_a）的返回值，这个方法用于获取某个实体上有指定附魔的物品。
    }

    // 附魔的最低可能等级
    @Override
    public int getMinLevel() {
        return 1;
    }

    //附魔的最高可能等级
    @Override
    public int getMaxLevel() {
        return 1;
    }

    //得到附魔的最低可能等级
    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    //得到附魔的最高可能等级
    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 15;
    }

    //能否与其它附魔共存
    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && Enchantment.getEnchantmentByID(1) != null;
    }

}
