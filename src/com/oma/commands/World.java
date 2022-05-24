package com.oma.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class World implements CommandExecutor {
    // Usage: /world ...
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {

        return true;
    }
}