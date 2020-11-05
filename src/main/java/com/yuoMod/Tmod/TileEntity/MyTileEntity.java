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
	//����3���洢λ
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
        if (!this.world.isRemote)//��֤������Ҫ�Զ����µ�����ֻ�����ڷ����
        {
        	//����1,2�Ų���Ʒ����������3�Ų�����
        	/*
        	 * extractItem��������������ǴӶ�Ӧ����Ʒ�ۻ�ȡ��Ʒ��
��һ��������������ָ����Ʒ�۵�����
�ڶ���������������ָ����Ҫ��ȡ����Ʒ����
�����������������������Ƿ�Ϊģ����Ϊ������Ϊ����߼ٲ�Ӱ�췵��ֵ�����������Ϊ�٣�����ɹ�����Ʒ�������Ʒ����ļ����ˣ�
������Ʒ�������Ʒ�������ᷢ���仯
        	 */
        	ItemStack itemStack=new ItemStack(Items.EMERALD,1);
            IBlockState state = this.world.getBlockState(pos);
            //insertItem������������������Ӧ����Ʒ��������Ʒ
            //1,2��λ�ж�������3��λ�ܷ���
            if (getInventory().getStackInSlot(0).getCount() >=1 && getInventory().getStackInSlot(1).getCount() >=1
            		&& getInventory().getStackInSlot(2).getCount()<64 && getInventory().getStackInSlot(0).getItem().equals(itemLoader.emerald_tree)
            		&& getInventory().getStackInSlot(1).getItem().equals(Items.DIAMOND))
            {
            	//�жϴ�λ�÷����Ƿ��Ǵ˻���
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
                        this.markDirty();//markDirty���������������TileEntity������ݷ����˱䶯
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
    //���������TileEntity����λ�õķ���״̬ʱ���ã�Ĭ�ϵ��ж���oldState��newState�����ʱ�滻��Ȼ������������Ҫ���·���״̬�Ա�ʾ¯���Ƿ�������������ֻ�ж������Ƿ���ͬ��
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
    public int getBurnTime()
    {
        return this.burnTime;
    }
    //��ȡ��ȼ��ʱ��
    public int getTotalBurnTime()
    {
    	return 150;
    }

	public ItemStackHandler getInventory() {
		return inventory;
	}
}
