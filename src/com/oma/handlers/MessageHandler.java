package com.oma.handlers;

import com.oma.enums.Message;
import com.oma.miscellaneous.Configuration;
import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.function.Function;

public class MessageHandler {

    private static MessageHandler Instance;
    private final Configuration config;

    public MessageHandler(Configuration config) {
        if (config == null) throw new NullPointerException();
        Instance = this;
        this.config = config;
    }

    public static MessageHandler createInstance() {
        return Instance;
    }

    /**
     * Function which sends a message to all online players.
     *
     * @param message Enum containing the path of the message.
     * @param replace Replacement function which replaces a String with another String.
     * @return Return value is without puropse.
     */
    public boolean sendBroadcast(@NotNull Message message, @NotNull Function<String, String> replace) {
        Bukkit.broadcastMessage(Utils.chatColor(replace.apply(config.getString(message.getPath()))));
        return true;
    }

    /**
     * Function which sends a message to all online players.
     *
     * @param message Enum containing the path of the message.
     * @return Return value is without puropse.
     */
    public boolean sendBroadcast(@NotNull Message message) {
        return sendBroadcast(message, Function.identity());
    }

    /**
     * Function which sends a message from the config at message.getPath() to the sender.
     *
     * @param message Enum containing the path of the message.
     * @param replace Replacement function which replaces a String with another String.
     * @param sender  Receiver.
     * @return Return value is without purpose.
     */
    public boolean sendMessage(@NotNull Message message, @NotNull Function<String, String> replace, @NotNull CommandSender sender) {
        if (config.isList(message.getPath())) {
            StringBuilder eval = new StringBuilder();
            for (String list : config.getStringList(message.getPath())) eval.append(list).append("\n");
            sender.sendMessage(Utils.chatColor(replace.apply(eval.toString())));
        } else if (config.isString(message.getPath()))
            sender.sendMessage(Utils.chatColor(replace.apply(config.getString(message.getPath()))));
        else throw new IllegalArgumentException();
        return true;
    }

    /**
     * Function which sends a message from the config at message.getPath() to the sender.
     *
     * @param message Enum containing the path of the message.
     * @param sender  Receiver.
     * @return Return value is without purpose.
     */
    public boolean sendMessage(@NotNull Message message, @NotNull CommandSender sender) {
        return sendMessage(message, Function.identity(), sender);
    }
}