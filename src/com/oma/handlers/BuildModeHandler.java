package com.oma.handlers;

import com.oma.enums.Message;
import com.sun.istack.internal.NotNull;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildModeHandler {

    // Declarations
    private static BuildModeHandler Instance;
    private List<Player> BuildMode = new ArrayList<>();

    public static BuildModeHandler createInstance() {
        return Instance;
    }

    public BuildModeHandler() {
        Instance = this;
    }

    public boolean listContains(@NotNull Player player) {
        return BuildMode.contains(player);
    }

    private boolean toggleOn(@NotNull Player player) {
        BuildMode.add(player);
        InventoryHandler.createInstance().saveArmorAndInventoryContents(player);
        InventoryHandler.createInstance().clearInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.CREATIVE);
        return MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", "BUILDMODE"), player);
    }

    private boolean toggleOff(@NotNull Player player) {
        BuildMode.remove(player);
        InventoryHandler.createInstance().returnInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.SURVIVAL);
        return MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", "SURVIVAL"), player);
    }

    public boolean setTargetBuildMode(@NotNull CommandSender sender, @NotNull Player target) {
        // If the target is invalid
        if (target == null) return MessageHandler.createInstance().sendMessage(Message.INVALID_TARGET, sender);
        if (listContains(target)) {
            // If the target is in BuildMode
            toggleOff(target);
            return MessageHandler.createInstance().sendMessage(Message.TARGET_UPDATE_GAMEMODE,
                    s -> s.replace("%target%", target.getName())
                            .replace("%gamemode%", "SURVIVAL"), sender);
        }
        // If the target is not in BuildMode
        toggleOn(target);
        MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", "BUILDMODE"), target);
        return MessageHandler.createInstance().sendMessage(Message.TARGET_UPDATE_GAMEMODE,
                s -> s.replace("%target%", target.getName())
                        .replace("%gamemode%", "BUILDMODE"), sender);
    }

    public boolean setSelfBuildMode(@NotNull Player player) {
        if (listContains(player)) toggleOff(player);
        else toggleOn(player);
        return true;
    }
}