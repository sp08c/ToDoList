package com.therewillbebugs.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Michael on 7/12/2016.
 */
public class TaskViewCreateFragment extends android.support.v4.app.Fragment {
    //Callback setup for Activity communication
    public interface OnTaskCreationCompleteListener{
        public void onTaskCreationComplete(boolean valid, Task t);
    }

    //Class members
    public static String TAG = "TaskViewCreateFragment";
    private View rootView;
    private static Task task;  //The 'new' task to be added to the global task list
    private OnTaskCreationCompleteListener callbackListener;
    private Button createButton, cancelButton;

    public TaskViewCreateFragment(){}



    //Override functions
    //-------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.taskview_create_layout,container,false);

        task = new Task();

        createButton = (Button)rootView.findViewById(R.id.taskview_create_btn_createtask);
        cancelButton = (Button)rootView.findViewById(R.id.taskview_create_btn_canceltask);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            callbackListener = (OnTaskCreationCompleteListener)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnTaskCreationCompleteListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewTask(v);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cancelCreateTask(v);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    //private functions
    //-------------------------------------

    public void onRadioButtonClicked(View view){

    }

    public void createNewTask(View view){
        //add task to global task list
        //TODO: add checks for complete form, require description
        EditText et = (EditText)rootView.findViewById(R.id.taskview_create_desc);
        task.setDescription(et.getText().toString());

        RadioGroup radioGroup = (RadioGroup)rootView.findViewById(R.id.taskview_create_radiogrp);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View rdoButton = radioGroup.findViewById(radioButtonID);
        int index = radioGroup.indexOfChild(rdoButton);
        task.setPriorityLevel(Task.PRIORITY_LEVEL.get(index));

        if(task != null)
            callbackListener.onTaskCreationComplete(true,task);
    }

    public void cancelCreateTask(View view){
        //return back to previous fragment
        callbackListener.onTaskCreationComplete(false,null);
    }

    //region TIME AND DATE
    public void showTimePickerDialog(View view){
        DialogFragment timePickFragment = new TimePickerFragment();
        timePickFragment.show(getActivity().getSupportFragmentManager(),"timePicker");
    }

    public void showDatePickerDialog(View view){
        DialogFragment datePickFragment = new DatePickerFragment();
        datePickFragment.show(getActivity().getSupportFragmentManager(),"datePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            //set the time in the new task
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,hourOfDay);
            c.set(Calendar.MINUTE,minute);
            task.setTime(c);
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            Calendar c = Calendar.getInstance();
            c.set(year,month,day);
            task.setDate(c);
        }
    }
    //endregion

}
