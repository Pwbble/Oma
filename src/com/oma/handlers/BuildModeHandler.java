package com.oma.handlers;

import com.oma.Main;
import com.oma.enums.Message;
import com.sun.istack.internal.NotNull;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildModeHandler extends ConfigHandler {

    // Declarations
    private static BuildModeHandler Instance;
    private List<Player> BuildMode = new ArrayList<>();

    public static BuildModeHandler createInstance() {
        return Instance;
    }

    public BuildModeHandler() {
        Instance = this;
    }

    public void saveSpecifiedData(Player player) {
        saveArmorContents(player);
        saveInventoryContents(player);
        Main.main.getConfig().set(player.getUniqueId().toString() + ".gamemode", player.getGameMode().toString());
    }

    public boolean listContains(@NotNull Player player) {
        return BuildMode.contains(player);
    }

    private boolean toggleOn(@NotNull Player player, @NotNull boolean sendMessages) {
        BuildMode.add(player);
        saveSpecifiedData(player);
        InventoryHandler.createInstance().clearInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.CREATIVE);
        if (sendMessages) return MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", "BUILDMODE"), player);
        return true;
    }

    private boolean toggleOff(@NotNull Player player, @NotNull boolean sendMessages) {
        BuildMode.remove(player);
        InventoryHandler.createInstance().returnInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.valueOf(Main.main.getConfig().getString(player.getUniqueId().toString() + ".gamemode")));
        if (sendMessages) return MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", Main.main.getConfig().getString(player.getUniqueId().toString() + ".gamemode")), player);
        return true;
    }

    public boolean setTargetBuildMode(@NotNull CommandSender sender, @NotNull Player target) {
        // If the target is invalid
        if (target == null) return MessageHandler.createInstance().sendMessage(Message.INVALID_TARGET, sender);
        if (listContains(target)) {
            // If the target is in BuildMode
            toggleOff(target, true);
            return MessageHandler.createInstance().sendMessage(Message.TARGET_UPDATE_GAMEMODE,
                    s -> s.replace("%target%", target.getName())
                            .replace("%gamemode%", "SURVIVAL"), sender);
        }
        // If the target is not in BuildMode
        toggleOn(target, true);
        MessageHandler.createInstance().sendMessage(Message.UPDATE_GAMEMODE,
                s -> s.replace("%gamemode%", "BUILDMODE"), target);
        return MessageHandler.createInstance().sendMessage(Message.TARGET_UPDATE_GAMEMODE,
                s -> s.replace("%target%", target.getName())
                        .replace("%gamemode%", "BUILDMODE"), sender);
    }

    public boolean setSelfBuildMode(@NotNull Player player, @NotNull boolean sendMessages) {
        if (listContains(player)) toggleOff(player, sendMessages);
        else toggleOn(player, sendMessages);
        return true;
    }
}