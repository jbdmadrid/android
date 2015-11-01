package io.keepcoding.twlocator.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

import static io.keepcoding.twlocator.model.db.DBConstants.CREATE_DATABASE;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "twitter.sqlite";
    public static final int DATABASE_VERSION = 3;
    public static final long INVALID_ID = -1;


    private static DBHelper sharedInstance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sharedInstance == null) {
            sharedInstance = new DBHelper(context.getApplicationContext());
        }
        return sharedInstance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        // called everytime a DB connection is opened. We activate foreing keys to have ON_CASCADE deletion
        db.execSQL("PRAGMA foreign_keys = ON");

        // if API LEVEL > 16, use this
        // db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createDb(sqLiteDatabase);
    }

    private void createDb(SQLiteDatabase db) {
        for (String sql: CREATE_DATABASE) {
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // upgrades for version 1->2
                Log.i("DBHelper", "Migrating from V1 to V2");

            case 2:
                // upgrades for version 2->3

            case 3:
                // upgrades for version 3->4
        }
    }


    public static SQLiteDatabase getDb(DBHelper dbHelper) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }
        return db;
    }

    // convenience methods to convert types Java <-->SQLite

    public static int convertBooleanToInt(boolean b) {
        return b ? 1 : 0;
    }

    public static boolean convertIntToBoolean(int b) {
        return b == 0 ? false : true;
    }


    public static Long convertDateToLong(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }

    public static Date convertLongToDate(Long dateAsLong) {
        if (dateAsLong == null) {
            return null;
        }
        return new Date(dateAsLong);
    }
}
