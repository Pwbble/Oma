package com.oma.handlers;

import com.oma.enums.Message;
import com.oma.miscellaneous.Config;
import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import java.util.function.Function;

public class MessageHandler {

    private static MessageHandler Instance;
    private final Config config;

    public MessageHandler(Config config) {
        if (config == null) throw new NullPointerException();
        Instance = this;
        this.config = config;
    }

    public static MessageHandler createInstance() {
        return Instance;
    }

    public boolean sendBroadcast(@NotNull Message message, @NotNull Function<String, String> replace) {
        Bukkit.broadcastMessage(Utils.chatColor(replace.apply(config.getString(message.getPath()))));
        return true;
    }

    public boolean sendBroadcast(@NotNull Message message) {
        return sendBroadcast(message, Function.identity());
    }

    public boolean sendMessage(@NotNull Message message, @NotNull Function<String, String> replace, @NotNull CommandSender sender) {
        sender.sendMessage(Utils.chatColor(replace.apply(config.getString(message.getPath()))));
        return true;
    }

    public boolean sendMessage(@NotNull Message message, @NotNull CommandSender sender) {
        return sendMessage(message, Function.identity(), sender);
    }
}