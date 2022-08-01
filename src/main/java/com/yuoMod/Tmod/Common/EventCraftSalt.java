package com.yuoMod.Tmod.Common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yuoMod.Tmod.Common.Items.ItemLoader;
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
 * @author https://github.com/rwtema/Extra-Utilities-2-Source
 * 源码来自 更多实用设备2
 */
public class EventCraftSalt {
    List<EntityItem> saltWashServer = new ArrayList<EntityItem>();
    List<EntityItem> saltWashClient = new ArrayList<EntityItem>();

    public EventCraftSalt() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {

        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            ItemStack entityItem = ((EntityItem) entity).getItem();
            if (!entityItem.isEmpty() && entityItem.getItem() == ItemLoader.salt_wash) {
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
            if (rawStack.getItem() != ItemLoader.salt_wash) {
                iterator.remove();
                continue;
            }
            //检测方块结构
            World world = item.world;
            BlockPos pos = item.getPosition();
            boolean found = false;

            //中间方块为岩浆
            if (world.getBlockState(pos).getMaterial() == Material.WATER) {
                for (EnumFacing dir : EnumFacing.values()) {
                    if (dir == EnumFacing.UP) continue;
                    //四周是煤矿
                    if (!world.getBlockState(pos.offset(dir)).getBlock().equals(Blocks.COAL_BLOCK)) {
                        found = true;
                        break;
                    }
                }
            }

            //方块结构正确
            if (found) {
                continue;
            }
            //生成盐物品栈
            if (!world.isRemote && world instanceof WorldServer) {
                WorldServer worldServer = (WorldServer) world;
                worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, false, item.posX, item.posY, item.posZ, 100, 0.0, 0D, 0D, 0.0);
                item.setDead();
                EntityItem demonicIngotItem = new EntityItem(world, item.posX, item.posY, item.posZ, new ItemStack(ItemLoader.salt, rawStack.getCount()));
                world.spawnEntity(demonicIngotItem);
                iterator.remove();
            }

        }
    }
}
