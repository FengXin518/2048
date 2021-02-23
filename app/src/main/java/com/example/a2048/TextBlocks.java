package com.example.a2048;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class TextBlocks extends Activity {
    private TextView[][] textView = new TextView[4][4];
    private TextView highest,current;
    private  int score = 0;
    private String tran;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int high;
    private SecondaryActivity secondaryActivity;
    private Toast toast = null;
    private boolean d2048 = true;

    TextBlocks(TextView[][] textView, TextView highest, TextView current, SharedPreferences pref,
               SharedPreferences.Editor editor,SecondaryActivity secondaryActivity){
        this.textView = textView;
        this.highest = highest;
        this.current = current;
        this.pref = pref;
        this.editor = editor;
        this.secondaryActivity = secondaryActivity;
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
        score = 0;
        high = pref.getInt("highest",0);
        current.setText(tran = String.valueOf(score));
        highest.setText(tran = String.valueOf(high));
    }
    int intrandom(){//生成随机数[1,4)
        Random random = new Random();
        return random.nextInt(4);
    }
    boolean judge(int a,int b){
        if(textView[a][b].getText().toString().equals(""))
            return true;
        else
            return false;
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
        nextJudge();
    }
    void nextJudge(){
        int blockCount = 0;
        int piece = 0;
        for(int i = 0;i < 4 ;i++)
            for(int j = 0;j <4;j++)
                if(!textView[i][j].getText().toString().equals(""))
                    blockCount++;
        if(blockCount == 16){
            blockCount = 0;
            for(int i = 0;i < 4 ;i++)
                for(int j = 0;j <4;j++){
                    piece = 0;
                    if(i-1>=0){
                        if(!textView[i][j].getText().toString().equals(textView[i-1][j].getText().toString()))
                            piece++;
                    }else
                        piece++;
                    if(i+1<=3){
                        if(!textView[i][j].getText().toString().equals(textView[i+1][j].getText().toString()))
                            piece++;
                    }else
                        piece++;
                    if(j-1>=0){
                        if(!textView[i][j].getText().toString().equals(textView[i][j-1].getText().toString()))
                            piece++;
                    }else
                        piece++;
                    if(j+1<=3){
                        if(!textView[i][j].getText().toString().equals(textView[i][j+1].getText().toString()))
                            piece++;
                    }else
                        piece++;
                    if(piece == 4)
                        blockCount++;
                }
            if(blockCount == 16){
                AlertDialog.Builder dialog = new AlertDialog.Builder(secondaryActivity);
                dialog.setTitle("菜鸡，你输了！");
                dialog.setCancelable(false);
                dialog.setPositiveButton("算了，菜鸡就菜鸡", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        secondaryActivity.finish();
                    }
                });
                dialog.setNegativeButton("再来！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        format();
                    }
                });
                dialog.show();
            }
        }
    }
    void maxBlockDataJudge(int num){
        if(num == 2048 &&d2048){
            AlertDialog.Builder dialog = new AlertDialog.Builder(secondaryActivity);
            dialog.setTitle("牛啊！竟然完成了2048，继续玩不？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("不玩了，没意思！", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    secondaryActivity.finish();
                }
            });
            dialog.setNegativeButton("还有？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialog.show();
            d2048 = false;
        }else if(num == 4096){
            AlertDialog.Builder dialog = new AlertDialog.Builder(secondaryActivity);
            dialog.setTitle("4096！膜拜大神！(弱弱的说一下，我都没完成过)");
            dialog.setCancelable(false);
            dialog.setPositiveButton("继续！", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder dialog1 = new AlertDialog.Builder(secondaryActivity);
                    dialog1.setTitle("兄弟，没了！你还想玩！自己写吧！");
                    dialog1.setCancelable(false);
                    dialog1.setPositiveButton("没了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            secondaryActivity.finish();
                        }
                    });
                    dialog1.show();
                }
            });
            dialog.setNegativeButton("不玩了！", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    secondaryActivity.finish();
                }
            });
            dialog.show();
        }
    }
    void slideDown(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        boolean flagjudge = false;//判断是否有方块发生移动
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 3;j >= 1;j--)
                for(int k = j - 1;k >= 0;k--){
                    if(textView[j][i].getText().toString().equals(textView[k][i].getText().toString())&&
                            (!textView[j][i].getText().toString().equals(""))){
                        if(j - k == 1){
                            if(flag[k]&&flag[j]){
                                flag[k] = false;
                                flag[j] = false;
                                data[count][1] = j;
                                data[count][0] = k;
                                count++;
                            }
                        } else if(j - k == 2){
                            if(textView[j - 1][i].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(j - k == 3){
                            if(textView[j - 1][i].getText().toString().equals("")&&textView[j - 2][i].getText().toString().equals("")) {
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
                flagjudge = true;
                num = Integer.parseInt(textView[data[l][0]][i].getText().toString()) * 2;
                change(data[l][0],i,num);
                change(data[l][1],i,0);
                maxBlockDataJudge(num);
                score += num;
                current.setText(tran = String.valueOf(score));
                if(score > high){
                    high = score;
                    editor.putInt("highest",high);
                    highest.setText(tran = String.valueOf(high));
                    editor.apply();
                }
            }
            for(int l = 3;l > 0;l--){
                if(textView[l][i].getText().toString().equals("")){
                    for(int m = l - 1;m >= 0 ;m--){
                        if(!textView[m][i].getText().toString().equals("")){
                            flagjudge = true;
                            num = Integer.parseInt(textView[m][i].getText().toString());
                            change(l,i,num);
                            change(m,i,0);
                            break;
                        }
                    }
                }
            }
        }
        if(flagjudge)
            ramdongenerate();
    }
    void slideUp(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        boolean flagjudge = false;//判断是否有方块发生移动
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 0;j <= 2;j++)
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
                            if(textView[j + 1][i].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(k - j == 3){
                            if(textView[j + 1][i].getText().toString().equals("")&&textView[j+2][i].getText().toString().equals("")) {
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
                flagjudge = true;
                num = Integer.parseInt(textView[data[l][0]][i].getText().toString()) * 2;
                change(data[l][1],i,num);
                change(data[l][0],i,0);
                maxBlockDataJudge(num);
                score += num;
                current.setText(tran = String.valueOf(score));
                if(score > high){
                    high = score;
                    editor.putInt("highest",high);
                    highest.setText(tran = String.valueOf(high));
                    editor.apply();
                }
            }
            for(int l = 0;l < 3;l++){
                if(textView[l][i].getText().toString().equals("")){
                    for(int m = l + 1;m <=3 ;m++){
                        if(!textView[m][i].getText().toString().equals("")){
                            num = Integer.parseInt(textView[m][i].getText().toString());
                            change(l,i,num);
                            change(m,i,0);
                            flagjudge = true;
                            break;
                        }
                    }
                }
            }
        }
        if(flagjudge)
            ramdongenerate();
    }
    void slideRight(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        boolean flagjudge = false;//判断是否有方块发生移动
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 3;j >= 1;j--)
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
                        } else if(j - k  == 2){
                            if(textView[i][j - 1].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(j - k  == 3){
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
                flagjudge = true;
                num = Integer.parseInt(textView[i][data[l][0]].getText().toString()) * 2;
                change(i,data[l][1],num);
                change(i,data[l][0],0);
                maxBlockDataJudge(num);
                score += num;
                current.setText(tran = String.valueOf(score));
                if(score > high){
                    high = score;
                    editor.putInt("highest",high);
                    highest.setText(tran = String.valueOf(high));
                    editor.apply();
                }
            }
            for(int l = 3;l > 0;l--){
                if(textView[i][l].getText().toString().equals("")){
                    for(int m = l - 1;m >= 0;m--){
                        if(!textView[i][m].getText().toString().equals("")){
                            flagjudge = true;
                            num = Integer.parseInt(textView[i][m].getText().toString());
                            change(i,l,num);
                            change(i,m,0);
                            break;
                        }
                    }
                }
            }
        }
        if(flagjudge)
            ramdongenerate();
    }
    void slideLeft(){
        int count = 0,num;
        int[][] data = new int[2][2];
        boolean[] flag = new boolean[4];
        boolean flagjudge = false;//判断是否有方块发生移动
        for(int i = 0;i < 4;i++){
            count = 0;
            flag[0] = true;
            flag[1] = true;
            flag[2] = true;
            flag[3] = true;
            for(int j = 0;j <= 2;j++)
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
                        } else if(k - j== 2){
                            if(textView[i][k - 1].getText().toString().equals("")) {
                                if(flag[k]&&flag[j]){
                                    flag[k] = false;
                                    flag[j] = false;
                                    data[count][0] = j;
                                    data[count][1] = k;
                                    count++;
                                }
                            }
                        }else if(k - j == 3){
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
                flagjudge = true;
                num = Integer.parseInt(textView[i][data[l][0]].getText().toString()) * 2;
                change(i,data[l][1],num);
                change(i,data[l][0],0);
                maxBlockDataJudge(num);
                score += num;
                current.setText(tran = String.valueOf(score));
                if(score > high){
                    high = score;
                    editor.putInt("highest",high);
                    highest.setText(tran = String.valueOf(high));
                    editor.apply();
                }
            }
            for(int l = 0;l < 3;l++){
                if(textView[i][l].getText().toString().equals("")){
                    for(int m = l + 1;m <= 3;m++){
                        if(!textView[i][m].getText().toString().equals("")){
                            flagjudge = true;
                            num = Integer.parseInt(textView[i][m].getText().toString());
                            change(i,l,num);
                            change(i,m,0);
                            break;
                        }
                    }
                }
            }
        }
        if(flagjudge)
            ramdongenerate();
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
