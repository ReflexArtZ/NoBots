package de.emeraldmc.nobots.utils;

import de.emeraldmc.nobots.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class ProxyCheck {
    public static boolean isProxy(String web, String result) {
        try {
            URL url = new URL(web);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inLine;
            String lineSum = new String();
            while ((inLine = in.readLine()) != null) {
                lineSum += inLine;
            }
            in.close();

            return lineSum.contains(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getCountry(String address) {
        try {
            URL url = new URL("http://geoip.nekudo.com/api/"+address);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inLine;
            String lineSum = new String();
            while ((inLine = in.readLine()) != null) {
                lineSum += inLine;
            }
            in.close();
            if (lineSum.contains("error")) {
                return "Unavailable";
            }
            String[] arr = lineSum.split(",");
            String c = arr[1];
            c = c.replaceAll(Pattern.quote("{"), "");
            c = c.replaceAll(String.valueOf('"'), "");
            c = c.replaceAll("country:name:", "");

            return c;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
