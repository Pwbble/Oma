package com.oma.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onEvent(BlockPlaceEvent event) {

        // Player can't build unless in gmc or buildmode
    }
}