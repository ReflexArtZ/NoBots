package de.emeraldmc.nobots.utils;

import de.emeraldmc.nobots.Main;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlackList {
    public static void addIp(String address) {
        if (address.length() > 15) return;
        if (BlackList.isIpBlackListed(address)) return;
        try {
            PreparedStatement preparedStatement = MySQL.getInstance().prepareStatement("INSERT INTO NoBots_BlackList (IP) VALUES (?)");
            preparedStatement.setString(1, address);
            preparedStatement.executeUpdate();
            Debug.print("&7Added &c"+address+" &7to the BlackList");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeIp(String address) {
        if (address.length() > 15) return;
        if (!BlackList.isIpBlackListed(address)) return;
        try {
            PreparedStatement preparedStatement = MySQL.getInstance().prepareStatement("DELETE FROM NoBots_BlackList WHERE IP = ?");
            preparedStatement.setString(1, address);
            preparedStatement.executeUpdate();
            Debug.print("Removed "+address+" from the BlackList");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static boolean isIpBlackListed(String address) {
        try {
            PreparedStatement preparedStatement = MySQL.getInstance().prepareStatement("SELECT * FROM NoBots_BlackList WHERE IP = ?");
            preparedStatement.setString(1, address);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void updateBlackList() {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    Main.getInstance().setUpdating(true);
                    URL url = new URL("https://myip.ms/files/blacklist/csf/latest_blacklist.txt");
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String address;
                    int z = 0;
                    while ((address = in.readLine()) != null) {
                        if (address.contains(":") || BlackList.isIpBlackListed(address) || address.startsWith("#")) {
                            continue;
                        }
                        BlackList.addIp(address);
                        z++;
                    }
                    in.close();
                    Bukkit.getConsoleSender().sendMessage("§aBlackList updated"+"\n"+"\t"+"-> Added "+z+" bad addresses!");
                    Main.getInstance().setUpdating(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
