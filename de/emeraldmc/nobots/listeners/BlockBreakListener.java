package de.emeraldmc.nobots.listeners;

import de.emeraldmc.nobots.Main;
import de.emeraldmc.nobots.utils.WhiteList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

import java.util.ArrayList;

public class BlockBreakListener implements Listener {

    private static ArrayList<Player> blockBreaked = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!blockBreaked.contains(e.getPlayer()) && !WhiteList.isUUIDWhiteListed(e.getPlayer().getUniqueId())) {
            Main.getInstance().getGoodPlayers().get(e.getPlayer()).addApplicables();
            blockBreaked.add(e.getPlayer());
        }
    }
}
