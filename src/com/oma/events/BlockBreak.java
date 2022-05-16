package com.oma.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener  {

    @EventHandler
    public void onEvent(BlockBreakEvent event) {

        // Player can't build unless in gmc or buildmode
    }
}