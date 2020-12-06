package ua.nure.andrushchenko.lab4.service;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.nure.andrushchenko.lab4.api.DummyAPI;
import ua.nure.andrushchenko.lab4.api.IO_API;

public class NotesManager {

	private static final IO_API api;

	public static List<Note> getITEMS() {
		return new ArrayList<>(ITEMS);
	}

	public static List<Note> ITEMS;
	private static Map<Long, Note> ITEM_MAP = new HashMap<Long, Note>();

	static {
		api = new DummyAPI();
		HashMap<Long, Note> notes = (HashMap<Long, Note>) api.read();
		if (notes != null) {
			ITEM_MAP = notes;
			ITEMS = new ArrayList<>(ITEM_MAP.values());
		} else {
			ITEM_MAP = new HashMap<>();
			ITEMS = new ArrayList<>();
		}
	}

	private static void addItem(Note item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.getId(), item);

		new Gson().toJson(ITEM_MAP);
	}

	public static void deleteItem(Note item) {
		ITEMS.remove(item);
		ITEM_MAP.remove(item.getId());
	}

	public static void replaceOrAddItem(Note old, Note current) {
		ITEMS.remove(old);
		ITEMS.add(current);

		ITEM_MAP.remove(old.getId());
		ITEM_MAP.put(current.getId(), current);
	}

	public static Map<Long, Note> getItemMap() {
		return new HashMap<Long, Note>(ITEM_MAP);
	}

}