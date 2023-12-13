package org.example;

import java.util.Date;

public class AdComment {

    private final String text;
    private final String username;
    private final Date date;

    public AdComment(String text, String username) {
        this.text = text;
        this.username = username;
        date = new Date();
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
}
