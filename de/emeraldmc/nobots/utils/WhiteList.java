package de.emeraldmc.nobots.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class WhiteList {
    public static void addUUID(UUID uuid) {
        if (WhiteList.isUUIDWhiteListed(uuid)) return;
        try {
            PreparedStatement preparedStatement = MySQL.getInstance().prepareStatement("INSERT INTO NoBots_WhiteList (UUID) VALUES (?)");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.executeUpdate();
            Debug.print("Added "+uuid+" to the WhiteList");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeUUID(UUID uuid) {
        if (!WhiteList.isUUIDWhiteListed(uuid)) return;
        try {
            PreparedStatement preparedStatement = MySQL.getInstance().prepareStatement("DELETE FROM NoBots_WhiteList WHERE UUID = ?");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.executeUpdate();
            Debug.print("Removed "+uuid+" from the WhiteList");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isUUIDWhiteListed(UUID uuid) {
        try {
            PreparedStatement preparedStatement = MySQL.getInstance().prepareStatement("SELECT * FROM NoBots_WhiteList WHERE UUID = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
