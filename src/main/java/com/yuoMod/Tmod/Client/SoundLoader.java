package com.yuoMod.Tmod.Client;

import com.yuoMod.Tmod.Tmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundLoader {
    public static final SoundEvent LTY_JueTiJueMimg = new SoundEvent(new ResourceLocation(Tmod.MOD_ID, "tmod.LTY.JueTiJueMimg"));
    public static final SoundEvent HY_MingJi = new SoundEvent(new ResourceLocation(Tmod.MOD_ID, "tmod.HY.MingJi"));
    public static final SoundEvent CD_Op = new SoundEvent(new ResourceLocation(Tmod.MOD_ID, "tmod.CD.Op"));
//	public static final SoundEvent tmodSound=new SoundEvent(new ResourceLocation(tmod.MODID,"mingji"));

//	public soundLoader(FMLInitializationEvent event)
//	{
//		registry(LTY_JueTiJueMimg,"tmodSound1"); 
//		registry(tmodSound,"mingji"); 
//	}
    // ע�⵽ע�����͹���ʱ������Ǹ�����Ч��������һ���¡�
    // ͨ����Ϊ�������һ������������ط�ʹ��ͬ�������֡�
//	@SubscribeEvent
//	public static void registry(SoundEvent sound,String name) {
//		ForgeRegistries.SOUND_EVENTS.register(sound.setRegistryName(name));
//	}
}
