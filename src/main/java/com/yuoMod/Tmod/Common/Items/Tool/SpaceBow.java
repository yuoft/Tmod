package com.yuoMod.Tmod.Common.Items.Tool;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;

import com.yuoMod.Tmod.Entity.EntitySpaceArrow;
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

public class SpaceBow extends ItemBow {
    public SpaceBow(String name) {
        this.setUnlocalizedName(name);
        this.setMaxDamage(1000);
        this.setMaxStackSize(1);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setContainerItem(Items.ARROW);
        this.setDamage(new ItemStack(this), 100);
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    return !(entityIn.getActiveItemStack().getItem() instanceof ItemBow) ? 0.0F : (float) (stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    @Override
    public EntityArrow customizeArrow(EntityArrow arrow) {
        return new EntitySpaceArrow(arrow.world, (EntityLivingBase) arrow.shootingEntity);
    }
}
