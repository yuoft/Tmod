package com.yuoMod.Tmod.Capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {
    //为想要获取到Capability的字段和方法添加@CapabilityInject注解
    @CapabilityInject(IPlayerLevel.class)
    public static Capability<IPlayerLevel> tmodLv;

    public CapabilityLoader(FMLPreInitializationEvent event) {
        /*
         * 第一个参数传入代表Capability的接口的class，第二个参数传入Capability.IStorage的实现，
         * 也就是用于Capability的序列化，最后一个参数传入默认实现的class，用于产生一个默认实现。
         */
        CapabilityManager.INSTANCE.register(IPlayerLevel.class, new CapabilityPlayerLevel.Storage(),
                CapabilityPlayerLevel.Implementation.class);
    }
}
