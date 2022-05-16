package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class World implements CommandExecutor {
    // Usage: /world <arg[0]:World>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender instanceof Player)) return MessageHandler.createInstance().sendMessage(Message.NO_CONSOLE, sender);
        Player player = (Player) sender;
        if (!(player.hasPermission("oma.command.world") || player.hasPermission("oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, player);
        // if (arg.length == 0) return MessageHandler.createInstance().sendMessage(Message., player);
        // if (player.getWorld() == null) return MessageHandler.createInstance().sendMessage(Message.INVALID_WORLD, player);
        // if (player.getWorld() == world) return MessageHandler.createInstance().sendMessage(Message.ALREADY_WORLD, player);
        return true;
    }
}