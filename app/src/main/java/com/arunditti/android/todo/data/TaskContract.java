package com.arunditti.android.todo.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by arunditti on 3/28/18.
 */

public class TaskContract {

    // Add ContentProvider constants to Contract
    //Authority which is how our code knows which ContentProvider to access
    public static final String AUTHORITY = "com.arunditti.android.todo";

    //The base Content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    //Define the path to accessing data in this contract
    public static final String PATH_TASKS = "tasks";

    //TaskEntry is the inner class that defines the contents of the task table
    public static final class TaskEntry implements BaseColumns {

        //TaskEntry URI = base content uri + path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

        //Task table and column names
        public static final String TABLE_NAME = "tasks";

        //Since TaskEntry implements the interface "BaseColumns", it has automatically produced _ID column in addition to the following
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_DONE = "done";

    }
}
