package de.emeraldmc.nobots.listeners;

import de.emeraldmc.nobots.Main;
import de.emeraldmc.nobots.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import java.net.InetAddress;
import java.util.ArrayList;

public class LoginListener implements Listener {

    // List of connected addresses
    public static ArrayList<String> addresses = new ArrayList<>(); //Format: 127.0.0.1

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        String player = e.getName();
        InetAddress address = e.getAddress();
        String strAddress = address.toString().replace("/", "");
        String country = ProxyCheck.getCountry(strAddress);
        Debug.print("Player: "+player+"\n"+"\t"+"-> Address: "+strAddress+"\n"+"\t"+"-> Country: "+country);
        if (strAddress.equals("127.0.0.1")) {
            return;
        }
        if (WhiteList.isUUIDWhiteListed(e.getUniqueId())) {
            return;
        }

        if (Main.getInstance().isUpdating()) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatAPI.translateColor(Main.getInstance().getConfiguration().getUpdatingKickMessage()));
            return;
        }
        if (limitReached(strAddress)) {
            Debug.print("\t"+"&cBlocked!");
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatAPI.translateColor(Main.getInstance().getConfiguration().getMaxIpKickMessage()));
            return;
        }
        if (BlackList.isIpBlackListed(strAddress)) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Bots are not allowed!");
            return;
        }
        if (!isInAllowedCountry(strAddress, country)) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatAPI.translateColor(Main.getInstance().getConfiguration().getWhiteListedCountriesKickMessage()));
            return;
        }
        if (ProxyCheck.isProxy("http://api.stopforumspam.org/api?ip=" + strAddress, "<appears>yes</appears>")) {
            Debug.print("Bot found!: "+strAddress);
            BlackList.addIp(strAddress);
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Bots are not allowed!");
            return;
        }
        if (ProxyCheck.isProxy("http://legacy.iphub.info/api.php?ip="+ strAddress +"&showtype=4", "\"proxy\":1")) {
            Debug.print("Bot found!: "+strAddress);
            BlackList.addIp(strAddress);
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Bots are not allowed!");
            return;
        }

        addresses.add(strAddress);
    }

    private boolean limitReached(String address) {
        int limit = Main.getInstance().getConfiguration().getMaxIpLimit();
        int i = 0;
        for (String strAddress : addresses) {
            if (strAddress.equals(address)) {
                i++;
            }
        }
        if (i >= Main.getInstance().getConfiguration().getMinimumAttackers()) {
            Main.getInstance().setAttacked(true);
        }
        return i > limit;
    }
    private boolean isInAllowedCountry(String address, String country) {
        if (country == null) return false;
        for (String c : Main.getInstance().getConfiguration().getWhiteListedCountries()) {
            if (c.equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

}