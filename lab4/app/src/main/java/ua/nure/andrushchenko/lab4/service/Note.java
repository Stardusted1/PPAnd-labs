package ua.nure.andrushchenko.lab4.service;

import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Note implements Serializable {
    private final long id;
    private String title;
    private String desc;
    private Date date;
    private Drawable image;
    private int importance;

    public Note(long id, String title, String desc, Drawable image) {
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

    public Drawable getPicture() {
        return image;
    }

    public void setImage(Drawable image) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
