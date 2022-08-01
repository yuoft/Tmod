package com.yuoMod.Tmod.Gui;

import com.yuoMod.Tmod.Common.Items.ItemLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerDemo extends Container {
    //�½���һ��ӵ��3����Ʒ��
    private final NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);

    private final IInventory inventory;

    private int burnTime;

    public ContainerDemo(InventoryPlayer inventory, IInventory tileEntity) {
        super();
        this.inventory = tileEntity;
        //����3����Ʒ�ۼ�����GUI
        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
        this.addSlotToContainer(new SlotDiamond(tileEntity, 1, 56, 53));
        this.addSlotToContainer(new SlotOut(tileEntity, 2, 116, 35));
        //��36����ұ����е���Ʒ�ۼ�����GUI
        for (int i = 0; i < 3; ++i)//9-36
        {
            for (int j = 0; j < 9; ++j) {
                //slot��һ�����������������IInventory���ڶ�������������Ʒ����IInventory�е�ID���������������������GUI�е����ꡣ
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)//0-8
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.inventory);
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    //��Ҿ��롶64
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    //shift��Ʒ����
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (itemstack1.getItem() == ItemLoader.emerald_tree || itemstack1.getItem() == ItemLoader.emerald_ingot_block) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() == Items.DIAMOND || itemstack1.getItem() == Items.LAVA_BUCKET) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    //����ͬ��
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.burnTime != this.inventory.getField(0)) {
                icontainerlistener.sendWindowProperty(this, 0, this.inventory.getField(0));
            }
        }
        this.burnTime = inventory.getField(0);
    }

    //��������
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        this.inventory.setField(id, data);
    }

}
