package com.yuoMod.Tmod.WorldCreate;

import java.util.Random;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Blocks.emerald_sapling;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldTreeCreate extends WorldGenAbstractTree
{
	public static final IBlockState tree=blockLoader.emerald_tree.getDefaultState();
	public static final IBlockState leaf=blockLoader.emerald_leaf.getDefaultState();
	private int minHeigth;
	public WorldTreeCreate() {
		super(false);
		this.minHeigth=6;
	}
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) 
	{
		int heigth=this.minHeigth+rand.nextInt(3);
		boolean flag=true;
		int x=pos.getX();
		int y=pos.getY();
		int z=pos.getZ();
		for(int yPos=y;yPos<=y+1+heigth;yPos++)
		{
			int j=2;
			if(yPos==y)
			{
				j=1;
			}
			if(yPos>=y+1+heigth-2) 
			{
				j=2;
			}
			for(int xPos=x-j;xPos<=x+j&&flag;xPos++)
			{
				for(int zPos=z-j;zPos<-z+j&&flag;zPos++)
				{
					if(yPos>=0&&yPos<world.getHeight())
					{
						if(!this.isReplaceable(world, new BlockPos(yPos, xPos, zPos))) 
							flag=false;
					}
					else flag=false;
				}
			}
		}
		if(!flag)
		{
			return false;
		}
		else
		{
			BlockPos down=pos.down();
			IBlockState state=world.getBlockState(down);
			boolean isSoll=state.getBlock().canSustainPlant(state, world, down, EnumFacing.UP, (emerald_sapling) blockLoader.emerald_sapling);
			if(isSoll&&y<world.getHeight()-heigth-1)
			{
				state.getBlock().onPlantGrow(state, world, down, pos);
				for(int yPos=y-3+heigth;yPos<=y+heigth;yPos++)
				{
					int i=yPos-(y+heigth);
					int j=1-i/2;
					for(int xPos=x-j;xPos<=x+j;xPos++)
					{
						int k=xPos-x;
						for(int zPos=z-j;zPos<=z+j;zPos++)
						{
							int l=zPos-z;
							if(Math.abs(k)!=j||Math.abs(l)!=j||rand.nextInt(2)!=0&&i!=0)
							{
								//·ÅÖÃÊ÷Ò¶
								BlockPos treePos=new BlockPos(xPos, yPos, zPos);
								IBlockState treeState=world.getBlockState(treePos);
								if(treeState.getBlock().isAir(treeState, world, treePos))
								{
									this.setBlockAndNotifyAdequately(world, treePos, leaf);
									this.setBlockAndNotifyAdequately(world, treePos.add(0, -0.25*heigth, 0), leaf);
									this.setBlockAndNotifyAdequately(world, treePos.add(0, -0.5*heigth, 0), treeState);
								}
							}
						}
					}
				}
				//·ÅÖÃÊ÷¸É
				for(int logHeigth=0;logHeigth<heigth;logHeigth++)
				{
					BlockPos up=pos.up(logHeigth);
					IBlockState logState=world.getBlockState(up);
					if(logState.getBlock().isAir(logState, world, up) || logState.getBlock().isLeaves(logState, world, up))
					{
						this.setBlockAndNotifyAdequately(world, up, tree);
					}
				}
				return true;
			}
		}
		return true;
	}
}
