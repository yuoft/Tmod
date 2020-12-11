package com.yuoMod.Tmod.Common.Items.ToolAndArmor;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class OPArmor extends ItemArmor implements ISpecialArmor
{
	/*
	 * name�������ArmorMaterial�Ĳ�������λ���йء�
maxDamage�����͸�ArmorMaterial��Ӧ�Ŀ��׵��;ó����ȡ�
renderIndexIn�������ĸ�Ԫ�ر�ʾ��Ӧ���׵�ͷ�����ؼס����ȡ���ѥ�ӵ����˺�����������Ƥ�׷ֱ�Ϊ1��3��2��1����Ϊ7����ʯ�׷ֱ�Ϊ3��8��6��3����Ϊ20���벻Ҫ���ĸ�Ԫ��ֵ�ĺͳ������ֵ��
equipmentSlotIn������ToolMaterialһ�����Ͷ�Ӧ���׵ĸ�ħ��������أ�ͬ��������׵ĸ�ħ������ߡ�
	 */
	public OPArmor(String name,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.TMOD);//����ģʽ��Ʒ��
	}
	// �������ϵ�ʱ���ÿʱÿ�̶�����õķ�������������׷��ҩˮЧ��ʲô��
	//��װЧ��
	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
		ItemStack stackHead=player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		ItemStack stackChest=player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		ItemStack stackLegs=player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
		ItemStack stackFeet=player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(stackHead !=null && stackChest !=null && stackLegs !=null && stackFeet !=null
			&& stackHead.getItem().equals(itemLoader.op_helmet) && stackChest.getItem().equals(itemLoader.op_chestplate)
				&& stackLegs.getItem().equals(itemLoader.op_leggings) && stackFeet.getItem().equals(itemLoader.op_boots))
		{
			if(player.isBurning())
	             player.extinguish();//�Ż�ʱϨ��
			 player.capabilities.allowFlying = true;//����
//			 player.capabilities.setFlySpeed(0.1f);
//			 player.capabilities.setPlayerWalkSpeed(0.3f);
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(16), 100000, 0));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(26), 100000, 0));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(11), 100000, 10));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 100000, 2));
			 player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 100000, 2));
			 player.attackEntityAsMob(player);
		}
		else
		{
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;//ȡ������
//			player.capabilities.setFlySpeed(0.01f);
//			player.clearActivePotions();
			player.removeActivePotionEffect(Potion.getPotionById(16));
			player.removeActivePotionEffect(Potion.getPotionById(26));
			player.removeActivePotionEffect(Potion.getPotionById(11));
			player.removeActivePotionEffect(Potion.getPotionById(8));
			player.removeActivePotionEffect(Potion.getPotionById(1));
		}
    }
	//��������(����1000���˺�������)
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
			int slot) {
		return new ArmorProperties(0, 1.0, 1000);//���ȼ����˺����ձȣ���������
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
