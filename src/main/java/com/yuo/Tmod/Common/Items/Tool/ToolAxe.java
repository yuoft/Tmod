package com.yuo.Tmod.Common.Items.Tool;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe {

    public ToolAxe(String name, ToolMaterial toolmaterial) {
        super(toolmaterial, 8.0f, -3.0f);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);
        this.setHarvestLevel("axe", toolmaterial.getHarvestLevel());
    }
}
