package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.TileEntity.NineTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ChestContainer extends Container 
{
	private final NineTileEntity tileEntity;
	private ItemStackHandler items=new ItemStackHandler(54);
	private BlockPos pos;
	
	public ChestContainer(EntityPlayer player, BlockPos pos, TileEntity tileEntity)
	{
		super();
		this.pos=pos;
		this.tileEntity=(NineTileEntity) tileEntity;
		this.items=(ItemStackHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for(int i=0; i< 6; i++)//54
		{
			for(int j=0; j<9; j++)
			{
				this.addSlotToContainer(new SlotItemHandler(items, j+i*9, 8+j*18, 18+i*18));
			}
		}
        for (int i = 0; i < 3; ++i)//9-36
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)//0-8
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 198));
        }
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
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
        if (index >= 0 && index <=53)
        {
            isMerged = mergeItemStack(newStack, 54, 90, true);
        }
        else if (index >= 54 && index < 81)
        {
            isMerged = mergeItemStack(newStack, 0, 54, false)
                    || mergeItemStack(newStack, 81, 90, false);
        }
        else if (index >= 81 && index < 90)
        {
            isMerged = mergeItemStack(newStack, 0, 54, false)
                    || mergeItemStack(newStack, 54, 81, false);
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
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }
	@SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);

        switch (id)
        {
        case 0:
            break;
        default:
            break;
        }
    }
	public void onContainerClosed(EntityPlayer playerIn) {
		playerIn.world.playSound(playerIn, this.pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 20.0f, 1.0f);
	}

}
