package com.yuoMod.Tmod.Potion;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionLoader {
    public static Potion potionFall = new FallReduction("potionFall");
    public static Potion frozen = new Frozen().registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "50102462-88b4-4cc7-9250-1608e340c333", -10000, 1);
    //药水类型构造;一级药水，一级加时长，二级
    public static PotionType potionFall_Type = new PotionType(new PotionEffect(potionFall, 3600));
    public static PotionType potionFall_TypeLong = new PotionType("potionfall_type", new PotionEffect(potionFall, 9600));
    public static PotionType potionFall_TypeStrong = new PotionType("potionfall_type", new PotionEffect(potionFall, 3600, 1));


    public PotionLoader(FMLPreInitializationEvent event) {
        onPotionRegister();
        potionTypeRegistration();
    }

    //注册状态效果
    @SubscribeEvent
    public static void onPotionRegister() {
        ForgeRegistries.POTIONS.register(potionFall.setRegistryName("tmod", "potionFall"));
        ForgeRegistries.POTIONS.register(frozen.setRegistryName("tmod", "frozen"));
    }

    //注册状态对应药水
    @SubscribeEvent
    public static void potionTypeRegistration() {
//    	event.getRegistry().register(potionFall_type.setRegistryName("tmod","potionFall"));
        ForgeRegistries.POTION_TYPES.register(potionFall_Type.setRegistryName("tmod", "potionFall_type"));
        ForgeRegistries.POTION_TYPES.register(potionFall_TypeLong.setRegistryName("tmod", "potionFall_type_long"));
        ForgeRegistries.POTION_TYPES.register(potionFall_TypeStrong.setRegistryName("tmod", "potionFall_type_strong"));
    }
}
