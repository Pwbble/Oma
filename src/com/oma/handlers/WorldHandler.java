package com.oma.handlers;

import com.sun.istack.internal.NotNull;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldHandler {
    public void teleportWorld(@NotNull Player player, @NotNull World world) {
        if (world == null) return;
        if (player.getWorld() == world) return;
        player.teleport(world.getSpawnLocation());
    }
}