package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Inventory.InventoryStorageRingBag;
import net.minecraft.entity.player.EntityPlayer;
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
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
		Slot slot = inventorySlots.get(index);
        if (slot == null || !slot.getHasStack())
        {
            return null;
        }
        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();
        boolean isMerged = false;
        if (index >= 0 && index <=26)
        {
            isMerged = mergeItemStack(newStack, 27, 65, true);
        }
        else if (index >= 27 && index < 54)
        {
            isMerged = mergeItemStack(newStack, 0, 27, false)
                    || mergeItemStack(newStack, 54, 65, false);
        }
        else if (index >= 54 && index < 65)
        {
            isMerged = mergeItemStack(newStack, 0, 27, false)
                    || mergeItemStack(newStack, 27, 54, false);
        }

        if (!isMerged)
        {
            return null;
        }
        if (newStack.getMaxStackSize() == 0)
        {
            slot.putStack(null);
        }
        else
        {
            slot.onSlotChanged();
        }
        slot.onTake(playerIn, newStack);

        return oldStack;
    }
	
}
