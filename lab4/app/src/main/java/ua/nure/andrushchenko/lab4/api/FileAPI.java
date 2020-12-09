package ua.nure.andrushchenko.lab4.api;

import android.content.Context;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import ua.nure.andrushchenko.lab4.service.Note;
import ua.nure.andrushchenko.lab4.service.NoteSerializable;

public class FileAPI implements IO_API {
	private static final String FILE_NAME = "notes.dat";

	@Override
	public boolean write(Object data, Context context) {
//		Map<Long, NoteSerializable> resMap = new ArrayMap<>();
//		for (Note note : ((Map<Long, Note>) data).values()) {
//			resMap.put(note.getId(), new NoteSerializable(note));
//		}
		String s = new Gson().toJson(data);
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
			outputStreamWriter.write(s);
			outputStreamWriter.close();
			return true;
		} catch (IOException e) {
			Log.e("Exception", "File write failed: " + e.toString());
			return false;
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public Object read(Context context) {
		String ret = "";

		try {
			InputStream inputStream = context.openFileInput(FILE_NAME);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append("\n").append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		HashMap vals = new Gson().fromJson(ret, HashMap.class);
		Map<Long, Note> res = new ArrayMap<>();
		if (vals != null) {
			for (Object map : vals.values()) {
				try {
					Note note = NoteSerializable.toNormalNote((LinkedTreeMap) map);
					res.put(note.getId(), note);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return res;
	}
}
