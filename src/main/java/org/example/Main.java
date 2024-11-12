package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {

        ConnectionString connectionString = new ConnectionString("mongodb+srv://isaacoliveeira:Arievilo_18@isaaccluster.q0ck5.mongodb.net/?retryWrites=true&w=majority&appName=IsaacCluster");
        MongoClient var = MongoClients.create(connectionString);
        MongoDatabase database = var.getDatabase("sample_mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        Document doc = collection.find(eq("title", "Traffic in Souls")).first();
        if (doc != null) {
            System.out.println(doc.toJson());
        } else {
            System.out.println("Não é possível fazer a consulta");
        }
    }
}