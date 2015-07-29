package com.staccato.twitter.main;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.staccato.twitter.action.TwitterManager;
import com.staccato.twitter.mongo.MongoDAO;

public class Main {
	
	public static void main(String[] args){
		//listenTwitter();
		getDB();
		//cleanDB();
		
	}
	
	public static void listenTwitter(){
		TwitterManager twitter = new TwitterManager();
		twitter.prepare();
		twitter.start();
	}
	
	public static void getDB(){
		MongoDAO mongo = new MongoDAO();
		DBCursor cursor = mongo.getDefaultCollection().find();
		for (DBObject obj : cursor) {
			System.out.println("date: " + obj.get("date"));
			System.out.println("Followers: " + obj.get("followers"));
			System.out.println("text: " + obj.get("text"));
		}
	}
	
	public static void cleanDB(){
		MongoDAO mongo = new MongoDAO();
		mongo.getDatabase().dropDatabase();
	}
}
