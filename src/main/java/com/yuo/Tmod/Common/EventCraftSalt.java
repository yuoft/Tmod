package com.yuo.Tmod.Common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yuo.Tmod.Common.Items.ItemLoader;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

//杂质盐洗净
public class EventCraftSalt {
    List<EntityItem> saltWashServer = new ArrayList<>();
    List<EntityItem> saltWashClient = new ArrayList<>();

    public EventCraftSalt() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {

        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            ItemStack entityItem = ((EntityItem) entity).getItem();
            if (!entityItem.isEmpty() && entityItem.getItem() == ItemLoader.saltWash) {
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
            if (rawStack.getItem() != ItemLoader.saltWash) {
                iterator.remove();
                continue;
            }
            //检测方块结构
            World world = item.world;
            BlockPos pos = item.getPosition();

            //生成盐
            if (isWater(pos, world) && !world.isRemote && world instanceof WorldServer) {
                WorldServer worldServer = (WorldServer) world;
                worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, false, item.posX, item.posY, item.posZ, 20, 0.0, 0.5D, 0D, 0.05d);
                item.setDead();
                EntityItem entityItem = new EntityItem(world, item.posX, item.posY, item.posZ, new ItemStack(ItemLoader.salt, rawStack.getCount()));
                world.spawnEntity(entityItem);
                iterator.remove();
            }

        }
    }

    /**
     * 判断结构是否正确
     * @param pos 坐标
     * @param world 世界
     * @return 正确 true
     */
    private static boolean isWater(BlockPos pos, World world){
        //中间方块为水
        if (world.getBlockState(pos).getMaterial() != Material.WATER)
            return false;
        for (EnumFacing dir : EnumFacing.values()) {
            if (dir == EnumFacing.UP) continue;
            //四周是煤矿
            if (world.getBlockState(pos.offset(dir)).getBlock() != Blocks.COAL_BLOCK) {
                return false;
            }
        }
        return true;
    }
}
