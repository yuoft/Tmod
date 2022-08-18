package com.yuo.Tmod.Enchantment;

import com.yuo.Tmod.Capability.CapabilityLoader;
import com.yuo.Tmod.Capability.EventMobLv;
import com.yuo.Tmod.Capability.IPlayerLevel;
import com.yuo.Tmod.Tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;

public class FireThorns extends Enchantment {
    FireThorns(String name) {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
        this.setName(name);
        this.setRegistryName(Tmod.MOD_ID, name);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20;
    }

    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level) {
        if (attacker instanceof EntityLivingBase){
            EntityLivingBase livingBase = (EntityLivingBase) attacker;
            if (user instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) user;
                IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, EnumFacing.DOWN);
                if (capability != null){
                    Integer playerLevel = capability.getPlayerLevel();
                    livingBase.attackEntityFrom(DamageSource.causeThornsDamage(user), level + playerLevel / 2f);
                }
            }else if (user instanceof EntityLiving){
                int i = EventMobLv.getAttrLevelValue((EntityLiving) user);
                livingBase.attackEntityFrom(DamageSource.causeThornsDamage(user), level + i / 3f);
            }else livingBase.attackEntityFrom(DamageSource.causeThornsDamage(user), level);
            livingBase.setFire(level);
        }
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != Enchantments.THORNS;
    }
}
