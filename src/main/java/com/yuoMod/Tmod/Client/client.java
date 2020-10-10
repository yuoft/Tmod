package com.yuoMod.Tmod.Client;

import com.yuoMod.Tmod.Common.common;
import com.yuoMod.Tmod.Common.Items.itemRenderLoader;
import com.yuoMod.Tmod.Entity.EntityRenderLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class client extends common
{
	@Override
    public void preInit(FMLPreInitializationEvent event)
    {
    	super.preInit(event);
    	new itemRenderLoader();//物品模型渲染
    	new EntityRenderLoader();//生物模型渲染
    }

	@Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new KeyLoader();//快捷键注册
    }
	@Override
    public void postInit(FMLPostInitializationEvent event)
    {
    	super.postInit(event);
    }
}
