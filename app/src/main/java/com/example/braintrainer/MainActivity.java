package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView timer;
    TextView question;
    TextView results;
    TextView scoreText;
    int a;
    int b;

    Button button0;
    Button button1;
    Button button2;
    Button button3;


    ArrayList<Integer> answers;
    int location;
    int[] scores = {0,0}; //


    public void startGame(View view){
        goButton.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);

        results.setText("Let's go!");
        scoreText.setText(Integer.toString(scores[0]) + "/" + Integer.toString(scores[1]));




        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long l) {
                int seconds = (int) l/1000;
                timer.setText(seconds +"s");

            }

            @Override
            public void onFinish() {
                results.setText("You have scored "+ scoreText.getText().toString()+"! Try again?");
                goButton.setVisibility(View.VISIBLE);
                timer.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                scoreText.setVisibility(View.INVISIBLE);
                scores[0] = 0;
                scores[1] = 0;

            }
        }.start();
    }

    public void setQuestion(){
        answers = new ArrayList<Integer>();

        Random rand = new Random();
        a = rand.nextInt(21);
        b = rand.nextInt(21);
        question.setText(Integer.toString(a) +"+"+Integer.toString(b));
        location = rand.nextInt(4);
        for (int j=0 ; j<4 ; j++){
            if (j == location) {
                answers.add(a+b);
            } else {
                int wrongAns = rand.nextInt(40);
                while(wrongAns == a+b){
                    wrongAns = rand.nextInt(40);
                }
                answers.add(wrongAns);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAns(View view){
        String chosen = view.getTag().toString();
        if(Integer.toString(location).equals(chosen)){
            results.setText("Correct!");
            scores[0]++;
            scores[1]++;
        } else {
            results.setText("Oops!Incorrect!");
            scores[1]++;
        }
        scoreText.setText(Integer.toString(scores[0]) + "/" + Integer.toString(scores[1]));
        setQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.GoButton);
        timer = findViewById(R.id.timerText);
        question = findViewById(R.id.question);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        results = findViewById(R.id.textView4);
        scoreText = findViewById(R.id.scoreText);

        setQuestion();

    }
}