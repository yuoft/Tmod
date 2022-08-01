package com.yuoMod.Tmod.Common.Items.Armor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPArmor extends ItemArmor implements ISpecialArmor {
    /*
     * name�������ArmorMaterial�Ĳ�������λ���йء�
maxDamage�����͸�ArmorMaterial��Ӧ�Ŀ��׵��;ó����ȡ�
renderIndexIn�������ĸ�Ԫ�ر�ʾ��Ӧ���׵�ͷ�����ؼס����ȡ���ѥ�ӵ����˺�����������Ƥ�׷ֱ�Ϊ1��3��2��1����Ϊ7����ʯ�׷ֱ�Ϊ3��8��6��3����Ϊ20���벻Ҫ���ĸ�Ԫ��ֵ�ĺͳ������ֵ��
equipmentSlotIn������ToolMaterialһ�����Ͷ�Ӧ���׵ĸ�ħ��������أ�ͬ��������׵ĸ�ħ������ߡ�
     */
    public OPArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);//����ģʽ��Ʒ��
        this.setNoRepair();
    }

    // �������ϵ�ʱ���ÿʱÿ�̶�����õķ�������������׷��ҩˮЧ��ʲô��
    //��װЧ��
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        if (armorType == EntityEquipmentSlot.CHEST) { //�ؼ�
            //������и���Ч��
            Collection<PotionEffect> effects = player.getActivePotionEffects();
            if (effects.size() > 0) {
                ArrayList<Potion> bad = new ArrayList<Potion>();
                for (Object effect : effects) {
                    if (effect instanceof PotionEffect) {
                        PotionEffect potion = (PotionEffect) effect;
                        if (potion.getPotion().isBadEffect())
                            bad.add(potion.getPotion());
                    }
                }
                if (bad.size() > 0) {
                    for (Potion potion : bad) {
                        player.removeActivePotionEffect(potion);
                    }
                }
            }
        } else if (armorType == EntityEquipmentSlot.LEGS) { //����
            if (player.isBurning())
                player.extinguish();//�Ż�ʱϨ��
        } else if (armorType == EntityEquipmentSlot.HEAD) { //ͷ��
            player.getFoodStats().addStats(20, 20F); //��ʳ��Ϊ����
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 300, 0)); //ҹ��
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        Item item = stack.getItem();
        if (item.equals(ItemLoader.op_helmet)) {
            tooltip.add(I18n.format("tmod.item.op_head", ""));
        }
        if (item.equals(ItemLoader.op_chestplate)) {
            tooltip.add(I18n.format("tmod.item.op_chest", ""));
        }
        if (item.equals(ItemLoader.op_leggings)) {
            tooltip.add(I18n.format("tmod.item.op_legs", ""));
        }
        if (item.equals(ItemLoader.op_boots)) {
            tooltip.add(I18n.format("tmod.item.op_feet", ""));
        }
    }

    //��ħ��Ч
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    //��������(����1000���˺�������)
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
                                         int slot) {
        return new ArmorProperties(1, 1.0, 1000);//���ȼ����˺����ձȣ���������
    }

    //��ȡ����ֵ
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    //�����;�����
    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

    }
}
