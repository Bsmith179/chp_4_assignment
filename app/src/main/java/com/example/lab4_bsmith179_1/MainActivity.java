package com.example.lab4_bsmith179_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Declare variables for weight calculation

    double bmi;
    double weightEntered;
    double heightEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Enable app icon to display in action bar
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // instantiate needed objects from class
        final EditText weight = findViewById(R.id.tvWeight);
        final EditText height = findViewById(R.id.tvHeight);
        final RadioButton rbStandard = findViewById(R.id.rbStandard);
        final RadioButton rbMetric = findViewById(R.id.rbMetric);
        final TextView result = findViewById(R.id.tvResult);
        Button convert = findViewById(R.id.btConvert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Return if input is empty
                if (weight.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.error_enter_input), Toast.LENGTH_LONG).show();
                    return;
                }

                if (height.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.error_enter_input), Toast.LENGTH_LONG).show();
                    return;
                }

                heightEntered = Double.parseDouble(height.getText().toString());
                weightEntered = Double.parseDouble(weight.getText().toString());
                DecimalFormat tenth = new DecimalFormat("#.#");

                if (weightEntered <= 0 || heightEntered <= 0) {
                    Toast.makeText(MainActivity.this, getString(R.string.error_invalid_number), Toast.LENGTH_LONG).show();
                    return;
                }

                if (rbStandard.isChecked()) {
                    if (weightEntered > 500) {
                        Toast.makeText(MainActivity.this, getString(R.string.error_pounds_limit), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (heightEntered > 120) {
                        Toast.makeText(MainActivity.this, getString(R.string.error_inches_limit), Toast.LENGTH_LONG).show();
                        return;
                    }

                    double bmi = (703 * weightEntered) / (heightEntered * heightEntered);
                    String category = getBMICategory(bmi);
                    result.setText("Your BMI is: " + tenth.format(bmi) + "\nCategory: " + category);

                } else if (rbMetric.isChecked()) {
                    if (weightEntered > 225) {
                        Toast.makeText(MainActivity.this, getString(R.string.error_kilos_limit), Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (heightEntered > 300) {
                        Toast.makeText(MainActivity.this, getString(R.string.error_cm_limit), Toast.LENGTH_LONG).show();
                        return;
                    }

                    double heightMeters = heightEntered / 100.0;
                    double bmi = weightEntered / (heightMeters * heightMeters);
                    String category = getBMICategory(bmi);
                    result.setText("Your BMI is: " + tenth.format(bmi) + "\nCategory: " + category);

                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.error_select_unit), Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}