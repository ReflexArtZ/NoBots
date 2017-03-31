package de.emeraldmc.nobots;

import de.emeraldmc.nobots.listeners.LoginListener;
import de.emeraldmc.nobots.listeners.QuitListener;
import de.emeraldmc.nobots.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    private static Main instance;
    private Config config;
    private boolean attacked; //ToDo
    private boolean updating;
    private HashMap<Player, GoodPercentage> goodPlayers = new HashMap<>();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        config = new Config();

        registerEvents();

        ConfigManager.readConfig();
        ConfigManager.setConfig();

        MySQL.createTables();

        BlackList.updateBlackList();
    }

    @Override
    public void onDisable() {
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
    }

    public static Main getInstance() {
        return instance;
    }
    public Config getConfiguration() {
        return config;
    }
    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }

    public HashMap<Player, GoodPercentage> getGoodPlayers() {
        return goodPlayers;
    }
}
