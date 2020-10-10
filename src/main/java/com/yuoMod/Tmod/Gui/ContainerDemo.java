package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Items.itemLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerDemo extends Container
{
	//�½���һ��ӵ��3����Ʒ�۵�ItemStackHandler
	private ItemStackHandler items = new ItemStackHandler(3);
	//��3����Ʒ����Ϊ�ֶδ洢����������ʹ�������ڲ���ķ�ʽ��չ��
    final Slot OneSlot;
	final Slot TwoSlot;
	final Slot ThreeSlot;
	
	public ContainerDemo(EntityPlayer entityPlayer)
    {
        super();
        //����3����Ʒ�ۼ�����GUI       
        this.addSlotToContainer(new SlotItemHandler(items, 0, 56, 17));
        this.addSlotToContainer(new SlotItemHandler(items, 1, 56, 53));
        this.addSlotToContainer(new SlotItemHandler(items, 2, 116, 35));
        //��36����ұ����е���Ʒ�ۼ�����GUI
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
            	//slot��һ�����������������IInventory���ڶ�������������Ʒ����IInventory�е�ID���������������������GUI�е����ꡣ
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }

		this.addSlotToContainer(this.OneSlot = new SlotItemHandler(items, 0, 56, 17) {
			//isItemValid���������ж���Ʒ�Ƿ���ԷŽ�ȥ��getItemStackLimit���������ж���Ʒ�����ԷŽ�ȥ����
			@Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null && stack.getItem() == itemLoader.emerald_tree && super.isItemValid(stack);
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 16;
            }
		});
		this.addSlotToContainer(this.TwoSlot = new SlotItemHandler(items, 1, 56, 53) {
			
			@Override
            public boolean isItemValid(ItemStack stack)
            {
                return stack != null && stack.getItem() == Items.DIAMOND && super.isItemValid(stack);
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 32;
            }
            //Ԥ������Ʒ
//			{
//                this.putStack(new ItemStack(Items.DIAMOND, 64));
//            }
			//��Ʒ�Ƿ����ȡ��
            @Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return true;
            }
		});
		this.addSlotToContainer(this.ThreeSlot = new SlotItemHandler(items, 2, 116, 35) {
			@Override
            public boolean isItemValid(ItemStack stack)
            {
				return stack != null && stack.getItem() == Items.EMERALD && super.isItemValid(stack);
            }
			//��Ʒ��������
//			@Override
//			public void onSlotChanged() {
//				ItemStack stack = this.getStack();
//				int amount = stack == null ? 0 : stack.getMaxStackSize()-1;
//				ContainerDemo.this.OneSlot.putStack(new ItemStack(Items.DIAMOND, amount));
//				ContainerDemo.this.TwoSlot.putStack(new ItemStack(Items.DIAMOND, amount));
//				super.onSlotChanged();
//			}
		});
    }
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		// TODO �Զ����ɵķ������
		return true;
	}
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        return null;
    }

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		//�������Ƿ��ڷ����
		if (playerIn.isServerWorld()) {
			//��3����Ҫ�������Ʒ���л�ȡ��Ʒ�����ж����Ƿ�Ϊ��
			ItemStack OneStack = this.OneSlot.getStack();
			if (OneStack != null) {
				//����ǿգ�����ͨ������EntityPlayer��dropPlayerItemWithRandomChoice���������ɵ����������Ʒ������Ϊ��
				playerIn.dropItem(OneStack, false);
//				this.OneSlot.putStack(null);
			}
			ItemStack TwoStack = this.TwoSlot.getStack();
			if (TwoStack != null) {
				playerIn.dropItem(TwoStack, false);
//				this.TwoSlot.putStack(null);
			}
			ItemStack ThreeStack = this.ThreeSlot.getStack();
			if (ThreeStack != null) {
				playerIn.dropItem(ThreeStack, false);
//				this.TwoSlot.putStack(null);
			}
		}
	}
}
