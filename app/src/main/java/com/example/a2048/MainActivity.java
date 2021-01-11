package com.example.a2048;//highest,current 分数

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {//30,28,26,24
    private LinearLayout mainLayout;
    private GridLayout secondaryLayout;
    private TextView highest;
    private TextView current;
    private DisplayMetrics dm;
    private TextBlocks textBlocks;
    Button[] btn = new Button[2];
    private TextView[][] textView = new TextView[4][4];
    private float mPosX, mPosY, mCurPosX, mCurPosY;
    private SharedPreferences pref ;
    private SharedPreferences.Editor editor ;
    boolean move = false;//判断手势是否移动

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        pref = getSharedPreferences("data",MODE_PRIVATE);
        editor = pref.edit();
        textBlocks = new TextBlocks(textView,highest,current,pref,editor,this);
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("退出游戏");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
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
                        move = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        if(move){
                            if (Math.abs(mCurPosY - mPosY) > Math.abs(mCurPosX  - mPosX)) {
                                if(Math.abs(mCurPosY-mPosY)>100){
                                    //向下滑动
                                    if((mCurPosY > mPosY))
                                        textBlocks.slideDown();
                                    //向上滑动
                                    else
                                        textBlocks.slideUp();
                                }
                            } else if (Math.abs(mCurPosY - mPosY) < Math.abs(mCurPosX  - mPosX)) {
                                if(Math.abs(mCurPosX  - mPosX)>100){
                                    //向右滑动
                                    if((mCurPosX > mPosX))
                                        textBlocks.slideRight();
                                    //向左滑动
                                    else if((mCurPosX < mPosX))
                                        textBlocks.slideLeft();
                                }
                            }
                            move = false;
                        }
                        break;
                    default:break;
                }
                return true;
            }
        });
    }
}