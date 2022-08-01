package com.yuoMod.Tmod.Client;

import com.yuoMod.Tmod.Tmod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Tmod.MOD_ID)
@Config.LangKey("config.tmod.general") // 这个用于本地化，稍后会讲
public final class ConfigLoader {

    @Config.Comment("Set the damage creature type of OP sword")
    @Config.LangKey("config.tmod.general.OpSwordType")
    @Config.Name("OpSwordType")
//    @Config.RequiresWorldRestart
    //op剑范围伤害，生物类型是否是全部生物;false:敌对;true:全部生命实体
    public static boolean opSwordType = false;

    @Config.Comment("Monster custom name display level")
    @Config.LangKey("config.tmod.general.mobInfo")
    @Config.Name("MobInfo")
    //怪物自定义名称显示等级;false:不显示;true:显示
    public static boolean mobInfo = false;

    @Config.Comment("Set the damage range of OP sword") // 有了这个就会多一个注释。
    @Config.LangKey("config.tmod.general.range") // 供配置 GUI 界面使用的本地化键，参阅“可视化配置文件编辑界面”一节
    @Config.Name("Range")
    @Config.RangeInt(min = 8, max = 128) // 整数值支持限定范围。
//    @Config.RequiresWorldRestart // meta 标记，代表需要重进存档才会生效
    public static int range = 32;

    @Config.Comment("Set highest level")
    @Config.LangKey("config.tmod.general.level")
    @Config.Name("Level")
    @Config.RangeInt(min = 50, max = 1000)
    @Config.RequiresWorldRestart
    public static int level = 100;

    //同步配置文件
    @Mod.EventBusSubscriber(modid = Tmod.MOD_ID)
    public static class ConfigSyncHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Tmod.MOD_ID)) {
                ConfigManager.sync(Tmod.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }


}