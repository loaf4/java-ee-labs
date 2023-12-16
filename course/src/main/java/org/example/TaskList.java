package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskList {

    private String title;
    private HashMap<String, Task> tasks;

    public TaskList(String title) {
        this.title = title;
        this.tasks = new HashMap<>();
    }

    public TaskList(String title, HashMap<String, Task> tasks) {
        this.title = title;
        this.tasks = tasks;
    }

    public void addTask(String t) {
        if (!tasks.containsKey(t)) {
            tasks.put(t, new Task(t));
        }
    }

    public void removeTask(String t) {
        tasks.remove(t);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<String, Task> tasks) {
        this.tasks = tasks;
    }
}

