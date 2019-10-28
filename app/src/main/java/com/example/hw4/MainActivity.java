package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addOneBtn) {
                currentNum++;
                number.setText(Integer.toString(currentNum));
            }
        }
    }

    private int currentNum = 0;
    private Button addOne;
    private TextView number;
    private Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener = new Listener();

        addOne = findViewById(R.id.addOneBtn);
        number = findViewById(R.id.number);

        addOne.setOnClickListener(listener);
    }
}
