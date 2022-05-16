package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    // Usage: /fly arg[0]:Player
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("oma.command.fly") || sender.hasPermission("oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        if (arg.length == 0) { // If arg[0] is null... /fly
            if (!(sender instanceof Player))
                return MessageHandler.createInstance().sendMessage(Message.USAGE_FLY, sender);
            toggleFlight((Player) sender);
        }
        // If arg[0] is specified... /fly Pvbble
        Player target = Bukkit.getServer().getPlayer(arg[0]);
        toggleFlight(target);
        if (target.getAllowFlight())
            return MessageHandler.createInstance().sendMessage(Message.DISABLE_FLY, sender); // Change Message
        return MessageHandler.createInstance().sendMessage(Message.ENABLE_FLY, sender); // Change Message
    }

    private boolean toggleFlight(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            return MessageHandler.createInstance().sendMessage(Message.DISABLE_FLY, player);
        }
        player.setAllowFlight(true);
        return MessageHandler.createInstance().sendMessage(Message.ENABLE_FLY, player);
    }
}