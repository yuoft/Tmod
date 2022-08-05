package com.yuo.Tmod.TileEntity;

import com.yuo.Tmod.Tmod;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
    public TileEntityLoader(FMLPreInitializationEvent event) {
        registerTileEntity(PowerTileEntity.class, "PowerTileEntity");
        registerTileEntity(NineTileEntity.class, "NineTileEntity");
        registerTileEntity(TileTorcherino.class, "tileTorcherino");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        // 第一个参数是你要注册的 TileEntity 对应的 class 对象。必须 extends TileEntity。
        // 第二个参数的要求和方块的注册名一致。
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(Tmod.MOD_ID, id));
    }
}
