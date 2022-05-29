package com.oma.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener {

    @EventHandler
    public void onEvent(EntitySpawnEvent event) {
        event.setCancelled(true);
    }
}