package com.will.helpers;

import com.will.Models.Stat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseManager {

    private Connection conn;
    private Statement statement;

    public DatabaseManager(Connection conn) throws SQLException {
        this.conn = conn;
        this.statement = conn.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    public void dropStatTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS stats;");
    }

    public void createStatTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER);");
    }

    public void insertIntoStatTable(String name, int wins, int losses) throws SQLException {
        //Vulnerable to SQL injections
        String sqlString = String.format("INSERT INTO stats (name, wins, losses) VALUES ('%s', %d, %d);", name, wins, losses);
        statement.executeUpdate(sqlString);
    }


    public ArrayList<Stat> getStats() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM stats");

        ArrayList<Stat> retVal = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int wins = rs.getInt("wins");
            int losses = rs.getInt("losses");

            Stat tempStat = new Stat(id, name, wins, losses);
            retVal.add(tempStat);
        }
        return retVal;
    }
}
