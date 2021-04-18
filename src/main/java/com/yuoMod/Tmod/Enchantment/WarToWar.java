package com.yuoMod.Tmod.Enchantment;

import com.yuoMod.Tmod.tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class WarToWar extends Enchantment
{
	private static final EntityEquipmentSlot[] SLOTS= {EntityEquipmentSlot.MAINHAND};//生效位置
	private static final EnumEnchantmentType TYPE = EnumEnchantmentType.WEAPON; //装备位置
	
	public WarToWar(String name) {
        super(Rarity.RARE, TYPE, SLOTS);
        this.setName(name);
        this.setRegistryName(tmod.MODID, name);
    }

    // 附魔的最低可能等级
    @Override
    public int getMinLevel() {
        return 1;
    }

    //附魔的最高可能等级
    @Override
    public int getMaxLevel() {
        return 4;
    }
    
    //得到附魔的最低可能等级
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 30;
    }

    //得到附魔的最高可能等级
    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 30;
    }
    
    //能否与其它附魔共存
    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench);
    }

}
