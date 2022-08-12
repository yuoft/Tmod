package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class TmodMusicRecord extends ItemRecord {
    protected TmodMusicRecord(String name, SoundEvent soundIn) {
        super(name, soundIn);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setUnlocalizedName(name);
    }

}
