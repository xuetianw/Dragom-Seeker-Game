package ca.cmpt276.as3;

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
import android.widget.Toast;

import ca.cmpt276.as3.GameModel.DragonSeekerGame;
import ca.cmpt276.as3.model.R;

public class MainActivity extends AppCompatActivity {
    private static boolean alreadySkipped = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TAG = "OrientationDemo";
        Log.e(TAG, "Running onCreate()!");  // test
        setBackgroundImage();
        setupLaunchButton();
        setupTextview();

    }

    private void setupTextview() {
        TextView numberOfTimesPlayedtv = (TextView) findViewById(R.id.textView2);
        TextView bestScoretv = (TextView) findViewById(R.id.textView3);
        try{
            numberOfTimesPlayedtv.setText("number of times played: "+ DragonSeekerGame.getInstance().getNumberOfGamesPlayed());
        } catch (Exception e){
            DragonSeekerGame.getInstance().setNumberOfGamesPlayed(0);
            numberOfTimesPlayedtv.setText("number of times played: "+ DragonSeekerGame.getInstance().getNumberOfGamesPlayed());
        }

        try{
            bestScoretv.setText("best score: "+ DragonSeekerGame.getInstance().getBestScore());
        } catch (Exception e){

        }

    }

    private void setupLaunchButton(){
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

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
