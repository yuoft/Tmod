package com.yuoMod.Tmod.Common.Items.Tool;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe {
    public ToolPickaxe(String name, ToolMaterial toolmaterial) {
        super(toolmaterial);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("pickaxe", toolmaterial.getHarvestLevel());
    }
}
