package org.example;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String email;
    private String password;
    private Map<String, TaskList> lists;

    public User(String email, String password, Map<String, TaskList> lists) {
        this.email = email;
        this.password = password;
        this.lists = lists;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.lists = new HashMap<>();
    }

    public void addTaskList(String tl) {
        if (!lists.containsKey(tl)) {
            lists.put(tl, new TaskList(tl));
        }
    }

    public void removeTaskList(String tl) {
        lists.remove(tl);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, TaskList> getLists() {
        return lists;
    }

    public void setLists(Map<String, TaskList> lists) {
        this.lists = lists;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User list = (User) obj;
        return list.getEmail().equals(this.email);
    }
}

