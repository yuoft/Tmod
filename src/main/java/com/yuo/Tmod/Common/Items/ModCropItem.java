package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.item.Item;

public class ModCropItem extends Item {

    public ModCropItem(String name){
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.CROP_TAB);
        this.setMaxStackSize(64);
    }
}
