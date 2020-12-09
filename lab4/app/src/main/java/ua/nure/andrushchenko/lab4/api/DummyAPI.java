package ua.nure.andrushchenko.lab4.api;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ua.nure.andrushchenko.lab4.service.Note;

public class DummyAPI implements IO_API {
	private static final int COUNT = 25;

	@Override
	public void write(Map data, Context context) {

	}

	@Override
	public Object read(Context context) {
		HashMap<Long, Note> res = new HashMap<>();
		for (long i = 1; i <= COUNT; i++) {
			res.put(i - 1, createDummyItem(i - 1, String.format("item %s", i - 1)));
		}
		return res;
	}

	private static Note createDummyItem(long id, String title) {
		Note note = new Note(id, title, "sdjfksdjhfj hsf bskjfksd jksdj skdhf kjdshf kjhdsfk jsd kjsh kjsdh jshd jksdh jsdh kjsdhf shsjkdkfjhsdfjkhsdfkh ", null);
		note.setImportance(Math.abs(new Random().nextInt() % 3));
		return note;
	}
}