package com.therewillbebugs.todolist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//Source:
//https://developer.android.com/training/material/lists-cards.html
//http://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465

//An adapter class to hold the recycler view components
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder>{
    //Callback interface for activity
    public interface OnCardViewAdapterClickListener{
        void onCardViewAdapterClicked(View v, int position);
        void onCardViewAdapterLongClicked(View v, int position);
    }

    //Class members
    private ArrayList<Task> taskList;
    private OnCardViewAdapterClickListener cbClickListener;

    //Reference to the views
    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{
        public CardView cv;
        public TextView description, priority, timedate;

        public interface OnCardViewClickListener{
            void cardViewOnClick(View v, int position);
            void cardViewOnLongClick(View v, int position);
        }

        private OnCardViewClickListener clickListener;

        public ViewHolder(View view, OnCardViewClickListener listener){
            super(view);
            cv = (CardView)view.findViewById(R.id.taskview_cardview);
            description = (TextView)view.findViewById(R.id.textview_task_description);
            priority = (TextView)view.findViewById(R.id.textview_task_priority);
            timedate = (TextView)view.findViewById(R.id.textview_task_timedate);

            this.clickListener = listener;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view){
            Log.d("adapter", "OnClick");
            clickListener.cardViewOnClick(view, getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View view){
            clickListener.cardViewOnLongClick(view, getLayoutPosition());
            return true;
        }

    }

    public TaskListAdapter(ArrayList<Task> taskList, OnCardViewAdapterClickListener callbackListener){
        //check to see if this is necessary
        this.taskList = new ArrayList<Task>();
        this.taskList.addAll(taskList);
        this.cbClickListener = callbackListener;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklist_item,parent,false);
        TaskListAdapter.ViewHolder  vh = new TaskListAdapter.ViewHolder(v, new ViewHolder.OnCardViewClickListener(){
            @Override
            public void cardViewOnClick(View view, int position){
                cbClickListener.onCardViewAdapterClicked(view,position);
            }

            @Override
            public void cardViewOnLongClick(View view, int position){
                cbClickListener.onCardViewAdapterLongClicked(view, position);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder  holder, int position){
        holder.description.setText(taskList.get(position).getDescription());
        holder.priority.setText(taskList.get(position).getPriorityLevel().toString());
        holder.timedate.setText(taskList.get(position).getDateTimeString());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount(){
        return taskList.size();
    }

    public void swap(ArrayList<Task> tl){
        this.taskList.clear();
        this.taskList.addAll(tl);
        notifyDataSetChanged();
    }
}