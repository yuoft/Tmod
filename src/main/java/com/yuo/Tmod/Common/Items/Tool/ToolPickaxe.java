package com.yuo.Tmod.Common.Items.Tool;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe {
    public ToolPickaxe(String name, ToolMaterial toolmaterial) {
        super(toolmaterial);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);
        this.setHarvestLevel("pickaxe", toolmaterial.getHarvestLevel());
    }
}
