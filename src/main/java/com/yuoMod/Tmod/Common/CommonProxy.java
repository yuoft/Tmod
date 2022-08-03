package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Capability.CapabilityLoader;
import com.yuoMod.Tmod.Capability.EventMobLv;
import com.yuoMod.Tmod.Common.Blocks.BlockLoader;
import com.yuoMod.Tmod.Client.CraftingLoader;
import com.yuoMod.Tmod.Common.Items.ItemLoader;
import com.yuoMod.Tmod.Tab.TmodGroup;
import com.yuoMod.Tmod.Enchantment.EnchantLoader;
import com.yuoMod.Tmod.Entity.EntityLoader;
import com.yuoMod.Tmod.Entity.Villager.VillagerLoader;
import com.yuoMod.Tmod.Fluid.FluidLoader;
import com.yuoMod.Tmod.Gui.GuiLoader;
import com.yuoMod.Tmod.Network.NetworkLoader;
import com.yuoMod.Tmod.Potion.PotionLoader;
import com.yuoMod.Tmod.TileEntity.TileEntityLoader;
import com.yuoMod.Tmod.World.WorldGens;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        new TmodGroup(event);//������Ʒ��ע��
        new FluidLoader(event);//����ע��
        ItemLoader.init(event);//ע����Ʒ
        new BlockLoader(event);//����ע��
        new PotionLoader(event);//ҩˮ״̬ע��
        new EntityLoader();//ʵ��ע��
        new TileEntityLoader(event);//TileEntityע��
        new VillagerLoader();//����ְҵע��
        new CapabilityLoader();//����ϵͳע��
        new NetworkLoader(event);//��Ϣ���ݣ�����ͬ��
    }

    public void init(FMLInitializationEvent event) {
        new CraftingLoader();//��¯�䷽ע��
        new EnchantLoader();//��ħע��
        new EventLoader();//�¼�ע��
        new EventCraftSalt();//������ϴ���¼�
        new EventCraftRuby();//�챦ʯ������
        new EventMobLv();//����ȼ�
        new LuckyEvent();//���˷����¼�
        new WorldGens();//��������ע��
        new GuiLoader();//guiע��
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
