package de.emeraldmc.nobots.listeners;

import de.emeraldmc.nobots.utils.Debug;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        String address = e.getPlayer().getAddress().getAddress().toString().replace("/", "");
        LoginListener.addresses.remove(address);
    }
}
