package com.redeheavy.heavycore.exceptions;

import java.sql.SQLException;

public class SQLNotConnectedException extends SQLException {

    public SQLNotConnectedException() {
        super("Tried to perform an action that needs a SQL conncetion, but no instances are connected.");
    }

    public SQLNotConnectedException(String message) {
        super("A fatal SQLNotConnectedException has ocurred: " + message);
    }

}
