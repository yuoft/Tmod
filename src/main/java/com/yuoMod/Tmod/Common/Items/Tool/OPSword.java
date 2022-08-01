package com.yuoMod.Tmod.Common.Items.Tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

import com.yuoMod.Tmod.Client.ConfigLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Entity.EntityLightningDiamond;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OPSword extends ItemSword {
    /*
     * harvestLevel������ʾ�������Ĺ��ߵȼ���
maxUses������ʾ�������Ĺ��߶�Ӧ�;á�
efficiency������ʾ�������Ĺ���ʹ��Ч�ʡ�
damageVsEntity������ʾ�����˺����ȡ�
enchantability�����븽ħ�ȼ����
     */
    public static final ToolMaterial OPSWORD = EnumHelper.addToolMaterial("op_sword", 99, -1, 999.0f, 0.0f, 0);

    public OPSword(String name) {
        super(OPSWORD);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
            map.put(Enchantment.getEnchantmentByID(21), 10);
            ItemStack stack = new ItemStack(this);
            EnchantmentHelper.setEnchantments(map, stack);
            items.add(stack);
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.op_sword", ""));
    }

    //��ħ��Ч
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    //����
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            if (playerIn.isSneaking()) {
                float range = ConfigLoader.range * 1.0f;
                attackAOE(playerIn, range, 10000.0f, ConfigLoader.opSwordType);
            } else {
                EntityLightningDiamond lightningDiamond = new EntityLightningDiamond(worldIn, playerIn);
                lightningDiamond.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead, 0, 2.0f, 0.1f);
                worldIn.spawnEntity(lightningDiamond);
            }
            playerIn.getCooldownTracker().setCooldown(heldItem.getItem(), 10);
            return new ActionResult<>(EnumActionResult.PASS, heldItem);
        } else return new ActionResult<>(EnumActionResult.PASS, heldItem);
    }

    //����ʵ��
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target instanceof EntityDragon)//��ĩӰ���˺�
        {
            ((EntityDragon) target).attackEntityFromPart(((EntityDragon) target).dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) attacker), 10000.0f);
        } else if (target instanceof EntityKiana) {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 300.0f);
        } else target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10000.0f);
        target.setHealth(0.0f);
        target.onDeath(DamageSource.causePlayerDamage((EntityPlayer) attacker));
        return true;
    }

    //aoe�˺�
    protected void attackAOE(EntityPlayer player, float range, float damage, boolean type) {
        if (player.getEntityWorld().isRemote) {
            return;
        }
        AxisAlignedBB aabb = player.getEntityBoundingBox().grow(range);//��Χ
        List<Entity> toAttack = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);//�����б�
        DamageSource src = DamageSource.causePlayerDamage(player);//�˺�����
        for (Entity entity : toAttack)//ѭ������
        {
            if (type) {
                if (entity instanceof EntityLiving) {
                    entity.attackEntityFrom(src, damage);//����ʵ���˺�
                }
            } else {
                if (entity instanceof IMob) {
                    if (entity instanceof EntityDragon) {
                        ((EntityDragon) entity).attackEntityFromPart(((EntityDragon) entity).dragonPartHead, src, damage);
                    } else if (entity instanceof EntityWither) {
                        EntityWither wither = (EntityWither) entity;
                        wither.setInvulTime(0);
                        entity.attackEntityFrom(src, damage);
                    } else entity.attackEntityFrom(src, damage);
                }
            }
        }
        player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 3.0f);
    }
}
