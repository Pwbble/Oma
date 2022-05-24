package com.oma.events;

import com.oma.enums.Message;
import com.oma.handlers.BuildmodeHandler;
import com.oma.handlers.MessageHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!(BuildmodeHandler.createInstance().listContains(player) || player.getGameMode() == GameMode.CREATIVE))
            event.setCancelled(true);
        // MessageHandler.createInstance().sendMessage(Message.INVALID_BUILD);
    }
}