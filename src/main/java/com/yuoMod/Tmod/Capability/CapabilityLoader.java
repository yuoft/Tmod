package com.yuoMod.Tmod.Capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {
    //Ϊ��Ҫ��ȡ��Capability���ֶκͷ������@CapabilityInjectע��
    @CapabilityInject(IPlayerLevel.class)
    public static Capability<IPlayerLevel> tmodLv;

    public CapabilityLoader(FMLPreInitializationEvent event) {
        /*
         * ��һ�������������Capability�Ľӿڵ�class���ڶ�����������Capability.IStorage��ʵ�֣�
         * Ҳ��������Capability�����л������һ����������Ĭ��ʵ�ֵ�class�����ڲ���һ��Ĭ��ʵ�֡�
         */
        CapabilityManager.INSTANCE.register(IPlayerLevel.class, new CapabilityPlayerLevel.Storage(),
                CapabilityPlayerLevel.Implementation.class);
    }
}
