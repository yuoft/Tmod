package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class LastStand extends Enchantment {
    LastStand(String name) {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 30;
    }

    //用经验抵挡伤害
    public static void lastStand(EntityPlayer player, LivingHurtEvent event, ItemStack stackFeet){
        float amount = event.getAmount(); //伤害值
        float health = player.getHealth();
        if ((health - amount) < 1){ //受到致命伤害
            int exp = player.experienceTotal; //玩家经验值
            int ceil = MathHelper.ceil((amount - (health - 1)) * 10); //将玩家血量扣到半颗心时 剩余的伤害值 * 抵消倍率
            if (exp >= ceil){ //玩家经验值能够抵消伤害
                addPlayerXP(player, -ceil); //扣除经验值
                player.setHealth(1);
                event.setAmount(0);
                stackFeet.damageItem(1, player);
            }

        }
    }

    /**
     * 消耗经验 ----开放式模组库
     * @param player 玩家
     * @param amount 数值
     */
    public static void addPlayerXP(EntityPlayer player, int amount) {
        int experience = getPlayerXP(player) + amount;
        player.experienceTotal = experience;
        player.experienceLevel = getLevelForExperience(experience);
        int expForLevel = getExperienceForLevel(player.experienceLevel);
        player.experience = (float)(experience - expForLevel) / (float)player.xpBarCap();
    }

    public static int getPlayerXP(EntityPlayer player) {
        return (int)(getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
    }

    public static int xpBarCap(int level) {
        if (level >= 30)
            return 112 + (level - 30) * 9;

        if (level >= 15)
            return 37 + (level - 15) * 5;

        return 7 + level * 2;
    }

    private static int sum(int n, int a0, int d) {
        return n * (2 * a0 + (n - 1) * d) / 2;
    }

    public static int getExperienceForLevel(int level) {
        if (level == 0) return 0;
        if (level <= 15) return sum(level, 7, 2);
        if (level <= 30) return 315 + sum(level - 15, 37, 5);
        return 1395 + sum(level - 30, 112, 9);
    }

    public static int getLevelForExperience(int targetXp) {
        int level = 0;
        while (true) {
            final int xpToNextLevel = xpBarCap(level);
            if (targetXp < xpToNextLevel) return level;
            level++;
            targetXp -= xpToNextLevel;
        }
    }
}
