package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class tmodMusicRecord extends ItemRecord {
    protected tmodMusicRecord(String name, SoundEvent soundIn) {
        super(name, soundIn);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setUnlocalizedName(name);
    }

}
