package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.e(TAG, "Running onCreate()!");  // test
        setContentView(R.layout.activity_menu);
        setupAllButtons();        // game, options, and help buttons
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
                // Launch the Menu Activity:
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
                // Launch the Menu Activity:
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
                // Launch the Menu Activity:
                Intent intent = HelpActivity.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });
    }
}
