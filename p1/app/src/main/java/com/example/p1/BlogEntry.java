package com.example.p1;
public class BlogEntry {
    private int id;
    private String username;
    private String comment;
    private String date;

    public BlogEntry(int id, String username, String comment, String date) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.date = date;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getComment() { return comment; }
    public String getDate() { return date; }
}