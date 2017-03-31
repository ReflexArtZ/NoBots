package de.emeraldmc.nobots.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Patrick Sommer
 */
public class Config {
    private static FileConfiguration cfg = ConfigManager.getConfigFileConfiguration();

    private boolean debug;

    private boolean maxIp;
    private int maxIpLimit;
    private String maxIpKickMessage;

    private int minimumAttackers;

    private String updatingKickMessage;

    private boolean enableCountryWhiteList;
    private ArrayList<String> whiteListedCountries;
    private String whiteListedCountriesKickMessage;

    private ArrayList<UUID> whiteListedPlayers;

    private String dbHost;
    private int dbPort;
    private String database;
    private String dbUser;
    private String dbPassword;

    public Config() {
        debug = cfg.getBoolean("Config.debug");

        maxIp = cfg.getBoolean("Config.MaxIp.enabled");
        maxIpLimit = cfg.getInt("Config.MaxIp.limit");
        maxIpKickMessage = cfg.getString("Config.MaxIp.kickMessage");

        minimumAttackers = cfg.getInt("Config.Attacked.minimumAttackers");

        updatingKickMessage = cfg.getString("Config.updatingKickMessage");

        enableCountryWhiteList = cfg.getBoolean("Config.WhiteListedCountries.enabled");
        whiteListedCountries = new ArrayList<>(cfg.getStringList("Config.WhiteListedCountries.countries"));
        whiteListedCountriesKickMessage = cfg.getString("Config.WhiteListedCountries.kickMessage");

        dbHost = cfg.getString("Config.MySQL.host");
        dbPort = cfg.getInt("Config.MySQL.port");
        database = cfg.getString("Config.MySQL.database");
        dbUser = cfg.getString("Config.MySQL.user");
        dbPassword = cfg.getString("Config.MySQL.password");
    }



    public static FileConfiguration getCfg() {
        return cfg;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isMaxIp() {
        return maxIp;
    }

    public int getMaxIpLimit() {
        return maxIpLimit;
    }

    public String getMaxIpKickMessage() {
        return maxIpKickMessage;
    }


    public int getMinimumAttackers() {
        return minimumAttackers;
    }


    public String getDbHost() {
        return dbHost;
    }

    public int getDbPort() {
        return dbPort;
    }

    public String getDatabase() {
        return database;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public ArrayList<String> getWhiteListedCountries() {
        return whiteListedCountries;
    }

    public boolean isEnableCountryWhiteList() {
        return enableCountryWhiteList;
    }

    public String getWhiteListedCountriesKickMessage() {
        return whiteListedCountriesKickMessage;
    }

    public String getUpdatingKickMessage() {
        return updatingKickMessage;
    }
}
