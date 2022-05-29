package com.oma.events;

import com.oma.handlers.BuildModeHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGameModeChange implements Listener {

    @EventHandler
    public void onEvent(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();

        if (BuildModeHandler.createInstance().listContains(player) && !event.getNewGameMode().equals(GameMode.CREATIVE))
            BuildModeHandler.createInstance().setSelfBuildMode(player, false);
    }
}