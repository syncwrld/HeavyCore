package com.redeheavy.heavycore.commons.database.abstraction;

public interface Connector {

    abstract boolean connect();

    abstract void disconnect();

    abstract boolean isConnected();

}
