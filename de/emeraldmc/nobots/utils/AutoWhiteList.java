package de.emeraldmc.nobots.utils;

import de.emeraldmc.nobots.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AutoWhiteList {
    private boolean started;
    private int taskId;

    /**
     * @deprecated scheduleAsyncRepeatingTask
     */
    public void start() {
        if (started) stop();
        taskId = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!WhiteList.isUUIDWhiteListed(p.getUniqueId())) {
                        if (Main.getInstance().getGoodPlayers().get(p).calcPercentage() > 50) {
                            WhiteList.addUUID(p.getUniqueId());
                            for (Player op : Bukkit.getOnlinePlayers()) {
                                if (op.isOp()) {
                                    ChatAPI.sendMessage(op, "Auto Whitelisted: "+p.getName());
                                }
                            }
                        }
                    }
                }
            }
        }, 0l, 120l); // 6 seconds
    }
    public void stop() {
        Bukkit.getServer().getScheduler().cancelTask(taskId);
        started = false;
        Debug.print("Stopped AutoWhiteList task ("+taskId+")");
    }
}
