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
    // Usage: /gmc (arg[0]:Player)
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.gamemode_creative") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        // If arg[0] is null. ex: /gmc
        if (arg.length == 0) {
            if (!(sender instanceof Player))
                return MessageHandler.createInstance().sendMessage(Message.USAGE_GAMEMODE,
                        s -> s.replace("%command%", string), sender);
            Player player = (Player) sender;
            if (player.getGameMode() == GameMode.CREATIVE)
                return MessageHandler.createInstance().sendMessage(Message.ALREADY_GAMEMODE,
                        s -> s.replace("%gamemode%", "Creative"), player);
            player.setGameMode(GameMode.CREATIVE);
            return MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                    s -> s.replace("%gamemode%", "Creative"), sender);
        }
        // If arg[0] is specified. ex: /gmc Pvbble
        Player target = Bukkit.getServer().getPlayer(arg[0]);
        if (target == null) return MessageHandler.createInstance().sendMessage(Message.INVALID_TARGET, sender);
        if (target.getGameMode() == GameMode.CREATIVE)
            return MessageHandler.createInstance().sendMessage(Message.TARGET_ALREADY_GAMEMODE,
                    s -> s.replace("%target%", target.getName())
                            .replace("%gamemode%", "Creative"), sender);
        target.setGameMode(GameMode.CREATIVE);
        return MessageHandler.createInstance().sendMessage(Message.TARGET_UPDATE_GAMEMODE,
                s -> s.replace("%target%", target.getName())
                        .replace("%gamemode%", "Creative"), sender);
    }
}