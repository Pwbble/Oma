package com.oma.handlers;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildmodeHandler {

    private List<Player> buildmode = new ArrayList<>();

    public void toggleOn(Player player) {
        buildmode.add(player);
        Utils.clearInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.CREATIVE);
    }

    public void toggleOff(Player player) {
        buildmode.remove(player);
        Utils.clearInventory(player);
        Utils.clearPotionEffects(player);
        player.setGameMode(GameMode.SURVIVAL);
    }

    public boolean listContains(Player player) {
        return buildmode.contains(player);
    }
}