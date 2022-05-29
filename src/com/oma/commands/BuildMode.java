package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.BuildModeHandler;
import com.oma.handlers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildMode implements CommandExecutor {
    // Usage: /build (arg[0]:Player target)
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.gamemode_buildmode") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        // If arg[0] is null. ex: /buildmode
        if (arg.length == 0) return (!(sender instanceof Player)) ?
                MessageHandler.createInstance().sendMessage(Message.USAGE_GAMEMODE,
                        s -> s.replace("%command%", string), sender) :
                BuildModeHandler.createInstance().setSelfBuildMode((Player) sender, true);
        // If arg[0] is specified. ex: /buildmode Pvbble
        return BuildModeHandler.createInstance().setTargetBuildMode(sender, Bukkit.getServer().getPlayer(arg[0]));
    }
}