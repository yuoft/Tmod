package com.yuoMod.Tmod.TileEntity;

import com.yuoMod.Tmod.Tmod;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
    public TileEntityLoader(FMLPreInitializationEvent event) {
        registerTileEntity(MyTileEntity.class, "MyTileEntity");
        registerTileEntity(NineTileEntity.class, "NineTileEntity");
        registerTileEntity(TileTorcherino.class, "tileTorcherino");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        // ��һ����������Ҫע��� TileEntity ��Ӧ�� class ���󡣱��� extends TileEntity��
        // �ڶ���������Ҫ��ͷ����ע����һ�¡�
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(Tmod.MOD_ID, id));
    }
}
