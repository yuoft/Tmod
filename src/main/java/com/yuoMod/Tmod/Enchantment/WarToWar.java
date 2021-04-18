package com.yuoMod.Tmod.Enchantment;

import com.yuoMod.Tmod.tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class WarToWar extends Enchantment
{
	private static final EntityEquipmentSlot[] SLOTS= {EntityEquipmentSlot.MAINHAND};//��Чλ��
	private static final EnumEnchantmentType TYPE = EnumEnchantmentType.WEAPON; //װ��λ��
	
	public WarToWar(String name) {
        super(Rarity.RARE, TYPE, SLOTS);
        this.setName(name);
        this.setRegistryName(tmod.MODID, name);
    }

    // ��ħ����Ϳ��ܵȼ�
    @Override
    public int getMinLevel() {
        return 1;
    }

    //��ħ����߿��ܵȼ�
    @Override
    public int getMaxLevel() {
        return 4;
    }
    
    //�õ���ħ����Ϳ��ܵȼ�
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 30;
    }

    //�õ���ħ����߿��ܵȼ�
    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 30;
    }
    
    //�ܷ���������ħ����
    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench);
    }

}
