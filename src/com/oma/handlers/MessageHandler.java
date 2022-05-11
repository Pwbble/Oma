package com.oma.handlers;

import com.oma.Main;
import com.oma.enums.Message;
import org.bukkit.command.CommandSender;
import java.util.function.Function;

public class MessageHandler {

    public static boolean sendMessage(CommandSender sender, Message path, Function<String, String> replace) {

        sender.sendMessage(replace.apply(Utils.chatColor(Main.main.getConfig().getString(path.getPath()))));
        return true;
    }

    public static boolean sendMessage(CommandSender sender, Message path) {
        return sendMessage(sender, path, Function.identity());
    }
}