package nure.andruschenko.lab1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

public class CalculatorActivity extends AppCompatActivity {

    TextView input;
    TextView result;
    String first = "";
    String second = "";
    MathAction action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar menu = findViewById(R.id.toolbar2);
        input = findViewById(R.id.input);
        result = findViewById(R.id.result);

        menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return Commons.onOptionsItemSelected(item, CalculatorActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activities_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        System.out.println(item.getItemId());
        Commons.onOptionsItemSelected(item, CalculatorActivity.this);
        return super.onOptionsItemSelected(item);
    }

    public void addValue(View view) {
        if(!result.getText().equals("0.0")){
            clearValue(view);
        }
        AppCompatButton b = (AppCompatButton) view;
        if (action == null) {
            first = first.concat(b.getText().toString());
            input.setText(first);
        } else {
            second = second.concat(b.getText().toString());
            input.setText(first.concat(action.action).concat(second));
        }
        input.invalidate();
    }

    public void addAction(View view) {
        AppCompatButton b = (AppCompatButton) view;
        if (action == null) {
            action = MathAction.get(b.getText().toString());
            if (action != null) {
                input.setText(first.concat(action.action));
                input.invalidate();
            }

        }
    }

    public void clearValue(View view) {
        first = "";
        second = "";
        action = null;
        input.setText(R.string.calc_value);
        input.invalidate();
        result.setText("0.0");
        result.invalidate();
    }

    public void evalValue(View view) {
        double f = Double.parseDouble(first);
        double s = Double.parseDouble(second);
        double res = 0;
        switch (action) {
            case PLUS: {
                res = f + s;
                break;
            }
            case MINUS: {
                res = f - s;
                break;
            }
            case DIVIDE: {
                try {
                    res = f / s;
                } catch (ArithmeticException e) {
                    res = -1.;
                }
                break;
            }
            case MULTIPLY: {
                res = f * s;
                break;
            }
        }
        result.setText(String.valueOf(res));
        result.invalidate();
    }
}