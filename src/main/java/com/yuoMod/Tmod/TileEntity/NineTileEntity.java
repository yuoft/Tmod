package com.yuoMod.Tmod.TileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class NineTileEntity extends TileEntity implements ITickable
{
	private final ItemStackHandler inventory = new ItemStackHandler(54);
//	private NonNullList<ItemStack> stack=NonNullList.withSize(54, ItemStack.EMPTY);;
//	private String name="emerald_chest";
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				|| super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this.inventory);
//                       					new ItemHandler(inventory));
		} 
		else return super.getCapability(capability, facing);
	}
	@Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.inventory.deserializeNBT(nbt.getCompoundTag("Inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setTag("Inventory", this.inventory.serializeNBT());
		return super.writeToNBT(nbt);
    }
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

	public ItemStackHandler getInventory() {
		return this.inventory;
	}

	@Override
	public void update() {
		// TODO 自动生成的方法存根
		
	}

//	@Override
//	public int getSizeInventory() {
//		return this.getItems().size();
//	}
//
//	@Override
//	public boolean isEmpty() {
//		for (ItemStack itemstack : stack)
//			if (!itemstack.isEmpty())//背包有一个物品返回false
//				return false;
//		return true;
//	}
//	@Override
//	public int getInventoryStackLimit() {
//		return 64;
//	}
//
//	@Override
//	public String getName() {
//		return this.name;
//	}
//
//	@Override
//	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
//		return new ChestContainer(playerIn, new NineTileEntity());
//	}
//
//	@Override
//	public String getGuiID() {
//		return "5";
//	}
//
//	@Override
//	protected NonNullList<ItemStack> getItems() {
//		return stack;
//	}
}
