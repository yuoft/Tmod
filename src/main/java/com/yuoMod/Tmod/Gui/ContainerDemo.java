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
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
		//获取对应的物品槽，然后判断对应的物品槽或者里面的物品是否存在，如果不存在，自然不需要放入其他的物品槽了：
		Slot slot = inventorySlots.get(index);
        if (slot == null || !slot.getHasStack())
        {
            return null;
        }
        //获取到对应的ItemStack，也就是newStack，等待进一步处理，同时为了作为返回值，我们需要复制一份，也就是oldStack。
        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();
        // // TODO注释的部分就是真正尝试把物品的部分放入第一个可用的物品槽的部分，我们同时新添加了isMerged布尔值用于判定是否被放入其他物品槽。
        boolean isMerged = false;

        // TODO
        /*
         * 调用Container类的mergeItemStack方法，以试图把物品的部分放入第一个可用的物品槽：
该方法的第一个参数传入想要更改的ItemStack
该方法的第二个参数和第三个参数传入想要放入的物品槽的开始ID（包含）和结束ID（不包含），在这里也就是3-38，即玩家背包中的36个物品槽
该方法的最后一个参数传入是正向查找第一个可用物品槽（3-38），还是反向查找（38-3），当然如果等放入的物品槽只有一个（后面会遇到）那么这两者是没有区别的
对于正向还是反向查找，游戏有一个约定：通常情况下，如果从非玩家背包的GUI物品槽中试图将物品放入玩家物品槽，那么使用反向查找，如果从玩家背包中试图将物品放入玩家背包或非玩家背包物品槽，则使用正向查找。
mergeItemStack方法的返回值用于标识是否成功把物品的部分放入了一个可用的物品槽，如果成功放入了，则返回真，否则返回假。
         */
        //从gui中放入玩家背包
        if (index == 0 || index == 1 || index == 2)
        {
            isMerged = mergeItemStack(newStack, 3, 39, true);
        }
        /*
         * 我们先试着把ID为3-29的27个物品槽中对应的物品槽中的物品放入绿宝石木头对应的ID为0的物品槽中，前提是该物品槽中没有物品，并且试图放入的不超过32个（!goldSlot.getHasStack() && newStack.stackSize <= 32）。
         * 如果没有放入成功（执行||后的语句），则试图放入绿宝石槽，同理，如果还是没有放入成功，则试图放入9个剩下的物品槽。
         */
        //从背包放入gui
        else if (index >= 3 && index < 30)
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
            		|| mergeItemStack(newStack, 1, 2, false)
                    || mergeItemStack(newStack, 30, 39, false);
        }
        else if (index >= 30 && index < 39)
        {
            isMerged = mergeItemStack(newStack, 0, 1, false)
            		|| mergeItemStack(newStack, 1, 2, false)
                    || mergeItemStack(newStack, 3, 30, false);
        }

        if (!isMerged)
        {
            return null;
        }
        //如果所有情况下都没有将物品放入其他的物品槽，那么就代表没有可用的物品槽用于放入了，此时我们返回null。

        //如果成功尝试将物品放入了一个物品槽，那么我们就需要告知游戏，对应的物品槽产生了更新：
        if (newStack.getMaxStackSize() == 0)
        {
            slot.putStack(null);
        }
        else
        {
            slot.onSlotChanged();
        }
        //Mojang独特的设计方式，有的物品槽还会覆写onTake方法进行处理，为了对其进行支持，我们同时调用了这个方法：
        slot.onTake(playerIn, newStack);

        return oldStack;
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
