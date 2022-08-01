package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Capability.CapabilityLoader;
import com.yuoMod.Tmod.Capability.EventMobLv;
import com.yuoMod.Tmod.Capability.IPlayerLevel;
import com.yuoMod.Tmod.Client.ConfigLoader;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class ModLevelCommand extends CommandBase {

    // ���븲д����һ���ֽ���Ϊ��������ֶ����ڡ�
    // ����֮��������ĵ�һ���ֽ����� /mycommand
    @Override
    public String getName() {
        return "tmod_level";
    }

    //ָ����Ϣ
    @Override
    public String getUsage(ICommandSender sender) {
        // ʵ��������Ӧ��ʹ�� I18n ����֤֧�ֹ��ʻ���
        return "commands.tmod_level.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        // �����ִ���߼�ȫ�������﷢����
        if (args.length < 2 || args.length > 3) {
            throw new WrongUsageException("commands.tmod_level.usage");
        } else {
            EntityPlayer player = getEntity(server, sender, args[0], EntityPlayer.class);
            if ("setItem".equals(args[1])) {
                if (args.length == 3) {
                    int level = parseInt(args[2], 0, ConfigLoader.level);
                    setItemLevel(player, level, sender);
                } else throw new CommandException("commands.tmod_mobLv.bug");
            } else if ("clearItem".equals(args[1])) {
                if (args.length == 2) {
                    clearItemLevel(player, sender);
                } else throw new CommandException("commands.tmod_mobLv.bug");
            } else if ("setPlayer".equals(args[1])) {
                if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                    IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                    if (args.length == 3 && capability != null) {
                        int level = parseInt(args[2], 0, ConfigLoader.level);
                        capability.setPlayerLevel(level);
                        capability.setPlayerExp(0);
                        notifyCommandListener(sender, this, "commands.tmod_level.success2", player.getName());
                    } else throw new CommandException("commands.tmod_mobLv.bug");
                } else throw new CommandException("commands.tmod_mobLv.bug");
            } else if ("clearPlayer".equals(args[1])) {
                if (player.hasCapability(CapabilityLoader.tmodLv, null)) {
                    IPlayerLevel capability = player.getCapability(CapabilityLoader.tmodLv, null);
                    if (capability != null) {
                        capability.setPlayerLevel(1);
                        capability.setPlayerExp(0);
                        notifyCommandListener(sender, this, "commands.tmod_level.success3", player.getName());
                    } else throw new CommandException("commands.tmod_mobLv.bug");
                } else throw new CommandException("commands.tmod_mobLv.bug");
            } else throw new CommandException("commands.tmod_level.set.bug");
        }
    }

    //ָ����ʾ
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "setItem", "setPlayer", "clearItem", "clearPlayer");
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    //������ƷΪָ���ȼ�
    private void setItemLevel(EntityPlayer player, int level, ICommandSender sender) throws CommandException {
        ItemStack mainhand = player.getHeldItemMainhand();
        if (mainhand.isEmpty()) throw new CommandException("commands.tmod_level.set.bug0");
        Item item = mainhand.getItem();
        if (!EventMobLv.isOpItem(item) && (item instanceof ItemSword || item instanceof ItemArmor || item instanceof ItemTool || item instanceof ItemBow)) {
            if (mainhand.getTagCompound() != null && mainhand.getTagCompound().hasKey("tmod_level"))
                mainhand.getTagCompound().setInteger("tmod_level", level);
            else mainhand.setTagInfo("tmod_level", new NBTTagInt(level));
            notifyCommandListener(sender, this, "commands.tmod_level.success0", mainhand.getDisplayName());
        } else throw new CommandException("commands.tmod_level.set.bug1", mainhand.getDisplayName());
    }

    //���ָ����Ʒ�ȼ�
    private void clearItemLevel(EntityPlayer player, ICommandSender sender) throws CommandException {
        ItemStack mainhand = player.getHeldItemMainhand();
        if (mainhand.isEmpty()) throw new CommandException("commands.tmod_level.set.bug0");
        if (!EventMobLv.isOpItem(mainhand.getItem()) && mainhand.getTagCompound() != null && mainhand.getTagCompound().hasKey("tmod_level")) {
            mainhand.getTagCompound().removeTag("tmod_level");
            notifyCommandListener(sender, this, "commands.tmod_level.success1", mainhand.getDisplayName());
        } else throw new CommandException("commands.tmod_level.set.bug2", mainhand.getDisplayName());
    }
}
