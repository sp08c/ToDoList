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
    }

    //Class members
    private ArrayList<Task> taskList;
    private OnCardViewAdapterClickListener callbackListener;

    //Reference to the views
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView cv;
        public TextView description, priority;

        public interface OnCardViewClickListener{
            void cardViewOnClick(View v, int position);
        }

        private OnCardViewClickListener holderCallbackListener;

        public ViewHolder(View view, OnCardViewClickListener listener){
            super(view);
            cv = (CardView)view.findViewById(R.id.taskview_cardview);
            description = (TextView)view.findViewById(R.id.textview_task_description);
            priority = (TextView)view.findViewById(R.id.textview_task_priority);

            this.holderCallbackListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Log.d("adapter", "OnClick");
            holderCallbackListener.cardViewOnClick(view, getLayoutPosition());
        }

    }

    public TaskListAdapter(ArrayList<Task> taskList, OnCardViewAdapterClickListener callbackListener){
        //check to see if this is necessary
        this.taskList = new ArrayList<Task>();
        this.taskList.addAll(taskList);
        this.callbackListener = callbackListener;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklist_item,parent,false);
        TaskListAdapter.ViewHolder  vh = new TaskListAdapter.ViewHolder(v, new ViewHolder.OnCardViewClickListener(){
            @Override
            public void cardViewOnClick(View view, int position){
                Log.d("adapt", "positoin: " + position);
                callbackListener.onCardViewAdapterClicked(view,position);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder  holder, int position){
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

    public void swap(ArrayList<Task> tl){
        Log.d("adapter", "Tasklist size A: " + tl.size());
        this.taskList.clear();
        Log.d("adapter", "Tasklist size B: " + tl.size());
        for(int i = 0; i<tl.size(); i++) {
            this.taskList.add(tl.get(i));
            Log.d("added task","#: " + i);
        }
        Log.d("adapter", "Tasklist size B: " + this.taskList.size());
        notifyDataSetChanged();
    }
}