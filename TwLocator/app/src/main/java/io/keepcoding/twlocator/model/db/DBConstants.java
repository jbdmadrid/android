package io.keepcoding.twlocator.model.db;

public class DBConstants {
    public static final String DROP_DATABASE = "";

    public static final String TABLE_TWITTER = "TWITTER";


    public static final String KEY_TWITTER_ID = "_id";
    public static final String KEY_TWITTER_NAME = "name";
    public static final String KEY_TWITTER_HAGTAG = "hagtag";
    public static final String KEY_TWITTER_TWEETS = "tweets";
    public static final String KEY_TWITTER_PHOTO_URL = "photoUrl";
    public static final String KEY_TWITTER_LATITUDE = "latitude";
    public static final String KEY_TWITTER_LONGITUDE = "longitude";
    public static final String KEY_TWITTER_HAS_COORDINATES = "hasCoordinates";


    // scripts creación

    public static final String SQL_CREATE_TWITTER_TABLE =
            "create table "
                    +TABLE_TWITTER+"( "+KEY_TWITTER_ID
                    +" integer primary  key autoincrement, "
                    + KEY_TWITTER_NAME + " text not null,"
                    +KEY_TWITTER_HAGTAG + " text,"
                    +KEY_TWITTER_TWEETS + " text,"
                    +KEY_TWITTER_PHOTO_URL + " text,"
                    +KEY_TWITTER_LATITUDE + " real,"
                    +KEY_TWITTER_LONGITUDE + " real,"
                    +KEY_TWITTER_HAS_COORDINATES + " INTEGER"
                    +");";

    public static final String[] CREATE_DATABASE = {
            SQL_CREATE_TWITTER_TABLE
    };
}
