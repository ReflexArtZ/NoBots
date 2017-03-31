package de.emeraldmc.nobots.utils;

import de.emeraldmc.nobots.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private static Connection connection = null;

    private static String host = Main.getInstance().getConfiguration().getDbHost();

    private static int port = Main.getInstance().getConfiguration().getDbPort();

    private static String database = Main.getInstance().getConfiguration().getDatabase();

    private static String user = Main.getInstance().getConfiguration().getDbUser();

    private static String password = Main.getInstance().getConfiguration().getDbPassword();

    private MySQL() {
        try {
            // load database drivers for ODBC interfaces
            Class.forName("com.mysql.jdbc.Driver");

            // connect to ODBC database
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?" + "user=" + user + "&" + "password=" + password);
        } catch (ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage("§c[-] §7Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[-] §7Could not connect to database!");
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            Debug.print("Cloes MySQL connection");
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }

    public static void createTables() {
        Connection conn = getInstance();
        try {
            // ToDo: conn.prepareStatement("CREATE TABLE IF NOT EXISTS NoBots_WhiteList (UUID VARCHAR (36))").execute();
            conn.prepareStatement("CREATE  TABLE IF NOT EXISTS NoBots_BlackList (IP VARCHAR (15))").execute(); // ToDo: Add IPv6 support
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getInstance() {
        if (connection == null) {
            new MySQL();
        }
        return connection;
    }
}
