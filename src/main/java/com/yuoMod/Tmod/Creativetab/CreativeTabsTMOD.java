package com.yuoMod.Tmod.Creativetab;

import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsTMOD extends CreativeTabs
{

	public CreativeTabsTMOD()
    {
        super("tmod");
    }

    @Override
    public ItemStack getTabIconItem()
    {
    	ItemStack is=new ItemStack(itemLoader.emerald_ingot);
        return is;
    }
}
