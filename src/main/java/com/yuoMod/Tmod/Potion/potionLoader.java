package com.yuoMod.Tmod.Potion;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class potionLoader 
{
	public static Potion potionFall=new potionFall("potionFall",false,0x02ff02);
	//ҩˮ���͹���;һ��ҩˮ��һ����ʱ��������
	public static PotionType potionFall_type=new PotionType("potionFall_type",new PotionEffect(potionFall,3600));
	public static PotionType potionFall_type_long=new PotionType("potionFall_type",new PotionEffect(potionFall,9600));
	public static PotionType potionFall_type_strong=new PotionType("potionFall_type",new PotionEffect(potionFall,3600,1));
	

    public potionLoader(FMLPreInitializationEvent event)
    {
    	onPotionRegister();
    	potionTypeRegistration();
    }
    //ע��״̬Ч��
    @SubscribeEvent
    public static void onPotionRegister() 
    {
        ForgeRegistries.POTIONS.register(potionFall.setRegistryName("tmod", "potionFall"));
    }
    //ע��״̬��Ӧҩˮ
    @SubscribeEvent
    public static void potionTypeRegistration() 
    {
//    	event.getRegistry().register(potionFall_type.setRegistryName("tmod","potionFall"));
    	ForgeRegistries.POTION_TYPES.register(potionFall_type.setRegistryName("tmod", "potionFall_type"));
    	ForgeRegistries.POTION_TYPES.register(potionFall_type_long.setRegistryName("tmod", "potionFall_type_long"));
    	ForgeRegistries.POTION_TYPES.register(potionFall_type_strong.setRegistryName("tmod", "potionFall_type_strong"));
    }
}
