package com.yuo.Tmod.Network;

import com.yuo.Tmod.Capability.CapabilityLoader;

import com.yuo.Tmod.Capability.IPlayerLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessagePlayerLevel implements IMessage {

    public NBTTagCompound nbt;

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
    }

    //客户端就必须要提供一个类来处理服务端发过来的包
	/*IMessageHandler的两个泛型参数：
	 * 首先我们需要确定收过来的包的类型，这就是第一个泛型参数
一端在收到另一端的包时常常会有把包发回另一端的需要，这个发回的包的类型就是第二个泛型参数，不过这里我们不需要发回数据包
	 */
    public static class Handler implements IMessageHandler<MessagePlayerLevel, IMessage> {
        @Override
        public IMessage onMessage(MessagePlayerLevel message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT)//判定接收方是客户端还是服务端
            {
                /*
                 * 从Minecraft 1.8开始，Minecraft的所有网络操作都是在一个单独的网络线程中进行，
                 * 这会导致其没有办法和游戏中的大多数对象交互。所以这里需要调用IThreadListener的addScheduledTask方法，
                 * 并传入一个Runnable
                 */
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                        IPlayerLevel tmodLv = player.getCapability(CapabilityLoader.tmodLv, null);
                        IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();
                        storage.readNBT(CapabilityLoader.tmodLv, tmodLv, null, message.nbt);
                    }
                });
            }
            return null;
        }
    }
}
