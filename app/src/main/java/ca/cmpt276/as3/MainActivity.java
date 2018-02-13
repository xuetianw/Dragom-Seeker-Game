package ca.cmpt276.as3;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ca.cmpt276.as3.model.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    /////////////////// adding code ////////////////////

    // development:
    private void setUpAnimation(){
        LinearLayout ll = new LinearLayout(this);

        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        ll.setOrientation(LinearLayout.VERTICAL);

        final TextView tv = new TextView(this);
        tv.setText("Animation");

        final TranslateAnimation moveLefttoRight =
                new TranslateAnimation(0, 200, 0, 0);

        moveLefttoRight.setDuration(1000);
        moveLefttoRight.setFillAfter(true);
        tv.startAnimation(moveLefttoRight);

        ll.addView(tv);
        setContentView(ll);
    }

    // development:
    private void setUpImageAnimation(){
        ImageView imageAnimation = (ImageView) findViewById(R.id.animationID);
        TranslateAnimation animation = new TranslateAnimation(400.0f, 1100.0f,
                0.0f, 400.0f);
        animation.setDuration(2000);
        animation.setRepeatCount(4);
        animation.setRepeatMode(2);   // repeat animation L to R and R to L


        ImageView logo = (ImageView)findViewById(R.id.animationID);
        ViewPropertyAnimator anim = logo.animate();
        anim.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(MainActivity.this,"yooooooooo "
                        , Toast.LENGTH_SHORT).show();
                Intent intent = MenuActivity.makeIntent(MainActivity.this);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
//                Intent intent = MenuActivity.makeIntent(MainActivity.this);
//                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }

        });
        imageAnimation.startAnimation(animation);
        imageAnimation.setVisibility(View.INVISIBLE);
    }

}
