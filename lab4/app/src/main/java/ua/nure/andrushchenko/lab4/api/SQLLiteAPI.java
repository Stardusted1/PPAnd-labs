package ua.nure.andrushchenko.lab4.api;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.ArrayMap;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import ua.nure.andrushchenko.lab4.service.Note;

public class SQLLiteAPI implements IO_API {
	private static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	@Override
	public void write(Map<Long, Note> data, Context context) {
		SQLiteDatabase db = context.openOrCreateDatabase("notes.db", Context.MODE_PRIVATE, null);
		if (!tableExists(db, "Notes")) {
			db.execSQL("CREATE TABLE 'Notes' (\n" +
					"\t id integer PRIMARY KEY AUTOINCREMENT,\n" +
					"\t data blob\n" +
					");\n");
		}


		for (Note note : data.values()) {
			try {
				db.execSQL("insert into 'Notes'(data) values( ?)", new Object[]{serialize(note)});
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		db.close();
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public Object read(Context context) {
		SQLiteDatabase db = context.openOrCreateDatabase("notes.db", Context.MODE_PRIVATE, null);
		if (!tableExists(db, "Notes")) {
			return new ArrayMap<>();
		}
		ArrayMap<Long, Note> res = new ArrayMap<>();
		Cursor query = db.rawQuery("SELECT * FROM 'Notes';", null);
		while (query.moveToNext()) {
			long id = query.getLong(0);
			byte[] noteBlob = query.getBlob(1);
			try {
				res.put(id, (Note) deserialize(noteBlob));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		db.close();
		return res;
	}

	private boolean tableExists(SQLiteDatabase db, String tableName) {
		if (tableName == null || db == null || !db.isOpen()) {
			return false;
		}
		Cursor cursor = db.rawQuery(
				"SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?",
				new String[]{"table", tableName}
		);
		if (!cursor.moveToFirst()) {
			cursor.close();
			return false;
		}
		int count = cursor.getInt(0);
		cursor.close();
		return count > 0;
	}
}
