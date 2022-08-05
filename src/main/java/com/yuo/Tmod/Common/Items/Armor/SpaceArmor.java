package com.yuo.Tmod.Common.Items.Armor;

import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class SpaceArmor extends ItemArmor {
    public SpaceArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);//创造模式物品栏
    }

    // 穿在身上的时候的每时每刻都会调用的方法，可以用来追加药水效果什么的
    //套装效果
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        NonNullList<ItemStack> stacks = player.inventory.armorInventory;
        boolean flag = stacks.size() >= 4;
        //未装备4件
        for (ItemStack itemStack : stacks) {
            if (itemStack.isEmpty() || !(itemStack.getItem() instanceof SpaceArmor)) //不是当前盔甲
                flag = false;
        }
        if (flag) {
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 1)); //抗性
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 0)); //力量
        }
    }
}
