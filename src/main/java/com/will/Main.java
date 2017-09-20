package com.will;

import com.will.Models.Stat;
import com.will.helpers.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            // CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER);

            DatabaseManager dbm = new DatabaseManager(conn);

            dbm.dropStatTable();
            dbm.createStatTable();
            dbm.insertIntoStatTable("joel", 100, 40);
            ArrayList<Stat> results = dbm.getStats();



        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("We encountered a problem talking to the database");
        }


        System.out.println("It works!");
    }
}

