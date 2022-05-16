package com.oma.events;

import com.oma.enums.Message;
import com.oma.handlers.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        MessageHandler.createInstance().sendMessage(Message.JOIN, player);
    }
}