package com.yuo.Tmod.Common.Items.Armor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yuo.Tmod.Common.Items.ItemLoader;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
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
     * name参数与该ArmorMaterial的材质所在位置有关。
maxDamage参数和该ArmorMaterial对应的盔甲的耐久成正比。
renderIndexIn参数的四个元素表示对应盔甲的头盔、胸甲、护腿、和靴子抵御伤害的能力，如皮甲分别为1，3，2，1，和为7，钻石甲分别为3，8，6，3，和为20，请不要让四个元素值的和超过这个值。
equipmentSlotIn参数和ToolMaterial一样，和对应盔甲的附魔能力正相关，同样，金盔甲的附魔能力最高。
     */
    public OPArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TOOL_TAB);//创造模式物品栏
        this.setNoRepair();
    }

    // 穿在身上的时候的每时每刻都会调用的方法，可以用来追加药水效果什么的
    //套装效果
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
        if (armorType == EntityEquipmentSlot.CHEST) { //胸甲
            //清除所有负面效果
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
        } else if (armorType == EntityEquipmentSlot.LEGS) { //护腿
            if (player.isBurning())
                player.extinguish();//着火时熄灭
        } else if (armorType == EntityEquipmentSlot.HEAD) { //头盔
            player.getFoodStats().addStats(20, 20F); //饱食度为满的
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0)); //夜视
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        Item item = stack.getItem();
        if (item.equals(ItemLoader.opHead)) {
            tooltip.add(I18n.format("tmod.item.op_head", ""));
        }
        if (item.equals(ItemLoader.opChest)) {
            tooltip.add(I18n.format("tmod.item.op_chest", ""));
        }
        if (item.equals(ItemLoader.opLegs)) {
            tooltip.add(I18n.format("tmod.item.op_legs", ""));
        }
        if (item.equals(ItemLoader.opFeet)) {
            tooltip.add(I18n.format("tmod.item.op_feet", ""));
        }
    }

    //附魔光效
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    //护甲属性(低于1000的伤害被免疫)
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage,
                                         int slot) {
        return new ArmorProperties(1, 1.0, 1000);//优先级，伤害吸收比，吸收上限
    }

    //获取护甲值
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    //护甲耐久消耗
    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

    }
}
