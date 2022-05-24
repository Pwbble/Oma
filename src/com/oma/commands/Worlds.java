package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import com.oma.handlers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Worlds implements CommandExecutor {
    // Usage: /worlds
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("oma.command.worlds") || sender.hasPermission("oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        StringBuilder eval = new StringBuilder();
        for (World world : Bukkit.getWorlds()) eval.append(world.getName()).append(Utils.chatColor("&7, &b"));
        return MessageHandler.createInstance().sendMessage(Message.WORLDS,
                s -> s.replace("%worlds%", eval), sender);
    }
}