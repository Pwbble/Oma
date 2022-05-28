package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.GameModeHandler;
import com.oma.handlers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gma implements CommandExecutor {
    // Usage: /gma (arg[0]:Player)
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.gamemode_adventure") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        // If arg[0] is null. ex: /gma
        if (arg.length == 0) return (!(sender instanceof Player)) ?
                MessageHandler.createInstance().sendMessage(Message.USAGE_GAMEMODE,
                        s -> s.replace("%command%", string), sender) : GameModeHandler.createInstance().setSelfGameMode((Player) sender, GameMode.ADVENTURE);
        // If arg[0] is specified. ex: /gma Pvbble
        return GameModeHandler.createInstance().setTargetGameMode(sender, Bukkit.getServer().getPlayer(arg[0]), GameMode.ADVENTURE);
    }
}