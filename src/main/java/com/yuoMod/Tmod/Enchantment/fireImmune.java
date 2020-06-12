package com.yuoMod.Tmod.Enchantment;

import com.yuoMod.Tmod.tmod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class fireImmune extends Enchantment
{
	public fireImmune(String name,Enchantment.Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] slots) {
        super(rarity, type, slots);
        this.setName(name);
        this.setRegistryName(tmod.MODID, name);
        // rarity �����������ħ��ϡ�г̶ȣ������� COMMON��UNCOMMON��RARE �� VERY_RARE��
        // type �����������ħ���Լ���ʲô����/����/װ���ϡ�
        // slots �����ˡ������ħ����ʲô������װ�Ĺ���/����/װ���ϲ���Ч���������羣��ֻ�ڿ����ļ�������Ч��
        // slots ��Ӱ�� getEnchantedItem��func_92099_a���ķ���ֵ������������ڻ�ȡĳ��ʵ������ָ����ħ����Ʒ��
    }

    // ��ħ����Ϳ��ܵȼ�
    @Override
    public int getMinLevel() {
        return 1;
    }

    //��ħ����߿��ܵȼ�
    @Override
    public int getMaxLevel() {
        return 1;
    }
    
    //�õ���ħ����Ϳ��ܵȼ�
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 15;
    }

    //�õ���ħ����߿��ܵȼ�
    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 15;
    }
    
    //�ܷ���������ħ����
    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return super.canApplyTogether(ench) && Enchantment.getEnchantmentByID(1) != null;
    }

}
