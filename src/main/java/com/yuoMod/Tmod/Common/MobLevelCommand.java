package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Client.ConfigLoader;
import com.yuoMod.Tmod.World.MobLevelInfoDataSave;
import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class MobLevelCommand extends CommandBase {

    @Override
    public String getName() {
        return "tmod_mobLv";
    }

    //指令信息
    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tmod_mobLv.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        // 命令的执行逻辑全部在这里发生。
        if (args.length < 1 || args.length > 2) {
            throw new WrongUsageException("commands.tmod_mobLv.usage");
        } else {
            MobLevelInfoDataSave dataSave = MobLevelInfoDataSave.get(server.getEntityWorld());
            if ("setLvBase".equals(args[0])) {
                if (args.length == 2) {
                    int level = parseInt(args[1], 1, ConfigLoader.level);
                    dataSave.setMobLevelBase(level);
                    notifyCommandListener(sender, this, "commands.tmod_mobLv.success0", level);
                } else throw new CommandException("commands.tmod.bug");
            } else if ("setLvUpSpeed".equals(args[0])) {
                if (args.length == 2) {
                    int speed = parseInt(args[1], 1, 30);
                    dataSave.setMobLevelUpSpeed(speed);
                    notifyCommandListener(sender, this, "commands.tmod_mobLv.success1", speed);
                } else throw new CommandException("commands.tmod.bug");
            } else if ("resetLvBase".equals(args[0])) {
                dataSave.setMobLevelBase(1);
                notifyCommandListener(sender, this, "commands.tmod_mobLv.success2", 1);
            } else if ("resetLvUpSpeed".equals(args[0])) {
                dataSave.setMobLevelUpSpeed(5);
                notifyCommandListener(sender, this, "commands.tmod_mobLv.success3", 5);
            } else if ("lookInfo".equals(args[0])) {
                notifyCommandListener(sender, this, "commands.tmod_mobLv.look", dataSave.getMobLevelBase(), dataSave.getMobLevelUpSpeed());
            } else throw new CommandException("commands.tmod.bug");
        }
    }

    //指令提示
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "setLvBase", "setLvUpSpeed", "resetLvBase", "resetLvUpSpeed", "lookInfo");
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

}
