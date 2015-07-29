package com.staccato.twitter.action;

import java.io.FileInputStream;
import java.util.Properties;

import twitter4j.conf.ConfigurationBuilder;

public final class PropertiesManager {
	
	private static String consumerKey;
	private static String consumerSecret;
	private static String accessToken;
	private static String secretToken;
	private static String defaultDB;
	private static String defaultColl;
	private static String[] filter;
	
	static{
		Properties prop = new Properties();
		String preFilter;
		try{
			prop.load(new FileInputStream("src/main/resources/twitter.properties"));
			
			consumerKey    = prop.getProperty("oauth.consumerKey");
			consumerSecret = prop.getProperty("oauth.consumerSecret");
			accessToken    = prop.getProperty("oauth.accessToken");
			secretToken    = prop.getProperty("oauth.accessTokenSecret");
			defaultDB      = prop.getProperty("default.database");
			defaultColl    = prop.getProperty("default.collection");
			preFilter 	   = prop.getProperty("keywords");
			
			filter = preFilter.split(",");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public ConfigurationBuilder getConfiguration(){
		ConfigurationBuilder config = new ConfigurationBuilder();
		
		config.setOAuthConsumerKey(consumerKey);
		config.setOAuthConsumerSecret(consumerSecret);
		config.setOAuthAccessToken(accessToken);
		config.setOAuthAccessTokenSecret(secretToken);
		
		return config;
	}
	
	public String[] getFilter(){
		return filter;
	}
	
	public static String getDefaultDB(){
		return defaultDB;
	}
	
	public static String getDefaultCollection(){
		return defaultColl;
	}
}
