package com.example.a2048;//highest,current 分数

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity {//30,28,26,24
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
    private TextBlocks textBlocks;
    Button[] btn = new Button[2];
    private TextView[][] textView = new TextView[4][4];
    private float mPosX, mPosY, mCurPosX, mCurPosY;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        temp = 200;
        middle = String.valueOf(temp);
        shigh = new StringBuffer(middle);
        highest.setText(shigh);
        current.setText("4");
        textBlocks = new TextBlocks(textView);
    }
    void Init(){
        int a;
        String middle;
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        a = dm.widthPixels;
        btn[0] = findViewById(R.id.btn0);
        btn[1] = findViewById(R.id.btn1);
        secondaryLayout = findViewById(R.id.secondaryLayout);
        mainLayout = findViewById(R.id.mainLayout);
        highest = findViewById(R.id.highest);
        current = findViewById(R.id.current);
        a *= 0.95;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(a,a);
        secondaryLayout.setLayoutParams(params);
        textView[0][0] = findViewById(R.id.b0);
        textView[0][1] = findViewById(R.id.b1);
        textView[0][2] = findViewById(R.id.b2);
        textView[0][3] = findViewById(R.id.b3);
        textView[1][0] = findViewById(R.id.b4);
        textView[1][1] = findViewById(R.id.b5);
        textView[1][2] = findViewById(R.id.b6);
        textView[1][3] = findViewById(R.id.b7);
        textView[2][0] = findViewById(R.id.b8);
        textView[2][1] = findViewById(R.id.b9);
        textView[2][2] = findViewById(R.id.b10);
        textView[2][3] = findViewById(R.id.b11);
        textView[3][0] = findViewById(R.id.b12);
        textView[3][1] = findViewById(R.id.b13);
        textView[3][2] = findViewById(R.id.b14);
        textView[3][3] = findViewById(R.id.b15);
        btn[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        btn[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                textBlocks.format();
            }
        });
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(mCurPosY - mPosY) > Math.abs(mCurPosX  - mPosX)) {
                            //向下滑动
                            if(mCurPosY > mPosY){
                                textBlocks.slideDown();
                                textBlocks.ramdongenerate();
                            }
                            //向上滑动
                            else{
                                textBlocks.slideUp();
                                textBlocks.ramdongenerate();
                            }
                        } else if (Math.abs(mCurPosY - mPosY) < Math.abs(mCurPosX  - mPosX)) {
                            //向右滑动
                            if(mCurPosX > mPosX){
                                textBlocks.slideRight();
                                textBlocks.ramdongenerate();
                            }
                            //向左滑动
                            else{
                                textBlocks.slideLeft();
                                textBlocks.ramdongenerate();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
}