package com.yuo.Tmod.Common.Items.Armor;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpaceArmor extends ItemArmor {
    public SpaceArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);//创造模式物品栏
    }

    // 穿在身上的时候的每时每刻都会调用的方法，可以用来追加药水效果什么的
    //根据装备的盔甲数量给予玩家抗性buff，最高3级
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        int i = 0;
        for (ItemStack stack : player.inventory.armorInventory) {
            if (stack.getItem() instanceof SpaceArmor)
                i++;
        }
        PotionEffect effect = player.getActivePotionEffect(MobEffects.RESISTANCE);
        if (effect != null){
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, effect.getDuration(), Math.min(2, i - 1)));
        }else player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, Math.min(2, i - 1))); //抗性
    }
}
