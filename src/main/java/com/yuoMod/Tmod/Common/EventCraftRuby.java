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
import net.minecraft.util.math.BlockPos;
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
public class EventCraftRuby {
    List<EntityItem> rubyServer = new ArrayList<EntityItem>();
    List<EntityItem> rubyClient = new ArrayList<EntityItem>();

    public EventCraftRuby() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {

        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            ItemStack entityItem = ((EntityItem) entity).getItem();
            if (!entityItem.isEmpty() && entityItem.getItem() == ItemLoader.rubyOre) {
                List<EntityItem> entityItems;
                if (event.getWorld().isRemote)
                    entityItems = rubyClient;
                else
                    entityItems = rubyServer;
                entityItems.add((EntityItem) entity);
            }
        }
    }

    @SubscribeEvent
    public void run(TickEvent.ClientTickEvent event) {
        handleIngots(rubyServer);
    }

    @SubscribeEvent
    public void run(TickEvent.ServerTickEvent event) {
        handleIngots(rubyClient);
    }

    private void handleIngots(List<EntityItem> ruby) {
        for (Iterator<EntityItem> iterator = ruby.iterator(); iterator.hasNext(); ) {
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
            if (rawStack.getItem() != ItemLoader.rubyOre) {
                iterator.remove();
                continue;
            }
            //��ⷽ��ṹ
            World world = item.world;
            BlockPos pos = item.getPosition();
            boolean found = false;

            //�м䷽��Ϊ�ҽ�
            if (world.getBlockState(pos).getMaterial() == Material.LAVA) {
                for (EnumFacing dir : EnumFacing.values()) {
                    if (dir == EnumFacing.UP) continue;
                    //�����ǵ���ש
                    if (!world.getBlockState(pos.offset(dir)).getBlock().equals(Blocks.NETHER_BRICK)) {
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
                worldServer.spawnParticle(EnumParticleTypes.LAVA, false, item.posX, item.posY, item.posZ, 100, 0.0, 0D, 0D, 0.0);
                item.setDead();
                EntityItem demonicIngotItem = new EntityItem(world, item.posX, item.posY, item.posZ, new ItemStack(ItemLoader.ruby, rawStack.getCount()));
                world.spawnEntity(demonicIngotItem);
                iterator.remove();
            }

        }
    }
}
