package com.yuoMod.Tmod.Common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yuoMod.Tmod.Common.Items.itemLoader;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

//杂质盐洗净
/**
 * 
 * 
 * @author https://github.com/rwtema/Extra-Utilities-2-Source
 * 源码来自 更多实用设备2
 */
public class EventCraftSalt 
{
	List<EntityItem> saltWashServer = new ArrayList<EntityItem>();
	List<EntityItem> saltWashClient = new ArrayList<EntityItem>();
	
	public EventCraftSalt() 
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event) {

		Entity entity = event.getEntity();
		if (entity instanceof EntityItem) {
			ItemStack entityItem = ((EntityItem) entity).getItem();
			if (entityItem.isEmpty() || entityItem.getItem() == itemLoader.salt_wash) {
				List<EntityItem> entityItems;
				if (event.getWorld().isRemote)
					entityItems = saltWashClient;
				else
					entityItems = saltWashServer;
				entityItems.add((EntityItem) entity);
			}
		}
	}
	@SubscribeEvent
	public void run(TickEvent.ClientTickEvent event) {
		handleIngots(saltWashServer);
	}

	@SubscribeEvent
	public void run(TickEvent.ServerTickEvent event) {
		handleIngots(saltWashClient);
	}

	private void handleIngots(List<EntityItem> salt_wash) {
		for (Iterator<EntityItem> iterator = salt_wash.iterator(); iterator.hasNext(); ) {
			EntityItem item = iterator.next();
			if (item.isDead) {
				iterator.remove();
				continue;
			}
			//得到物品实体的物品栈
			ItemStack rawStack = item.getItem();
			//物品栈不为空
			if (rawStack.isEmpty()) {
				continue;
			}
			//物品是杂质盐
			if (rawStack.getItem() != itemLoader.salt_wash) {
				iterator.remove();
				continue;
			}
			//检测方块结构
			World world = item.world;
			AxisAlignedBB bb = item.getEntityBoundingBox().grow(0.1, 0.1, 0.1);

			int x1 = MathHelper.floor(bb.minX);
			int x2 = MathHelper.ceil(bb.maxX);
			int y1 = MathHelper.floor(bb.minY);
			int y2 = MathHelper.ceil(bb.maxY);
			int z1 = MathHelper.floor(bb.minZ);
			int z2 = MathHelper.ceil(bb.maxZ);
			BlockPos.PooledMutableBlockPos mutPos = BlockPos.PooledMutableBlockPos.retain();

			boolean found = false;
			mainLoop:
			for (int x = x1; x < x2; ++x) {
				for (int y = y1; y < y2; ++y) {
					for (int z = z1; z < z2; ++z) {
						//中间方块是水
						if (world.getBlockState(mutPos.setPos(x, y, z)).getMaterial() == Material.WATER) {
							found = true;
							for (EnumFacing dir : EnumFacing.HORIZONTALS) {
								//水周围是煤块
								if (!world.getBlockState(mutPos.offset(dir)).getBlock().equals(Blocks.COAL_BLOCK)) 
								{
									found = false;
									break;
								}
							}
							if (found) {
								break mainLoop;
							}

						}
					}
				}
			}
			//方块结构正确
			if (!found) {
				continue;
			}
			//生成盐物品栈
			if (!world.isRemote && world instanceof WorldServer) {
				WorldServer worldServer = (WorldServer) world;
				worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, false, item.posX, item.posY, item.posZ, 100, 0.0, 0D, 0D, 0.0);
				item.setDead();
				EntityItem demonicIngotItem = new EntityItem(world, item.posX, item.posY, item.posZ, new ItemStack(itemLoader.salt, rawStack.getCount()));
				world.spawnEntity(demonicIngotItem);
				iterator.remove();
			}

		}
	}
}
