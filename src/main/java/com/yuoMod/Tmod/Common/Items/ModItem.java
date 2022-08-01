package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItem extends Item {
    public ModItem(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(64);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getItem().equals(ItemLoader.space_line)) {
            tooltip.add(I18n.format("tmod.item.space_line1"));
            tooltip.add(I18n.format("tmod.item.space_line2"));
        }
        if (stack.getItem() == ItemLoader.dragonString) {
            tooltip.add(I18n.format("tmod.item.dragon_string"));
        }
        if (stack.getItem().equals(ItemLoader.salt)) {
            tooltip.add(I18n.format("tmod.item.salt1"));
        }
    }
}
