package com.oma.events;

import com.oma.Main;
import com.oma.handlers.BuildModeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onEvent(PlayerQuitEvent event) {
        // Declarations
        Player player = event.getPlayer();

        event.setQuitMessage(null);
        // Main.main.getConfig().set(player.getUniqueId().toString() + ".buildmode", BuildModeHandler.createInstance().listContains(player));
    }
}