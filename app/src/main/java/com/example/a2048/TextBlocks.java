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

import java.util.Random;

public class TextBlocks extends Activity {
    private TextView[][] textView = new TextView[4][4];
    TextBlocks(TextView[][] textView){
        this.textView = textView;
        init();
    }
    void format(){//格式化
        for(int i = 0;i< 4;i++)
            for(int j = 0;j < 4;j++){
                textView[i][j].setBackgroundResource(R.drawable.cover);
                textView[i][j].setText("");
                textView[i][j].setTextSize(30);
            }
        init();
    }
    void init(){//初始化
        for(int i = 0;i < 2;i++)
            ramdongenerate();
    }
    int intrandom(){
        Random random = new Random();
        return random.nextInt(4);
    }
    boolean judge(int a,int b){
        if(textView[a][b].getText().toString().equals(""))
            return true;
        else
            return false;
    }
    void slideDown(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 0;j <= 3;j++)
                for(int k = j + 1;k <= 3;k++){
                    if(textView[j][i].getText().toString().equals(textView[k][i].getText().toString())&&
                            (!textView[j][i].getText().toString().equals(""))){
                        if(k - j == 1){
                            if(flag[k]&&flag[j]){
                                flag[k] = false;
                                flag[j] = false;
                                data[count][0] = j;
                                data[count][1] = k;
                                count++;
                            }
                        } else if(k - j == 2){
                            if(textView[k - 1][i].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(k - j== 3){
                            if(textView[k - 1][i].getText().toString().equals("")&&textView[k - 2][i].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }
                    }
                }
            for(int l = 0;l < count;l++){
                num = Integer.parseInt(textView[data[l][0]][i].getText().toString()) * 2;
                change(data[l][1],i,num);
                change(data[l][0],i,0);
            }
            for(int l = 3;l > 0;l--){
                if(textView[l][i].getText().toString().equals("")){
                    for(int m = l - 1;m >= 0 ;m--){
                        if(!textView[m][i].getText().toString().equals("")){
                            num = Integer.parseInt(textView[m][i].getText().toString());
                            change(l,i,num);
                            change(m,i,0);
                            break;
                        }
                    }
                }
            }
        }

    }
    void slideUp(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 3;j >= 0;j--)
                for(int k = j - 1;k >= 0;k--){
                    if(textView[j][i].getText().toString().equals(textView[k][i].getText().toString())&&
                            (!textView[j][i].getText().toString().equals(""))){
                        if(j - k == 1){
                            if(flag[k]&&flag[j]){
                                flag[k] = false;
                                flag[j] = false;
                                data[count][0] = j;
                                data[count][1] = k;
                                count++;
                            }
                        } else if(j - k == 2){
                            if(textView[k + 1][i].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(j - k== 3){
                            if(textView[k + 1][i].getText().toString().equals("")&&textView[k+2][i].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }
                    }
                }
            for(int l = 0;l < count;l++){
                num = Integer.parseInt(textView[data[l][0]][i].getText().toString()) * 2;
                change(data[l][1],i,num);
                change(data[l][0],i,0);
            }
            for(int l = 0;l < 3;l++){
                if(textView[l][i].getText().toString().equals("")){
                    for(int m = l + 1;m <=3 ;m++){
                        if(!textView[m][i].getText().toString().equals("")){
                            num = Integer.parseInt(textView[m][i].getText().toString());
                            change(l,i,num);
                            change(m,i,0);
                            break;
                        }
                    }
                }
            }
        }
    }
    void slideRight(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 0;j <= 3;j++)
                for(int k = j + 1;k <= 3;k++){
                    if(textView[i][j].getText().toString().equals(textView[i][k].getText().toString())&&
                            (!textView[i][j].getText().toString().equals(""))){
                        if(k - j == 1){
                            if(flag[k]&&flag[j]){
                                flag[k] = false;
                                flag[j] = false;
                                data[count][0] = j;
                                data[count][1] = k;
                                count++;
                            }
                        } else if(k - j  == 2){
                            if(textView[i][k - 1].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(k - j  == 3){
                            if(textView[i][k - 1].getText().toString().equals("")&&textView[i][k - 2].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }
                    }
                }
            for(int l = 0;l < count;l++){
                System.out.println(" data0："+data[l][0]+" data1："+data[l][0]+" count:"+count);
                num = Integer.parseInt(textView[i][data[l][0]].getText().toString()) * 2;
                change(i,data[l][1],num);
                change(i,data[l][0],0);
            }
            for(int l = 3;l > 0;l--){
                if(textView[i][l].getText().toString().equals("")){
                    for(int m = l - 1;m >= 0;m--){
                        if(!textView[i][m].getText().toString().equals("")){
                            num = Integer.parseInt(textView[i][m].getText().toString());
                            change(i,l,num);
                            change(i,m,0);
                            break;
                        }
                    }
                }
            }
        }

    }
    void slideLeft(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 3;j >= 0;j--)
                for(int k = j - 1;k >= 0;k--){
                    if(textView[i][j].getText().toString().equals(textView[i][k].getText().toString())&&
                            (!textView[i][j].getText().toString().equals(""))){
                        if(j - k == 1){
                            if(flag[k]&&flag[j]){
                                flag[k] = false;
                                flag[j] = false;
                                data[count][0] = j;
                                data[count][1] = k;
                                count++;
                            }
                        } else if(j - k== 2){
                            if(textView[i][j - 1].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(j - k == 3){
                            if(textView[i][j - 1].getText().toString().equals("")&&textView[i][j - 2].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }
                    }
                }
            for(int l = 0;l < count;l++){
                num = Integer.parseInt(textView[i][data[l][0]].getText().toString()) * 2;
                change(i,data[l][1],num);
                change(i,data[l][0],0);
            }
            for(int l = 0;l < 3;l++){
                if(textView[i][l].getText().toString().equals("")){
                    for(int m = l + 1;m <= 3;m++){
                        if(!textView[i][m].getText().toString().equals("")){
                            num = Integer.parseInt(textView[i][m].getText().toString());
                            change(i,l,num);
                            change(i,m,0);
                            break;
                        }
                    }
                }
            }
        }
    }
    void ramdongenerate(){//数值为2的块随机生成
        int r,c;
        while(true) {
            r = intrandom();
            c = intrandom();
            if(judge(r,c)){
                textView[r][c].setBackgroundResource(R.drawable.data2);
                textView[r][c].setText("2");
                textView[r][c].setTextSize(30);
                break;
            }
        }
    }
    void change(int row,int column,int data){
        switch(data){
            case 0:
                textView[row][column].setBackgroundResource(R.drawable.cover);
                textView[row][column].setText("");
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
