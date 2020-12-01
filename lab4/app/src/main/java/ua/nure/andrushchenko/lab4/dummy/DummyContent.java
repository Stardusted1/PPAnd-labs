package ua.nure.andrushchenko.lab4.dummy;

import android.graphics.Picture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.nure.andrushchenko.lab4.service.Note;

public class DummyContent {
    public static final List<Note> ITEMS = new ArrayList<Note>();

    public static final Map<Long, Note> ITEM_MAP = new HashMap<Long, Note>();

    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Note item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static Note createDummyItem(String title, String desc, Picture picture) {
        return new Note(ITEMS.size(), title, desc, picture);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}