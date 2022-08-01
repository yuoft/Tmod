package com.yuoMod.Tmod.Client;

import com.yuoMod.Tmod.Tmod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Tmod.MOD_ID)
@Config.LangKey("config.tmod.general") // ������ڱ��ػ����Ժ�ὲ
public final class ConfigLoader {

    @Config.Comment("Set the damage creature type of OP sword")
    @Config.LangKey("config.tmod.general.OpSwordType")
    @Config.Name("OpSwordType")
//    @Config.RequiresWorldRestart
    //op����Χ�˺������������Ƿ���ȫ������;false:�ж�;true:ȫ������ʵ��
    public static boolean opSwordType = false;

    @Config.Comment("Monster custom name display level")
    @Config.LangKey("config.tmod.general.mobInfo")
    @Config.Name("MobInfo")
    //�����Զ���������ʾ�ȼ�;false:����ʾ;true:��ʾ
    public static boolean mobInfo = false;

    @Config.Comment("Set the damage range of OP sword") // ��������ͻ��һ��ע�͡�
    @Config.LangKey("config.tmod.general.range") // ������ GUI ����ʹ�õı��ػ��������ġ����ӻ������ļ��༭���桱һ��
    @Config.Name("Range")
    @Config.RangeInt(min = 8, max = 128) // ����ֵ֧���޶���Χ��
//    @Config.RequiresWorldRestart // meta ��ǣ�������Ҫ�ؽ��浵�Ż���Ч
    public static int range = 32;

    @Config.Comment("Set highest level")
    @Config.LangKey("config.tmod.general.level")
    @Config.Name("Level")
    @Config.RangeInt(min = 50, max = 1000)
    @Config.RequiresWorldRestart
    public static int level = 100;

    //ͬ�������ļ�
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