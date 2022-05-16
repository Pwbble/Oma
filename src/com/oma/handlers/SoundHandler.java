package com.oma.handlers;

import com.oma.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundHandler {

    private static SoundHandler Instance;

    public static SoundHandler createInstance() {
        return Instance;
    }

    public void playSound(String path, Player player) {
        player.playSound(player.getLocation(),
                Sound.valueOf(Main.main.getConfig().getString(path).toUpperCase()), 1, 1);
    }
}