package com.arunditti.android.todo;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.arunditti.android.todo.data.TaskContract;

/**
 * Created by arunditti on 3/27/18.
 */

public class AddTaskActivity extends AppCompatActivity {

    //Declare a member variable for priority
    private int mPriority;
    private boolean mDone;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_task);

        //Initialize to highest priority by default (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
        mDone = false;
    }

    //onClickAddTask is called when ADD button is clicked and it retrieves user input and inserts that new task data into the underlying  database
    public void onClickAddTask(View view) {
        //Check if EditText is empty, if not retrieve the input and store in ContentValues. If EditText is empty, dont create an entry
        String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (input.length() == 0) {
            return;
        }

        //Insert new task data via a ContentResolver
        //Create a new ContentValue object
        ContentValues contentValues = new ContentValues();

        //Put the task description and priority into the Contentvalues
        contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, input);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, mPriority);
        contentValues.put(TaskContract.TaskEntry.COLUMN_DONE, mDone);

        //Insert the content values via a ContentResolver
        Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);

        //Display the Uri that is returned with a toast
        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
        finish();
    }

    //onPrioritySelected is called whenever a priority button is clicked
    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }


}
