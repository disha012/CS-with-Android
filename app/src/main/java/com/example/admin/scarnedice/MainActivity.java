package com.example.admin.scarnedice;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int usertotal = 0, userturn = 0, computertotal = 0, computerturn = 0;
    private static int WIN_SCORE=100;
    private static TextView UserScore, ComputerScore, Activityscore;
    private static Button RollButton, HoldButton, ResetButton;
    private static ImageView UserImage, ComputerImage;

    final static Handler handler = new Handler();
    final static Runnable r = new Runnable() {
        public void run() {
            cpuTurn();
        }
    };
    private static int diceFaces[] = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };
    private static Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserScore = (TextView) findViewById(R.id.textView);
        ComputerScore = (TextView) findViewById(R.id.textView2);
        Activityscore = (TextView) findViewById(R.id.textView3);
        RollButton = (Button) findViewById(R.id.Roll);
        HoldButton = (Button) findViewById(R.id.Hold);
        ResetButton = (Button) findViewById(R.id.Reset);

        UserImage = (ImageView) findViewById(R.id.imageView);
        ComputerImage = (ImageView) findViewById(R.id.imageView2);
        Intent intent = getIntent();

        RollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RollDice();
            }
        });
        HoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoldDice();
            }
        });
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usertotal=0;
                userturn=0;
                computertotal=0;
                computerturn=0;
                UserImage.setImageResource(R.drawable.dice1);
                ComputerImage.setImageResource(R.drawable.dice1);
                updateScoreLabel();
                String l1="Roll/Hold Status";
                Activityscore.setText(l1);

                RollButton.setEnabled(true);
                HoldButton.setEnabled(true);
            }
        });

    }


    public static void RollDice(){
        int diceValue = rand.nextInt(6) + 1;
        if(diceValue == 1){
            Log.d("Score", "You rolled 1");
            UserImage.setImageResource(R.drawable.dice1);
            userturn = 0;
            String l1="You rolled 1\nNow comes computer's turn";
            Activityscore.setText(l1);
            updateScoreLabel();
            RollButton.setEnabled(false);
            handler.postDelayed(r, 2000);

        }
        else {
            Log.d("Score", "You rolled " + diceValue);
            UserImage.setImageResource(diceFaces[diceValue - 1]);
            userturn += diceValue;
            String l1="You rolled "+ diceValue;
            Activityscore.setText(l1);
            updateScoreLabel();
        }

    }
    public static void HoldDice(){
            usertotal = usertotal + userturn;
            userturn = 0;
            String l1="You selected to hold\nNow it's computer's turn";
            Activityscore.setText(l1);
            updateScoreLabel();
        if (usertotal >= WIN_SCORE) {
            Activityscore.setText(R.string.user_winner);
            RollButton.setEnabled(false);
            HoldButton.setEnabled(false);
            return;
        }
        handler.postDelayed(r, 2000);

    }

    public static void updateScoreLabel() {
        String lbl_userOverallScore = "Your Overall Score: ";
        String lbl_userTurnScore = "Your Turn Score: ";
        String lbl_cpuOverallScore = "Computer's Overall Score: ";
        String lbl_cpuTurnScore = "Computer's Turn Score: ";

        String UserScoreLabel = lbl_userOverallScore + usertotal + "\n"
                + lbl_userTurnScore + userturn;

        String ComputerScoreLabel =  lbl_cpuOverallScore + computertotal + "\n"
                + lbl_cpuTurnScore + computerturn;

        //String scoreLabel = UserScoreLabel + "\n" + ComputerScoreLabel;

        UserScore.setText(UserScoreLabel);
        ComputerScore.setText(ComputerScoreLabel);
    }

    private static void cpuTurn(){
        RollButton.setEnabled(false);
        HoldButton.setEnabled(false);
        int diceValue=-1, random;

        //if(diceValue!=1) {
            random = rand.nextInt(4) + 1;
            if (random == 1 || random == 2 || random == 3) {
                diceValue = rand.nextInt(6) + 1;
                if (diceValue == 1) {
                    Log.d("Score", "Computer rolled 1");
                    ComputerImage.setImageResource(R.drawable.dice1);
                    computerturn = 0;
                    updateScoreLabel();
                    String l1= "Computer rolled 1\nNow it's your turn";
                    Activityscore.setText(l1);
                    RollButton.setEnabled(true);
                    HoldButton.setEnabled(true);
                    return;
                } else {
                    Log.d("Score", "Computer rolled " + diceValue);
                    ComputerImage.setImageResource(diceFaces[diceValue - 1]);
                    computerturn += diceValue;
                    updateScoreLabel();
                    String l1 = "Computer rolled "+diceValue;
                    Activityscore.setText(l1);
                    handler.postDelayed(r, 2000);

                }
            } else {
                computertotal += computerturn;
                Log.d("Score", "CPU Overall Score: " + computertotal);
                computerturn = 0;
                String l1 = "Computer holds\nNow it's your turn";
                Activityscore.setText(l1);
                updateScoreLabel();
                RollButton.setEnabled(true);
                HoldButton.setEnabled(true);

                if (computertotal >= WIN_SCORE) {
                    Activityscore.setText(R.string.computer_wins);
                    RollButton.setEnabled(false);
                    HoldButton.setEnabled(false);
                    return;
                }
                return;

            }
        //}
    }
}
