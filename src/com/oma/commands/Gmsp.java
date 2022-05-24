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

public class Gmsp implements CommandExecutor {
    // Usage: /gmsp (arg[0]:Player)
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.gamemode_spectator") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        // If arg[0] is null. ex: /gmsp
        if (arg.length == 0) {
            if (!(sender instanceof Player))
                return MessageHandler.createInstance().sendMessage(Message.USAGE_GAMEMODE,
                        s -> s.replace("%command%", string), sender);
            return GameModeHandler.createInstance().setSelfGameMode((Player) sender, GameMode.SPECTATOR);
        }
        // If arg[0] is specified. ex: /gmsp Pvbble
        return GameModeHandler.createInstance().setTargetGameMode(sender, Bukkit.getServer().getPlayer(arg[0]), GameMode.SPECTATOR);
    }
}