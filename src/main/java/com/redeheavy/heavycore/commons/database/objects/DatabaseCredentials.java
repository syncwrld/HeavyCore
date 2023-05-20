package com.redeheavy.heavycore.commons.database.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class DatabaseCredentials {

    @NonNull
    private String host;
    @NonNull
    private String port;
    @NonNull
    private String database;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private boolean autoReconnect;

}
