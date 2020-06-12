package com.yuoMod.Tmod.Creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader
{
    public static CreativeTabs TMOD;

    public CreativeTabsLoader(FMLPreInitializationEvent event)
    {
        TMOD = new CreativeTabsTMOD();//Add a creation mode item column
    }
}