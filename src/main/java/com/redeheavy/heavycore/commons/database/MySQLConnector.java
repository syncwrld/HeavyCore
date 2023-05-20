package com.redeheavy.heavycore.commons.database;

import com.redeheavy.heavycore.commons.Basement;
import com.redeheavy.heavycore.commons.database.objects.DatabaseCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

    static DatabaseCredentials credentials;
    static Connection connection;
    private static String host;
    private static String port;
    private static String database;
    private static String username;
    private static String password;

    public MySQLConnector(DatabaseCredentials credentials) {
        MySQLConnector.credentials = credentials;
        host = credentials.getHost();
        port = credentials.getPort();
        database = credentials.getDatabase();
        username = credentials.getUsername();
        password = credentials.getPassword();
    }

    public MySQLConnector(String host, String port, String database, String username, String password) {
        MySQLConnector.host = host;
        MySQLConnector.port = port;
        MySQLConnector.database = database;
        MySQLConnector.username = username;
        MySQLConnector.password = password;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
    }

    public boolean connect() {
        try {
            connection = getConnection();
            return true;
        } catch (SQLException exception) {
            Basement.getLogger().error("Houve um erro ao conectar-se ao MySQL: " + exception.getMessage());
            return false;
        }
    }

    public void disconnect() throws SQLException {
        try {
            if (isConnected()) connection.close();
        } catch (SQLException exception) {
            Basement.getLogger().error("Houve um erro ao desconectar-se do MySQL: " + exception.getMessage());

        }
    }

    protected static boolean isConnected() {
        return connection != null;
    }

    public MySQLExecutor executor() {
        return new MySQLExecutor();
    }

}
