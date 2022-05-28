package com.oma.events;

import com.oma.handlers.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {

    @EventHandler
    public void onEvent(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        if (event.getPlayer().hasPermission("Oma.chat.chatcolor")) event.setMessage(Utils.chatColor(message));
    }
}