package com.yuo.Tmod.Common.Items;

import com.yuo.Tmod.Capability.CapabilityLoader;
import com.yuo.Tmod.Capability.IPlayerLevel;
import com.yuo.Tmod.Tab.TmodGroup;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * 升级宝石 提示一级
 *
 * @author yuo
 */
public class UpGradeGem extends Item {

    public UpGradeGem(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.OTHER_TAB);
        this.setMaxStackSize(16);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.up_grade_gem", ""));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItemOffhand(); //获取玩家副手物品
        if (!worldIn.isRemote) {
            if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("tmod_level")
                    && playerIn.hasCapability(CapabilityLoader.tmodLv, null)) {
                int level = stack.getTagCompound().getInteger("tmod_level"); // 获取物品等级
                IPlayerLevel cap = playerIn.getCapability(CapabilityLoader.tmodLv, null);
                if (cap != null){
                    Integer value = cap.getPlayerLevel();
                    if ((level + 1) <= value) {
                        stack.getTagCompound().setInteger("tmod_level", level + 1 + new Random().nextInt(3)); // 物品等级加一 ~ 三
                        playerIn.getHeldItemMainhand().shrink(1);
                        playerIn.sendMessage(new TextComponentTranslation(I18n.format("tmod.text.upgrade_gem1") + (level + 1)));
                    } else {
                        playerIn.sendMessage(new TextComponentTranslation("tmod.text.upgrade_gem3"));
                    }
                }
            } else {
                playerIn.sendMessage(new TextComponentTranslation("tmod.text.upgrade_gem2"));
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
