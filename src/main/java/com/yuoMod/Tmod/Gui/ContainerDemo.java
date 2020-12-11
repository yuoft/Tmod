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
	//新建了一个拥有3个物品槽的ItemStackHandler
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
        //从tileEntity接收物品槽
        this.OneItems= (ItemHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.UP);
        this.TwoItems= (ItemHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null);
        this.ThreeItems= (ItemHandler) this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.DOWN);
        //把这3个物品槽加入了GUI       
        this.addSlotToContainer(new SlotItemHandler(OneItems, 0, 56, 17) {
			//isItemValid方法用于判定物品是否可以放进去，getItemStackLimit方法用于判定物品最多可以放进去多少
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
        //把36个玩家背包中的物品槽加入了GUI
        for (int i = 0; i < 3; ++i)//9-36
        {
            for (int j = 0; j < 9; ++j)
            {
            	//slot第一个参数代表相关联的IInventory，第二个参数代表物品槽在IInventory中的ID，最后两个参数代表它在GUI中的坐标。
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)//0-8
        {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }
    }
	//玩家距离《64
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
	}
	//shift物品交换
	/**
	 * 代码来自等价交换模组
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
	//数据同步
	@Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.burnTime = tileEntity.getBurnTime();
        /*
         * Container类提供了一个名为crafters的列表，用于表示监听该Container的所有对象，如玩家等。我们遍历这一列表，并调用其sendProgressBarUpdate方法发送数据。
sendProgressBarUpdate方法的第一个参数用于指定同步数据的Container，这里传入this就可以了
sendProgressBarUpdate方法的第二个参数用于指定数据的标识符，一般情况下硬编码
sendProgressBarUpdate方法的第三个参数用于指定一个整数数据
         */
        for (IContainerListener i : this.listeners)
        {
            i.sendWindowProperty(this, 0, this.burnTime);
        }
    }
	//更新数据
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
//		//检查玩家是否处于服务端
//		if (playerIn.isServerWorld()) {
//			//从3个需要掉落的物品栏中获取物品，并判断其是否为空
//			ItemStack OneStack = this.OneSlot.getStack();
//			if (OneStack != null) {
//				//如果非空，我们通过调用EntityPlayer的dropPlayerItemWithRandomChoice方法以生成掉落物，并把物品槽设置为空
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
