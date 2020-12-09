package ua.nure.andrushchenko.lab4.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileAPI implements IO_API {
	@Override
	public boolean write(Object data, Context context) {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
			outputStreamWriter.write(new Gson().toJson(data));
			outputStreamWriter.close();
			return true;
		} catch (IOException e) {
			Log.e("Exception", "File write failed: " + e.toString());
			return false;
		}
	}

	@Override
	public Object read(Context context) {
		String ret = "";

		try {
			InputStream inputStream = context.openFileInput("config.txt");

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

		return ret;
	}
}
