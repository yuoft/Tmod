package com.yuoMod.Tmod.Common.Items.Armor;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ModArmor extends ItemArmor {

    public ModArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);//创造模式物品栏
    }

}
