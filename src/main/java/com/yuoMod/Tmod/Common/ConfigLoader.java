package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.tmod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = tmod.MODID) 
@Config.LangKey("config.tmod.general") // 这个用于本地化，稍后会讲
public final class ConfigLoader {

    @Config.Comment("给予玩家经验") // 有了这个就会多一个注释。
    @Config.LangKey("config.tmod.general.txp") // 供配置 GUI 界面使用的本地化键，参阅“可视化配置文件编辑界面”一节
    @Config.Name("Txp")
    @Config.RangeInt(min = 1, max = 100) // 整数值支持限定范围。
    @Config.RequiresWorldRestart // meta 标记，代表需要重进存档才会生效
    public static int txp = 5;

    @Config.Comment("附魔ID")
    @Config.LangKey("config.tmod.general.enchID01")
    @Config.Name("EnchID01")
    @Config.RangeInt(min = 1, max = 10000) // 和 RangeInt 一个意思，不过是给 double 的。
    @Config.RequiresWorldRestart
    @Config.RequiresMcRestart// meta 标记，代表需要重启游戏才会生效
    public static double enchID01 = 99;

    // 数组也是支持的。
    public static String[] strArr = new String[] { "test","tmod","bh3" };
    
    //同步配置文件
    @Mod.EventBusSubscriber(modid =tmod.MODID)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(tmod.MODID)) {
                ConfigManager.sync(tmod.MODID, Config.Type.INSTANCE);
            }
        }
    }

    
}