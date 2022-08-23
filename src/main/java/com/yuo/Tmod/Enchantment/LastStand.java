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
                removeExp(player, -ceil); //扣除经验值
                player.setHealth(1);
                event.setAmount(0);
                stackFeet.damageItem(1, player);
            }

        }
    }

    /**
     * 消耗经验
     * @param player 玩家
     * @param amount 数值
     */
    public static void removeExp(EntityPlayer player, int amount){
        if (amount > 0){
            player.addExperience(amount);
        }else {
            player.addScore(amount);
            int i = Integer.MAX_VALUE - player.experienceTotal;

            if (amount > i) {
                amount = i;
            }

            player.experience -= (float)amount / (float)player.xpBarCap();

            for (player.experienceTotal -= amount; player.experience >= 1.0F; player.experience /= (float)player.xpBarCap()) {
                player.experience = (player.experience - 1.0F) * (float)player.xpBarCap();
                player.addExperienceLevel(-1);
            }
        }
    }
}
