package com.oma.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Utils {

    public static String chatColor(String arg) {
        return (arg == null) ? arg : ChatColor.translateAlternateColorCodes('&', arg);
    }

    public static void clearInventory(Player player) {
        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
    }

    public static void clearPotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) player.removePotionEffect(effect.getType());
    }
}