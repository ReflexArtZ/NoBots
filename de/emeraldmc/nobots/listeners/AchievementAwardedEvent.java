package de.emeraldmc.nobots.listeners;

import de.emeraldmc.nobots.Main;
import de.emeraldmc.nobots.utils.WhiteList;
import org.bukkit.Achievement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AchievementAwardedEvent {

    @EventHandler
    public void onAchievementAwarded(PlayerAchievementAwardedEvent e) {
        if (e.getAchievement() != Achievement.OPEN_INVENTORY && !WhiteList.isUUIDWhiteListed(e.getPlayer().getUniqueId())) {
            Main.getInstance().getGoodPlayers().get(e.getPlayer()).addApplicables();
        }
    }
}
