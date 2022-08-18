package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class Slow extends Enchantment {
    Slow(String name) {
        super(Rarity.COMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != Enchantments.EFFICIENCY;
    }

}
