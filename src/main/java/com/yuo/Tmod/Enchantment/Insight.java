package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Random;

public class Insight extends Enchantment {
    Insight(String name) {
        super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    //额外经验掉落
    public static void addDropExp(BlockEvent.BreakEvent event, int insight){
        //额外获取 原本经验值 * （1 + insight * 30%）经验值
        double exp = event.getExpToDrop() + (100 + insight * 30) / 100.0;
        event.setExpToDrop((int) Math.ceil(exp));
    }

    //增加钓鱼经验
    public static void addFishingExp(EntityPlayer player, World world, int insight){
        EntityXPOrb xpOrb = new EntityXPOrb(world, player.posX, player.posY, player.posZ, MathHelper.getInt(world.rand, insight, insight * 3));
        world.spawnEntity(xpOrb);
    }
}
