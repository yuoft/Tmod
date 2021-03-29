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

    @Config.Comment("�޸ĸ�ħID")
    @Config.LangKey("config.tmod.general.enchID01")
    @Config.Name("EnchID01")
    @Config.RangeInt(min = 1, max = 10000) // �� RangeInt һ����˼�������Ǹ� double �ġ�
    @Config.RequiresWorldRestart
    @Config.RequiresMcRestart// meta ��ǣ�������Ҫ������Ϸ�Ż���Ч
    public static double enchID01 = 99;

    // ����Ҳ��֧�ֵġ�
    public static String[] strArr = new String[] { "test","tmod","bh3" };
    
    @Config.Comment("����OP�����˺���������")
    @Config.LangKey("config.tmod.general.OpSwordType")
    @Config.Name("OpSwordType")
    @Config.RequiresWorldRestart
    //op����Χ�˺������������Ƿ���ȫ������;false:�ж�;true:ȫ������ʵ��
    public static boolean opSwordType = false;
    
    @Config.Comment("����OP�����˺���Χ") // ��������ͻ��һ��ע�͡�
    @Config.LangKey("config.tmod.general.range") // ������ GUI ����ʹ�õı��ػ��������ġ����ӻ������ļ��༭���桱һ��
    @Config.Name("Range")
    @Config.RangeInt(min = 1, max = 100) // ����ֵ֧���޶���Χ��
    @Config.RequiresWorldRestart // meta ��ǣ�������Ҫ�ؽ��浵�Ż���Ч
    public static int range = 25;
    
    @Config.Comment("������ߵȼ�") 
    @Config.LangKey("config.tmod.general.level") 
    @Config.Name("Level")
    @Config.RangeInt(min = 10, max = 100) 
    @Config.RequiresWorldRestart 
    public static int level = 100;
    
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