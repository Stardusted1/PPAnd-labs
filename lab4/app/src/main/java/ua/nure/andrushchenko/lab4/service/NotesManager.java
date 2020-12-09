package ua.nure.andrushchenko.lab4.service;

import android.os.Build;
import android.util.ArrayMap;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.nure.andrushchenko.lab4.App;
import ua.nure.andrushchenko.lab4.api.DummyAPI;
import ua.nure.andrushchenko.lab4.api.FileAPI;
import ua.nure.andrushchenko.lab4.api.IO_API;
import ua.nure.andrushchenko.lab4.list.MyItemRecyclerViewAdapter;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class NotesManager {

	private static final IO_API api;
	public static List<Note> ITEMS;
	private static Map<Long, Note> ITEM_MAP = new HashMap<Long, Note>();

	static {
		api = new FileAPI();
		Map<Long, Note> notes = (ArrayMap<Long, Note>) api.read(App.getAppContext());
		if (notes != null) {
			ITEM_MAP = notes;
			ITEMS = new ArrayList<>(ITEM_MAP.values());
		} else {
			ITEM_MAP = new HashMap<>();
			ITEMS = new ArrayList<>();
		}
	}

	public static List<Note> getITEMS() {
		return new ArrayList<>(ITEMS);
	}

	public static void addItem(Note item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.getId(), item);
		MyItemRecyclerViewAdapter.mainValues.add(item);
		api.write(ITEM_MAP, App.getAppContext());
	}

	public static void deleteItem(Note item) {
		ITEMS.remove(item);
		ITEM_MAP.remove(item.getId());
		MyItemRecyclerViewAdapter.mainValues.remove(item);
		api.write(ITEM_MAP, App.getAppContext());
	}

	public static void replaceOrAddItem(Note old, Note current) {
		ITEMS.remove(old);
		ITEMS.add(current);

		ITEM_MAP.remove(old.getId());
		ITEM_MAP.put(current.getId(), current);

		MyItemRecyclerViewAdapter.mainValues.remove(old);
		MyItemRecyclerViewAdapter.mainValues.add(current);
		api.write(ITEM_MAP, App.getAppContext());
	}

	public static Map<Long, Note> getItemMap() {
		return new HashMap<Long, Note>(ITEM_MAP);
	}

}