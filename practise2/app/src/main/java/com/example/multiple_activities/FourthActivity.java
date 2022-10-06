package com.example.multiple_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);
    }

    public void onHoover(View view) {
        Button but = findViewById(R.id.hooverBut);
        but.setBackgroundColor(getResources().getColor(R.color.green));
    }
}