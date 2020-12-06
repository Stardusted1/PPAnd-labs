package ua.nure.andrushchenko.lab4.api;

import java.util.HashMap;
import java.util.Random;

import ua.nure.andrushchenko.lab4.service.Note;

public class DummyAPI implements IO_API {
	private static final int COUNT = 25;

	@Override
	public boolean write(Object data) {
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Object read() {
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