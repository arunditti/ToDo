package com.arunditti.android.todo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.arunditti.android.todo.data.TaskContract.TaskEntry;

import java.util.SimpleTimeZone;

/**
 * Created by arunditti on 3/28/18.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TaskDbHelper.class.getSimpleName();

    //Name of the database
    private static final String DATABASE_NAME = "tasksDb.db";

    //Database version which needs to be changed if you change schema
    private static final int VERSION = 3;

    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //This is called when tasks database is created first time
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create tasks table
        final String CREATE_TABLE = "CREATE TABLE "  + TaskEntry.TABLE_NAME + " (" +
                TaskEntry._ID                + " INTEGER PRIMARY KEY, " +
                TaskEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                TaskEntry.COLUMN_PRIORITY    + " INTEGER NOT NULL, " +
                TaskEntry.COLUMN_DONE   + " BOOLEAN NOT NULL);";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    //This method discards the old tableof data and calls onCreate to recreate a new one and it only occurs whrn the version number of the databse is incremented
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
