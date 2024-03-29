package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Tmod;
import net.minecraft.block.BlockTNT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlastArrow extends Enchantment {
    BlastArrow(String name) {
        super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != Enchantments.FLAME;
    }

    //产生爆炸
    public static void boom(EntityArrow arrow){
        //产生爆炸
        World world = arrow.world;
        if (!world.isRemote){
            world.createExplosion(arrow, arrow.posX, arrow.posY, arrow.posZ, 4.0f, true);
            arrow.setDead(); //删除实体
        }
    }
}
