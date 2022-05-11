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
            return MessageHandler.sendMessage(sender, Message.INVALID_PERMISSION);
        if (arg.length == 0) { // If arg[0] is null. ex: /fly
            if (!(sender instanceof Player)) return MessageHandler.sendMessage(sender, Message.USAGE_FLY);
            toggleFlight((Player) sender);
        }
        // If arg[0] is specified. ex: /fly Pvbble
        Player target = Bukkit.getServer().getPlayer(arg[0]);
        toggleFlight(target);
        if (target.getAllowFlight()) return MessageHandler.sendMessage(sender, Message.DISABLE_FLY); // Change Message
        return MessageHandler.sendMessage(sender, Message.ENABLE_FLY); // Change Message
    }

    public void toggleFlight(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            return MessageHandler.sendMessage(player, Message.DISABLE_FLY);
        }
        player.setAllowFlight(true);
        return MessageHandler.sendMessage(player, Message.ENABLE_FLY);
    }
}