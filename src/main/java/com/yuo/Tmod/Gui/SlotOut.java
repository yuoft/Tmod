package com.yuo.Tmod.Gui;

import com.yuo.Tmod.Common.Recipe.PowerRecipeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class SlotOut extends Slot {
    public SlotOut(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return super.canTakeStack(playerIn);
    }

//    @Override
//    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
//        int level = thePlayer.experienceLevel;
//        int exp = PowerRecipeManager.getRecipeExp(stack);
//        if (exp > 0 && !thePlayer.world.isRemote){
//            int expTotal = exp * stack.getCount();
//            if (level >= expTotal){
//                thePlayer.addExperienceLevel(-expTotal);
//                return super.onTake(thePlayer, stack);
//            }else if (level > exp){
//                thePlayer.addExperienceLevel(-(level / exp));
//                onSlotChanged();
//                return new ItemStack(stack.getItem(), (level / exp));
//            } else {
//                thePlayer.sendStatusMessage(new TextComponentString("经验不足"), true);
//                return ItemStack.EMPTY;
//            }
//        }else return super.onTake(thePlayer, stack);
//    }
}
