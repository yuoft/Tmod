package com.yuoMod.Tmod.Common.Blocks;

import java.util.Random;

import com.yuoMod.Tmod.Creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class emerald_tree extends Block
{
	//绿宝石树木
	public emerald_tree(String name) 
	{
		super(Material.WOOD);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(name);
	    this.setHardness(2f);
	    this.setResistance(2f);
	    this.setCreativeTab(CreativeTabsLoader.TMOD);
	    this.setSoundType(SoundType.WOOD);
	    this.setHarvestLevel("斧", 4);
	}
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess blockAccess, BlockPos pos, IBlockState state, int fortune) {
	    // 把要掉落的物品塞进 drops 里即可。
		Random random=new Random();
		ItemStack stack= new ItemStack(this);
		ItemStack stack2=new ItemStack(Items.EMERALD,1);
		if(fortune!=0)
		{
			int number=random.nextInt(fortune+1);
			ItemStack stack1=new ItemStack(Items.EMERALD,number+1);
			if(random.nextInt(10)>4)
			{
				drops.add(0, stack);
				drops.add(1, stack1);
			}
			else drops.add(0, stack);
		}
		else
		{
			if(random.nextInt(10)>8)
			{
				drops.add(0, stack);
				drops.add(1, stack2);
			}
			else drops.add(0,stack);
		}
	}
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player)
    {
    	ItemStack pickaxe=player.getHeldItemMainhand();//获取玩家主手物品
    	//获取物品本地化名称判断
    	if(pickaxe.getUnlocalizedName().toString().equals("item.emerald_axe") ||
    			pickaxe.getUnlocalizedName().toString().equals("item.hatchetDiamond"))
    	{
    		return true;
    	}
    	else 
    	{
    		return false;
    	}
    }
}
