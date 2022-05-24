package com.oma.events;

import com.oma.handlers.BuildmodeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGameModeChange implements Listener {

    @EventHandler
    public void onEvent(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();

        if (BuildmodeHandler.createInstance().listContains(player))
            BuildmodeHandler.createInstance().toggleOff(player);
    }
}