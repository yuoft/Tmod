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

//������ϴ��

/**
 * @author https://github.com/rwtema/Extra-Utilities-2-Source
 * Դ������ ����ʵ���豸2
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
            //�õ���Ʒʵ�����Ʒջ
            ItemStack rawStack = item.getItem();
            //��Ʒջ��Ϊ��
            if (rawStack.isEmpty()) {
                continue;
            }
            //��Ʒ��������
            if (rawStack.getItem() != ItemLoader.salt_wash) {
                iterator.remove();
                continue;
            }
            //��ⷽ��ṹ
            World world = item.world;
            BlockPos pos = item.getPosition();
            boolean found = false;

            //�м䷽��Ϊ�ҽ�
            if (world.getBlockState(pos).getMaterial() == Material.WATER) {
                for (EnumFacing dir : EnumFacing.values()) {
                    if (dir == EnumFacing.UP) continue;
                    //������ú��
                    if (!world.getBlockState(pos.offset(dir)).getBlock().equals(Blocks.COAL_BLOCK)) {
                        found = true;
                        break;
                    }
                }
            }

            //����ṹ��ȷ
            if (found) {
                continue;
            }
            //��������Ʒջ
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
