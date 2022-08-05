package com.yuo.Tmod.Network;

import com.yuo.Tmod.Tmod;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkLoader {
    //调用NetworkRegistry的newSimpleChannel方法，并传入Mod id以生成一个新的SimpleNetworkWrapper
    public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(Tmod.MOD_ID);

    private static int nextID = 0;

    public NetworkLoader(FMLPreInitializationEvent event) {
        registerMessage(MessagePlayerLevel.Handler.class, MessagePlayerLevel.class, Side.CLIENT);
    }

    //用SimpleNetworkWrapper的registerMessage方法绑定IMessage和其对应的IMessageHandler
    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
            Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
    	/*
    	 * 第二个参数表示用于发送的IMessage的class
第一个参数表示用于接收的IMessageHandler
第四个参数表示接收方为服务端还是客户端，这里因为是客户端接收，所以传入一个Side.CLIENT
第三个参数表示这一频道的唯一id，一个比较好的做法是静态存储下一个id的值，每注册一次就加一，正如上面提供的示例代码一样，教程就是这么做的
    	 */
        instance.registerMessage(messageHandler, requestMessageType, nextID++, side);
    }
}
