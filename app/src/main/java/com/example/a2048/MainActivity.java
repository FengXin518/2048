package com.example.a2048;//highest,current 分数

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {//30,28,26,24
    private LinearLayout mainLayout;
    private GridLayout secondaryLayout;
    private TextView highest;
    private TextView current;
    private StringBuffer shigh;
    private StringBuffer scurrent;
    private int temp;
    private String middle;
    private DisplayMetrics dm;
    private LabeledIntent ds;
    private Shape d2;
    private TextView[] textView = new TextView[12];
    void Init(){
        int a;
        String middle;
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        a = dm.widthPixels;
        secondaryLayout = findViewById(R.id.secondaryLayout);
        mainLayout = findViewById(R.id.mainLayout);
        highest = findViewById(R.id.highest);
        current = findViewById(R.id.current);
        textView[0] = findViewById(R.id.b0);
        textView[1] = findViewById(R.id.b1);
        textView[2] = findViewById(R.id.b2);
        textView[3] = findViewById(R.id.b3);
        textView[4] = findViewById(R.id.b4);
        textView[5] = findViewById(R.id.b5);
        textView[6] = findViewById(R.id.b6);
        textView[7] = findViewById(R.id.b7);
        textView[8] = findViewById(R.id.b8);
        textView[9] = findViewById(R.id.b9);
        textView[10] = findViewById(R.id.b10);
        textView[11] = findViewById(R.id.b11);
        textView[11] = findViewById(R.id.b12);
        textView[11] = findViewById(R.id.b13);
        textView[11] = findViewById(R.id.b14);
        textView[11] = findViewById(R.id.b15);
        a *= 0.95;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(a,a);
        secondaryLayout.setLayoutParams(params);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        temp = 200;
        middle = String.valueOf(temp);
        shigh = new StringBuffer(middle);
        highest.setText(shigh);
        current.setText("4");

    }
}
/*textView[0].setBackgroundResource(R.drawable.data4096);
  textView[0].setText("4096");
  textView[0].setTextSize(24);*/