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
    	new itemRenderLoader();//��Ʒģ����Ⱦ
    	new EntityRenderLoader();//����ģ����Ⱦ
    }

	@Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new KeyLoader();//��ݼ�ע��
    }
	@Override
    public void postInit(FMLPostInitializationEvent event)
    {
    	super.postInit(event);
    }
}
