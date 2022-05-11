package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gmc implements CommandExecutor {
    // Usage: /gmc arg[0]:Player

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {

        if (!(sender.hasPermission("Oma.command.GMC") || sender.hasPermission("Oma.*")))
            return MessageHandler.sendMessage(sender, Message.INVALID_PERMISSION);
        if (arg.length == 0) { // If arg[0] is null. ex: /gmc
            if (!(sender instanceof Player)) return MessageHandler.sendMessage(sender, Message.USAGE_GMC);
            Player player = (Player) sender;
            if (player.getGameMode().equals(GameMode.CREATIVE))
                return MessageHandler.sendMessage(sender, Message.ALREADY_GMC);
            player.setGameMode(GameMode.CREATIVE);
            return MessageHandler.sendMessage(player, Message.UPDATE_GMC);
        }
        // If arg[0] is specified. ex: /gmc Pvbble
        Player target = Bukkit.getServer().getPlayer(arg[0]);
        if (target == null) return MessageHandler.sendMessage(sender, Message.INVALID_TARGET);
        if (target.getGameMode().equals(GameMode.CREATIVE))
            return MessageHandler.sendMessage(sender, Message.ALREADY_GMC); // Change Message
        target.setGameMode(GameMode.CREATIVE);
        MessageHandler.sendMessage(target, Message.UPDATE_GMC);
        return MessageHandler.sendMessage(sender, Message.UPDATE_GMC); // Change Message
    }
}