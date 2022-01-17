package com.example.gamefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LableEnd extends AppCompatActivity {

    Button button2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lable_end);

        button2 = findViewById(R.id.button2);
        textView3 = findViewById(R.id.textView3);

        Bundle argument = getIntent().getExtras();
        String name = argument.get("hell").toString();

        textView3.setText("Вы проиграли\nВаш результат: " + name);

        button2.setOnClickListener(view -> {
            finishAndRemoveTask();
        });
    }
}