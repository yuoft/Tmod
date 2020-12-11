package com.yuoMod.Tmod.Gui;

import javax.annotation.Nonnull;

import com.yuoMod.Tmod.Common.Inventory.InventoryStorageRingBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ThreeContainer extends Container 
{
	private final InventoryStorageRingBag inventorys;
	
	public ThreeContainer(EntityPlayer player, InventoryStorageRingBag inventoryStorageRingBag)
	{
		super();
		this.inventorys=inventoryStorageRingBag;
		for(int i=0; i< 3; i++)//27
		{
			for(int j=0; j<9; j++)
			{
				this.addSlotToContainer(new Slot(inventorys, j+i*9, 8+j*18, 18+i*18));
			}
		}
        for (int i = 0; i < 3; ++i)//9-36
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)//0-8
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)//´ýÐÞ¸Ä
    {
		Slot slot = this.getSlot(index);
		
		if (slot == null || !slot.getHasStack()) 
		{
			return ItemStack.EMPTY;
		}
		
		ItemStack stack = slot.getStack();
		ItemStack newStack = stack.copy();
		
		if (index < 27)
		{
			if (!this.mergeItemStack(stack, 27, this.inventorySlots.size(), true))
				return ItemStack.EMPTY;
			slot.onSlotChanged();
		}
		else if (!this.mergeItemStack(stack, 0, 27, false))
		{
			return ItemStack.EMPTY;
		}
		if (stack.isEmpty())
		{
			slot.putStack(ItemStack.EMPTY);
		}
		else
		{
			slot.onSlotChanged();
		}
		
		return slot.onTake(playerIn, newStack);
    }
	@Nonnull
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
	{
		if(slotId == (inventorySlots.size() - 1) - (8 - player.inventory.currentItem))
		{
			return ItemStack.EMPTY;
		}
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
}
