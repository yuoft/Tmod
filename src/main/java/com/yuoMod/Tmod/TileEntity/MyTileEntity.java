package com.yuoMod.Tmod.TileEntity;

import java.util.Random;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Blocks.power_extractor;
import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Gui.ItemHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class MyTileEntity extends TileEntity implements ITickable
{
	private  int burnTime = 0;
	//创建3个存储位
	private final ItemStackHandler inventory = new ItemStackHandler(3);

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
    	return capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
    	if (capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) 
    	{
    		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(//inventory
    				new ItemHandler(inventory) 
    				);
        } 
    	else return super.getCapability(capability, facing);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        this.burnTime = nbt.getInteger("BurnTime");
        this.getInventory().deserializeNBT(nbt.getCompoundTag("Inventory"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("BurnTime", this.burnTime);
        nbt.setTag("Inventory", this.getInventory().serializeNBT());
		return super.writeToNBT(nbt);
    }
    public void update()
    {
        if (!this.world.isRemote)//保证我们想要自动更新的内容只作用在服务端
        {
        	//减少1,2号槽物品数量，增加3号槽数量
        	/*
        	 * extractItem这个方法的作用是从对应的物品槽获取物品：
第一个参数的作用是指定物品槽的序数
第二个参数的作用是指定想要获取的物品数量
第三个参数的作用是设置是否为模拟行为，设置为真或者假不影响返回值，但如果设置为假，如果成功，物品槽里的物品就真的减少了，
否则物品槽里的物品数量不会发生变化
        	 */
        	ItemStack itemStack=new ItemStack(Items.EMERALD,1);
            IBlockState state = this.world.getBlockState(pos);
            //insertItem这个方法的作用是向对应的物品槽塞入物品
            //1,2号位有东西，且3号位能放入
            if (getInventory().getStackInSlot(0).getCount() >=1 && getInventory().getStackInSlot(1).getCount() >=1
            		&& getInventory().getStackInSlot(2).getCount()<64 && getInventory().getStackInSlot(0).getItem().equals(itemLoader.emerald_tree)
            		&& getInventory().getStackInSlot(1).getItem().equals(Items.DIAMOND))
            {
            	//判断此位置方块是否是此机器
            	if(this.world.getBlockState(pos).getBlock().equals(blockLoader.power_extractor))
            	{
            		 this.world.setBlockState(pos, state.withProperty(power_extractor.BURNING, Boolean.TRUE));
            		 this.world.getBlockState(pos).getBlock().setLightLevel(10);
            	}
                int burnTotalTime = 150;

                if (++this.burnTime >= burnTotalTime)
                {
                    this.burnTime = 0;
                    getInventory().extractItem(0, 1, false);
                    Random random=new Random();
                    if(random.nextInt(99) < 66)
                    {
                        getInventory().insertItem(2, itemStack, false);
                        this.markDirty();
                    }
                    else 
                    {
                    	getInventory().extractItem(1, 1, false);
                        getInventory().insertItem(2, itemStack, false);
                        this.markDirty();//markDirty方法用于设置这个TileEntity里的数据发生了变动
                    }
                }
            }
            else
            {
            	if(this.world.getBlockState(pos).getBlock().equals(blockLoader.power_extractor))
            	{
            		this.burnTime=0;
            		this.world.setBlockState(pos, state.withProperty(power_extractor.BURNING, Boolean.FALSE));
            		this.world.getBlockState(pos).getBlock().setLightLevel(0);
//            		OneInventory.extractItem(0, 1, false);
            	}
            		
            }
        }
    }
    //在世界更新TileEntity所在位置的方块状态时调用，默认的判定是oldState和newState不相等时替换，然而这里我们需要更新方块状态以表示炉子是否工作，所以这里只判定方块是否相同。
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
    public int getBurnTime()
    {
        return this.burnTime;
    }
    //获取总燃烧时间
    public int getTotalBurnTime()
    {
    	return 150;
    }

	public ItemStackHandler getInventory() {
		return inventory;
	}
}
