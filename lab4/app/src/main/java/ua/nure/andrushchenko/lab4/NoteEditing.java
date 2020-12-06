package ua.nure.andrushchenko.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ua.nure.andrushchenko.lab4.service.NotesManager;
import ua.nure.andrushchenko.lab4.service.Note;

public class NoteEditing extends AppCompatActivity {
    private static final int PICK_IMAGE = 101;


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE) {
            if (data != null) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(data.getDataString());
//                picture.setImageBitmap(myBitmap);
                picture.setImageURI(data.getData());
                picture.invalidate();
//                picture.invalidate();
            }
            Toast.makeText(getApplicationContext(), "withoutException", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onImagePick(View view) {
        if (picture.getDrawable()==null) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        } else {
            picture.setImageResource(0);
        }
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