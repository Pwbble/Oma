package com.oma.events;

import com.oma.handlers.BuildModeHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener {

    @EventHandler
    public void onEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (BuildModeHandler.createInstance().listContains(player) || player.getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }
}