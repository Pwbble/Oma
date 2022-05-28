package com.oma.handlers;

import com.oma.Main;
import com.oma.miscellaneous.ItemStackSerializer;
import com.sun.istack.internal.NotNull;
import org.bukkit.entity.Player;

public class InventoryHandler extends ItemStackSerializer {

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

    public void saveArmorAndInventoryContents(@NotNull Player player) {
        String playerUUID = player.getUniqueId().toString();
        Main.main.getConfig().set(playerUUID + ".armor", itemStackSerialization(player.getInventory().getArmorContents()));
        Main.main.getConfig().set(playerUUID + ".inventory", itemStackSerialization(player.getInventory().getContents()));
        Main.main.saveConfig();
    }

    public void returnInventory(@NotNull Player player) {
        clearInventory(player);
        player.getInventory().setArmorContents(itemStackDeserialization(Main.main.getConfig().getString(player.getUniqueId().toString() + ".armor")));
        player.getInventory().setContents(itemStackDeserialization(Main.main.getConfig().getString(player.getUniqueId().toString() + ".inventory")));
    }
}