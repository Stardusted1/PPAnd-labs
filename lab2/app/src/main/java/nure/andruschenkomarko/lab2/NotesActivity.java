package nure.andruschenkomarko.lab2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.widget.Toolbar;

public class NotesActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar menu = findViewById(R.id.toolbar3);
        try {
            menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return Commons.onOptionsItemSelected(item, NotesActivity.this);
                }
            });
            searchView.setOnQueryTextListener(new OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    System.out.println(s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    System.out.println(newText);
                    return false;
                }
            });
        } catch (Exception e) {
            System.out.println("was exception");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                System.out.println(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                return false;
            }
        });

        return true;
    }

    public void Search(View view) {
        System.out.println("search");
        onSearchRequested();
    }

    public void onActionCreate(MenuItem item) {
        System.out.println("action create");
    }
}