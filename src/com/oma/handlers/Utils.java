package com.oma.handlers;

import com.sun.istack.internal.NotNull;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Utils {

    /**
     * Translates certain substrings of the arg as chat color.
     *
     * @param arg The string which has its substrings translated.
     * @return A colorful message.
     */
    public static String chatColor(@NotNull String arg) {
        return (arg == null) ? arg : ChatColor.translateAlternateColorCodes('&', arg);
    }

    /**
     * Clears the player's active potion effects.
     *
     * @param player The player whose potion effects are cleared.
     */
    public static void clearPotionEffects(@NotNull Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) player.removePotionEffect(effect.getType());
    }
}