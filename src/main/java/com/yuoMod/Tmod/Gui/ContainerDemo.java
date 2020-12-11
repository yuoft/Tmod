package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.TileEntity.MyTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerDemo extends Container
{
	//�½���һ��ӵ��3����Ʒ�۵�ItemStackHandler
	private ItemHandler OneItems;
    private ItemHandler TwoItems;
    private ItemHandler ThreeItems;
	
    protected MyTileEntity tileEntity;

    protected int burnTime = 0;
	
	public ContainerDemo(EntityPlayer entityPlayer,TileEntity tileEntity)
    {
        super();
        this.tileEntity=(MyTileEntity)tileEntity;
        this.burnTime=this.tileEntity.getBurnTime();
        //��tileEntity������Ʒ��
        this.OneItems= (ItemHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.UP);
        this.TwoItems= (ItemHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null);
        this.ThreeItems= (ItemHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.DOWN);
        //����3����Ʒ�ۼ�����GUI       
        this.addSlotToContainer(new SlotItemHandler(OneItems, 0, 56, 17) {
			//isItemValid���������ж���Ʒ�Ƿ���ԷŽ�ȥ��getItemStackLimit���������ж���Ʒ�����ԷŽ�ȥ����
			@Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null && stack.getItem().equals(itemLoader.emerald_tree) && super.isItemValid(stack);
            }
		});
		this.addSlotToContainer(new SlotItemHandler(TwoItems, 1, 56, 53) {
			
			@Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null && stack.getItem().equals(Items.DIAMOND) && super.isItemValid(stack);
            }
		});
		this.addSlotToContainer(new SlotItemHandler(ThreeItems, 2, 116, 35) {
			@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;//stack != null && stack.getItem() == Items.EMERALD && super.isItemValid(stack);
            }
		});
        //��36����ұ����е���Ʒ�ۼ�����GUI
        for (int i = 0; i < 3; ++i)//9-36
        {
            for (int j = 0; j < 9; ++j)
            {
            	//slot��һ�����������������IInventory���ڶ�������������Ʒ����IInventory�е�ID���������������������GUI�е����ꡣ
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)//0-8
        {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }
    }
	//��Ҿ��롶64
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
	}
	//shift��Ʒ����
	/**
	 * �������Եȼ۽���ģ��
	 */
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = this.getSlot(index);
		
		if (slot == null || !slot.getHasStack()) 
		{
			return ItemStack.EMPTY;
		}
		
		ItemStack stack = slot.getStack();
		ItemStack newStack = stack.copy();
		
		if (index < 3)
		{
			if (!this.mergeItemStack(stack, 3, this.inventorySlots.size(), true))
				return ItemStack.EMPTY;
			slot.onSlotChanged();
		}
		else if (!this.mergeItemStack(stack, 0, 3, false))
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
	//����ͬ��
	@Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.burnTime = tileEntity.getBurnTime();
        /*
         * Container���ṩ��һ����Ϊcrafters���б����ڱ�ʾ������Container�����ж�������ҵȡ����Ǳ�����һ�б���������sendProgressBarUpdate�����������ݡ�
sendProgressBarUpdate�����ĵ�һ����������ָ��ͬ�����ݵ�Container�����ﴫ��this�Ϳ�����
sendProgressBarUpdate�����ĵڶ�����������ָ�����ݵı�ʶ����һ�������Ӳ����
sendProgressBarUpdate�����ĵ�������������ָ��һ����������
         */
        for (IContainerListener i : this.listeners)
        {
            i.sendWindowProperty(this, 0, this.burnTime);
        }
    }
	//��������
	@SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);

        switch (id)
        {
        case 0:
            this.burnTime = data;
            break;
        default:
            break;
        }
    }

	public int getBurnTime() {
		return this.burnTime;
	}

	public int getTotalBurnTime() {
		return this.tileEntity.getTotalBurnTime();
	}
//	@Override
//	public void onContainerClosed(EntityPlayer playerIn) {
//		super.onContainerClosed(playerIn);
//		//�������Ƿ��ڷ����
//		if (playerIn.isServerWorld()) {
//			//��3����Ҫ�������Ʒ���л�ȡ��Ʒ�����ж����Ƿ�Ϊ��
//			ItemStack OneStack = this.OneSlot.getStack();
//			if (OneStack != null) {
//				//����ǿգ�����ͨ������EntityPlayer��dropPlayerItemWithRandomChoice���������ɵ����������Ʒ������Ϊ��
//				playerIn.dropItem(OneStack, false);
////				this.OneSlot.putStack(null);
//			}
//			ItemStack TwoStack = this.TwoSlot.getStack();
//			if (TwoStack != null) {
//				playerIn.dropItem(TwoStack, false);
////				this.TwoSlot.putStack(null);
//			}
//			ItemStack ThreeStack = this.ThreeSlot.getStack();
//			if (ThreeStack != null) {
//				playerIn.dropItem(ThreeStack, false);
////				this.TwoSlot.putStack(null);
//			}
//		}
//	}
}
