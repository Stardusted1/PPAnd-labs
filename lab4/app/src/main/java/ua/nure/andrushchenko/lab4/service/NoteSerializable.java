package ua.nure.andrushchenko.lab4.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.internal.LinkedTreeMap;

import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class NoteSerializable implements Serializable {
	private Note note;

	public NoteSerializable(Note note) {
		this.note = note;
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	public static Note toNormalNote(LinkedTreeMap note) {
		Note note1;
		long id = ((Double) (note.get("id"))).longValue();
		String title = String.valueOf(note.get("title"));
		int imp = ((Double) (note.get("importance"))).intValue();
		String desc = String.valueOf(note.get("desc"));
		Date date = new Date((String) note.get("date"));
		ArrayList<Double> bmpdat = ((ArrayList<Double>) note.get("image"));
		if (bmpdat != null) {
			byte[] bytes = new byte[bmpdat.size()];
			AtomicInteger i = new AtomicInteger();
			bmpdat.forEach(a -> bytes[i.getAndIncrement()] = (a.byteValue()));
			byte[] bmpData = (byte[]) ArrayUtils.toPrimitive(bytes);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inMutable = true;
			Bitmap bmp = null;

			bmp = BitmapFactory.decodeByteArray(bmpData, 0, bmpData.length, options);
			note1 = new Note(id, title, desc, bmp);
		} else {
			note1 = new Note(id, title, desc, null);
		}
		note1.setDate(date);
		note1.setImportance(imp);
		return note1;
	}

	public String getTitle() {
		return this.note.getTitle();
	}

	public void setTitle(String title) {
		this.note.setTitle(title);
		this.note.setDate(new Date());
	}

	public String getDesc() {
		return note.getDesc();
	}

	public void setDesc(String desc) {
		note.setDesc(desc);
		this.note.setDate(new Date());
	}

	public Date getDate() {
		return note.getDate();
	}

	public void setDate(Date date) {
		this.note.setDate(new Date());
	}

	public byte[] getImage() {
		if (note.getImage() != null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			note.getImage().compress(Bitmap.CompressFormat.JPEG, 100, stream);
			return stream.toByteArray();
		}
		return null;
	}

	public void setImage(byte[] image) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inMutable = true;
		Bitmap bmp = null;
		bmp = BitmapFactory.decodeByteArray(image, 0, image.length, options);
		this.note.setImage(bmp);
		this.note.setDate(new Date());
	}

	public int getImportance() {
		return note.getImportance();
	}

	public void setImportance(int importance) {
		this.note.setImportance(importance);
		this.note.setDate(new Date());
	}

	public long getId() {
		return note.getId();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NoteSerializable note = (NoteSerializable) o;
		return this.note.getId() == note.getId();
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public int hashCode() {
		return Objects.hashCode(note.getId());
	}
}
