package com.yuoMod.Tmod.Sound;

import com.yuoMod.Tmod.tmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class soundLoader {
	public static final SoundEvent LTY_JueTiJueMimg =new SoundEvent(new ResourceLocation(tmod.MODID,"tmodSound1"));
	public static final SoundEvent HY_MingJi =new SoundEvent(new ResourceLocation(tmod.MODID,"tmod.HY.MingJi"));
	public static final SoundEvent CD_Op =new SoundEvent(new ResourceLocation(tmod.MODID,"tmod.CD.Op"));
	public static final SoundEvent tmodSound=new SoundEvent(new ResourceLocation(tmod.MODID,"mingji"));
	
	public soundLoader(FMLInitializationEvent event)
	{
		registry(LTY_JueTiJueMimg,"tmodSound1"); 
		registry(tmodSound,"mingji"); 
	}
	// 注意到注册名和构造时传入的那个“音效名”不是一回事。
	// 通常，为简单起见，一般会在这两个地方使用同样的名字。
	@SubscribeEvent
	public static void registry(SoundEvent sound,String name) {
		ForgeRegistries.SOUND_EVENTS.register(sound.setRegistryName(name));
	}
}
