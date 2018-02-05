package com.mds.android.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mds.android.common.app.MdsApplication;


/**
 * Created by vivekjha on 20/01/16.
 */
public class SqliteDbHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;

    public final static String DATABASE_NAME = "seb.db";

    private MdsApplication application;

    public static class SearchHistory {

        public static final String TABLE_NAME = "SearchHistory";

        public class Columns {

            public static final String ID = "_id";
            public static final String MED_ID = "medId";
            public static final String MED_NAME = "medName";
            public static final String MED_MANUFACTURER = "medManufacturer";
            public static final String TYPE = "medType";
            public static final String PRICE = "medPrice";
            public static final String INFO = "medInfo";
            public static final String IN_BAG = "wasInBag";
            public static final String TIMESTAMP = "timestamp";

        }
    }
    public SqliteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SearchHistory.TABLE_NAME + " ("
                + SearchHistory.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SearchHistory.Columns.MED_ID + " TEXT NOT NULL,"
                + SearchHistory.Columns.MED_NAME + " TEXT NOT NULL,"
                + SearchHistory.Columns.MED_MANUFACTURER + " TEXT,"
                + SearchHistory.Columns.TYPE + " TEXT,"
                + SearchHistory.Columns.PRICE + " TEXT,"
                + SearchHistory.Columns.INFO + " TEXT,"
                + SearchHistory.Columns.IN_BAG + " INTEGER,"
                + SearchHistory.Columns.TIMESTAMP + " INTEGER NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SearchHistory.TABLE_NAME);
        onCreate(db);
    }
}
