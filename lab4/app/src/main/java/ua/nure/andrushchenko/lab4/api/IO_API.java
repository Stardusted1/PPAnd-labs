package ua.nure.andrushchenko.lab4.api;

import android.content.Context;

public interface IO_API {
	public boolean write(Object data, Context context);

	public Object read(Context context);
}
