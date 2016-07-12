package com.therewillbebugs.todolist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michael on 7/12/2016.
 */
public class TaskViewFragment extends android.support.v4.app.Fragment {
    //Class members
    private View rootView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerManager;
    private RecyclerView.Adapter recyclerAdapter;

    private ArrayList<Task> taskList;

    public TaskViewFragment(){}

    //Override functions
    //-------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.taskview_layout,container,false);

        initRecycler();

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.taskview_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //private functions
    //-------------------------------------

    //region RECYCLER
    private void initRecycler(){
        recyclerView = (RecyclerView)rootView.findViewById(R.id.taskview_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(recyclerManager);


        //Temporary TaskList init
        taskList = new ArrayList<Task>();
        taskList.add(new Task("First task", Task.PRIORITY_LEVEL.HIGH));
        taskList.add(new Task("Second task", Task.PRIORITY_LEVEL.MEDIUM));
        taskList.add(new Task("Third task", Task.PRIORITY_LEVEL.LOW));


        recyclerAdapter = new TaskViewAdapter(taskList);
        recyclerView.setAdapter(recyclerAdapter);
    }



    //endregion




}