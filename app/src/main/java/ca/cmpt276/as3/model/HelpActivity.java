package ca.cmpt276.as3.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This class is responsible to tell the user about the
 * author and the date the app was fully developed and
 * published. It will also tell the user about the req-
 * uired skills to play the game as efficient as possible.
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpActivity.class);
    }

}
