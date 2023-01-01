package com.example.practice6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.instruction_for_button);

        Intent intent = getIntent();
        String str = intent.getStringExtra("station_chosen");

        if (str != null) {
            textView.setText(str);
        }
        else if (counter != 0) {
            textView.setText("Не вибрано станцію");
        }

        button.setOnClickListener(view -> {
            counter += 1;
            openStationsList();
        });
    }

    public void openStationsList() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}