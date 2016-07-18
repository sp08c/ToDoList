package com.therewillbebugs.todolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class TaskManager {
    //class members
    private ArrayList<Task> taskList;

    public TaskManager(){
        taskList = new ArrayList<Task>();
    }

    public void tempInit(){
        taskList.add(new Task("First task","This is a task note", Task.PRIORITY_LEVEL.HIGH));
        taskList.add(new Task("Second task","", Task.PRIORITY_LEVEL.MEDIUM));
        taskList.add(new Task("Third task","Task note task note", Task.PRIORITY_LEVEL.LOW));
    }

    //Sort Functions
    public CharSequence[] getSortLevels(){
        return new CharSequence[]{"Time & Date", "Priority"};
    }

    public void sortByPriority(){
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getPriorityLevel().compareTo(t2.getPriorityLevel());
            }
        });
    }

    public void sortByTimeDate(){
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if(t1.getDate().getTime().equals(t2.getDate().getTime())){
                    return t1.getTime().getTime().compareTo(t2.getTime().getTime());
                }
                else return t1.getDate().getTime().compareTo(t2.getDate().getTime());
            }
        });
    }

    public void sortByTimeAndPriority(){
        //TODO: Default sorting type, will sort first by Date, and then priority for each date
    }

    public void swapPositions(int positionA, int positionB){
        Task temp = taskList.get(positionA);
        taskList.set(positionA, taskList.get(positionB));
        taskList.set(positionB, temp);
    }

    //Mutators
    public boolean add(Task t){
        return taskList.add(t);
    }

    public void add(int position, Task t){
        taskList.add(position,t);
    }

    public boolean remove(Task t){
        if(taskList.contains(t)){
            return taskList.remove(t);
        }
        else return false;
    }

    public void clear(){
        taskList.clear();
    }

    public int size(){
        return taskList.size();
    }

    //Accessors
    public ArrayList<Task> getTaskList(){
        return taskList;
    }
    public Task get(int position){
        return taskList.get(position);
    }
}
