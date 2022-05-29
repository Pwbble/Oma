package com.oma.events;

import com.oma.handlers.BuildModeHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem implements Listener {

    @EventHandler
    public void onEvent(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (BuildModeHandler.createInstance().listContains(player) || player.getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }
}