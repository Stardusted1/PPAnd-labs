package ua.nure.andrushchenko.lab4.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Note implements Serializable {
	private final long id;
	private String title;
	private String desc;
	private Date date;
	private byte[] image;
	private int importance;

	public Note(long id, String title, String desc, Bitmap image) {
		this.id = id;
		this.title = title;
		this.desc = desc;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		this.image = stream.toByteArray();
		this.date = new Date();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.date = new Date();
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Bitmap getImage() {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inMutable = true;
		return BitmapFactory.decodeByteArray(image, 0, image.length, options);
	}

	public void setImage(Bitmap image) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		this.image = stream.toByteArray();
		this.date = new Date();
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
		this.date = new Date();
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


	public String toDescString() {
		return "" + title + ' ' + desc + ' ' + date;
	}
}
