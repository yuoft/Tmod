package com.yuo.Tmod.Client;

import com.yuo.Tmod.Common.CommonProxy;
import com.yuo.Tmod.Common.Items.ItemRenderLoader;
import com.yuo.Tmod.Entity.EntityRenderLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new ItemRenderLoader();//物品模型渲染
        new EntityRenderLoader();//生物模型渲染
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        new KeyLoader();//快捷键注册
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
