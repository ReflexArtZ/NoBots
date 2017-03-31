package de.emeraldmc.nobots.utils;

import de.emeraldmc.nobots.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatAPI {
    public static String translateColor(String str) {
        str = ChatColor.translateAlternateColorCodes('&', str);
        return str;
    }
    public static void sendMessage(Player p, String str) {
        str = translateColor(str);
        p.sendMessage("§aNoBots§7: "+str);
    }
}
