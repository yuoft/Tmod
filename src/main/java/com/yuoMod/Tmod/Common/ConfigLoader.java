package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.tmod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = tmod.MODID) 
@Config.LangKey("config.tmod.general") // ������ڱ��ػ����Ժ�ὲ
public final class ConfigLoader {

    @Config.Comment("������Ҿ���") // ��������ͻ��һ��ע�͡�
    @Config.LangKey("config.tmod.general.txp") // ������ GUI ����ʹ�õı��ػ��������ġ����ӻ������ļ��༭���桱һ��
    @Config.Name("Txp")
    @Config.RangeInt(min = 1, max = 100) // ����ֵ֧���޶���Χ��
    @Config.RequiresWorldRestart // meta ��ǣ�������Ҫ�ؽ��浵�Ż���Ч
    public static int txp = 5;

    @Config.Comment("��ħID")
    @Config.LangKey("config.tmod.general.enchID01")
    @Config.Name("EnchID01")
    @Config.RangeInt(min = 1, max = 10000) // �� RangeInt һ����˼�������Ǹ� double �ġ�
    @Config.RequiresWorldRestart
    @Config.RequiresMcRestart// meta ��ǣ�������Ҫ������Ϸ�Ż���Ч
    public static double enchID01 = 99;

    // ����Ҳ��֧�ֵġ�
    public static String[] strArr = new String[] { "test","tmod","bh3" };
    
    //ͬ�������ļ�
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