package ua.nure.andrushchenko.lab4.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ua.nure.andrushchenko.lab4.service.Note;

public class NotesManager {
    public static final List<Note> ITEMS = new ArrayList<Note>();

    public static final Map<Long, Note> ITEM_MAP = new HashMap<Long, Note>();

    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(String.format("item %s", i)));
        }
    }

    private static void addItem(Note item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public static void deleteItem(Note item) {
        ITEMS.remove(item);
        ITEM_MAP.remove(item.getId());
    }

    public static void replaceOrAddItem(Note old, Note current) {
        ITEMS.remove(old);
        ITEMS.add(current);

        ITEM_MAP.remove(old.getId());
        ITEM_MAP.put(current.getId() ,current);
    }

    private static Note createDummyItem(String title) {
        Note note = new Note(ITEMS.size(), title, "sdjfksdjhfj hsf bskjfksd jksdj skdhf kjdshf kjhdsfk jsd kjsh kjsdh jshd jksdh jsdh kjsdhf shsjkdkfjhsdfjkhsdfkh ", null);
        note.setImportance(Math.abs(new Random().nextInt() % 3));
        return note;
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