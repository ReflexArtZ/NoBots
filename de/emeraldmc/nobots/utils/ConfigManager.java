package de.emeraldmc.nobots.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates, loads and fills the Config file so the user can configure it
 * @author Patrick Sommer
 */
public class ConfigManager {
    public static File getConfigFile() {
        return new File("plugins/NoBots", "config.yml");
    }
    public static FileConfiguration getConfigFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getConfigFile());
    }

    /**
     * Set the standard configuration
     */
    public static void setConfig() {
        FileConfiguration cfg = getConfigFileConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("Config.debug", false);
        cfg.addDefault("Config.MaxIp.enabled", true);
        cfg.addDefault("Config.MaxIp.limit", 2);
        cfg.addDefault("Config.MaxIp.kickMessage", "&cYou are kicked!");
        cfg.addDefault("Config.Attacked.minimumAttackers", 5);
        ArrayList<String> whiteListedCountries = new ArrayList<>();
        whiteListedCountries.add("Austria");
        whiteListedCountries.add("Germany");
        whiteListedCountries.add("United States");
        cfg.addDefault("Config.WhiteListedCountries.enabled", true);
        cfg.addDefault("Config.WhiteListedCountries.countries", whiteListedCountries);
        cfg.addDefault("Config.WhiteListedCountries.kickMessage", "&cYou are not in a whitelisted country!");
        cfg.addDefault("Config.updatingKickMessage", "&cPlease come back in a minute, the Blacklist is updating!");
        cfg.addDefault("Config.MySQL.host", "127.0.0.1");
        cfg.addDefault("Config.MySQL.port", 3306);
        cfg.addDefault("Config.MySQL.database", "NoBots");
        cfg.addDefault("Config.MySQL.user", "root");
        cfg.addDefault("Config.MySQL.password", "toor");
        try {
            cfg.save(getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the configuration
     */
    public static void readConfig() {
        FileConfiguration cfg = getConfigFileConfiguration();
    }
}