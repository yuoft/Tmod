package com.yuo.Tmod.Common.Items.Tool;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Entity.EntityIronArrow;
import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class IronBow extends ModBow {
    public IronBow(String name) {
        super(name, 397);
    }

    @Override
    public EntityArrow customizeArrow(EntityArrow arrow) {
        return new EntityIronArrow(arrow.world, (EntityLivingBase) arrow.shootingEntity);
    }

    @Override
    protected boolean isArrow(ItemStack stack) {
        return stack.getItem() == ItemLoader.ironArrow;
    }
}
