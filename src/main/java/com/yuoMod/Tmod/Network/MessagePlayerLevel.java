package com.yuoMod.Tmod.Network;

import com.yuoMod.Tmod.Capability.CapabilityLoader;
import com.yuoMod.Tmod.Capability.IPlayerLevel;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessagePlayerLevel implements IMessage{

	public NBTTagCompound nbt;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}
	//�ͻ��˾ͱ���Ҫ�ṩһ�������������˷������İ�
	/*IMessageHandler���������Ͳ�����
	 * ����������Ҫȷ���չ����İ������ͣ�����ǵ�һ�����Ͳ���
һ�����յ���һ�˵İ�ʱ�������аѰ�������һ�˵���Ҫ��������صİ������;��ǵڶ������Ͳ����������������ǲ���Ҫ�������ݰ�
	 */
	public static class Handler implements IMessageHandler<MessagePlayerLevel, IMessage>
    {
        @Override
        public IMessage onMessage(MessagePlayerLevel message, MessageContext ctx)
        {
            if (ctx.side == Side.CLIENT)//�ж����շ��ǿͻ��˻��Ƿ����
            {
//                NBTTagList nbt = new NBTTagList();
//                NBTTagCompound compound = new NBTTagCompound();
//                compound.setInteger("player_level", message.nbt.getInteger("player_level"));
//                compound.setInteger("player_exp", message.nbt.getInteger("player_exp"));
//                nbt.appendTag(compound);
            	final NBTBase nbt = message.nbt.getTag("tmod_level");
                /*
                 * ��Minecraft 1.8��ʼ��Minecraft�������������������һ�������������߳��н��У�
                 * ��ᵼ����û�а취����Ϸ�еĴ�������󽻻�������������Ҫ����IThreadListener��addScheduledTask������
                 * ������һ��Runnable
                 */
                Minecraft.getMinecraft().addScheduledTask(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        EntityPlayer player = Minecraft.getMinecraft().player;
                        if (player.hasCapability(CapabilityLoader.tmodLv, null))
                        {
                            IPlayerLevel tmodLv = player.getCapability(CapabilityLoader.tmodLv, null);
                            IStorage<IPlayerLevel> storage = CapabilityLoader.tmodLv.getStorage();
                            storage.readNBT(CapabilityLoader.tmodLv, tmodLv, null, nbt);
                        }
                    }
                });
            }
            return null;
        }
    }
}
