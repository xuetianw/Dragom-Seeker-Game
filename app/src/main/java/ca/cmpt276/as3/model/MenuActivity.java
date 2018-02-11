package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This class is responsible for showing the game screen,
 * options menu, and help menu on its screen so the user
 * can use for futher information.
 */
public class MenuActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "Running onCreate()!");     // test
        setContentView(R.layout.activity_menu);     // set up the screen
        setupAllButtons();                          // game, options, and help buttons
        setBackgroundImage();

    }

    public static Intent makeIntent(Context context){
        return new Intent(context, MenuActivity.class);
    }

    public void setupAllButtons(){
        Button gameScreenBtn = (Button) findViewById(R.id.gameScreenID);
        gameScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"Clicked on 'Game Screen' ", Toast.LENGTH_SHORT)
                        .show();
                // Launch the GameActivity
                Intent intent = GameActivity.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });

        Button optionsBtn = (Button) findViewById(R.id.optionsBtnID);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"Clicked on 'Options' ", Toast.LENGTH_SHORT)
                        .show();
                // Launch the OptionsActivity
                Intent intent = OptionsActivity.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });

        Button helpBtn = (Button) findViewById(R.id.helpBtnID);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"Clicked on 'Help' ", Toast.LENGTH_SHORT)
                        .show();
                // Launch the HelpActivity
                Intent intent = HelpActivity.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.background_image2);
    }
}
