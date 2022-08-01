package com.yuoMod.Tmod.Common.Items.Tool;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpaceSword extends ItemSword {

    public SpaceSword(String name, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.space_sword1", ""));
        tooltip.add(I18n.format("tmod.item.space_sword2", ""));
    }

    //主动
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isSneaking()) {
            float range = 5.0f;
            ItemStack mainhand = playerIn.getHeldItemMainhand();
            if (mainhand.getTagCompound() != null && mainhand.getTagCompound().hasKey("tmod_level")) {
                int level = mainhand.getTagCompound().getInteger("tmod_level");
                attackAOE(playerIn, range, 10.0f + level, false);
            } else attackAOE(playerIn, range, 10.0f, false);
            playerIn.getCooldownTracker().setCooldown(mainhand.getItem(), 20 * 60);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        } else return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    //攻击实体
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        Random random = new Random();
        if (random.nextInt(100) > 95) {
            if (target instanceof EntityPlayer) {
                target.setHealth(0.0f);
            } else {
                if (target instanceof EntityDragon) {//对末影龙暴击
                    ((EntityDragon) target).attackEntityFromPart(((EntityDragon) target).dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) attacker), 100.0f);
                } else target.attackEntityFrom(DamageSource.GENERIC, 100.0f);
            }
            BH3Sword.addParticle(target.world, target.getPosition(), EnumParticleTypes.CRIT);
        }
        return super.hitEntity(stack, target, attacker);
    }

    //aoe伤害
    private void attackAOE(EntityPlayer player, float range, float damage, boolean type) {
        if (player.getEntityWorld().isRemote) {
            return;
        }
        AxisAlignedBB aabb = player.getEntityBoundingBox().grow(range);//范围
        List<Entity> toAttack = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);//生物列表
        DamageSource src = DamageSource.causePlayerDamage(player);//伤害类型
        for (Entity entity : toAttack)//循环遍历
        {
            if (type) {
                if (entity instanceof EntityLiving) {
                    entity.attackEntityFrom(src, damage);//给与实体伤害
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
        player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }
}
