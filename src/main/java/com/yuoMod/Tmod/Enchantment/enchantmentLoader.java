package com.yuoMod.Tmod.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class enchantmentLoader 
{
	public static Enchantment fireimmune = new fireImmune("fireimmune"); //��������
	public static Enchantment wartowar = new WarToWar("wartowar"); // ��ս��ս(��Ѫ)

    public enchantmentLoader()
    {
        	Registr();//ForgeRegistries.ENCHANTMENTS.register();
    }
    
    @SubscribeEvent
    public static void Registr() 
    {
    	ForgeRegistries.ENCHANTMENTS.registerAll(fireimmune, wartowar);
    }
}
