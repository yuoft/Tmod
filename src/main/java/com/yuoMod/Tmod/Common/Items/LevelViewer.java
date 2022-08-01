package com.yuoMod.Tmod.Common.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.yuoMod.Tmod.Capability.CapabilityLoader;
import com.yuoMod.Tmod.Capability.IPlayerLevel;
import com.yuoMod.Tmod.Tab.TmodGroup;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//玩家等级查看器
public class LevelViewer extends Item {
    public LevelViewer(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(TmodGroup.TMOD);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            IPlayerLevel playerCaop = playerIn.getCapability(CapabilityLoader.tmodLv, null);
            Integer exp = playerCaop.getPlayerExp();
            Integer lv = playerCaop.getPlayerLevel();
            int expCeil = (int) Math.pow(lv, 2) + 2 * lv;
            playerIn.sendMessage(new TextComponentTranslation(I18n.format("tmod.text.level") + lv.intValue()));
            if (lv == 100) {
                playerIn.sendMessage(new TextComponentTranslation(I18n.format("tmod.text.exp_top")));
            } else
                playerIn.sendMessage(new TextComponentTranslation(I18n.format("tmod.text.exp") + exp.intValue() + "/" + expCeil));
//			ItemStack heldItem = playerIn.getHeldItem(EnumHand.OFF_HAND);
//			if (!heldItem.isEmpty()){
//				heldItem.damageItem(heldItem.getMaxDamage() / 2, playerIn);
//			}
//			int id = worldIn.provider.getDimension();
//			playerIn.sendMessage(new TextComponentTranslation("世界维度：" + id)); 
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tmod.item.player_level", ""));
    }
}
