package com.melih.tictactoex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0 ; // 0 = 0 , 1=X

    boolean gameIsActive = true;

    int [] gameState = {2,2,2, 2,2,2, 2,2,2};

    int [][]  winnigPositions = {{0,1,2}, {0,3,6},{0,4,8}, {1,4,7}, {2,4,6}, {2,5,8}, {3,4,5},{6,7,8} };


    public void dropIn(View view) {


        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        System.out.println(" Tapped : " + tappedCounter);

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if(activePlayer==0) {
                counter.setImageResource(R.drawable.o);

                activePlayer=1;
            }

            else {
                counter.setImageResource(R.drawable.x);
                activePlayer=0;
            }

            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition : winnigPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[2]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[0]]!=2){

                    gameIsActive = false;
                    String winner = "X";
                    if( gameState[winningPosition[0]] == 0){
                        winner = "0";
                    }


                    TextView winnerMessage = (TextView) findViewById(R.id.winMessage);

                    winnerMessage.setText("Player " + winner + " has won");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameIsOver = true ;
                    for(int counterState : gameState){
                        if(counterState==2)
                            gameIsOver=false;
                    }
                    if(gameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winMessage);

                        winnerMessage.setText("Draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }

    }


    public void playAgain(View view){

        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer=0;

        for (int i=0 ; i<gameState.length; i++){
            gameState[i]=2;
        }

        GridLayout layout1 = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0 ; i<layout1.getChildCount(); i++){
            ((ImageView)layout1.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
