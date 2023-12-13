package org.example;

import java.util.Date;
import java.util.ArrayList;

public class AdModel {

    private static int ids = 0;
    private final int id;
    private final String title;
    private final String text;
    private final String username;
    private final Date date;
    private ArrayList<AdComment> comments = new ArrayList<>();

    public AdModel(String title, String text, String username) {
        this.id = ++ids;
        this.title = title;
        this.text = text;
        this.username = username;
        date = new Date();
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
    public String getUsername() {
        return username;
    }
    public Date getDate() {
        return date;
    }
    public ArrayList<AdComment> getComments() {
        return comments;
    }
}
