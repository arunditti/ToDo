package com.arunditti.android.todo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TaskCursorAdapter mAdapter;
    RecyclerView taskRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Fab to open AddTaskActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new intent to start the AddTaskActivity
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        //Set the RecyclerView to its corresponding view
        taskRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_list) ;

        //Set layout for the RecyclerView
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Create an adapter for the cursor to display the data
        mAdapter = new TaskCursorAdapter(this);

        //Link the adapter to the RecyclerView
        taskRecyclerView.setAdapter(mAdapter);

    }

}
