package ua.nure.andrushchenko.lab4.api;

import java.util.HashMap;
import java.util.Random;

import ua.nure.andrushchenko.lab4.service.Note;

public class DummyAPI implements IO_API {
    private static final int COUNT = 25;

    private static Note createDummyItem(long id, String title) {
        Note note = new Note(id, title, "sdjfksdjhfj hsf bskjfksd jksdj skdhf kjdshf kjhdsfk jsd kjsh kjsdh jshd jksdh jsdh kjsdhf shsjkdkfjhsdfjkhsdfkh ", null);
        note.setImportance(Math.abs(new Random().nextInt() % 3));
        return note;
    }

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
            res.put(i, createDummyItem(i, String.format("item %s", i)));
        }
        return res;
    }
}