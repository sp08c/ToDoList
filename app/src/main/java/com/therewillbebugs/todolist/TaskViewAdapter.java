package com.therewillbebugs.todolist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//Source:
//https://developer.android.com/training/material/lists-cards.html
//http://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465

//An adapter class to hold the recycler view components
public class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.ViewHolder>{
    private ArrayList<Task> taskList;

    //Reference to the views
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cv;
        public TextView description, priority;
        public ViewHolder(View view){
            super(view);
            cv = (CardView)view.findViewById(R.id.taskview_cardview);
            description = (TextView)view.findViewById(R.id.textview_task_description);
            priority = (TextView)view.findViewById(R.id.textview_task_priority);
        }
    }

    public TaskViewAdapter(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    @Override
    public TaskViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskview_item,parent,false);
        TaskViewAdapter.ViewHolder  vh = new TaskViewAdapter.ViewHolder (v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskViewAdapter.ViewHolder  holder, int position){
        holder.description.setText(taskList.get(position).getDescription());
        holder.priority.setText(taskList.get(position).getPriorityLevel().toString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return taskList.size();
    }

    public void swap(ArrayList<Task> taskList){
        this.taskList.clear();
        this.taskList.addAll(taskList);
        notifyDataSetChanged();
    }
}