package com.yuo.Tmod.Common.Items.Armor;

import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class NetheriteArmor extends ItemArmor {
    public NetheriteArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);//创造模式物品栏
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        if (item.getItem() instanceof NetheriteArmor) {
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 0, 0)); //防火
        }
    }
}
