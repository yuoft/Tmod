package com.yuo.Tmod.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EnchantLoader {
    public static Enchantment fireImmune = new FireImmune("fire_immune"); //火焰免疫
    public static Enchantment warToWar = new WarToWar("war_to_war"); // 以战养战(吸血)
    public static Enchantment blastArrow = new BlastArrow("blast_arrow"); // 爆炸箭
    public static Enchantment fireThorns = new FireThorns("fire_thorns"); // 火焰荆棘
    public static Enchantment insight = new Insight("insight"); // 洞察
    public static Enchantment lastStand = new LastStand("last_stand"); // 屹立不倒
    public static Enchantment lavaWalker = new LavaWalker("lava_walker"); // 熔岩行者
    public static Enchantment manyArrow = new ManyArrow("many_arrow"); // 万箭
    public static Enchantment slow = new Slow("slow"); // 拖拉


    public EnchantLoader() {
        Registr();//ForgeRegistries.ENCHANTMENTS.register();
    }

    @SubscribeEvent
    public static void Registr() {
        ForgeRegistries.ENCHANTMENTS.registerAll(fireImmune, warToWar, blastArrow, fireThorns, insight, lastStand, lavaWalker, manyArrow, slow);
    }
}
