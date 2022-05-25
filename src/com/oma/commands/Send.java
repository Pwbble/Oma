package com.oma.commands;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Send implements CommandExecutor {
    // Usage: /send <arg[0]:Player> <arg[1]:World>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        // If [Player] sender doesn't have permission to run /send
        if (!(sender.hasPermission("oma.command.world") || sender.hasPermission("oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        // If arg[0:1] are missing
        if (arg.length < 2) return MessageHandler.createInstance().sendMessage(Message.USAGE_SEND, sender);
        Player target = Bukkit.getPlayer(arg[0]);
        World world = Bukkit.getWorld(arg[1]);
        if (target == null || world == null)
            return (target == null) ? MessageHandler.createInstance().sendMessage(Message.INVALID_TARGET, sender) :
                    MessageHandler.createInstance().sendMessage(Message.INVALID_WORLD, sender);
        if (target.getWorld() == world)
            return MessageHandler.createInstance().sendMessage(Message.ALREADY_WORLD,
                    s -> s.replace("%world%", world.toString()), sender);
        target.teleport(world.getSpawnLocation());
        return MessageHandler.createInstance().sendMessage(Message.TELEPORT_WORLD,
                s -> s.replace("%target%", target.getName())
                        .replace("%world%", world.toString()), sender);
    }
}