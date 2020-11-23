package com.yuoMod.Tmod.Common.Inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;

public class InventoryStorageRingBag implements IInventory
{
	private String name;
	private NonNullList<ItemStack> inventory;
	private final ItemStack invItem;
	public int INV_SIZE;
	
	public InventoryStorageRingBag(ItemStack stack, int size) 
	{
		invItem = stack;
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		this.INV_SIZE=size;
		inventory = NonNullList.withSize(INV_SIZE, ItemStack.EMPTY);
		name = stack.getDisplayName();
		readFromNBT(stack.getTagCompound());
	}
	@Override
	public String getName() {
		return name;
	}
	//是否自定义名称
	@Override
	public boolean hasCustomName() {
		return name.length() > 0;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}
	//背包大小
	@Override
	public int getSizeInventory() {
		return inventory.size();
	}
	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : inventory)
			if (!itemstack.isEmpty())//背包有一个物品返回false
				return false;
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.get(index);
	}
	//减少堆叠大小
	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(inventory, index, count);
		
		if (!itemstack.isEmpty())
			markDirty();
		return itemstack;
	}
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventory, index);
	}
	//设置背包目录
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory.set(index, stack);

		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
			stack.setCount(getInventoryStackLimit());

		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < getSizeInventory(); ++i)
			if (getStackInSlot(i).isEmpty())
				inventory.set(i, ItemStack.EMPTY);
		writeToNBT(invItem.getTagCompound());
	}
	//可供玩家使用
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}
	//物品对插槽有效吗
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inventory.clear();
	}
	public void readFromNBT(NBTTagCompound tagcompound)
	{
		NBTTagList items = tagcompound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < items.tagCount(); ++i)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");

			if (slot >= 0 && slot < getSizeInventory())
				inventory.set(slot, new ItemStack(item));
		}
	}

	public void writeToNBT(NBTTagCompound tagcompound)
	{
		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i)
			if (!getStackInSlot(i).isEmpty())
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				getStackInSlot(i).writeToNBT(item);

				items.appendTag(item);
			}
		tagcompound.setTag("ItemInventory", items);
	}
	
}
