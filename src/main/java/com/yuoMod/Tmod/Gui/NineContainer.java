package com.yuoMod.Tmod.Gui;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NineContainer extends Container 
{
	private ItemStackHandler items=new ItemStackHandler(82);
	private Slot[][] slots=new Slot[9][9]; 
	private World world;
	
	public NineContainer(EntityPlayer player,World worldIn)
	{
		super();
		this.world=worldIn;
		for(int i=0; i<9; i++)//0-80
		{
			for(int j=0; j<9; j++)
			{
				this.addSlotToContainer(this.slots[i][j] = new SlotItemHandler(items, j+i*9, 12+j*18, 8+i*18));
			}
		}
		this.addSlotToContainer(new SlotItemHandler(items, 81, 210, 79));
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
		return false;
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
        if (index >= 0 && index <=81)
        {
            isMerged = mergeItemStack(newStack, 82, 118, true);
        }
        else if (index >= 82 && index < 109)
        {
            isMerged = mergeItemStack(newStack, 0, 81, false)
            		|| mergeItemStack(newStack, 81, 82, false)
                    || mergeItemStack(newStack, 109, 118, false);
        }
        else if (index >= 109 && index < 118)
        {
            isMerged = mergeItemStack(newStack, 0, 81, false)
            		|| mergeItemStack(newStack, 81, 82, false)
                    || mergeItemStack(newStack, 82, 109, false);
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
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		//检查玩家是否处于服务端
		if (playerIn.isServerWorld()) {
			for(int i=0; i<9 ;i++)
			{
				for(int j=0; j<9 ;j++)
				{
					ItemStack itemStack=this.slots[i][j].getStack();
					if(itemStack !=null)
					{
//						playerIn.dropItem(itemStack,false);
						BlockPos pos=playerIn.getPosition();
						Block.spawnAsEntity(this.world, pos, itemStack);
					}
				}
			}
		}
	}
	
}
