package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
//tileEntity输入输出控制
public class ItemHandler extends ItemStackHandler implements IItemHandlerModifiable,IItemHandler
{
	private ItemStackHandler items = new ItemStackHandler(3);
	
	public ItemHandler(ItemStackHandler inventory)
	{
		this.items=inventory;
	}
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		// 仅当对 0 ,1号槽位尝试输入物品时允许通过，否则拒绝输入
		if(slot == 0)
		{
			if(stack.getItem().equals(itemLoader.emerald_tree))
				return items.insertItem(slot, stack, simulate);
			else return stack;
		}
		else if(slot == 1)
		{
			if(stack.getItem().equals(Items.DIAMOND))
				return items.insertItem(slot, stack, simulate);
			else return stack;
		}
		else return stack;
//        if (slot == 0 || slot == 1) {
//            return items.insertItem(slot, stack, simulate);
//        }
//		return stack;
	}
	@Override
	public ItemStack getStackInSlot(int slot) {
		return items.getStackInSlot(slot);
	}
	@Override
	public int getSlots() {
		return items.getSlots();
	}
	@Override
	public int getSlotLimit(int slot) {
		return items.getSlotLimit(slot);
	}
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		// 仅当对 2号槽位尝试提取物品时允许通过，否则拒绝提取
//        if (items.getStackInSlot(slot).getItem().equals(Items.EMERALD)) {
            return items.extractItem(slot, amount, simulate);
//        }
//        else  return ItemStack.EMPTY;
	}
	@Override
    public boolean isItemValid(int slot, ItemStack stack) {
        // 我们只允许 0 ,1号槽位输入物品。
        // 2 号槽位中的物品不能通过自动化手段输入，必须由我们自己控制。
		if(slot == 0)
		{
			if(stack.getItem().equals(itemLoader.emerald_tree))
				return true;
			else return false;
		}
		else if(slot == 1)
		{
			if(stack.getItem().equals(Items.DIAMOND))
				return true;
			else return false;
		}
		else return false;
//		return slot == 0 || slot ==1;
    }
	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		items.setStackInSlot(slot, stack);
	}
}