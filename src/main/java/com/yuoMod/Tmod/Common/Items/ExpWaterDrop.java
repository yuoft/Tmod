package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExpWaterDrop extends Item {
    public ExpWaterDrop(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(64);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (playerIn.isSneaking())//玩家是否潜行
        {
            if (stack.getItem().equals(ItemLoader.exp_small)) {
                playerIn.addExperience(10 * stack.getCount());
            } else {
                playerIn.addExperience(100 * stack.getCount());
            }
            stack.setCount(0);
        } else {
            if (stack.getItem().equals(ItemLoader.exp_small)) {
                playerIn.addExperience(10);
            } else {
                playerIn.addExperience(100);
            }
            stack.setCount(stack.getCount() - 1);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    //物品介绍信息
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.exp", ""));
    }
}
