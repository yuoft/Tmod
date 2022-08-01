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

    // 必须覆写。这一部分将作为命令的名字而存在。
    // 换言之，该命令的第一部分将会是 /mycommand
    @Override
    public String getName() {
        return "tmod_level";
    }

    //指令信息
    @Override
    public String getUsage(ICommandSender sender) {
        // 实际上这里应该使用 I18n 来保证支持国际化。
        return "commands.tmod_level.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        // 命令的执行逻辑全部在这里发生。
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

    //指令提示
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "setItem", "setPlayer", "clearItem", "clearPlayer");
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

    //设置物品为指定等级
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

    //清除指定物品等级
    private void clearItemLevel(EntityPlayer player, ICommandSender sender) throws CommandException {
        ItemStack mainhand = player.getHeldItemMainhand();
        if (mainhand.isEmpty()) throw new CommandException("commands.tmod_level.set.bug0");
        if (!EventMobLv.isOpItem(mainhand.getItem()) && mainhand.getTagCompound() != null && mainhand.getTagCompound().hasKey("tmod_level")) {
            mainhand.getTagCompound().removeTag("tmod_level");
            notifyCommandListener(sender, this, "commands.tmod_level.success1", mainhand.getDisplayName());
        } else throw new CommandException("commands.tmod_level.set.bug2", mainhand.getDisplayName());
    }
}
