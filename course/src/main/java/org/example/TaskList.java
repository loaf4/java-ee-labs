package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskList {

    private String title;
    private Map<String, Task> tasks;

    public TaskList(String title) {
        this.title = title;
        this.tasks = new HashMap<>();
    }

    public TaskList(String title, Map<String, Task> tasks) {
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

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskList list = (TaskList) obj;
        return list.getTitle().equals(this.title);
    }
}

