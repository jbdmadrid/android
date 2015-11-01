package io.keepcoding.twlocator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Twitter {
    private long id;
    private String name;
    private String hagtag;
    private String tweets;
    private String photo;
    private double longitude;
    private double latitude;
    private boolean hasCoordinates;

    private List<Twitter> twitterList;

    public Twitter(String name, String tweets) {
        this.name = name;
        this.tweets = tweets;
    }



    public List<Twitter> getTwitterList() {
        return twitterList;
    }

    public void setTwitterList(List<Twitter> twitterList) {
        this.twitterList = twitterList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHagtag() {
        return hagtag;
    }

    public void setHagtag(String hagtag) {
        this.hagtag = hagtag;
    }

    public String getTweets() {
        return tweets;
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isHasCoordinates() {
        return hasCoordinates;
    }

    public void setHasCoordinates(boolean hasCoordinates) {
        this.hasCoordinates = hasCoordinates;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.hagtag);
        dest.writeString(this.tweets);
        dest.writeString(this.photo);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeList(this.twitterList);

    }

    protected Twitter(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.hagtag= in.readString();
        this.tweets = in.readString();
        this.photo = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();

        this.twitterList = new ArrayList<Twitter>();
        in.readList(this.twitterList, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Twitter> CREATOR = new Parcelable.Creator<Twitter>() {
        public Twitter createFromParcel(Parcel source) {
            return new Twitter(source);
        }

        public Twitter[] newArray(int size) {
            return new Twitter[size];
        }
    };

}
