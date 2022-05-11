package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build implements CommandExecutor {
    // Usage: /build

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {

        if (!(sender instanceof Player)) return MessageHandler.sendMessage(sender, Message.NO_CONSOLE);
        Player player = (Player) sender;
        if (!(player.hasPermission("oma.command.build") || player.hasPermission("oma.*")))
            return MessageHandler.sendMessage(player, Message.INVALID_PERMISSION);
        return true;
    }
}