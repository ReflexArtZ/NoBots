package de.emeraldmc.nobots.listeners;

import de.emeraldmc.nobots.Main;
import de.emeraldmc.nobots.utils.GoodPercentage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        GoodPercentage goodPercentage = new GoodPercentage(4); // 2 for AchievementAwards, 1 for BlockBreak, 1 for Command
        Main.getInstance().getGoodPlayers().put(p, goodPercentage);
    }
}
