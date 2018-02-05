package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This class is responsible for allowing the user
 * to play the game.
 */
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, GameActivity.class);
    }
}
