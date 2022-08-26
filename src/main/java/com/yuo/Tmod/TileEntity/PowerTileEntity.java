package com.yuo.Tmod.TileEntity;

import com.yuo.Tmod.Common.Blocks.PowerExtractor;
import com.yuo.Tmod.Common.Recipe.PowerRecipeManager;
import com.yuo.Tmod.Gui.ContainerDemo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class PowerTileEntity extends TileEntityLockable implements ITickable, ISidedInventory {
    private int burnTime = 0;
    private int totalTime = 0;
    private int exp = 0;
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDES = new int[]{1};
    //创建3个存储位
    private NonNullList<ItemStack> stacks = NonNullList.withSize(3, ItemStack.EMPTY);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.burnTime = nbt.getInteger("BurnTime");
        this.totalTime = nbt.getInteger("TotalTime");
        this.exp = nbt.getInteger("Exp");
        this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.stacks);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("BurnTime", this.burnTime);
        nbt.setInteger("TotalTime", this.totalTime);
        nbt.setInteger("Exp", this.exp);
        ItemStackHelper.saveAllItems(nbt, stacks);
        return nbt;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        ItemStack stack0 = this.stacks.get(0);
        ItemStack stack1 = this.stacks.get(1);
        ItemStack stack2 = this.stacks.get(2);

        lightChange();
        if (stack0.isEmpty() || stack1.isEmpty()) {
            this.burnTime = 0;
            return;
        }

        ItemStack output = PowerRecipeManager.getRecipe(stack0, stack1);
        int recipeExp = PowerRecipeManager.getRecipeExp(output);
        if (!output.isEmpty() && (stack2.isEmpty() || stack2.isItemEqual(output))
            && exp >= recipeExp){ //配方有输出 产物为空或相同 经验足够时 才运行
            this.burnTime++;
            if (this.totalTime == 0){
                this.totalTime = PowerRecipeManager.getTime(stack0, stack1);
            }
            this.markDirty();
        }

        if (this.burnTime >= this.totalTime && this.totalTime > 0){ //合成时间到 输出产物
            if (recipeExp > 0 && this.exp >= recipeExp){
                outStack(stack0, stack1, stack2, output);
                this.exp -= recipeExp;
            }else if (recipeExp <= 0){
                outStack(stack0, stack1, stack2, output);
            }
            this.markDirty();
        }
    }

    /**
     * 玩家添加经验等级
     * @param player 玩家
     */
    public void setExp(EntityPlayer player, BlockPos pos){
        int level = player.experienceLevel;
        if (level > 0){
            if (exp < 10){
                int shrinkExp = level + exp <= 10 ? level : 10 - exp;
                player.addExperienceLevel(-shrinkExp);
                this.exp += shrinkExp;
                this.exp = Math.min(this.exp, 10);
                player.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1.0f, 3.0f);
                this.markDirty();
            }else player.sendStatusMessage(new TextComponentTranslation("tmod.text.exp_ceil", ""),true );
        }else player.sendStatusMessage(new TextComponentTranslation("tmod.text.level_floor", ""),true );
    }

    /**
     * 合成产物
     * @param stack0 up
     * @param stack1 down
     * @param stack2 out
     * @param output 配方产物
     */
    private void outStack(ItemStack stack0, ItemStack stack1, ItemStack stack2, ItemStack output) {
        if (stack2.isItemEqual(output)){
            stack2.grow(output.getCount());
            this.stacks.set(2, stack2);
        }else this.stacks.set(2, output);

        int[] shrink = PowerRecipeManager.getRecipeShrink(output);
        stack0.shrink(shrink[0]);
        stack1.shrink(shrink[1]);
        this.stacks.set(0, stack0);
        this.stacks.set(1, stack1);
        this.burnTime = 0;
        this.totalTime = 0;
    }

    /**
     * 切换方块亮度 和状态
     */
    private void lightChange(){
        if (this.burnTime > 0) {
            this.world.setBlockState(pos, world.getBlockState(this.pos).withProperty(PowerExtractor.BURNING, Boolean.TRUE), 3);
            this.world.getBlockState(pos).getBlock().setLightLevel(12);
        } else {
            this.world.setBlockState(pos, world.getBlockState(this.pos).withProperty(PowerExtractor.BURNING, Boolean.FALSE), 3);
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
        compound.setInteger("TotalTime", this.totalTime);
        compound.setInteger("Exp", this.exp);
        ItemStackHelper.saveAllItems(compound, this.stacks);
        return compound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tag, this.stacks);
        this.burnTime = tag.getInteger("BurnTime");
        this.totalTime = tag.getInteger("TotalTime");
        this.exp = tag.getInteger("Exp");
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
        return direction == EnumFacing.DOWN && index == 2;
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
        return index != 2;
    }

    @Override
    public int getField(int id) {
        switch (id){
            case 0: return this.burnTime;
            case 1: return this.totalTime;
            case 2: return this.exp;
            default: return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0: this.burnTime = value;break;
            case 1: this.totalTime = value;break;
            case 2: this.exp = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 3;
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
