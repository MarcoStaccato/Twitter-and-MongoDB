package com.staccato.twitter.action;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterManager {

	PropertiesManager propManager;
	ListenerManager listenManager;
	TwitterStream stream;
	FilterQuery filter;
	
	public TwitterManager(){
		propManager = new PropertiesManager();
		listenManager = new ListenerManager();
		filter = new FilterQuery();
		stream = new TwitterStreamFactory(propManager.getConfiguration().build()).getInstance();
	}
	
	
	public void prepare(){
		filter.track(propManager.getFilter());
		stream.addListener(listenManager.getListener());
	}
	
	public void start(){
		stream.filter(filter);
	}
	
	public void stop(){
		stream.shutdown();
	}
}
