package com.oma.handlers;

import com.oma.enums.Message;
import com.oma.miscellaneous.Config;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.CommandSender;
import java.util.function.Function;

public class MessageHandler {

    private final Config config;
    private static MessageHandler Instance;

    public MessageHandler(Config config) {
        if (config == null) throw new NullPointerException();
        Instance = this;
        this.config = config;
    }

    public static MessageHandler createInstance() {
        return Instance;
    }

    public boolean sendMessage(@NotNull Message path, @NotNull Function<String, String> replace, @NotNull CommandSender sender) {
        sender.sendMessage(replace.apply(Utils.chatColor(config.getString(path.getPath()))));
        return true;
    }

    public boolean sendMessage(@NotNull Message path, @NotNull CommandSender sender) {
        return sendMessage(path, Function.identity(), sender);
    }
}