package com.redeheavy.heavycore.ambient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.redeheavy.heavycore.commons.database.mongodb.MongoDBConnector;
import com.redeheavy.heavycore.commons.database.mongodb.MongoExecutor;
import lombok.val;
import org.bson.Document;

import java.util.Arrays;
import java.util.logging.Logger;

public class CoreDebug {

    private static Logger logger = Logger.getLogger("[HeavyCore - Debug]");

    public static void main(String[] args) {
        execute();
    }

    static void execute() {

        /*
        Exemplo de uso -> Easy MongoDB by HeavyCore [github.com/syncwrld]
         */
        String connectionURL = "mongodb://atlas-sql-633628361a72416dfa84108e-kkzse.a.query.mongodb.net/myVirtualDatabase?ssl=true&authSource=admin";

        /*
        Criando uma nova instância do conector
         @param connection_url - a url usada para conectar-se ao mongodb
         */
        MongoDBConnector connector = new MongoDBConnector(connectionURL);

        if (connector.connect()) {
            logger.info("Conectado ao MongoDB.");
        } else {
            logger.severe("Não foi possível conectar-se ao MongoDB.");
        }

        MongoDatabase database = connector.executor().getDatabase("myDatabase");
        MongoCollection<Document> collection = connector.executor().getCollection(database, "myCollection");

        Document user_1 = new Document("user_1", "syncwrld");
        Document user_2 = new Document("user_2", "vaaaaz");
        Document user_3 = new Document("user_3", "nexy");

        // Inserindo 1x item por vez
        connector.executor().insertInto(collection, user_1);
        // Inserindo mais de um item por vez
        connector.executor().insertManyInto(collection, Arrays.asList(user_1, user_2, user_3));

        MongoExecutor executor = connector.executor();

        for (Document document : executor.getDatabasesList()) {
            document.forEach((key, value) -> {
                String fvalue = (String) value;
                logger.info("Documento encontrado: " + key + " -> " + fvalue);
            });
        }
    }

}
