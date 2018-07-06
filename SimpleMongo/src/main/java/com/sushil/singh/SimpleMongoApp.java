package com.sushil.singh;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class SimpleMongoApp {

	public static void main(String args[]) {
		
		try {
			
			// Connecting Database
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			
			// Creating Database
			MongoDatabase db = mongoClient.getDatabase("mydb");
			
			// Creating Collection
			MongoCollection<Document> collection = db.getCollection("mycollection");
			
			// Creating Document
			Document doc = new Document("name", "Raju");
			doc.append("Phone", "1212211212");
			
			// Inserting Data
			collection.insertOne(doc);
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
