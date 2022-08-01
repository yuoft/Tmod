package com.yuoMod.Tmod.Common;

import com.yuoMod.Tmod.Client.ConfigLoader;
import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;

import javax.annotation.Nullable;
import java.util.List;

public class MobInfoCommand extends CommandBase {

    @Override
    public String getName() {
        return "tmod_mobInfo";
    }

    //指令信息
    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tmod_mobInfo.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        // 命令的执行逻辑全部在这里发生。
        if (args.length != 1) {
            throw new WrongUsageException("commands.tmod_mobInfo.usage");
        } else {
            if ("true".equals(args[0])) {
                ConfigLoader.mobInfo = true;
                ConfigManager.sync("tmod", Config.Type.INSTANCE);
                notifyCommandListener(sender, this, "commands.tmod_mobInfo.success");
            } else if ("false".equals(args[0])) {
                ConfigLoader.mobInfo = false;
                ConfigManager.sync("tmod", Config.Type.INSTANCE); //保存配置信息
                notifyCommandListener(sender, this, "commands.tmod_mobInfo.success");
            } else throw new CommandException("commands.tmod_mobInfo.bug");
        }
    }

    //指令提示
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "true", "false");
        }
        return super.getTabCompletions(server, sender, args, targetPos);
    }

}
