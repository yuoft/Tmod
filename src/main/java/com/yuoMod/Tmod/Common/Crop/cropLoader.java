package com.yuoMod.Tmod.Common.Crop;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class cropLoader 
{
	public static emerald_crop emerald_crop=new emerald_crop("emerald_crop");
	
	public cropLoader() 
	{
		// TODO �Զ����ɵĹ��캯�����
		Registr();
	}
	
	@SubscribeEvent
    public static void Registr() 
    {
   	    ForgeRegistries.BLOCKS.register(emerald_crop);
    }
}
