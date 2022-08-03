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
        new TmodGroup(event);//创造物品栏注册
        new FluidLoader(event);//流体注册
        ItemLoader.init(event);//注册物品
        new BlockLoader(event);//方块注册
        new PotionLoader(event);//药水状态注册
        new EntityLoader();//实体注册
        new TileEntityLoader(event);//TileEntity注册
        new VillagerLoader();//村民职业注册
        new CapabilityLoader();//能力系统注册
        new NetworkLoader(event);//消息传递，数据同步
    }

    public void init(FMLInitializationEvent event) {
        new CraftingLoader();//熔炉配方注册
        new EnchantLoader();//附魔注册
        new EventLoader();//事件注册
        new EventCraftSalt();//含杂盐洗净事件
        new EventCraftRuby();//红宝石矿烧炼
        new EventMobLv();//怪物等级
        new LuckyEvent();//幸运方块事件
        new WorldGens();//矿物生成注册
        new GuiLoader();//gui注册
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
