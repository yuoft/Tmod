package com.yuo.Tmod.Common.Items.Tool;

import com.google.common.collect.Multimap;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;
import com.yuo.Tmod.Tmod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.UUID;

public class ToolSword extends ItemSword {
    public static AttributeModifier SPEED_ATTR = new AttributeModifier(UUID.fromString("e18b4711-199d-b5b0-3bc3-ae28032ae0db"), Tmod.MOD_ID + ":speed", 0.1, 1);
    public static AttributeModifier HEALTH_ATTR = new AttributeModifier(UUID.fromString("e18b4711-199d-b5b0-3bc3-ae28032ae0db"), Tmod.MOD_ID + ":health", 2, 0);
    public ToolSword(String name, ToolMaterial toolmaterial) {
        super(toolmaterial);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

        Item item = stack.getItem();
        if (slot == EntityEquipmentSlot.MAINHAND || slot == EntityEquipmentSlot.OFFHAND) {
            if (item == ItemLoader.beaconSword){
                multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), SPEED_ATTR);
                multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), HEALTH_ATTR);
            }
        }

        return multimap;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        return super.hitEntity(stack, target, attacker);
    }
}
