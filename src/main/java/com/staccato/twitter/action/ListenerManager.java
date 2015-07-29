package com.staccato.twitter.action;

import com.mongodb.BasicDBObject;
import com.staccato.twitter.mongo.MongoDAO;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class ListenerManager {

	private StatusListener listener;
	
	public ListenerManager(){
		final MongoDAO mongo = new MongoDAO();
		
		listener = new StatusListener(){

			public void onException(Exception ex) {
				// TODO Auto-generated method stub
			}

			public void onStatus(Status status) {
				BasicDBObject doc = new BasicDBObject();
				doc.put("date", status.getCreatedAt().toString());
				doc.put("followers", status.getUser().getFollowersCount());
				doc.put("text", status.getText());
				
				mongo.saveDocument(doc);
				
				System.out.println(status.getCreatedAt().toString() + " " + status.getText());
			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
				
			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}

			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}

			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	public StatusListener getListener(){
		return listener;
	}
}
