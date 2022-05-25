package com.oma.commands;

import com.oma.Main;
import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import com.oma.miscellaneous.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Config implements CommandExecutor {

    /* Usage:
     * /config reset
     * /config edit <arg[1]:String> <arg[2]:String>
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.config") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        if (arg.length == 0) return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
        switch (arg[0]) {
            default:
                return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
                /*
                            case "reset":
                Configuration.createInstance().load();
                return MessageHandler.createInstance().sendMessage(Message.RESET_CONFIG, sender);
                 */
            case "edit":
                if (arg.length < 3) return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
                if (Main.main.getConfig().getString(arg[1]) == null)
                    return MessageHandler.createInstance().sendMessage(Message.INVALID_PATH, sender);
                Main.main.getConfig().set(arg[1], arg[2]);
                Main.main.saveConfig();
                return MessageHandler.createInstance().sendMessage(Message.EDIT_CONFIG,
                        s -> s.replace("%path%", arg[1])
                                .replace("%string%", arg[2]), sender);
        }
    }
}