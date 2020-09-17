package nure.andruschenko.lab1;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class Commons {
    public static boolean onOptionsItemSelected(@NonNull MenuItem item, @NonNull Context context) {
        System.out.println(item.getItemId());
        switch (item.getItemId()) {
            case R.id.calc_menu: {
                Intent myIntent = new Intent(context, CalculatorActivity.class);
                context.startActivity(myIntent);
                return true;
            }
            case R.id.notes_menu: {
                Intent myIntent = new Intent(context, NotesActivity.class);
                context.startActivity(myIntent);
                return true;
            }
            case R.id.rgb_menu: {
                Intent myIntent = new Intent(context, RGBActivity.class);
                context.startActivity(myIntent);
                return true;
            }
        }
        return false;
    }
}
