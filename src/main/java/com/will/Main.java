package com.will;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            // CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS stats;");
            statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER);");
            statement.executeUpdate("INSERT INTO stats (name, wins, losses) VALUES ('joel', '10', '3');");
            ResultSet rs = statement.executeQuery("SELECT * FROM stats");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");

                System.out.printf("%s: %s %s %s \n", id, name, wins, losses);
            }

        } catch (SQLException ex) {
            System.out.println("We encountered a problem talking to the database");
        }


        System.out.println("It works!");
    }
}

