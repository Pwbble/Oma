package com.oma.handlers;

import com.sun.istack.internal.NotNull;
import org.bukkit.entity.Player;

public class InventoryHandler extends ConfigHandler {

    // Declarations
    private static InventoryHandler Instance;

    public InventoryHandler() {
        Instance = this;
    }

    public static InventoryHandler createInstance() {
        return Instance;
    }

    public void clearInventory(@NotNull Player player) {
        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
    }

    public void returnInventory(@NotNull Player player) {
        player.getInventory().setContents(retrieveData(".inventory", player));
        player.getInventory().setArmorContents(retrieveData(".armor", player));
    }
}