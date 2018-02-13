package ca.cmpt276.as3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import ca.cmpt276.as3.model.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TAG = "OrientationDemo";
        Log.e(TAG, "Running onCreate()!");  // test
        setUpImageAnimation();
        setupLaunchButton();
        setImage();
        setBackgroundImage();
    }

    private void setupLaunchButton(){
        Button skipBtn = (Button) findViewById(R.id.skipBtnID);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Clicked on 'Go To Menu' "
                        , Toast.LENGTH_SHORT).show();
                // Launch the Menu Activity:
                Intent intent = MenuActivity.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.welcomeImageID);
        myImageView.setImageResource(R.drawable.welcome_image);
    }

    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.background_image3);
    }

    private void setUpImageAnimation(){
        ImageView imageAnimation = (ImageView) findViewById(R.id.animationID);
        TranslateAnimation animation = new TranslateAnimation(400.0f, 1100.0f,
                0.0f, 400.0f);
        animation.setDuration(1000);
        animation.setRepeatCount(4);
        animation.setRepeatMode(2);                 // repeat animation L to R and R to L
        imageAnimation.startAnimation(animation);
        imageAnimation.setVisibility(View.INVISIBLE);

        Handler useHandler  = new Handler();
        useHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = MenuActivity.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        },4500);
    }
}
