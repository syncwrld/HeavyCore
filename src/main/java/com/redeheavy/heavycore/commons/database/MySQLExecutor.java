package com.redeheavy.heavycore.commons.database;

import com.redeheavy.heavycore.commons.database.enums.Signal;
import com.redeheavy.heavycore.commons.database.objects.MySQLTable;
import com.redeheavy.heavycore.exceptions.SQLNotConnectedException;
import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public class MySQLExecutor {

    static Connection connection;

    @SneakyThrows
    public MySQLExecutor() {

        if (MySQLConnector.getConnection() == null) {
            throw new SQLNotConnectedException();
        }

        MySQLExecutor.connection = MySQLConnector.getConnection();
    }

    public void createTable(MySQLTable table) {
        val query = MySQLTable.getQuery(table);
    }

    void execute() {
        System.out.println(select(Double.class, "coins", "rankup.coins", "player", Signal.EQUALS, "syncwrld"));
    }

    @SneakyThrows
    public <T> CompletableFuture<T> select(Class<T> resultType, String what, String in_table, String where_this, Signal signal, String thing) {
        val query = "SELECT " + what + " FROM " + in_table + " WHERE " + where_this + " " + Signal.getString(signal) + " ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, thing);

        return CompletableFuture.supplyAsync(() -> {
            ResultSet rs = null;
            try {
                rs = statement.executeQuery();
                if (rs.next()) {
                    return rs.getObject(1, resultType);
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


}
