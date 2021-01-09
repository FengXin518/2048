package com.example.a2048;//highest,current 分数

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class SecondaryActivity extends AppCompatActivity {
    private LinearLayout mainLayout;
    private GridLayout secondaryLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondarylayout);
        double a;
        secondaryLayout = findViewById(R.id.secondaryLayout);
        mainLayout = findViewById(R.id.mainLayout);
        a = mainLayout.getWidth();
        secondaryLayout.setMinimumWidth(100);
    }
}