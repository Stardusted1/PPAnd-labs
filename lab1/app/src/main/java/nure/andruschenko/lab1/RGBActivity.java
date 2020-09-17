package nure.andruschenko.lab1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class RGBActivity extends AppCompatActivity {
    View colorView;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Color color = Color.valueOf(((ColorDrawable) colorView.getBackground()).getColor());
            if (seekBar.getId() == R.id.redColorSeekBar) {
                colorView.setBackgroundColor(Color.argb(color.alpha(), 1f / 255 * progress, color.green(), color.blue()));
            } else if (seekBar.getId() == R.id.greenSeekBar) {
                colorView.setBackgroundColor(Color.argb(color.alpha(), color.red(), 1f / 255 * progress, color.blue()));
            } else if (seekBar.getId() == R.id.blueSeekBar) {
                colorView.setBackgroundColor(Color.argb(color.alpha(), color.red(), color.green(), 1f / 255 * progress));
            }
            colorView.invalidate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorView = findViewById(R.id.view);
        SeekBar redSeekBar = findViewById(R.id.redColorSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        Toolbar menu = findViewById(R.id.toolbar);
        menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return Commons.onOptionsItemSelected(item, RGBActivity.this);
            }
        });
    }
}