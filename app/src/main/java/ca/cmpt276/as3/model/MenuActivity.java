package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setupGameScreen();        // for game screen, options and help
        setupOptionsScreen();
        setupHelpScreen();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, MenuActivity.class);
    }

    public void setupGameScreen(){
        Button gameScreenBtn = (Button) findViewById(R.id.gameScreenID);
        gameScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"Clicked on 'Game Screen' ", Toast.LENGTH_SHORT)
                        .show();
                // Launch the Menu Activity:
                Intent intent = GameScreen.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });

//        Button optionsBtn = (Button) findViewById(R.id.optionsBtnID);
//        optionsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MenuActivity.this,"Clicked on 'Options' ", Toast.LENGTH_SHORT)
//                        .show();
//                // Launch the Menu Activity:
//                Intent intent = GameScreen.makeIntent(MenuActivity.this);
//                startActivity(intent);
//            }
//        });

//        Button helpBtn = (Button) findViewById(R.id.helpBtnID);
//        helpBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MenuActivity.this,"Clicked on 'Help' ", Toast.LENGTH_SHORT)
//                        .show();
//                // Launch the Menu Activity:
//                Intent intent = GameScreen.makeIntent(MenuActivity.this);
//                startActivity(intent);
//            }
//        });
    }

    public void setupOptionsScreen(){
        Button optionsBtn = (Button) findViewById(R.id.optionsBtnID);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"Clicked on 'Options' ", Toast.LENGTH_SHORT)
                        .show();
                // Launch the Menu Activity:
                Intent intent = OptionsScreen.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });
    }

    public void setupHelpScreen(){
        Button helpBtn = (Button) findViewById(R.id.helpBtnID);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this,"Clicked on 'Help' ", Toast.LENGTH_SHORT)
                        .show();
                // Launch the Menu Activity:
                Intent intent = HelpScreen.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });
    }

}
