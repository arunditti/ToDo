package com.arunditti.android.todo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by arunditti on 3/27/18.
 */

public class TaskCursorAdapter extends RecyclerView.Adapter<TaskCursorAdapter.TaskViewHolder> {

    //Class variables for the cursor that holds context
    private Context mContext;

    //Constructor for the TaskCursorAdapter that initializes the context
    public TaskCursorAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Inner class for creating ViewHolders
    class TaskViewHolder extends RecyclerView.ViewHolder {
        //Class variables for the task description and task priority
        TextView textViewTaskDescription;
        TextView textViewTaskPriority;

        //Constructor for the TaskViewHolder
        public TaskViewHolder(View itemView) {
            super(itemView);

            textViewTaskDescription = (TextView) itemView.findViewById(R.id.tv_description);
            textViewTaskPriority = (TextView) itemView.findViewById(R.id.tv_priority);
        }
    }
}
