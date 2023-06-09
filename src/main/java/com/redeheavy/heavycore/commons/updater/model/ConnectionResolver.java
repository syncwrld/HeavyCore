package com.redeheavy.heavycore.commons.updater.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionResolver {
    private final String url;
    private static String gh_token;
    private String response;

    public ConnectionResolver(String url) {
        this.url = url;
         gh_token = null;
    }

    public ConnectionResolver(String url, String github_token) {
        this.url = url;
        gh_token = github_token;
    }
    public void connect() {
        StringBuilder responseContent = new StringBuilder();

        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (gh_token != null)
                connection.setRequestProperty("Authorization", String.format("Bearer %s", gh_token));

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) responseContent.append(line);
            reader.close();
        } catch (IOException exception) {
            return;
        }

        response = responseContent.toString();
    }

    public String getResponse() {
        return response;
    }

}
