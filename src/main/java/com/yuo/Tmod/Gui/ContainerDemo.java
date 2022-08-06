package com.yuo.Tmod.Gui;

import com.yuo.Tmod.Common.Items.ItemLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerDemo extends Container {
    //新建了一个拥有3个物品槽
    private final NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);

    private final IInventory inventory;

    private int burnTime;
    private int totalTime;

    public ContainerDemo(InventoryPlayer inventory, IInventory tileEntity) {
        super();
        this.inventory = tileEntity;
        //把这3个物品槽加入了GUI
        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
        this.addSlotToContainer(new SlotDiamond(tileEntity, 1, 56, 53));
        this.addSlotToContainer(new SlotOut(tileEntity, 2, 116, 35));
        //把36个玩家背包中的物品槽加入了GUI
        for (int i = 0; i < 3; ++i) {//9-36
            for (int j = 0; j < 9; ++j) {//slot第一个参数代表相关联的IInventory，第二个参数代表物品槽在IInventory中的ID，最后两个参数代表它在GUI中的坐标。

                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {//0-9
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.inventory);
    }

    //玩家距离《64
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    //shift物品交换
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
                if (itemstack1.getItem() == ItemLoader.emeraldTree || itemstack1.getItem() == ItemLoader.emeraldIngotBlock) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.getItem() == Items.DIAMOND || itemstack1.getItem() == Items.LAVA_BUCKET) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
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

    //数据同步
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener icontainerlistener : this.listeners) {
            if (this.burnTime != this.inventory.getField(0)) {
                icontainerlistener.sendWindowProperty(this, 0, this.inventory.getField(0));
            }
            if (this.totalTime != this.inventory.getField(1)) {
                icontainerlistener.sendWindowProperty(this, 1, this.inventory.getField(1));
            }
        }
        this.burnTime = inventory.getField(0);
        this.totalTime = inventory.getField(1);
    }

    //更新数据
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        this.inventory.setField(id, data);
    }

}
