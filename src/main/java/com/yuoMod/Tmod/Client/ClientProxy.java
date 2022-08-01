package com.yuoMod.Tmod.Client;

import com.yuoMod.Tmod.Common.CommonProxy;
import com.yuoMod.Tmod.Common.Items.ItemRenderLoader;
import com.yuoMod.Tmod.Entity.EntityRenderLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new ItemRenderLoader();//��Ʒģ����Ⱦ
        new EntityRenderLoader();//����ģ����Ⱦ
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        new KeyLoader();//��ݼ�ע��
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
