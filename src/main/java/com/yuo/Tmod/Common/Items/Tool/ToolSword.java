package com.yuo.Tmod.Common.Items.Tool;

import com.google.common.collect.Multimap;
import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.ForgeHooks;

public class ToolSword extends ItemSword {
    public ToolSword(String name, ToolMaterial toolmaterial) {
        super(toolmaterial);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

        Item item = stack.getItem();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            if (item == ItemLoader.beaconSword){
                multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), ModAttributeModifiers.SPEED_ATTR_ADD);
                multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), ModAttributeModifiers.HEALTH_ATTR_ADD);
            }
            if (item == ItemLoader.obsidianSword){
                multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), ModAttributeModifiers.SPEED_ATTR_REMOVE);
            }
        }

        return multimap;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        return super.hitEntity(stack, target, attacker);
    }
}
