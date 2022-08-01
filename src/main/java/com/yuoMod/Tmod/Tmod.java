package com.yuoMod.Tmod;

import com.yuoMod.Tmod.Common.MobInfoCommand;
import com.yuoMod.Tmod.Common.MobLevelCommand;
import com.yuoMod.Tmod.Common.ModLevelCommand;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.yuoMod.Tmod.Common.Common;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Tmod.MOD_ID, name = Tmod.NAME, version = Tmod.VERSION, useMetadata = true,
        acceptedMinecraftVersions = Tmod.AcceptedMinecraftVersions, guiFactory = Tmod.ConfigGuiFactory)
public class Tmod {
    public static final String MOD_ID = "tmod";//modID
    public static final String NAME = "T Mod";//mod名称
    public static final String VERSION = "1.6.8";//mod版本
    public static final String AcceptedMinecraftVersions = "1.12.2";//mod运行游戏版本
    public static final String ConfigGuiFactory = "com.yuoMod.Tmod.Client.ConfigGuiFactory";//可视化配置文件

    @Mod.Instance(Tmod.MOD_ID)
    public static Tmod instance;//生成mod实例

    public static final String client0 = "com.yuoMod.Tmod.Client.Client";//客户端
    public static final String common0 = "com.yuoMod.Tmod.Common.Common";//sever

    @SidedProxy(serverSide = Tmod.common0, clientSide = Tmod.client0)//mod代理
    public static Common proxy;

    public Tmod() {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    //注册指令
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new ModLevelCommand());
        event.registerServerCommand(new MobInfoCommand());
        event.registerServerCommand(new MobLevelCommand());
    }
}
