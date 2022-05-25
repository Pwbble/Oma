package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class World implements CommandExecutor {
    // Usage: /world <arg[0]:World>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.world") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        // if (arg.length == 0) return MessageHandler.createInstance().sendMessage(Message.USAGE_WORLD, sender);
        return true;
    }
}