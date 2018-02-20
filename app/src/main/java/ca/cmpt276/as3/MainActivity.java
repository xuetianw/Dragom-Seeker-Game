package ca.cmpt276.as3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ca.cmpt276.as3.GameModel.DragonSeekerGame;
import ca.cmpt276.as3.model.R;

/**
 * Main activity class is the welcome screen of
 * this application. In this class there we have
 * implemented skip button, background image, the
 * user game information, and an animation.
 */
public class MainActivity extends AppCompatActivity {
    // the animation will not skip if the user clicks on skips
    private static boolean alreadySkipped = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TAG = "OrientationDemo";
        Log.e(TAG, "Running onCreate()!");
        setBackgroundImage();
        setupSkipButton();
        setupUserGameInfo();
    }

    // It sets up the number of times the user has played the game, and user best score
    @SuppressLint("SetTextI18n")
    private void setupUserGameInfo() {
        TextView userGameInfoText = (TextView) findViewById(R.id.uesrGameInfoID);

            userGameInfoText.setText("Number of times played: "
                    + DragonSeekerGame.getInstance().getNumberOfGamesPlayed());
    }

    // Setting up the skip button and image animation
    private void setupSkipButton(){
        Button skipBtn = (Button) findViewById(R.id.skipBtnID);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alreadySkipped = true;
                Intent intent = MenuActivity.makeIntent(MainActivity.this);
                finish();
                startActivity(intent);
            }
        });

        setUpImageAnimation();
    }


    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.chinese_new_year1);
    }

    // the moving animation on the welcome screen
    private void setUpImageAnimation(){
        ImageView imageAnimation = (ImageView) findViewById(R.id.animationID);
        TranslateAnimation animation = new TranslateAnimation(400.0f, 1100.0f,
                0.0f, 400.0f);
        animation.setDuration(1000);
        animation.setRepeatCount(4);
        animation.setRepeatMode(2);                 // repeat animation L to R and R to L
        imageAnimation.startAnimation(animation);

            Handler useHandler  = new Handler();
            useHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!alreadySkipped){  // don't go to Menu if user has clicked on skip already
                        Intent intent = MenuActivity.makeIntent(MainActivity.this);
                        startActivity(intent);
                    }
                }
            },4500);
    }

    // Intent for Main activity if necessary
    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
