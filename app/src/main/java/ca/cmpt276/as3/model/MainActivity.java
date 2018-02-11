package ca.cmpt276.as3.model;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "Running onCreate()!");  // test
        setupLaunchButton();
        setImage();
    }

    private void setupLaunchButton(){
        Button skipBtn = (Button) findViewById(R.id.skipBtnID);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Clicked on 'Go To Menu' ", Toast.LENGTH_SHORT)
                        .show();
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

}
