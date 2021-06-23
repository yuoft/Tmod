package com.yuoMod.Tmod;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.yuoMod.Tmod.Common.common;

@Mod(modid = tmod.MODID, name = tmod.NAME, version = tmod.VERSION,
acceptedMinecraftVersions = tmod.AcceptedMinecraftVersions,guiFactory = tmod.ConfigGuiFactory)
public class tmod
{
    public static final String MODID = "tmod";//modID
    public static final String NAME = "T Mod";//mod名称
    public static final String VERSION = "1.5.1";//mod版本
    public static final String AcceptedMinecraftVersions="1.12.2";//mod运行游戏版本
    public static final String ConfigGuiFactory="com.yuoMod.Tmod.Common.ConfigGuiFactory";//可视化配置文件

    @Mod.Instance(tmod.MODID)
    public static tmod instance;//生成mod实例
    
    public static final String client0="com.yuoMod.Tmod.Client.client";//客户端
    public static final String common0="com.yuoMod.Tmod.Common.common";//sever
    
    @SidedProxy(serverSide = tmod.common0,clientSide = tmod.client0)//mod代理
    public static common proxy;
   
    public tmod() {
        FluidRegistry.enableUniversalBucket();
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
}
