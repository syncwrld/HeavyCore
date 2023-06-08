package com.redeheavy.heavycore.ambient;

import com.redeheavy.heavycore.commons.database.MySQLConnector;
import com.redeheavy.heavycore.commons.database.enums.Signal;
import lombok.val;

public class CoreDebug {

    public static void main(String[] args) {
        execute();
    }

    static void execute() {
        MySQLConnector connector = new MySQLConnector("localhost", "3306", "database", "root", "mypassword");
        connector.connect();


        val coins = connector.executor().select(Double.class, "fishQuantity", "fishing_players", "player", Signal.EQUALS, "syncwrld");
        System.out.println("O jogador sync tem " + coins + " peixes");
    }

}
