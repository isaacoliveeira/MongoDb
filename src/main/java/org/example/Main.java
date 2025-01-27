package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {

        ConnectionString connectionString = new ConnectionString("mongodb+srv://isaacoliveeira:Arievilo_18@isaaccluster.q0ck5.mongodb.net/?retryWrites=true&w=majority&appName=IsaacCluster");
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");

            
            Document newMovie = new Document("title", "New Movie")
                    .append("year", 2025)
                    .append("genre", "Action")
                    .append("rating", 8.5);
            collection.insertOne(newMovie);
            System.out.println("Documento inserido: " + newMovie.toJson());

            Document movie = collection.find(eq("title", "New Movie")).first();
            if (movie != null) {
                System.out.println("Documento encontrado: " + movie.toJson());
            } else {
                System.out.println("Documento não encontrado.");
            }

            collection.updateOne(eq("title", "New Movie"), new Document("$set", new Document("rating", 9.0)));
            System.out.println("Documento atualizado com novo rating.");

            Document updatedMovie = collection.find(eq("title", "New Movie")).first();
            if (updatedMovie != null) {
                System.out.println("Documento atualizado: " + updatedMovie.toJson());
            }

            collection.deleteOne(eq("title", "New Movie"));
            System.out.println("Documento removido.");

            Document deletedMovie = collection.find(eq("title", "New Movie")).first();
            if (deletedMovie == null) {
                System.out.println("Confirmação: Documento removido com sucesso.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar ou executar operações no MongoDB: " + e.getMessage());
        }
    }
}
