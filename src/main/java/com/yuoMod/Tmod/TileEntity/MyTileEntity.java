package com.yuoMod.Tmod.TileEntity;

import com.yuoMod.Tmod.Common.Blocks.PowerExtractor;
import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Fluid.FluidLoader;
import com.yuoMod.Tmod.Gui.ContainerDemo;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class MyTileEntity extends TileEntityLockable implements ITickable, ISidedInventory {
    private int burnTime = 0;
    private final int burnTotalTime = 160;
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDES = new int[]{1};
    //����3���洢λ
    private NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.burnTime = nbt.getInteger("BurnTime");
        this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.stacks);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("BurnTime", this.burnTime);
        ItemStackHelper.saveAllItems(nbt, stacks);
        return nbt;
    }

    public int getBurnTime() {
        return this.burnTime;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        ItemStack stack0 = this.stacks.get(0);
        ItemStack stack1 = this.stacks.get(1);
        ItemStack stack2 = this.stacks.get(2);
        if (stack0.isEmpty() || stack1.isEmpty()) return;

        //��Ʒ��ȷ ����δ��
        if (stack0.getItem() == ItemLoader.emeraldTree && stack1.getItem() == Items.DIAMOND &&
                (stack2.isEmpty() || (stack2.getItem() == Items.EMERALD && stack2.getCount() < 64))) {
            this.burnTime++;
            if (burnTime >= burnTotalTime) {
                stack0.shrink(1);
                if (stack2.isEmpty()) {
                    this.stacks.set(2, new ItemStack(Items.EMERALD));
                } else stack2.grow(1);
                if (world.rand.nextInt(100) > 70) { //30%����������ʯ
                    stack1.shrink(1);
                }
                this.burnTime = 0;
            }
            this.markDirty();
        } else if (stack0.getItem() == ItemLoader.emeraldIngotBlock && stack1.getItem() == Items.LAVA_BUCKET && stack2.isEmpty()) {
            this.burnTime++;
            if (burnTime >= burnTotalTime) {
                stack0.shrink(1);
                FluidStack fluidStack = FluidRegistry.getFluidStack(FluidLoader.emerald_fluid.getName(), 0);
                if (fluidStack != null){
                    ItemStack bucket = FluidUtil.getFilledBucket(fluidStack);
                    this.stacks.set(2, bucket);
                    stack1.shrink(1);
                    this.burnTime = 0;
                }
            }
        } else burnTime = 0;

        if (this.burnTime > 0) {
            this.world.setBlockState(pos, world.getBlockState(this.pos).withProperty(PowerExtractor.BURNING, Boolean.TRUE));
            this.world.getBlockState(pos).getBlock().setLightLevel(12);
        } else {
            this.world.setBlockState(pos, world.getBlockState(this.pos).withProperty(PowerExtractor.BURNING, Boolean.FALSE));
            this.world.getBlockState(pos).getBlock().setLightLevel(0);
        }
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.getUpdateTag();
        compound.setInteger("BurnTime", this.burnTime);
        ItemStackHelper.saveAllItems(compound, this.stacks);
        return compound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        ItemStackHelper.loadAllItems(tag, this.stacks);
        this.burnTime = tag.getInteger("BurnTime");
    }

    //���������TileEntity����λ�õķ���״̬ʱ���ã�Ĭ�ϵ��ж���oldState��newState�����ʱ�滻��Ȼ������������Ҫ���·���״̬�Ա�ʾ¯���Ƿ�������������ֻ�ж������Ƿ���ͬ��
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }
//    public int getBurnTime()
//    {
//        return this.burnTime;
//    }
//    //��ȡ��ȼ��ʱ��
//    public int getTotalBurnTime()
//    {
//    	return this.burnTotalTime;
//    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN) {
            return SLOTS_BOTTOM;
        } else {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1) {
            Item item = stack.getItem();
            return item == Items.DIAMOND;
        }
        return true;
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : stacks) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.stacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.stacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.stacks.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.stacks.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag) {
            this.burnTime = 0;
            this.markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = this.stacks.get(1);
            return itemstack.getItem() == Items.DIAMOND;
        }
    }

    @Override
    public int getField(int id) {
        return this.burnTime;
    }

    @Override
    public void setField(int id, int value) {
        this.burnTime = value;
    }

    @SideOnly(Side.CLIENT)
    public static int getBurnTime(IInventory inventory) {
        return inventory.getField(0);
    }

    @Override
    public int getFieldCount() {
        return 1;
    }

    @Override
    public void clear() {
        this.stacks.clear();
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerDemo(playerInventory, this);
    }

    @Override
    public String getGuiID() {
        return "tmod:emerald_extractor";
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? "" : "container.tmod.emerald_extractor";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing) {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
