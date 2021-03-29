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

    @Config.Comment("修改附魔ID")
    @Config.LangKey("config.tmod.general.enchID01")
    @Config.Name("EnchID01")
    @Config.RangeInt(min = 1, max = 10000) // 和 RangeInt 一个意思，不过是给 double 的。
    @Config.RequiresWorldRestart
    @Config.RequiresMcRestart// meta 标记，代表需要重启游戏才会生效
    public static double enchID01 = 99;

    // 数组也是支持的。
    public static String[] strArr = new String[] { "test","tmod","bh3" };
    
    @Config.Comment("设置OP剑的伤害生物类型")
    @Config.LangKey("config.tmod.general.OpSwordType")
    @Config.Name("OpSwordType")
    @Config.RequiresWorldRestart
    //op剑范围伤害，生物类型是否是全部生物;false:敌对;true:全部生命实体
    public static boolean opSwordType = false;
    
    @Config.Comment("设置OP剑的伤害范围") // 有了这个就会多一个注释。
    @Config.LangKey("config.tmod.general.range") // 供配置 GUI 界面使用的本地化键，参阅“可视化配置文件编辑界面”一节
    @Config.Name("Range")
    @Config.RangeInt(min = 1, max = 100) // 整数值支持限定范围。
    @Config.RequiresWorldRestart // meta 标记，代表需要重进存档才会生效
    public static int range = 25;
    
    @Config.Comment("设置最高等级") 
    @Config.LangKey("config.tmod.general.level") 
    @Config.Name("Level")
    @Config.RangeInt(min = 10, max = 100) 
    @Config.RequiresWorldRestart 
    public static int level = 100;
    
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