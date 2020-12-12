package ua.nure.andrushchenko.lab4.api;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.util.ArrayMap;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import ua.nure.andrushchenko.lab4.App;
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
		WriteTask task = new WriteTask();

		task.execute(data);

	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public Object read(Context context) {
		ReadTask task = new ReadTask();
		try {
			return task.execute().get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
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

	private class WriteTask extends AsyncTask<Map<Long, Note>, Void, Void> {

		@SafeVarargs
		@Override
		protected final Void doInBackground(Map<Long, Note>... maps) {

			SQLiteDatabase db = App.getAppContext()
					.openOrCreateDatabase("notes.db", Context.MODE_PRIVATE, null);
			if (tableExists(db, "Notes")) {
				db.execSQL("DROP TABLE 'Notes' ;");
			}

			db.execSQL("CREATE TABLE 'Notes' (\n" +
					"\t id integer PRIMARY KEY AUTOINCREMENT,\n" +
					"\t data blob\n" +
					");\n");
			for (Note note : maps[0].values()) {
				try {
					db.execSQL("insert into 'Notes'(data)" +
							" values( ?)", new Object[]{serialize(note)});
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			db.close();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	private class ReadTask extends AsyncTask<Void, Void, Object>{
		@RequiresApi(api = Build.VERSION_CODES.KITKAT)
		@Override
		protected Object doInBackground(Void... voids) {
			SQLiteDatabase db =
					App.getAppContext()
							.openOrCreateDatabase("notes.db",
									Context.MODE_PRIVATE, null);
			if (!tableExists(db, "Notes")) {
				return new ArrayMap<>();
			}
			ArrayMap<Long, Note> res = new ArrayMap<>();
			Cursor query = db.rawQuery("SELECT * FROM 'Notes';", null);
			while (query.moveToNext()) {
				long id = query.getLong(0);
				byte[] noteBlob = query.getBlob(1);
				try {
					Note note = (Note) deserialize(noteBlob);
					res.put(note.getId(), note);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			db.close();
			return res;
		}
	}
}
