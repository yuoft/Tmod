package com.yuo.Tmod.TileEntity;

import com.yuo.Tmod.Common.Blocks.PowerExtractor;
import com.yuo.Tmod.Common.Recipe.PowerRecipeManager;
import com.yuo.Tmod.Gui.ContainerDemo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class PowerTileEntity extends TileEntityLockable implements ITickable, ISidedInventory {
    private int burnTime = 0;
    private final int burnTotalTime = 160;
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDES = new int[]{1};
    //创建3个存储位
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
        if (stack0.isEmpty() || stack1.isEmpty()) {
            this.burnTime = 0;
            return;
        }

        ItemStack recipe = PowerRecipeManager.getRecipe(stack0, stack1);
        if (!recipe.isEmpty() && (stack2.isEmpty() || stack2.isItemEqual(recipe))){ //配方有输出 产物为空或相同 才运行
            this.burnTime++;
            this.markDirty();
        }

        if (this.burnTime >= this.burnTotalTime){ //合成时间到 输出产物
            if (stack2.isItemEqual(recipe)){
                this.burnTime++;
                stack2.grow(recipe.getCount());
            }else this.stacks.set(2, recipe);
            stack0.shrink(1);
            stack1.shrink(1);
            this.burnTime = 0;
            this.markDirty();
        }

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

    //在世界更新TileEntity所在位置的方块状态时调用，默认的判定是oldState和newState不相等时替换，然而这里我们需要更新方块状态以表示炉子是否工作，所以这里只判定方块是否相同。
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

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

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable EnumFacing facing) {
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
