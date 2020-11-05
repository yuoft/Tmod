package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Crafting.CraftingLoader;
import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;
import com.yuoMod.Tmod.Enchantment.enchantmentLoader;
import com.yuoMod.Tmod.Entity.EntityLoader;
import com.yuoMod.Tmod.Fluid.fluidLoader;
import com.yuoMod.Tmod.Gui.guiLoader;
import com.yuoMod.Tmod.Potion.potionLoader;
import com.yuoMod.Tmod.TileEntity.TileEntityLoader;
import com.yuoMod.Tmod.WorldCreate.WorldOreLoader;
import com.yuoMod.Tmod.WorldCreate.WorldTreeLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class common
{
    public void preInit(FMLPreInitializationEvent event)
    {
//    	new ConfigLoader();//配置文件
//    	new ConfigGuiFactory();
    	new CreativeTabsLoader(event);//创造物品栏注册
    	new fluidLoader(event);//流体注册
    	itemLoader.init(event);//注册物品
    	new blockLoader(event);//方块注册
    	new potionLoader(event);//药水状态注册
    	new EntityLoader();//实体注册
    	new TileEntityLoader(event);//TileEntity注册
    }

    public void init(FMLInitializationEvent event)
    {
    	new CraftingLoader();//熔炉配方注册
    	new enchantmentLoader();//附魔注册
    	new eventLoader();//事件注册
    	new WorldOreLoader();//世界生成与矿物生成注册
    	new WorldTreeLoader();
    	new guiLoader();//gui注册
    }
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
