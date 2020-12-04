package ua.nure.andrushchenko.lab4.service;

import android.graphics.Picture;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private final long id;
    private String title;
    private String desc;
    private Date date;
    private Picture image;
    private int importance;

    public Note(long id, String title, String desc, Picture image) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Picture getPicture() {
        return image;
    }

    public void setImage(Picture image) {
        this.image = image;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public long getId() {
        return id;
    }
}
