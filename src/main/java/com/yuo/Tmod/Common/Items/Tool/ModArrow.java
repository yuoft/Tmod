package com.yuo.Tmod.Common.Items.Tool;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Entity.*;
import com.yuo.Tmod.Tab.TmodGroup;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArrow extends ItemArrow {
    public ModArrow(String name){
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);
    }

    //创建箭矢
    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter) {
        Item bow = stack.getItem();
        EntityArrow arrow = null;
        if (bow == ItemLoader.spaceArrow){
            arrow = new EntitySpaceArrow(worldIn, shooter);
        }else if (bow == ItemLoader.dragonArrow){
            arrow = new EntityDragonArrow(worldIn, shooter);
        }else if (bow == ItemLoader.netheriteArrow){
            arrow = new EntityNetheriteArrow(worldIn, shooter);
        }else if (bow == ItemLoader.diamondArrow){
            arrow = new EntityDiamondArrow(worldIn, shooter);
        }else if (bow == ItemLoader.goldArrow){
            arrow = new EntityGoldArrow(worldIn, shooter);
        }else if (bow == ItemLoader.ironArrow){
            arrow = new EntityIronArrow(worldIn, shooter);
        }
        return arrow;
    }

    //是否无限
    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player) {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant > 0;
    }
}
