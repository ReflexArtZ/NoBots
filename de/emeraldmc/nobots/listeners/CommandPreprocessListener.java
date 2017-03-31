package de.emeraldmc.nobots.listeners;

import de.emeraldmc.nobots.Main;
import de.emeraldmc.nobots.utils.WhiteList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class CommandPreprocessListener implements Listener {

    private static ArrayList<Player> commandPlayers = new ArrayList<>();

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        if (!commandPlayers.contains(e.getPlayer()) && (!e.getMessage().contains("register") || !e.getMessage().contains("login")) && !WhiteList.isUUIDWhiteListed(e.getPlayer().getUniqueId())) {
            Main.getInstance().getGoodPlayers().get(e.getPlayer()).addApplicables();
        }
    }
}
