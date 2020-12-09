package ua.nure.andrushchenko.lab4.api;

import android.content.Context;

import java.util.Map;

import ua.nure.andrushchenko.lab4.service.Note;

public interface IO_API {
	public void write(Map<Long, Note> data, Context context);

	public Object read(Context context);
}
