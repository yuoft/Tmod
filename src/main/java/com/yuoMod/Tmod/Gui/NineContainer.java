package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Inventory.InventoryStorageRingBag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class NineContainer extends Container 
{
	private final InventoryStorageRingBag inventorys;
	
	public NineContainer(EntityPlayer player, InventoryStorageRingBag inventoryStorageRingBag)
	{
		super();
		this.inventorys=inventoryStorageRingBag;
		for(int i=0; i< 9; i++)//0-80
		{
			for(int j=0; j<9; j++)
			{
				this.addSlotToContainer(new Slot(inventorys, j+i*9, 39+j*18, 8+i*18));
			}
		}
        for (int i = 0; i < 3; ++i)//9-36
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 39 + j * 18, 174 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)//0-8
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 39 + i * 18, 232));
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
        if (index >= 0 && index <=80)
        {
            isMerged = mergeItemStack(newStack, 81, 117, true);
        }
        else if (index >= 81 && index < 108)
        {
            isMerged = mergeItemStack(newStack, 0, 81, false)
                    || mergeItemStack(newStack, 108, 117, false);
        }
        else if (index >= 108 && index < 117)
        {
            isMerged = mergeItemStack(newStack, 0, 80, false)
                    || mergeItemStack(newStack, 81, 108, false);
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
//	@Override
//	public void onContainerClosed(EntityPlayer playerIn) {
//		super.onContainerClosed(playerIn);
//		//检查玩家是否处于服务端
//		if (playerIn.isServerWorld()) {
//			for(int i=0; i<9 ;i++)
//			{
//				for(int j=0; j<9 ;j++)
//				{
//					ItemStack itemStack=this.slots[i][j].getStack();
//					if(itemStack !=null)
//					{
////						playerIn.dropItem(itemStack,false);
//						BlockPos pos=playerIn.getPosition();
//						Block.spawnAsEntity(this.world, pos, itemStack);
//					}
//				}
//			}
//		}
//	}
	
}
