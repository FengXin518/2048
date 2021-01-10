package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.TextView;

public class TextBlocks extends Activity {
    private TextView[][] textView = new TextView[4][4];
    TextBlocks(TextView[][] textView){
        this.textView = textView;
    }
    void change(int row,int column,int data){
        String middle = new String();
        if(data != 0)
            middle.valueOf(data);
        else
            middle = new String(" ");
        row--;
        column--;
        textView[row][column].setText(middle);
        switch(data){
            case 0:
                textView[row][column].setBackgroundResource(R.drawable.cover);
                textView[row][column].setText(" ");
                textView[row][column].setTextSize(30);
                break;
            case 2:
                textView[row][column].setBackgroundResource(R.drawable.data2);
                textView[row][column].setText("2");
                textView[row][column].setTextSize(30);
                break;
            case 4:
                textView[row][column].setBackgroundResource(R.drawable.data4);
                textView[row][column].setText("4");
                textView[row][column].setTextSize(30);
                break;
            case 8:
                textView[row][column].setBackgroundResource(R.drawable.data8);
                textView[row][column].setText("8");
                textView[row][column].setTextSize(30);
                break;
            case 16:
                textView[row][column].setBackgroundResource(R.drawable.data16);
                textView[row][column].setText("16");
                textView[row][column].setTextSize(28);
                break;
            case 32:
                textView[row][column].setBackgroundResource(R.drawable.data32);
                textView[row][column].setText("32");
                textView[row][column].setTextSize(28);
                break;
            case 64:
                textView[row][column].setBackgroundResource(R.drawable.data64);
                textView[row][column].setText("64");
                textView[row][column].setTextSize(28);
                break;
            case 128:
                textView[row][column].setBackgroundResource(R.drawable.data128);
                textView[row][column].setText("128");
                textView[row][column].setTextSize(26);
                break;
            case 256:
                textView[row][column].setBackgroundResource(R.drawable.data256);
                textView[row][column].setText("256");
                textView[row][column].setTextSize(26);
                break;
            case 512:
                textView[row][column].setBackgroundResource(R.drawable.data512);
                textView[row][column].setText("512");
                textView[row][column].setTextSize(26);
                break;
            case 1024:
                textView[row][column].setBackgroundResource(R.drawable.data1024);
                textView[row][column].setText("1024");
                textView[row][column].setTextSize(24);
                break;
            case 2048:
                textView[row][column].setBackgroundResource(R.drawable.data2048);
                textView[row][column].setText("2048");
                textView[row][column].setTextSize(24);
                break;
            case 4096:
                textView[row][column].setBackgroundResource(R.drawable.data4096);
                textView[row][column].setText("4096");
                textView[row][column].setTextSize(24);
                break;
            default:break;
        }
    }
}
