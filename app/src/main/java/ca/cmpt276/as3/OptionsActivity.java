package ca.cmpt276.as3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.cmpt276.as3.GameModel.Dragon;
import ca.cmpt276.as3.GameModel.DragonSeekerGame;
import ca.cmpt276.as3.model.R;

/**
 * Options class is responsible for showing the board size
 * and the number of mines that user want to use to play
 * the game.
 */

public class OptionsActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Log.e(TAG, "Running onCreate()!");      // test
        setBoardSize();
        setNumDragons();
        setUpSetGameButton();
        setBackgroundImage();
        setupTextview();
    }

    private void setupTextview() {
        TextView numberOfTimesPlayedtv = (TextView) findViewById(R.id.textView10);
        numberOfTimesPlayedtv.setText("number of times played: "+ DragonSeekerGame.getInstance().getNumberOfGamesPlayed());

        TextView bestScoretv = (TextView) findViewById(R.id.textView11);

        if(DragonSeekerGame.getInstance().getBestScore() != 0){
            bestScoretv.setText("best score: "+ DragonSeekerGame.getInstance().getBestScore());
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsActivity.class);
    }

    private void setUpSetGameButton() {
        Button btn1 = (Button) findViewById(R.id.setGameID);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setting up the board size:
                RadioGroup boardSizeGroup = (RadioGroup) findViewById(R.id.radio_group_install_boardSize);
                int idOfSelectedBoardSize = boardSizeGroup.getCheckedRadioButtonId();
                RadioButton boardRadioButton = findViewById(idOfSelectedBoardSize);
                String messageBoard = null;
                try{
                    messageBoard = boardRadioButton.getText().toString();
                    System.out.println(messageBoard);
                }catch (Exception e){
                    Toast.makeText(OptionsActivity.this, "please select game size "
                            , Toast.LENGTH_SHORT).show();
                }

                // setting up the number of mines:
                RadioGroup dragonsNumGroup = (RadioGroup) findViewById(R.id.radio_group_install_dragons);
                int idOfSelectedDragonNum = dragonsNumGroup.getCheckedRadioButtonId();
                RadioButton dragonsNumRadioButton = findViewById(idOfSelectedDragonNum);
                String messageDragon = null;
                try{
                    messageDragon = dragonsNumRadioButton.getText().toString();
                }catch (Exception e){
                    Toast.makeText(OptionsActivity.this, "please select num of mines"
                            , Toast.LENGTH_SHORT).show();
                }

                if(messageDragon != null && messageBoard != null){
                    switch (messageDragon){
                        case "6 dragons":
                            DragonSeekerGame.getInstance().setNumDragons(6);
                            break;
                        case "10 dragons":
                            DragonSeekerGame.getInstance().setNumDragons(10);
                            break;
                        case "15 dragons":
                            DragonSeekerGame.getInstance().setNumDragons(15);
                            break;
                        case "20 dragons":
                            DragonSeekerGame.getInstance().setNumDragons(20);
                        default:
                    }
                    switch (messageBoard) {
                        case "4 rows by 6 columns":
                            DragonSeekerGame.getInstance().setRow(4);
                            DragonSeekerGame.getInstance().setCol(6);
                            break;
                        case "5 rows by 10 columns":
                            DragonSeekerGame.getInstance().setRow(5);
                            DragonSeekerGame.getInstance().setCol(10);
                            break;
                        case "6 rows by 15 columns":
                            DragonSeekerGame.getInstance().setRow(6);
                            DragonSeekerGame.getInstance().setCol(15);
                            break;
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    private void setBoardSize(){
        RadioGroup group =(RadioGroup) findViewById(R.id.radio_group_install_boardSize);
        int [] BoardRowArray = getResources().getIntArray(R.array.number_of_rows);
        int [] BoardColArray = getResources().getIntArray(R.array.number_of_columns);

        // create the buttons:
        for(int i = 0; i < BoardRowArray.length; i++){
            final int boardRow = BoardRowArray[i];
            final int boardCol = BoardColArray[i];
            RadioButton button = new RadioButton(this);
            button.setTextColor(Color.WHITE);

            button.setText(boardRow + " rows by " + boardCol + " columns");

            // Add to radio group
            group.addView(button);
            button.setTextColor(Color.BLUE);
            button.setTextColor(getColorRadioText());
            button.setButtonTintList(getColorRadioCircle());
            button.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")        // necessary?
    private void setNumDragons(){
        RadioGroup group =(RadioGroup) findViewById(R.id.radio_group_install_dragons);
        int [] numDragonsArray = getResources().getIntArray(R.array.number_of_dragons);
        for(int i = 0; i < numDragonsArray.length; i++){
            final int NUM_DRAGONS = numDragonsArray[i];
            RadioButton button = new RadioButton(this);
            button.setText(NUM_DRAGONS + " dragons");
            button.setTextColor(Color.WHITE);

            // Add to radio group
            group.addView(button);
            button.setTextColor(Color.BLUE);
            button.setTextColor(getColorRadioText());
            button.setButtonTintList(getColorRadioCircle());
            button.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    // set the background for options screen only
    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.chinese_new_year1);
    }

    // change the color of the selected radio button
    private ColorStateList getColorRadioText(){
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked}
                            },
                new int[]{Color.WHITE, Color.rgb (0,0,255),}
        );
        return colorStateList;
    }

    // change the color of the selected radio button
    private ColorStateList getColorRadioCircle(){
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{Color.WHITE, Color.rgb (0,0,255),}
        );
        return colorStateList;
    }
}
