package io.keepcoding.twlocator.model.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import io.keepcoding.twlocator.model.Twitter;
import io.keepcoding.twlocator.model.db.DBConstants;
import io.keepcoding.twlocator.model.db.DBHelper;

public class TwitterDAO implements DAOPersistable<Twitter> {
    private final WeakReference<Context> context;

    public static final String[] allColumns = {
            DBConstants.KEY_TWITTER_ID,
            DBConstants.KEY_TWITTER_NAME,
            DBConstants.KEY_TWITTER_HAGTAG,
            DBConstants.KEY_TWITTER_TWEETS,
            DBConstants.KEY_TWITTER_PHOTO_URL,
            DBConstants.KEY_TWITTER_LATITUDE,
            DBConstants.KEY_TWITTER_LONGITUDE,
            DBConstants.KEY_TWITTER_HAS_COORDINATES
    };

    public TwitterDAO(WeakReference<Context> context) {
        this.context = context;
    }



    @Override
    public long insert(@NonNull Twitter data) {
        if (data == null) {
            return DBHelper.INVALID_ID;
        }

        final DBHelper dbHelper = DBHelper.getInstance(context.get());
        SQLiteDatabase db = DBHelper.getDb(dbHelper);

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;

        try {
            id = db.insert(DBConstants.TABLE_TWITTER, null, getContentValues(data));
            // data.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            dbHelper.close();
        }

        return id;
    }

    public static ContentValues getContentValues(Twitter twitter) {



        ContentValues content = new ContentValues();
        content.put(DBConstants.KEY_TWITTER_NAME, twitter.getName());
        content.put(DBConstants.KEY_TWITTER_HAGTAG, twitter.getHagtag());
        content.put(DBConstants.KEY_TWITTER_TWEETS, twitter.getTweets());

        content.put(DBConstants.KEY_TWITTER_PHOTO_URL, twitter.getPhoto());
        content.put(DBConstants.KEY_TWITTER_LATITUDE, String.format("%f", twitter.getLatitude()));
        content.put(DBConstants.KEY_TWITTER_LONGITUDE, String.format("%f", twitter.getLongitude()));

        Boolean hasCoord = twitter.isHasCoordinates();
        content.put(DBConstants.KEY_TWITTER_HAS_COORDINATES, String.format("%d", DBHelper.convertBooleanToInt(hasCoord)));

        return content;
    }
    @Override
    public void update(long id, @NonNull Twitter data) {
        if (data == null) {
            return;
        }

        final DBHelper dbHelper = DBHelper.getInstance(context.get());
        SQLiteDatabase db = DBHelper.getDb(dbHelper);

        db.beginTransaction();

        try {
            db.update(DBConstants.TABLE_TWITTER, getContentValues(data), DBConstants.KEY_TWITTER_ID + "=" + id, null);
            // db.update(TABLE_NOTEBOOK, getContentValues(data), KEY_NOTE_ID + "=?", new String[] { "" + id });
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            dbHelper.close();
        }

    }

    @Override
    public void delete(long id) {
        final DBHelper dbHelper = DBHelper.getInstance(context.get());
        SQLiteDatabase db = DBHelper.getDb(dbHelper);

        if (id == DBHelper.INVALID_ID) {
            db.delete(DBConstants.TABLE_TWITTER, null, null);
        } else {
            db.delete(DBConstants.TABLE_TWITTER, DBConstants.KEY_TWITTER_ID + "=?", new String[]{"" + id});
        }

        db.close();
    }

    @Override
    public void delete(@NonNull Twitter data) {
        if (data != null) {
            delete(data.getId());
        }
    }

    @Override
    public void deleteAll() {
        delete(DBHelper.INVALID_ID);
    }


    @Nullable
    @Override
    public Cursor queryCursor() {
        final DBHelper dbHelper = DBHelper.getInstance(context.get());
        SQLiteDatabase db = DBHelper.getDb(dbHelper);

        Cursor cursor = db.query(DBConstants.TABLE_TWITTER, allColumns, null, null, null, null, DBConstants.KEY_TWITTER_ID);
        return cursor;
    }

    @Override
    public Twitter query(long id) {
        Twitter twitter = null;

        final DBHelper dbHelper = DBHelper.getInstance(context.get());
        SQLiteDatabase db = DBHelper.getDb(dbHelper);

        final String whereClause = DBConstants.KEY_TWITTER_ID + "=" + id;
        Cursor cursor = db.query(DBConstants.TABLE_TWITTER, allColumns, whereClause, null, null, null, DBConstants.KEY_TWITTER_ID);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                twitter = twitterFromCursor(cursor);
            }
        }

        cursor.close();
        db.close();

        return twitter;


    }



    public static Twitter twitterFromCursor(Cursor cursor) {
        Twitter twitter;
        twitter = new Twitter(cursor.getString(cursor.getColumnIndex(DBConstants.KEY_TWITTER_NAME)),cursor.getString(cursor.getColumnIndex(DBConstants.KEY_TWITTER_TWEETS)));
        twitter.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_TWITTER_ID)));
        String hah = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_TWITTER_HAGTAG));
        twitter.setLongitude(cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_TWITTER_LONGITUDE)));
        twitter.setLatitude(cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_TWITTER_LATITUDE)));

        twitter.setHagtag(hah);

        return twitter;
    }

}
