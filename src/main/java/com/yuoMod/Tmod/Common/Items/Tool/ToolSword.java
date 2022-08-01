package com.yuoMod.Tmod.Common.Items.Tool;

import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword {
    public ToolSword(String name, ToolMaterial toolmaterial) {
        super(toolmaterial);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }
}
