package com.yuo.Tmod.Common.Items.Armor;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DragonArmor extends ItemArmor {
    public DragonArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);//创造模式物品栏
    }

    // 穿在身上的时候的每时每刻都会调用的方法，可以用来追加药水效果什么的
    //套装效果
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        int i = 0;
        for (ItemStack stack : player.inventory.armorInventory) {
            if (stack.getItem() instanceof DragonArmor)
                i++;
        }
        PotionEffect effect = player.getActivePotionEffect(MobEffects.HASTE);
        if (effect != null){
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, effect.getDuration(), Math.min(2, i - 1)));
        }else player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 0, Math.min(2, i - 1))); //急迫
    }
}
