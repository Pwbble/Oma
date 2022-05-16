package com.oma.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onEvent(PlayerQuitEvent event) {

        event.setQuitMessage(null);
    }
}