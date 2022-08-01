package com.yuoMod.Tmod.Network;

import com.yuoMod.Tmod.Tmod;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkLoader {
    //����NetworkRegistry��newSimpleChannel������������Mod id������һ���µ�SimpleNetworkWrapper
    public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(Tmod.MOD_ID);

    private static int nextID = 0;

    public NetworkLoader(FMLPreInitializationEvent event) {
        registerMessage(MessagePlayerLevel.Handler.class, MessagePlayerLevel.class, Side.CLIENT);
    }

    //��SimpleNetworkWrapper��registerMessage������IMessage�����Ӧ��IMessageHandler
    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
            Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
    	/*
    	 * �ڶ���������ʾ���ڷ��͵�IMessage��class
��һ��������ʾ���ڽ��յ�IMessageHandler
���ĸ�������ʾ���շ�Ϊ����˻��ǿͻ��ˣ�������Ϊ�ǿͻ��˽��գ����Դ���һ��Side.CLIENT
������������ʾ��һƵ����Ψһid��һ���ȽϺõ������Ǿ�̬�洢��һ��id��ֵ��ÿע��һ�ξͼ�һ�����������ṩ��ʾ������һ�����̳̾�����ô����
    	 */
        instance.registerMessage(messageHandler, requestMessageType, nextID++, side);
    }
}
