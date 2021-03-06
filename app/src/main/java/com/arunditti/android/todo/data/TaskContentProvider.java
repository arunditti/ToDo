package com.arunditti.android.todo.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.arunditti.android.todo.data.TaskContract;

import static com.arunditti.android.todo.data.TaskContract.TaskEntry.TABLE_NAME;

/**
 * Created by arunditti on 3/28/18.
 */

public class TaskContentProvider extends ContentProvider{

    //Define final integer constatnts for the directory of tasks and a single item
    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;

    //Declare static variable for the Urimatcher that we will construct
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final UriMatcher buildUriMatcher() {
        //Initialize UriMatcher with no matches
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.PATH_TASKS, TASKS);
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.PATH_TASKS + "/#", TASK_WITH_ID);

        return uriMatcher;
    }

    //member variable for the TaskDbHelper that's initialized in the onCreate() method
    private TaskDbHelper mTaskDbHelper;

    @Override
    public boolean onCreate() {
        //Initialize a TaskDbHelper on startup.
        Context context = getContext();
        mTaskDbHelper = new TaskDbHelper(context);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //Get access to task database read only for query
        final SQLiteDatabase db = mTaskDbHelper.getReadableDatabase();

        //Write Uri match code and set a variable to return a cursor
        int match = sUriMatcher.match(uri);
        Cursor returnCursor;

        //Query for the tasks directory and write a default case
        switch (match) {
            //Query for the task directory
            case TASKS:
                returnCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

                //Default exception
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
        //Set a notification URI on the cursor and return the cursor
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        //Return the desired cursor
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //Get access to task database to write new data into

        final SQLiteDatabase db = mTaskDbHelper.getWritableDatabase();

        //Write Uri matching code to identify the match for the task directory
        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case TASKS:
                //Insert new values into the database
                long id = db.insert(TABLE_NAME, null, contentValues);
                if(id > 0) {
                    returnUri = ContentUris.withAppendedId(TaskContract.TaskEntry.CONTENT_URI, id);
                } else {
                    throw new IllegalArgumentException("Failed to insert row into " + uri);
                }
                break;

                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        //Notify the resolver if the uri has been changed and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);

        //Return constructed uri
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        //Get access to the database and write URI matching code to recognize a single item
        final SQLiteDatabase db = mTaskDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        //Keep track of the number of deleted tasks
        int taskDeleted; //starts as 0

        //Write the code to delete a single row of data
        switch (match) {
            case TASK_WITH_ID:
                //Get task ID from the uri path
                String id = uri.getPathSegments().get(1);
                //Use selection and selectionArgs to filter for this ID
                taskDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});
                break;
                default:
                    throw new UnsupportedOperationException("Unknown uri: " +uri);
        }

        //Notify the resolver of the change and return the number of items deleted
        if(taskDeleted != 0) {
            //A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }
        //Return the number of task deleted
        return taskDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
