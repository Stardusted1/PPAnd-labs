package ua.nure.andrushchenko.lab4.api;

import android.content.Context;

public class SQLLiteAPI implements IO_API {
	@Override
	public boolean write(Object data, Context context) {
		return false;
	}

	@Override
	public Object read(Context context) {
		return null;
	}
}
