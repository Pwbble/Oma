package com.oma.commands;

import com.oma.Main;
import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Config implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.config") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        try {
            switch (arg[0].toLowerCase()) {
                default:
                    return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
                case "edit": // Usage: /config edit <arg[1]:String path> <arg[2]: String updated_message>
                    if (Main.main.getConfig().getString(arg[1]) == null)
                        return MessageHandler.createInstance().sendMessage(Message.INVALID_PATH, sender);
                    Main.main.getConfig().set(arg[1], arg[2]);
                    Main.main.saveConfig();
                    return MessageHandler.createInstance().sendMessage(Message.EDIT_CONFIG, sender);
                case "list": // Usage: /config list (arg[1]:String section)
                    StringBuilder eval = new StringBuilder();
                    if (arg.length == 1) {
                        for (String section : Main.main.getConfig().getConfigurationSection("oma").getKeys(false)) {
                            for (String path : Main.main.getConfig().getConfigurationSection("oma." + section).getKeys(false)) {
                                eval.append("oma." +  section + "." + path + "&7, \n&b");
                            }
                        }
                        return MessageHandler.createInstance().sendMessage(Message.LIST_PATHS, s -> s.replace("%paths%", eval), sender);
                    } else {
                        String section = (arg[1].startsWith("oma.")) ? arg[1] : "oma." + arg[1];
                        for (String path : Main.main.getConfig().getConfigurationSection(section).getKeys(false)) {
                            System.out.println(section + "." + path);
                        }
                    }
                    break;
                case "reload": // Usage: /config reload
                    break;
                case "reset": // Usage: /config reset
                    // return MessageHandler.createInstance().sendMessage(Message.RESET_CONFIG, sender);
                    return MessageHandler.createInstance().sendMessage(Message.COMING_SOON, sender);
                case "view": // Usage: /config view <arg[1]:String path>
                    if (Main.main.getConfig().getString(arg[1]) == null)
                        return MessageHandler.createInstance().sendMessage(Message.INVALID_PATH, sender);
                    return MessageHandler.createInstance().sendMessage(Message.VIEW_PATH,
                            s -> s.replace("%path%", Main.main.getConfig().getString(arg[1])), sender);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
        }
        return true;
    }
}