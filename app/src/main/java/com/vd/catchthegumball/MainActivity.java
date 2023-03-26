package com.vd.catchthegumball;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView myImg;
    TextView textView, textView2;
    int score;
    int screenX, screenY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;

        score = 0;

        myImg = findViewById(R.id.gumball);
        textView = findViewById(R.id.timetext);
        textView2 = findViewById(R.id.scoretext);

        startGame();
    }

    public void startGame() {
        textView2.setText("Score: " + (score = 0));
        new CountDownTimer(10000, 600) {
            public void onTick(long milisaniye) {
                textView.setText("Time: " + milisaniye / 1000);
                float rndmx = new Random().nextInt(screenX - myImg.getMeasuredWidth());
                float rndmY = new Random().nextInt(screenY - 2 * myImg.getMeasuredHeight());
                myImg.setX(rndmx);
                myImg.setY(rndmY);
            }

            public void onFinish() {
                textView.setText("Time is Finish!");
                myImg.setVisibility(View.INVISIBLE);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Do you want try again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                        myImg.setVisibility(View.VISIBLE);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_LONG);
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void imgclick(View view) {
        score++;
        textView2.setText("Score: " + score);
    }
}