package ca.cmpt276.as3.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class is responsible to tell the user about the
 * author and the date the app was fully developed and
 * published. It will also tell the user about the req-
 * uired skills to play the game as efficient as possible.
 */
public class HelpActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "Running onCreate()!");  // test
        setContentView(R.layout.activity_help);
        setTextView();
        setBackgroundImage();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpActivity.class);
    }


    @SuppressLint("SetTextI18n")        // ask if necessary
    private void setTextView(){
        TextView myTextView = (TextView) findViewById(R.id.aboutAuthorTextID);
        myTextView.setText("Mine Seeker written by Kourosh Azizi and Fred Wu. " +
                            "This application was made for a school project for CMPT 276 class at SFU ");

        myTextView = (TextView) findViewById(R.id.instructionsTextID);
        myTextView.setText("Go to Main Menu and then and click on the options" +
                            "button to choose your favorite board size and number " +
                            "of mines that you want to find in the game. Once you " +
                            "are done, go back to the menu, and select game screen" +
                            "to begin the game. In the game you need to tap on each " +
                            "cell to perform a scan in order to find out the mines. " +
                            "Keep in mind that each time you scan, there is scan counter " +
                            "that will increase to show how many times you have looked " +
                            "for a mine. You should try to win the round with least number " +
                            "of scans as possible. Compete and break your records! :)");
    }

    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.background_image3);
    }
}
