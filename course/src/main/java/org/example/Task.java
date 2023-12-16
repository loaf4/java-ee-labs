package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Task {

    private String text;
    private HashMap<String, SubTask> subTasks;

    public Task(String text) {
        this.text = text;
        this.subTasks = new HashMap<>();
    }

    public Task(String text, HashMap<String, SubTask> subTasks) {
        this.text = text;
        this.subTasks = subTasks;
    }

    public void addSubTask(String st) {
        if (!subTasks.containsKey(st)) {
            subTasks.put(st, new SubTask(st));
        }
    }

    public void removeSubTask(String st) {
        subTasks.remove(st);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HashMap<String, SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(HashMap<String, SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}

