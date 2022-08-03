package com.yuoMod.Tmod.Common.Items;

import com.yuoMod.Tmod.Tmod;
import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Gui.GuiLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StorageRing extends Item {
    public StorageRing(String name) {
        super();
        this.setUnlocalizedName(name);
        this.setCreativeTab(TmodGroup.TMOD);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }

    //右键打开gui
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (!worldIn.isRemote) {//判断是否是service
            if (stack.getItem().equals(ItemLoader.storageRingBig)) {
                BlockPos pos = playerIn.getPosition();
                playerIn.openGui(Tmod.instance, GuiLoader.NINE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            if (stack.getItem().equals(ItemLoader.storageRingIn)) {
                BlockPos pos = playerIn.getPosition();
                playerIn.openGui(Tmod.instance, GuiLoader.SIX_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            if (stack.getItem().equals(ItemLoader.storageRingSmall)) {
                BlockPos pos = playerIn.getPosition();
                playerIn.openGui(Tmod.instance, GuiLoader.THREE_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
