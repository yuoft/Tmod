package com.yuoMod.Tmod.Common.Items.Tool;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe {

    public ToolAxe(String name, ToolMaterial toolmaterial) {
        super(toolmaterial, 8.0f, -3.0f);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("axe", toolmaterial.getHarvestLevel());
    }
}
