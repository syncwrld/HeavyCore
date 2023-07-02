package com.redeheavy.heavycore.commons.database.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.redeheavy.heavycore.commons.database.abstraction.Connector;
import org.bson.Document;

public class MongoDBConnector implements Connector {

    private final String connectionURL;

    private static MongoClient mongoClient;
    private static MongoClientURI mongoClientURI;

    public MongoDBConnector(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    @Override
    public boolean connect() {
        try {
            mongoClientURI = new MongoClientURI(connectionURL);
            mongoClient = new MongoClient(mongoClientURI);

            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public void disconnect() {
        if (mongoClient != null && mongoClientURI != null)
            mongoClient.close();
    }

    @Override
    public boolean isConnected() {
        return mongoClient != null && mongoClientURI != null;
    }

    public MongoClient getClient() {
        return mongoClient;
    }

    public static MongoClientURI getMongoClientURI() {
        return mongoClientURI;
    }

    public MongoExecutor executor() {
        return new MongoExecutor(mongoClient);
    }

}
