package com.therewillbebugs.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskListFragment extends android.support.v4.app.Fragment implements TaskListAdapter.OnCardViewAdapterClickListener {
    //Callback setup for Activity communication
    public interface OnTaskListItemClicked{
        public void onTaskListItemClicked(int position);
    }

    //Class members
    public static final String TAG = "TaskListFragment";
    public static final String SERIAL_LIST_KEY = "SerialTaskList";
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerManager;
    private RecyclerView.Adapter recyclerAdapter;
    private Toolbar toolbar;
    private OnTaskListItemClicked callbackListener;

    private ArrayList<Task> taskList;

    public TaskListFragment(){}

    public static TaskListFragment newInstance(ArrayList<Task> taskList){
        TaskListFragment fragment = new TaskListFragment();
        if(taskList != null){
            Bundle args = new Bundle();
            args.putSerializable(SERIAL_LIST_KEY,taskList);
            fragment.setArguments(args);
        }
        return fragment;
    }

    //Override functions
    //-------------------------------------
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            callbackListener = (OnTaskListItemClicked)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnTaskListItemClicked");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.taskList = new ArrayList<Task>();

        Bundle args = getArguments();
        if(args != null && args.containsKey(SERIAL_LIST_KEY)) {
            ArrayList<Task> temp = (ArrayList<Task>) args.getSerializable(SERIAL_LIST_KEY);
            this.taskList.addAll(temp);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.tasklist_layout,container,false);

        initRecycler();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.taskview_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //Callback listener
    @Override
    public void onCardViewAdapterClicked(View view, int position){
        //handle changes to the fragment
        Log.d("tasklistfragment", "cardviewclick");
        // signal to the activity that an item was clicked
        callbackListener.onTaskListItemClicked(position);
    }

    //private functions
    //-------------------------------------

    //region RECYCLER
    private void initRecycler(){
        recyclerView = (RecyclerView)rootView.findViewById(R.id.taskview_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(recyclerManager);

        recyclerAdapter = new TaskListAdapter(taskList,this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    public void refreshRecyclerList(ArrayList<Task> tl){
        this.taskList.clear();
        for(int i = 0; i<tl.size(); i++){
            this.taskList.add(tl.get(i));
        }
        Log.d("tasklistfragment", "Final List size before swap: " + this.taskList.size());
        ((TaskListAdapter)recyclerAdapter).swap(this.taskList);
        recyclerAdapter.notifyDataSetChanged();
    }
    //endregion

}
