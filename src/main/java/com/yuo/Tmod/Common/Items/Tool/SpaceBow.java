package com.yuo.Tmod.Common.Items.Tool;

import javax.annotation.Nullable;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import com.yuo.Tmod.Entity.EntitySpaceArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpaceBow extends ModBow {
    public SpaceBow(String name) {
        super(name,498);
    }

    @Override
    public EntityArrow customizeArrow(EntityArrow arrow) {
        return new EntitySpaceArrow(arrow.world, (EntityLivingBase) arrow.shootingEntity);
    }

    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() == ItemLoader.spaceArrow;
    }
}
