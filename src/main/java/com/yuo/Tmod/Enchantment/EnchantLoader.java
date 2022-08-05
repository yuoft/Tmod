package com.yuo.Tmod.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EnchantLoader {
    public static Enchantment fireimmune = new FireImmune("fireimmune"); //火焰免疫
    public static Enchantment wartowar = new WarToWar("wartowar"); // 以战养战(吸血)


    public EnchantLoader() {
        Registr();//ForgeRegistries.ENCHANTMENTS.register();
    }

    @SubscribeEvent
    public static void Registr() {
        ForgeRegistries.ENCHANTMENTS.registerAll(fireimmune, wartowar);
    }
}
