package ua.nure.andrushchenko.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ua.nure.andrushchenko.lab4.service.Note;
import ua.nure.andrushchenko.lab4.service.NotesManager;

import static ua.nure.andrushchenko.lab4.list.ItemFragment.adapter;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar myToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(myToolbar);
		SearchView searchView = findViewById(R.id.main_search);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				adapter.getFilter().filter(query);
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				adapter.getFilter().filter(newText);
				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_add_note: {
				Intent intent = new Intent(this.getApplicationContext(), NoteEditing.class);
				startActivity(intent);
				return true;
			}
			case R.id.menu_test_item: {
				for (long i = 0; i < 25L; i++) {
					NotesManager.addItem(new Note(i, String.format("Note %L", i), "ahahahsdhasdsadhasd asd asd hads sh ashd ", null));
				}
			}
			default:
				// If we got here, the user's action was not recognized.
				// Invoke the superclass to handle it.
				return super.onOptionsItemSelected(item);

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		adapter.notifyDataSetChanged();
//        list.invalidate();
		super.onActivityResult(requestCode, resultCode, data);
	}
}