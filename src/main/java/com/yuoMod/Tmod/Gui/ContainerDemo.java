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
	//新建了一个拥有3个物品槽的ItemStackHandler
	private ItemStackHandler items = new ItemStackHandler(3);
	//把3个物品槽作为字段存储起来，并且使用匿名内部类的方式扩展：
    final Slot OneSlot;
	final Slot TwoSlot;
	final Slot ThreeSlot;
	
	public ContainerDemo(EntityPlayer entityPlayer)
    {
        super();
        //把这3个物品槽加入了GUI       
        this.addSlotToContainer(new SlotItemHandler(items, 0, 56, 17));
        this.addSlotToContainer(new SlotItemHandler(items, 1, 56, 53));
        this.addSlotToContainer(new SlotItemHandler(items, 2, 116, 35));
        //把36个玩家背包中的物品槽加入了GUI
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
            	//slot第一个参数代表相关联的IInventory，第二个参数代表物品槽在IInventory中的ID，最后两个参数代表它在GUI中的坐标。
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }

		this.addSlotToContainer(this.OneSlot = new SlotItemHandler(items, 0, 56, 17) {
			//isItemValid方法用于判定物品是否可以放进去，getItemStackLimit方法用于判定物品最多可以放进去多少
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
            //预放入物品
//			{
//                this.putStack(new ItemStack(Items.DIAMOND, 64));
//            }
			//物品是否可以取出
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
			//物品数量更新
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
		// TODO 自动生成的方法存根
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
		//检查玩家是否处于服务端
		if (playerIn.isServerWorld()) {
			//从3个需要掉落的物品栏中获取物品，并判断其是否为空
			ItemStack OneStack = this.OneSlot.getStack();
			if (OneStack != null) {
				//如果非空，我们通过调用EntityPlayer的dropPlayerItemWithRandomChoice方法以生成掉落物，并把物品槽设置为空
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
