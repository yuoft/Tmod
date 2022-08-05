package com.yuo.Tmod.TileEntity;

import com.yuo.Tmod.Gui.ChestContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class NineTileEntity extends TileEntityLockableLoot implements ITickable {
    //    private final ItemStackHandler inventory = new ItemStackHandler(54);
    public NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);
    private final String name = "emerald_chest";

    @Override
    public boolean hasCapability(@Nonnull Capability<?> cap, EnumFacing side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, side);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, EnumFacing side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemStackHandler(this.items));
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        ItemStackHelper.loadAllItems(nbt, items);
//        inventory.deserializeNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
//        nbt = super.writeToNBT(nbt);
        ItemStackHelper.saveAllItems(nbt, items);
//        nbt.merge(inventory.serializeNBT());
        return nbt;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public void update() {
        // TODO 自动生成的方法存根

    }

    @Override
    public int getSizeInventory() {
        return this.getItems().size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : items)
            if (!itemstack.isEmpty())//背包有一个物品返回false
                return false;
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 54;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ChestContainer(playerIn, this);
    }

    @Override
    public String getGuiID() {
        return "tmod:" + this.name;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }
}
