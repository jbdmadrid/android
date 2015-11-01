package io.keepcoding.twlocator.model.mapper;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.twlocator.model.Twitter;
import twitter4j.HashtagEntity;
import twitter4j.ResponseList;
import twitter4j.Status;

public class TweetMapper {
    public static List<Twitter> mapTwitter(final ResponseList<Status> statuses){
        if(statuses == null){
            return null;
        }
        List<Twitter> tweetList = new ArrayList<Twitter>(statuses.size());
        for (Status status:statuses) {
            Twitter tweet = new Twitter(status.getUser().getName(),status.getText());
            double latitude = status.getGeoLocation().getLatitude();
            double longitude = status.getGeoLocation().getLongitude();
            String photo = status.getUser().getMiniProfileImageURL();
            HashtagEntity[] hashtag = status.getHashtagEntities();

            tweet.setLongitude(longitude);
            tweet.setLatitude(latitude);
            tweet.setPhoto(photo);
            tweet.setHagtag(String.valueOf(hashtag));



            tweetList.add(tweet);
            
        }

        return tweetList;
    }
}
