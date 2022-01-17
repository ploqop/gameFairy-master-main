package com.example.gamefairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LableWin extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lable_win);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("hello").toString();

        textView.setText("Ваш результат: " + name);

        button.setOnClickListener(view -> {
            finishAndRemoveTask();
        });
    }
}