package com.staccato.twitter.mongo;

import java.util.ArrayList;
import java.util.List;

import com.staccato.twitter.action.PropertiesManager;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDAO {
	
	private DB database;
	private MongoClient client;
	private DBCollection collection;

	public MongoDAO(){
		client = MongoConnection.getClient();
		database = client.getDB(PropertiesManager.getDefaultDB());
		collection = database.getCollection(PropertiesManager.getDefaultCollection());
	}
	
	public List<String> getAllDatabaseNames(){
		return client.getDatabaseNames();
	}
	
	public void setDatabase(String dbName){
		database = client.getDB(dbName);
	}
	
	public DB getDatabase(){
		return database;
	}
	
	public DBCollection getCollection(String dbName, String tableName){
		return client.getDB(dbName).getCollection(tableName);
	}
	
	public DBCollection getDefaultCollection(){
		return client.getDB(PropertiesManager.getDefaultDB()).getCollection(PropertiesManager.getDefaultCollection());
	}
	
	public void setCollection(String collectionName){
		collection = database.getCollection(collectionName);
	}
	
	public void saveDocument(BasicDBObject document){
		 collection.insert(document);
	}
	
	public void update(String field, String oldValue, String updateValue){
		
		BasicDBObject oldQuery = new BasicDBObject();
		oldQuery.put(field, oldValue);
	 
		BasicDBObject newQuery = new BasicDBObject();
		newQuery.put(field, updateValue);
	 
		BasicDBObject update = new BasicDBObject();
		update.put("$set", newQuery);
		
		collection.update(oldQuery, update);
	}
	
	public List<DBObject> find(String field, String value){
		List<DBObject> result = new ArrayList<DBObject>();
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(field, value);
		
		DBCursor cursor = collection.find(searchQuery);
		
		while (cursor.hasNext()) {
			result.add(cursor.next());
		}
		
		return result;
	}
	
	public void delete(String field, String value){
		BasicDBObject query = new BasicDBObject();
		
		query.put(field, value);
		
		collection.remove(query);
	}
	
	
}
