package com.oma.commands;

import com.oma.Main;
import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import com.oma.miscellaneous.Configuration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Config implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arg) {
        if (!(sender.hasPermission("Oma.command.config") || sender.hasPermission("Oma.*")))
            return MessageHandler.createInstance().sendMessage(Message.INVALID_PERMISSION, sender);
        try {
            switch (arg[0].toLowerCase()) {
                default:
                    return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
                case "edit": // Usage: /config edit <arg[1]:String path> <arg[2]:String updated_message>
                    if (!(sender.hasPermission("Oma.command.config_edit") || sender.hasPermission("Oma.*"))) ;
                    if (Main.main.getConfig().getString(arg[1]) == null)
                        return MessageHandler.createInstance().sendMessage(Message.INVALID_PATH, sender);
                    if (arg.length == 2) { // Temporary, full implementation of this isn't set up
                        Main.main.getConfig().set(arg[1], ((Player) sender).getInventory());
                        return true;
                    }
                    StringBuilder eval = new StringBuilder();
                    for (int a = 2; a < arg.length; a++) eval.append(arg[a]).append(' ');
                    Main.main.getConfig().set(arg[1], eval.toString());
                    Main.main.saveConfig();
                    return MessageHandler.createInstance().sendMessage(Message.EDIT_CONFIG, sender);
                case "list": // Usage: /config list (arg[1]:String section)
                    if (!(sender.hasPermission("Oma.command.config_list") || sender.hasPermission("Oma.*"))) ;
                    /*
                    /config list oma -> all sections
                    /config list oma. -> all sections
                    /config list om -> searches for paths beginning with om
                    /config list oma.gamemo -> searches for paths begining with oma.gamemo
                    /config list asisaieufn -> invalid thing
                     */
                    return MessageHandler.createInstance().sendMessage(Message.LIST_PATHS,
                            s -> s.replace("%paths%", (arg.length == 1) ?
                                    listPaths() : (listSections((arg[1].startsWith("oma.")) ? arg[1] : "oma." + arg[1]))), sender);
                case "reload": // Usage: /config reload
                    if (!(sender.hasPermission("Oma.command.config_reload") || sender.hasPermission("Oma.*")));
                    return MessageHandler.createInstance().sendMessage(Message.COMING_SOON, sender);
                case "reset": // Usage: /config reset
                    if (!(sender.hasPermission("Oma.command.config_reset") || sender.hasPermission("Oma.*")));
                    Configuration.createInstance().loadDefaults("config.yml");
                    return MessageHandler.createInstance().sendMessage(Message.RESET_CONFIG, sender);
                case "view": // Usage: /config view <arg[1]:String path>
                    if (!(sender.hasPermission("Oma.command.config_view") || sender.hasPermission("Oma.*")));
                    return (Main.main.getConfig().getString(arg[1]) == null) ?
                            MessageHandler.createInstance().sendMessage(Message.INVALID_PATH, sender) :
                            MessageHandler.createInstance().sendMessage(Message.VIEW_PATH, s -> s.replace("%path%", Main.main.getConfig().getString(arg[1])), sender);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return MessageHandler.createInstance().sendMessage(Message.USAGE_CONFIG, sender);
        }
    }

    // Function to loop through all config paths in a given section (oma.section.path)
    private StringBuilder listSections(String section) {
        StringBuilder eval = new StringBuilder();
        for (String path : Main.main.getConfig().getConfigurationSection(section).getKeys(false))
            eval.append(section + "." + path + "&7, \n&b");
        return eval;
    }

    // Function to loop through the config.yml and returns all the paths
    private StringBuilder listPaths() {
        StringBuilder eval = new StringBuilder();
        for (String section : Main.main.getConfig().getConfigurationSection("oma").getKeys(false))
            eval.append(listSections("oma." + section));
        return eval;
    }
}