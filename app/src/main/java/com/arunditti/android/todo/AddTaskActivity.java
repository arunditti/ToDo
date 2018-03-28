package com.arunditti.android.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by arunditti on 3/27/18.
 */

public class AddTaskActivity extends AppCompatActivity {

    //Declare a member variable for priority
    private int mPriority;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_task);

        //Initialize to highest priority by default (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
    }

    //onClickAddTask is called when ADD button is clicked and it retrieves user input and inserts that new task data into the underlying  database
    public void onClickAddTask(View view) {

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
