package com.mds.android.database.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by vivekjha on 20/01/16.
 */
public class SqliteDataSource {

    private static final String TAG = SqliteDataSource.class.getName();
    private SQLiteDatabase database;
    private SqliteDbHelper dbHelper;

    public SqliteDataSource(Context context) {
        dbHelper = new SqliteDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private boolean convertInteger(Integer value) {
        return value!=0;
    }

    /**
     * Method to convert boolean into integer
     * @param value boolean value
     * @return integer
     */
    private Integer convertBoolean(boolean value) {
        return (value) ? 1 : 0;
    }


}
