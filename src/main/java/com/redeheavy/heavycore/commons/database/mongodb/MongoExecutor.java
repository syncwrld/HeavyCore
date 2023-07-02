package com.redeheavy.heavycore.commons.database.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MongoExecutor {


    private final MongoClient mongoClient;
    public MongoExecutor(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public ListDatabasesIterable<Document> getDatabasesList() {
        return mongoClient.listDatabases();
    }

    public void insertInto(MongoCollection<Document> collection, Document document) {
        collection.insertOne(document);
    }

    public void insertManyInto(MongoCollection<Document> collection, List<Document> documents) {
        collection.insertMany(documents);
    }

    public MongoDatabase getDatabase(String database) {
        return mongoClient.getDatabase(database);
    }

    public MongoCollection<Document> getCollection(MongoDatabase mongoDatabase, String collection) {
        return mongoDatabase.getCollection(collection);
    }

}
