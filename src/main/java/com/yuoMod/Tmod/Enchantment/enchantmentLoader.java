package com.yuoMod.Tmod.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class enchantmentLoader 
{
//	public static RegistryEvent.Register<Enchantment> event;
//	public static EntityPlayer player;
	private final static EntityEquipmentSlot[] slots= {EntityEquipmentSlot.CHEST,EntityEquipmentSlot.FEET,EntityEquipmentSlot.HEAD,EntityEquipmentSlot.LEGS};//��������
	
	public static Enchantment fireimmune=new fireImmune("fireimmune",Rarity.COMMON, EnumEnchantmentType.ARMOR, slots);

    public enchantmentLoader()
    {
        	Registr();//ForgeRegistries.ENCHANTMENTS.register();
    }
    
    @SubscribeEvent
    public static void Registr() 
    {
    	ForgeRegistries.ENCHANTMENTS.registerAll(fireimmune);
//        event.getRegistry().registerAll(new FireImmune(Rarity.RARE,EnumEnchantmentType.ARMOR_CHEST,
//        		slots).setName(tmod.MODID).setRegistryName(fireimmune));
        // setName��func_77322_b��������ɸ�ħ���Ƶı��ػ�������ע�����޹ء����ػ���ص�������ο��� 13 �¡�
    }
}
