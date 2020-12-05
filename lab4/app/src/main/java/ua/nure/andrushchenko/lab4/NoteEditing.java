package ua.nure.andrushchenko.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ua.nure.andrushchenko.lab4.dummy.NotesManager;
import ua.nure.andrushchenko.lab4.service.Note;

public class NoteEditing extends AppCompatActivity {
    Note currentNote;
    TextView title;
    SeekBar importance;
    EditText desc;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editing);
        title = findViewById(R.id.form_title_input);
        desc = findViewById(R.id.form_desc_input);
        importance = findViewById(R.id.form_note_imp_input);
        picture = findViewById(R.id.form_note_image_picker);


        try {
            Long noteId = getIntent().getLongExtra("CURRENT_ID", -1L);
            currentNote = NotesManager.ITEM_MAP.get(noteId);
            title.setText(currentNote.getTitle());
            desc.setText(currentNote.getDesc());
            importance.setProgress(currentNote.getImportance());
            picture.setImageDrawable(currentNote.getPicture());
            Toast.makeText(getApplicationContext(), "withoutException", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            currentNote = new Note(NotesManager.ITEMS.size(), title.getText().toString(), desc.getText().toString(), picture.getDrawable());
        }
        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        ((SeekBar) findViewById(R.id.form_note_imp_input)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentNote.setImportance(importance.getProgress());
            }
        });
    }

    public void onImagePick(View view) {
    }

    public void onSaveNote(View view) {
        Note newNote = new Note(NotesManager.ITEMS.size(), title.getText().toString(), desc.getText().toString(), picture.getDrawable());
        newNote.setImportance(importance.getProgress());
        NotesManager.replaceOrAddItem(currentNote, newNote);
        finish();
    }

    public void onDeleteNote(View view) {
        NotesManager.deleteItem(currentNote);
        finish();
    }

}