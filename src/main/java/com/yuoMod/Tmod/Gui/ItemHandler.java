package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
//tileEntity�����������
public class ItemHandler extends ItemStackHandler implements IItemHandlerModifiable,IItemHandler
{
	private ItemStackHandler items = new ItemStackHandler(3);
	
	public ItemHandler(ItemStackHandler inventory)
	{
		this.items=inventory;
	}
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		// ������ 0 ,1�Ų�λ����������Ʒʱ����ͨ��������ܾ�����
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
		// ������ 2�Ų�λ������ȡ��Ʒʱ����ͨ��������ܾ���ȡ
//        if (items.getStackInSlot(slot).getItem().equals(Items.EMERALD)) {
            return items.extractItem(slot, amount, simulate);
//        }
//        else  return ItemStack.EMPTY;
	}
	@Override
    public boolean isItemValid(int slot, ItemStack stack) {
        // ����ֻ���� 0 ,1�Ų�λ������Ʒ��
        // 2 �Ų�λ�е���Ʒ����ͨ���Զ����ֶ����룬�����������Լ����ơ�
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