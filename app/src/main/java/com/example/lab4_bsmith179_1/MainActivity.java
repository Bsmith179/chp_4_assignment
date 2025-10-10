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
    double conversionRate = 2.2;
    double weightEntered;
    double convertedWeight;

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
        final EditText weight = (EditText) findViewById(R.id.tvWeight);
        final RadioButton lbToKilo = (RadioButton) findViewById(R.id.rbLbToKilo);
        final RadioButton kiloToLb = (RadioButton) findViewById(R.id.rbKiloToLb);
        final TextView result = (TextView) findViewById(R.id.tvResult);
        Button convert = (Button) findViewById(R.id.btConvert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightEntered = Double.parseDouble(weight.getText().toString());
                DecimalFormat tenth = new DecimalFormat("#.#");

                if (lbToKilo.isChecked()) {
                    if (weightEntered <= 500) {
                        convertedWeight = weightEntered / conversionRate;
                        result.setText(tenth.format(convertedWeight) + " kilograms");
                    } else {
                        Toast.makeText(MainActivity.this, "Pounds must be less than 500", Toast.LENGTH_LONG).show();
                    }
                }
                if (kiloToLb.isChecked()) {
                    if (weightEntered <= 225) {
                        convertedWeight = weightEntered * conversionRate;
                        result.setText(tenth.format(convertedWeight) + " Pounds");
                    } else
                        Toast.makeText(MainActivity.this, "Kilograms must be less than 225", Toast.LENGTH_LONG).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}