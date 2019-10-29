package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private class Listener implements View.OnClickListener, View.OnTouchListener {

        private float initX, initY;

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.addOneBtn) {
                currentNum++;
                number.setText(Integer.toString(currentNum));
            }
            else if (view.getId() == R.id.subOneBtn) {
                currentNum--;
                number.setText(Integer.toString(currentNum));
            }
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                initX = motionEvent.getX();
                initY = motionEvent.getY();
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                float diffX = motionEvent.getX() - initX;
                float diffY = motionEvent.getY() - initY;

                if (diffX == 0 || diffY / diffX > 1.0 || diffY / diffX < -1.0 ) {
                    
                }
                else {
                    int r, g, b;
                    Random rand = new Random();

                    r = rand.nextInt();
                    g = rand.nextInt();
                    b = rand.nextInt();

                    if (diffX > 0) {
                        //slide right
                        number.setBackgroundColor(Color.rgb(r, g, b));
                    }
                    else if (diffX < 0) {
                        // slide left
                        number.setTextColor(Color.rgb(r, g, b));
                    }
                }
            }
            return true;
        }
    }

    private int currentNum = 0;
    private Button addOne, subOne;
    private TextView number;
    private Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener = new Listener();

        addOne = findViewById(R.id.addOneBtn);
        subOne = findViewById(R.id.subOneBtn);
        number = findViewById(R.id.number);

        addOne.setOnClickListener(listener);
        subOne.setOnClickListener(listener);

        number.setOnTouchListener(listener);
    }
}
