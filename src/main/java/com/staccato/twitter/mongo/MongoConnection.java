package com.staccato.twitter.mongo;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public final class MongoConnection {

	private static MongoClient client;

	static {
		try {
			client = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static MongoClient getClient(){
		return client;
	}
}
