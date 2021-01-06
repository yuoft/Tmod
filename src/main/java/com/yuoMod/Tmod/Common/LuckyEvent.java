package com.yuoMod.Tmod.Common;

import java.util.Random;

import com.yuoMod.Tmod.Common.Blocks.blockLoader;
import com.yuoMod.Tmod.Common.Items.itemLoader;
import com.yuoMod.Tmod.Entity.EntityGreenCreeper;
import com.yuoMod.Tmod.Entity.EntityGreenSkeleton;
import com.yuoMod.Tmod.Entity.EntityGreenSpider;
import com.yuoMod.Tmod.Entity.EntityGreenZombies;
import com.yuoMod.Tmod.Entity.EntityKiana;
import com.yuoMod.Tmod.Entity.EntityRedCreeper;
import com.yuoMod.Tmod.Entity.EntityRedEnderman;
import com.yuoMod.Tmod.Entity.EntityRedSkeleton;
import com.yuoMod.Tmod.Entity.EntityRedSpider;
import com.yuoMod.Tmod.Entity.EntityRedZombies;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//幸运或霉运事件
public class LuckyEvent 
{
	private Random RANDOM = new Random();
	public LuckyEvent()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
	@SubscribeEvent
	public void luckEvent(BlockEvent.BreakEvent event)
	{
		World worldIn =event.getWorld();
		EntityPlayer player =event.getPlayer();
		Block block =event.getState().getBlock();
		BlockPos pos =event.getPos();
		if(block.equals(blockLoader.unlucky_block))//霉运方块事件
		{
			int num = RANDOM.nextInt(30);
			switch(num)
			{
			case 0:spawnLightingBolt(worldIn, pos, 3);break;
			case 2:spawnLava(worldIn, pos, 3);break;
			case 6:trappedPlayer(worldIn, player);break;
			case 5:spawnMob(worldIn, pos,new EntityRedZombies(worldIn), 1);break;
			case 9:spawnMob(worldIn, pos,new EntityGreenCreeper(worldIn), 1);break;
			case 11:spawnMob(worldIn, pos,new EntityRedEnderman(worldIn), 1);break;
			case 22:spawnMob(worldIn, pos,new EntityRedSkeleton(worldIn), 1);break;
			case 19:spawnMob(worldIn, pos,new EntityRedSpider(worldIn), 1);break;
			case 3:spawnMob(worldIn, pos,new EntityGreenZombies(worldIn), RANDOM.nextInt(3) + 1);break;
			case 25:spawnMob(worldIn, pos,new EntityGreenSpider(worldIn), RANDOM.nextInt(3) + 1);break;
			case 14:spawnMob(worldIn, pos,new EntityGreenSkeleton(worldIn), RANDOM.nextInt(3) + 1);break;
			default : nullEvent(worldIn, pos, player);break;
			}
		}
		if(block.equals(blockLoader.lucky_block))//幸运方块事件
		{
			int num = RANDOM.nextInt(30);
			switch(num)
			{
			case 0: spawnEntityItem(worldIn, pos, Items.DIAMOND, RANDOM.nextInt(8) + 4);break;
			case 3: spawnEntityItem(worldIn, pos, Items.EMERALD, RANDOM.nextInt(8) + 2);break;
			case 5: spawnEntityItem(worldIn, pos, Items.GOLD_INGOT, RANDOM.nextInt(8) + 8);break;
			case 11: spawnEntityItem(worldIn, pos, Items.IRON_INGOT, RANDOM.nextInt(8) + 12);break;
			case 23: spawnEntityItem(worldIn, pos, Items.REDSTONE, RANDOM.nextInt(8) + 8);break;
			case 18: spawnEntityBlock(worldIn, pos, Blocks.LAPIS_ORE, RANDOM.nextInt(8) + 4);break;
			case 13: spawnEntityBlock(worldIn, pos, Blocks.DIAMOND_BLOCK, RANDOM.nextInt(2) + 2);break;
			case 22: spawnEntityBlock(worldIn, pos, Blocks.EMERALD_BLOCK, RANDOM.nextInt(2) + 1);break;
			case 10: spawnEntityBlock(worldIn, pos, Blocks.GOLD_BLOCK, RANDOM.nextInt(2) + 2);break;
			case 9: spawnEntityBlock(worldIn, pos, Blocks.IRON_BLOCK, RANDOM.nextInt(2) + 3);break;
			case 19: spawnEntityBlock(worldIn, pos, Blocks.REDSTONE_BLOCK, RANDOM.nextInt(2) + 2);break;
			case 25: spawnEntityBlock(worldIn, pos, Blocks.LAPIS_BLOCK, RANDOM.nextInt(2) + 2);break;
			default : nullEvent(worldIn, pos);break;
			}
		}
	}
	//生成怪物
	private void spawnMob(World worldIn, BlockPos pos, Entity entity, int number)
	{
		if(!worldIn.isRemote) {
			entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
			for(int i = 0; i < number; i++)
				worldIn.spawnEntity(entity);
		}
	}
	//生成闪电
	private void spawnLightingBolt(World worldIn, BlockPos pos, int fire)
	{
		EntityLightningBolt lightningBolt = new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true);
		lightningBolt.setFire(fire);
		worldIn.spawnEntity(lightningBolt);
	}
	//生成岩浆
	private void spawnLava(World worldIn, BlockPos pos,int range)
	{
		int minX = pos.getX() - range;
		int minY = pos.getZ() - range;
		int minZ = pos.getY() - range;
		int maxX = pos.getX() + range+1;
		int maxY = pos.getZ() + range+1;
		int maxZ = pos.getY() + range+1;
		for(int x=minX; x<maxX; x++)
		{
			for(int y=minY; y<maxY; y++)
			{
				for(int z=minZ; z< maxZ; z++)
				{
					BlockPos newPos = new BlockPos(x, z, y);
					worldIn.setBlockState(newPos, Blocks.LAVA.getDefaultState());
				}
			}
		}
	}
	//困住玩家
	private void trappedPlayer(World worldIn, EntityPlayer player)
	{
		BlockPos pos =player.getPosition();
		int minX = pos.getX() -1;
		int minY = pos.getZ() -1;
		int minZ = pos.getY() -1;
		int maxX = pos.getX() +2;
		int maxY = pos.getZ() +2;
		int maxZ = pos.getY() +3;
		for(int x=minX; x<maxX; x++)
		{
			for(int y=minY; y<maxY; y++)
			{
				for(int z=minZ; z< maxZ; z++)
				{
					BlockPos newPos = new BlockPos(x, z, y);
					if(x == pos.getX() && y ==pos.getZ() && z == pos.getY())
						worldIn.setBlockState(newPos, Blocks.LAVA.getDefaultState());
					else if(x == pos.getX() && y ==pos.getZ() && z == pos.getY()+1)
						worldIn.setBlockState(newPos, Blocks.WEB.getDefaultState());
					else worldIn.setBlockState(newPos, Blocks.OBSIDIAN.getDefaultState());
				}
			}
		}
	}
	//空事件和特殊事件
	private void nullEvent(World worldIn, BlockPos pos,EntityPlayer player)
	{
		if(RANDOM.nextInt(100) > 70)
		{
			int i = RANDOM.nextInt(100);
			if(i == 99)
				spawnMob(worldIn, pos, new EntityKiana(worldIn), 1);//生成boss
			else if(i == 50)
				spawnMob(worldIn, pos, new EntityRedCreeper(worldIn), 1);//生成红名苦力怕
			else if(i == 0)
				reachVoid(player, worldIn);//掉虚空
			else spawnMob(worldIn, pos, new EntityZombie(worldIn), RANDOM.nextInt(10) + 2);//生成普通怪物
		}
	}
	//达到虚空
	private void reachVoid(EntityPlayer player,World worldIn)
	{
		BlockPos pos0 =player.getPosition();
		if(!worldIn.isRemote)
			player.sendMessage(new TextComponentTranslation("tmod.text.unlucky"));
		for(int i = pos0.getY(); i > -1; i--)
		{
			BlockPos newPos =new BlockPos(pos0.getX(), i, pos0.getZ());
			worldIn.setBlockToAir(newPos);
		}
	}
	//掉落矿物锭
	private void spawnEntityItem(World worldIn, BlockPos pos, Item item, int number)
	{
		EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ());
		entityItem.setItem(new ItemStack(item, number));
		worldIn.spawnEntity(entityItem);
	}
	//掉落矿物块
	private void spawnEntityBlock(World worldIn, BlockPos pos, Block block, int number)
	{
		EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ());
		entityItem.setItem(new ItemStack(block, number));
		worldIn.spawnEntity(entityItem);
	}
	//空事件和特殊事件
	private void nullEvent(World worldIn, BlockPos pos)
	{
		if(RANDOM.nextInt(100) > 70)
		{
			int i = RANDOM.nextInt(100);
			if(i == 10)
				spawnEntityItem(worldIn, pos, itemLoader.op_boots, 1);//掉落op套
			else if(i == 20)
				spawnEntityItem(worldIn, pos, itemLoader.op_chestplate, 1);
			else if(i == 30)
				spawnEntityItem(worldIn, pos, itemLoader.op_helmet, 1);
			else if(i == 40)
				spawnEntityItem(worldIn, pos, itemLoader.op_leggings, 1);
			else if(i == 50)
				spawnEntityItem(worldIn, pos, itemLoader.op_sword, 1);
			else spawnEntityItem(worldIn, pos, Items.POTATO, RANDOM.nextInt(16) + 1);//掉落土豆
		}
	}
}
