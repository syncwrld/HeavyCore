package com.redeheavy.heavycore.ambient;

import com.redeheavy.heavycore.commons.database.MySQLConnector;
import com.redeheavy.heavycore.commons.database.enums.Signal;
import lombok.val;

public class CoreDebug {

    public static void main(String[] args) {
        execute();
    }

    static void execute() {
        MySQLConnector connector = new MySQLConnector("mysql.meteorcloud.com.br", "3306", "s01_testes", "u192_KfmQxZA0hX", "UiiWA4U.m46g2t^aAx+6RZMs");
        connector.connect();


        val coins = connector.executor().select(Double.class, "fishQuantity", "fishing_players", "player", Signal.EQUALS, "syncwrld");
        System.out.println("O jogador sync tem " + coins + " peixes");
    }

}
