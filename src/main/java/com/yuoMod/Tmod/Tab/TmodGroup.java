package com.yuoMod.Tmod.Tab;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TmodGroup {
    public static CreativeTabs TMOD;

    public TmodGroup(FMLPreInitializationEvent event) {
        TMOD = new CreativeTabsTMOD();//添加创造物品栏
    }

    static class CreativeTabsTMOD extends CreativeTabs {

        public CreativeTabsTMOD() {
            super("tmod");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemLoader.emeraldIngot);
        }
    }
}