package com.therewillbebugs.todolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskManager {
    //class members
    private ArrayList<Task> taskList;
    private int sortLevel;

    public TaskManager(){
        taskList = new ArrayList<Task>();
        sortLevel = 0;
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

    public void sortByTimeDate(){
        this.sortLevel = 0;
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                //XOR for Only one complete item and one non complete
                if(t1.isComplete() ^ t2.isComplete()){
                    if(t1.isComplete())
                        return 1;
                    else return -1;
                }
                else {
                    int dates = t1.getDate().compareTo(t2.getDate());

                    //If no time is set, return dates
                    if (t1.getTime() == null || t2.getTime() == null) {
                        if (dates == 0) //return by priority level if dates are equal
                            return t1.getPriorityLevel().compareTo(t2.getPriorityLevel());
                        else return dates;
                    }

                    int times = t1.getTime().compareTo(t2.getTime());
                    if (dates == 0) {  //If dates are equal
                        if (times == 0)
                            return t1.getPriorityLevel().compareTo(t2.getPriorityLevel());
                        else return times;
                    } else return dates;
                }
            }
        });
    }


    public void sortByPriority(){
        this.sortLevel = 1;
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                //XOR for Only one complete item and one non complete
                if(t1.isComplete() ^ t2.isComplete()){
                    if(t1.isComplete())
                        return 1;
                    else return -1;
                }
                else {
                    int level = t1.getPriorityLevel().compareTo(t2.getPriorityLevel());
                    if (level == 0) {
                        //Sort by time/date
                        int dates = t1.getDate().compareTo(t2.getDate());
                        if (t1.getTime() == null || t2.getTime() == null) {
                            return dates;
                        }

                        int times = t1.getTime().compareTo(t2.getTime());
                        if (dates == 0) {  //If dates are equal
                            return times;
                        } else return dates;
                    } else return level;
                }
            }
        });
    }

    public void swapPositions(int positionA, int positionB){
        Collections.swap(taskList, positionA, positionB);
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
    public int getSortLevel(){return sortLevel; }
}
