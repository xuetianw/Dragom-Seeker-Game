package ca.cmpt276.as3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import ca.cmpt276.as3.GameModel.DragonSeekerGame;
import ca.cmpt276.as3.model.R;

import static ca.cmpt276.as3.GameActivity.AppStates;

/**
 * Options class is responsible for showing the board size
 * and the number of mines that user want to select from to
 * play the game. This class is using a Singleton design
 * pattern. Also, there is a reset button that will reset the
 * user game information from the game.
 */

public class OptionsActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Log.e(TAG, "Running onCreate()!");
        setBoardSize();
        setNumDragons();
        setUpSetGameButton();
        setBackgroundImage();
        setupUserGameInfo();
        setupResetButton();
    }

    private void setupResetButton() {
        Button restBtn = (Button) findViewById(R.id.resetButtonID);
        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DragonSeekerGame.getInstance().setNumberOfGamesPlayed(0);
                SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();


                editor.putInt("466", 0);
                editor.putInt("4610", 0);
                editor.putInt("4615", 0);
                editor.putInt("4620", 0);
                editor.putInt("5106", 0);
                editor.putInt("51010", 0);
                editor.putInt("51015", 0);
                editor.putInt("51020", 0);
                editor.putInt("6156", 0);
                editor.putInt("61510", 0);
                editor.putInt("61515", 0);
                editor.putInt("61520", 0);
                editor.commit();
                setupUserGameInfo();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setupUserGameInfo() {
        TextView userGameInfoText = (TextView) findViewById(R.id.userGameInfoID);
        userGameInfoText.setText("Number of times played: "
                + DragonSeekerGame.getInstance().getNumberOfGamesPlayed());
        userGameInfoText.setText("Number of times played: N/A"
                + "\nBest score: N/A");
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
                    Toast.makeText(OptionsActivity.this, "please select game size!"
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
                    Toast.makeText(OptionsActivity.this, "please select num of mines!"
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
            button.setTextColor(getColorSateList());
            button.setButtonTintList(getColorSateList());
            button.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
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
            button.setTextColor(getColorSateList());
            button.setButtonTintList(getColorSateList());
            button.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    // set the background for options screen only
    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.chinese_new_year1);
    }

    // change the color of the selected radio buttons and circle
    private ColorStateList getColorSateList(){
        ColorStateList colorStateList = new ColorStateList(
                new int[][]{new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked}
                            },
                new int[]{Color.WHITE, Color.rgb (0,0,255),}
        );
        return colorStateList;
    }
}
