package com.example.gamefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity {

    TextView timer;
    int nextFairy = 0;
    int hearts = 3;
    int pointsInt = 0;
    TextView points;
    ImageView firstHeart;
    ImageView secondHeart;
    ImageView thirdHeart;
    long addTime = 3;
    CountDownTimer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timer = findViewById(R.id.timer);
        Intent intent1 = new Intent(this, LableWin.class);

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText("" + l/60000 + " : " + ((l/1000) - (60 * (l/60000))));
            }

            @Override
            public void onFinish() {

                timer.setText("- : -");

                if(hearts < 1){
                    finishAndRemoveTask();
                }
                else {
                    String str = "Ваш счет: " + pointsInt;
                    Toast toast = Toast.makeText(getApplicationContext(), "" + str, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    finishAndRemoveTask();
                }
            }
        }.start();



        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        firstHeart = findViewById(R.id.firstHeart);
        secondHeart = findViewById(R.id.secondHeart);
        thirdHeart = findViewById(R.id.thirdHeart);

        points = findViewById(R.id.points);

        ImageView bloom = findViewById(R.id.bloom);
        ImageView stella = findViewById(R.id.stella);
        ImageView flora = findViewById(R.id.flora);
        ImageView musa = findViewById(R.id.musa);
        ImageView tecna = findViewById(R.id.tecna);
        ImageView fairyToCatch = findViewById(R.id.fairyToCatch);
        ImageView[] fairies = {bloom, stella, flora, musa, tecna};
        Integer[] fairiesInteger = {R.drawable.bloom, R.drawable.stella, R.drawable.flora, R.drawable.musa, R.drawable.tecna};
        String[] fairiesString = {"bloom", "stella", "flora", "musa", "tecna"};

        bloom.setOnClickListener(view -> {
            CheckFairy("bloom", fairiesString);
            MoveRandomly(fairies, width, height);
            SelectNewFairy(fairiesInteger, fairyToCatch);
        });

        stella.setOnClickListener(view -> {
            CheckFairy("stella", fairiesString);
            MoveRandomly(fairies, width, height);
            SelectNewFairy(fairiesInteger, fairyToCatch);
        });

        flora.setOnClickListener(view -> {
            CheckFairy("flora", fairiesString);
            MoveRandomly(fairies, width, height);
            SelectNewFairy(fairiesInteger, fairyToCatch);
        });

        musa.setOnClickListener(view -> {
            CheckFairy("musa", fairiesString);
            MoveRandomly(fairies, width, height);
            SelectNewFairy(fairiesInteger, fairyToCatch);
        });

        tecna.setOnClickListener(view -> {
            CheckFairy("tecna", fairiesString);
            MoveRandomly(fairies, width, height);
            SelectNewFairy(fairiesInteger, fairyToCatch);
        });
    }

    void SelectNewFairy(Integer[] fairies, ImageView fairyToCatch)
    {
        Random random = new Random();
        nextFairy = random.nextInt(5);

        fairyToCatch.setImageResource(fairies[nextFairy]);
    }

    void CheckFairy(String fairy, String[] fairiesString)
    {
        if (fairy.equals(fairiesString[nextFairy]))
            AddPoint();
        else
            LoseHeart();
    }

    void AddPoint()
    {
        pointsInt++;

        points.setText("" + pointsInt);
    }

    void LoseHeart()
    {
        Intent intent2 = new Intent(this, LableEnd.class);
        hearts--;

        switch (hearts) {
            case (2):
                firstHeart.setImageAlpha(0);
                break;
            case (1):
                secondHeart.setImageAlpha(0);
                break;
            case (0): {
                thirdHeart.setImageAlpha(0);
                String str = "Ваш счет: " + pointsInt;
                Toast toast = Toast.makeText(getApplicationContext(), "" + str, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finishAndRemoveTask();
            }
        }

    }

    boolean IsFairyOverlap(ImageView first, ImageView second)
    {
        float firstPosX = first.getX();
        float secondPosX = second.getX();
        float firstPosY = first.getY();
        float secondPosY = second.getY();
        int firstWidth = first.getWidth();
        int secondWidth = second.getWidth();
        int firstHeight = first.getHeight();
        int secondHeight = second.getHeight();
        float firstScale = first.getScaleX();
        float secondScale = second.getScaleX();

        return  (((secondPosX - firstPosX < (firstWidth*firstScale)) && (firstPosX - secondPosX < (secondWidth*secondScale)) &&
                (secondPosY - firstPosY < (firstHeight*firstScale)) && (firstPosY - secondPosY < (secondHeight*secondScale))));
    }

    void MoveRandomly(ImageView[] fairies, int width, int height)
    {
        Random randomPosition = new Random();
        Random randomScale = new Random();

        while (true) {
            for (ImageView next :
                    fairies) {
                float scale = randomScale.nextInt(7) + 5;
                next.setScaleX(scale / 10);
                next.setScaleY(next.getScaleX());
                next.setX(randomPosition.nextInt((int) (width - next.getWidth())));
                next.setY(randomPosition.nextInt((int) (height - next.getHeight() - 400)) + 400);
            }

            boolean overlap = false;

            for (int i = 1; i < 5; i++)
            {
                for (int j = 0; j < i; j++)
                {
                    if (IsFairyOverlap(fairies[i], fairies[j]))
                        overlap = true;
                }
            }

            if (!overlap)
                break;

        }
    }
}