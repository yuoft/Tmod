package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Entity.EntityGoldenTNT;

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

public class GoldenTNT extends Item {
    public GoldenTNT(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote)//判断是否是service
        {
            ItemStack itemStack = playerIn.getHeldItem(handIn);
            if (!playerIn.capabilities.isCreativeMode) {
                itemStack.setCount(itemStack.getCount() - 1);
            }
            EntityGoldenTNT egg = new EntityGoldenTNT(worldIn, playerIn);
            //确定投掷物的初速度方向rotationYaw:实体围绕Y轴旋转程度；rotationPitch：实体围绕X轴旋转的程度;render:渲染偏移
            egg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead, 0f, 2.0f, 0.1f);
            worldIn.spawnEntity(egg);
            return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
        } else return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    //物品介绍信息
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.golden_tnt1", ""));
        tooltip.add(I18n.format("tmod.item.golden_tnt2", ""));
    }
}
