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

            isTargetReached();
        }

        /*
        OnTouch:
            Use slope of the vector formed by initial position and last position
            to decide whether it is vertical or horizontal. Then decide it direction.

            slide up    ---->   currentNum * 2
            slide down  ---->   currentNum + 10
            slide right ---->   random background color
            slide left  ---->   random text color

            To be careful: The coordinate should be like this

                phone
                ------------
                |       X  |
                |     ---> |
                |     |    |
                |     |    |
                |  Y  V    |
                |          |
                ------------
         */

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
                    if (diffY < 0) {
                        //slide up
                        currentNum *= 2;
                        number.setText(Integer.toString(currentNum));
                    }
                    else {
                        //slide down
                        currentNum += 10;
                        number.setText(Integer.toString(currentNum));
                    }
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

            isTargetReached();

            return true;
        }
    }

    private int currentNum = 0, target;
    private Button addOne, subOne;
    private TextView number, targetNum;
    private Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener = new Listener();

        addOne = findViewById(R.id.addOneBtn);
        subOne = findViewById(R.id.subOneBtn);
        number = findViewById(R.id.number);
        targetNum = findViewById(R.id.targetNum);

        addOne.setOnClickListener(listener);
        subOne.setOnClickListener(listener);

        number.setOnTouchListener(listener);

        Random rand = new Random();
        target = Math.abs(rand.nextInt() % 500);
        targetNum.setText(targetNum.getText() + Integer.toString(target));
    }

    private void isTargetReached() {
        if (currentNum == target) {
            targetNum.setText("Congratulation!!\n" + "You reach the target!!");
        }
    }

    private void reset() {
        //TODO
    }
}
