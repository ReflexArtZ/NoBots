package de.emeraldmc.nobots.utils;

import de.emeraldmc.nobots.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Debug {
    public static void print(String s) {
        if (Main.getInstance().getConfiguration().isDebug()) {
            Bukkit.getServer().getConsoleSender().sendMessage("§aNoBots§7: " + ChatColor.translateAlternateColorCodes('&', s));
        }
    }
}
