package com.example.minesweeperminima;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    public int[][] bombCounter(int w,int l, int[][] board){
        for(int i = 0; i < w; i++){
            for(int j = 0; j < l; l++){

                if (board[i][j] == 10){
                    continue;
                }
                else if(i>0 && board[i-1][j]==0){
                    continue;
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random rand = new Random();
        int max = 35;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView timer = findViewById(R.id.timer);
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        final int GRID_SIZE = 11;
        final int GRID_SIZE2 = 20;
        Button[][] buttons = new Button[GRID_SIZE2][GRID_SIZE];


        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        timer.setText(Long.toString(TimeUnit.NANOSECONDS.toSeconds(endTime-startTime)));
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setColumnCount(GRID_SIZE);
        gridLayout.setRowCount(GRID_SIZE2);

        for (int i = 0; i < GRID_SIZE2; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = new Button(this);
                int padding_in_dp = 10;  // 10 dps
                final float scale = getResources().getDisplayMetrics().density;
                int padding_in_px = (int) (padding_in_dp * scale + 0.5f);
                buttons[i][j].setLayoutParams(new LinearLayout.LayoutParams(90, 90));
                buttons[i][j].setPadding(-0, 0, 0, 0);
                gridLayout.addView(buttons[i][j]);
            }
        }
        int[][] mineSweeperBS = new int[GRID_SIZE2][GRID_SIZE];
        for (int i = 0; i < max; i++) {//places 8 bombs
            int bombCoordX = rand.nextInt(GRID_SIZE);//randomizes x coord
            int bombCoordY = rand.nextInt(GRID_SIZE2);//randomizes y coord
            if (mineSweeperBS[bombCoordY][bombCoordX] == 10) { //O9 is the secret code for the bomb. So if there's a bomb already on that coord, then it loops again.
                i--;
            } else {
                mineSweeperBS[bombCoordY][bombCoordX] = 10;//Places bomb
                buttons[bombCoordY][bombCoordX].setText(String.format(String.valueOf(mineSweeperBS[bombCoordY][bombCoordX])));
            }
        }
    }
}