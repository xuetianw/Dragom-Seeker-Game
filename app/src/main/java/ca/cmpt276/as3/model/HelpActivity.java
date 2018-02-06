package ca.cmpt276.as3.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This class is responsible to tell the user about the
 * author and the date the app was fully developed and
 * published. It will also tell the user about the req-
 * uired skills to play the game as efficient as possible.
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTextView();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpActivity.class);
    }


    @SuppressLint("SetTextI18n")        // ask if necessary
    private void setTextView(){
        TextView myTextView = (TextView) findViewById(R.id.aboutAuthorID);
        myTextView.setText("This game was developed for a school project at " +
                            "Simon Fraser University in Spring 2018. The developers " +
                            "of this application are Kourosh Azizi and Fred Wu.");

        myTextView = (TextView) findViewById(R.id.instructionsID);
        myTextView.setText("Instructions: Instructions: Go to Main Menu and then " +
                            "click on the Options button to choose your desired " +
                            "board size and the number of mines that you want to " +
                            "find in the game. Once you are done, go back to the " +
                            "main menu and select on the Start Game button to begin " +
                            "the game. Throughout the game, you can tap on each cell" +
                            " to perform a scan in order to find out the number of mines." +
                            " Each time you scan, there is a scan counter that will increase," +
                            " you should try to win the round with less scans as possible.");
    }


}
