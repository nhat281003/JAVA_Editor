package com.example.test_exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Space;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {
    ProgressBar icon;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        icon = findViewById(R.id.xoay);
        icon.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                icon.setVisibility(View.GONE);

            }
        }, 3000);

    }
}
