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

public class BeaconArmor extends ItemArmor {
    public BeaconArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);//创造模式物品栏
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        NonNullList<ItemStack> stacks = player.inventory.armorInventory;
        boolean flag = stacks.size() >= 4;
        //未装备4件
        for (ItemStack itemStack : stacks) {
            if (itemStack.isEmpty() || !(itemStack.getItem() instanceof BeaconArmor)) //不是当前盔甲
                flag = false;
        }
        if (flag) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 0, 0)); //急迫
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 0, 0));
        }
    }
}
