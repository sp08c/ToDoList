package com.therewillbebugs.todolist;

/**
 * Created by Michael on 7/12/2016.
 */
public class Task {
    public enum PRIORITY_LEVEL{
        HIGH, MEDIUM, LOW, NONE
    }

    //class members
    //-------------------------------------
    private String description;
    private int timeToCompleteBy, dateOfCompletion;
    private PRIORITY_LEVEL priorityLevel;
    private boolean complete, notifications;

    //public functions
    //-------------------------------------
    public Task(String description, PRIORITY_LEVEL priorityLevel){
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.complete = false;
        this.notifications = true;
    }

    //Mutators
    //-------------------------------------
    public void setDescription(String in){
        this.description = in;
    }

    public void setPriorityLevel(PRIORITY_LEVEL in){
        this.priorityLevel = in;
    }

    public void setComplete(boolean in){
        this.complete = in;
    }
    public void setNotificationsEnabled(boolean in){
        this.notifications = in;
    }

    //Accessors
    //-------------------------------------
    public String getDescription(){
        return description;
    }

    public PRIORITY_LEVEL getPriorityLevel(){
        return priorityLevel;
    }

    public boolean isComplete(){
        return complete;
    }

    public boolean isNotificationsEnabled(){
        return notifications;
    }

    //private functions
    //-------------------------------------
}
