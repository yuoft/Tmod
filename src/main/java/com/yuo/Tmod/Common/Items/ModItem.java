package com.yuo.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuo.Tmod.Tab.TmodGroup;

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
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setMaxStackSize(64);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getItem().equals(ItemLoader.salt)) {
            tooltip.add(I18n.format("tmod.item.salt1"));
        }
        if (stack.getItem().equals(ItemLoader.stemSeed)) {
            tooltip.add(I18n.format("tmod.item.stem_seed"));
        }
    }
}
