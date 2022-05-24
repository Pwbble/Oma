package com.oma.handlers;

import com.oma.enums.Message;
import com.sun.istack.internal.NotNull;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeHandler {

    private static GameModeHandler Instance;

    public static GameModeHandler createInstance() {
        return Instance;
    }

    public GameModeHandler() {
        Instance = this;
    }

    public boolean setTargetGameMode(@NotNull CommandSender sender, @NotNull Player target, @NotNull GameMode gamemode) {
        if (target == null) return MessageHandler.createInstance().sendMessage(Message.INVALID_TARGET, sender);
        if (target.getGameMode().equals(gamemode))
            return MessageHandler.createInstance().sendMessage(Message.TARGET_ALREADY_GAMEMODE,
                    s -> s.replace("%target%", target.getName())
                            .replace("%gamemode%", gamemode.name()), sender);
        target.setGameMode(gamemode);
        MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", gamemode.name()), target);
        return MessageHandler.createInstance().sendMessage(Message.TARGET_UPDATE_GAMEMODE,
                s -> s.replace("%target%", target.getName())
                        .replace("%gamemode%", gamemode.name()), sender);
    }

    public boolean setSelfGameMode(@NotNull Player player, @NotNull GameMode gamemode) {
        if (player.getGameMode().equals(gamemode))
            return MessageHandler.createInstance().sendMessage(Message.ALREADY_GAMEMODE,
                    s -> s.replace("%gamemode%", gamemode.name()), player);
        player.setGameMode(gamemode);
        return MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", gamemode.name()), player);
    }
}