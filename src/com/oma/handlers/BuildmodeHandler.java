package com.oma.handlers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildmodeHandler {

    private static BuildmodeHandler Instance;

    public static BuildmodeHandler createInstance() {
        return Instance;
    }

    public BuildmodeHandler() {
        Instance = this;
    }

    private List<Player> BuildMode = new ArrayList<>();

    public void toggleOn(Player player) {
        BuildMode.add(player);
        Utils.clearInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.CREATIVE);
    }

    public void toggleOff(Player player) {
        BuildMode.remove(player);
        Utils.clearInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.SURVIVAL);
    }

    public boolean listContains(Player player) {
        return BuildMode.contains(player);
    }
}