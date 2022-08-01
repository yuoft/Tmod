package com.yuoMod.Tmod.Common.Items.Tool;

import com.yuoMod.Tmod.Capability.EventMobLv;
import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Potion.PotionLoader;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BH3Sword extends ItemPickaxe {
    /*
    The harvestlevel parameter represents the level of the tool you make. This is especially obvious in pickaxes. For example, if the wood is 0, only the diamonds with the corresponding level of 0 can be excavated to drop objects, such as stones.
    If the diamond is 3, then the diamonds with the corresponding level of 3 can be excavated, and other pickaxes cannot dig out objects, such as obsidian. The highest level 3 is used here
    The maxuses parameter indicates the durability of the tool. For example, the diamond tool is 1561, with the highest durability, while the wood tool is 59, with the lowest durability. This value is deliberately reduced to 16
    The efficiency parameter indicates the efficiency of the tool. The efficiency is proportional to the value of this parameter. This value is deliberately increased to 16.0f
    The damagevsentity parameter indicates the attack damage strength. Similarly, the force is positively correlated with the value of the parameter. Here is 0.0F, indicating low attack power
    The enchantability parameter is related to the enchantment level. The system of enchantment level in minecraft is very complex. But one thing is that the higher the value is, the easier it is for the corresponding enchant to get a higher level.
    That's why gold is more likely to get high-level enchantments, while stone's enchantments are relatively low. It's 10 here. It's the same as a diamond
    */
    public BH3Sword(String name, ToolMaterial toolmaterial) {
        super(toolmaterial);
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setHarvestLevel("pickaxe", 4);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        Item item = stack.getItem();
        if (item == ItemLoader.hengShuang) {
            tooltip.add(I18n.format("tmod.item.emerald_pickaxe1", ""));
            tooltip.add(I18n.format("tmod.item.emerald_pickaxe2", ""));
        } else if (item == ItemLoader.tianHuo) {
            tooltip.add(I18n.format("tmod.item.emerald_sword1", ""));
            tooltip.add(I18n.format("tmod.item.emerald_sword2", ""));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }


    //主动
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking() && (playerIn.experienceLevel > 30 || playerIn.isCreative())) {
            playerIn.setActiveHand(handIn);
            return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
        } else {
            playerIn.sendMessage(new TextComponentTranslation("tmod.text.emeraldSword"));
            return new ActionResult<>(EnumActionResult.PASS, heldItem);
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
        Item item = stack.getItem();
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            AxisAlignedBB aabb = player.getEntityBoundingBox().grow(8);
            List<Entity> entityList = player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, aabb);
            DamageSource source = DamageSource.GENERIC;
            if (item == ItemLoader.hengShuang) {
                world.playSound(player, player.getPosition(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 3.0f, 1.0f);
                source = DamageSource.MAGIC;
            } else if (item == ItemLoader.tianHuo) {
                world.playSound(player, player.getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 3.0f, 1.0f);
                source = DamageSource.IN_FIRE;
            }
            for (Entity entity : entityList) {
                if (entity instanceof EntityLivingBase && !(entity instanceof EntityArmorStand)) {
                    float damage = 10;
                    if (EventMobLv.isItemHasLv(stack)) {
                        damage += stack.getTagCompound().getInteger("tmod_level");
                    }
                    entity.attackEntityFrom(source, damage);
                    BlockPos pos = entity.getPosition();
                    if (item == ItemLoader.hengShuang) {
                        addParticle(world, pos, EnumParticleTypes.SNOWBALL);
                        ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(PotionLoader.frozen, 3 * 20, 0));
                    } else if (item == ItemLoader.tianHuo) {
                        addParticle(world, pos, EnumParticleTypes.LAVA);
                        entity.setFire(6);
                    }
                }
            }
            if (!player.isCreative()) {
                player.addExperienceLevel(-30);
                stack.damageItem(5, player);
            }
            player.getCooldownTracker().setCooldown(item, 20 * 60);
        }

        return stack;
    }

    /**
     * 粒子生成
     *
     * @param world d
     * @param pos   玩家坐标
     */
    static void addParticle(World world, BlockPos pos, EnumParticleTypes particle) {
        for (int i = 0; i < 20; i++) {
            world.spawnParticle(particle, pos.getX() + world.rand.nextGaussian(), pos.getY() + world.rand.nextGaussian(), pos.getZ() + world.rand.nextGaussian(),
                    0.05, 0.05, 0.05);
        }
    }

    //恒霜被动
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        World world = target.world;
        if (world.rand.nextDouble() < 0.5 && stack.getItem().equals(ItemLoader.hengShuang)) {
            target.addPotionEffect(new PotionEffect(PotionLoader.frozen, 60, 0));
        }
        if (world.rand.nextDouble() < 0.7 && stack.getItem().equals(ItemLoader.tianHuo)) {
            target.setFire(6);
        }
        return super.hitEntity(stack, target, attacker);
    }
}
