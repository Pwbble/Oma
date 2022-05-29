package com.oma.handlers;

import com.oma.Main;
import com.oma.miscellaneous.ItemStackSerializer;
import com.sun.istack.internal.NotNull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ConfigHandler extends ItemStackSerializer {

    public void saveArmorContents(@NotNull Player player) {
        Main.main.getConfig().set(player.getUniqueId().toString() + ".armor", serializer(player.getInventory().getArmorContents()));
        Main.main.saveConfig();
    }

    public void saveInventoryContents(@NotNull Player player) {
        Main.main.getConfig().set(player.getUniqueId().toString() + ".inventory", serializer(player.getInventory().getContents()));
        Main.main.saveConfig();
    }

    // protected void savePotionEffects(@NotNull Player player) { }

    public ItemStack[] retrieveData(@NotNull String path, @NotNull Player player) {
        return deserializer(Main.main.getConfig().getString(player.getUniqueId().toString() + path));
    }
}