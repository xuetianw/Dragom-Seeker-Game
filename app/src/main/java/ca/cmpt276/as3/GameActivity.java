package ca.cmpt276.as3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import ca.cmpt276.as3.model.R;

/**
 * This class is responsible for allowing the user
 * to play the game. It controls all the number and
 * information happening on the game screen activity.
 * It gets the information received from options acti-
 * vity to set the board size and the number of dragons
 * that the user wants to play with.
 */
public class GameActivity extends AppCompatActivity {
    public static final String AppStates                  = "Game";
    public static final String NUM_OF_ROWS                = "Rows";
    public static final String NUM_OF_COLS                = "Columns";
    public static final String NUM_OF_DRAGONS             = "Dragons";
    public static final String NUM_OF_REVEALED_DRAGONS    = "RevealedDragons";
    public static final String SCANS_USED                 = "scanUsed";
    public static final String REVEALED_LIST              = "revealedList";
    public static final String DRAGON_LOCATIONS_LIST      = "dragonLocationsList";
    public static final String NUMBER_OF_GAMES_PLAYED     = "number of games played";
    private static int numOfRows;
    private static int numOfCols;
    private static int targetNumOfDragons;
    private static int numOfRevealedDragons;
    private static int scansUsed;
    private String TAG = "OrientationDemo";

    Button[][] buttons ;
    Set<Integer> dragonLocationSet = new HashSet<>();
    Set<Integer> revealedSet = new HashSet<>();
    TextView numberOfMineTV;
    SoundPool sounds;
    int sExplosion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.e(TAG, "Running onCreate()!");  // test

        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        sExplosion = sounds.load(getApplicationContext(), R.raw.scansound, 1);

        setUpDataFromSharedPreference();

        buttons = new Button[numOfRows][numOfCols];
        populateButtons();
        setBackgroundImage();
        updateGameStatus();
        setupUserGameInfo();
        for (int location : revealedSet) {
            if (numOfRows == 0 || numOfCols == 0) return;

            int row = location / numOfCols;
            int col = location % numOfCols;
            gridButtonClicked(col, row);
        }
    }


    // user information regarding the number of times played and the current best score
    @SuppressLint("SetTextI18n")
    private void setupUserGameInfo() {
        TextView userGameInfoText = findViewById(R.id.userGameInfoID);
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);


        int bestScore = getBestScore();
        if (bestScore!= 0){
            userGameInfoText.setText(">> Number of times played: "
                    + preferences.getInt(NUMBER_OF_GAMES_PLAYED,0)
                    + "\n>> Best score: " + bestScore);
            userGameInfoText.setTextColor(Color.BLUE);

        } else {
            userGameInfoText.setText(">> Number of times played: "
                    + preferences.getInt(NUMBER_OF_GAMES_PLAYED,0)
                    + "\n>> Best score: N/A ");
            userGameInfoText.setTextColor(Color.BLUE);
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateGameStatus() {
        numberOfMineTV = findViewById(R.id.numOfRevealDragon);
        numberOfMineTV.setText(">> Dragon Num Total: " + targetNumOfDragons
                + "\n>> Dragon Revealed: " + numOfRevealedDragons
                + "\n>> Scans used :" + scansUsed);
        numberOfMineTV.setTextColor(Color.BLUE);
        numberOfMineTV.setTypeface(Typeface.DEFAULT_BOLD);

    }

    public void setupDragons() {
        while (dragonLocationSet.size() != targetNumOfDragons){
            Random rand = new Random();
            int randomLocationNum = rand.nextInt(numOfCols * numOfRows);

            dragonLocationSet.add(randomLocationNum);
        }
    }

    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        for (int row = 0; row < numOfRows; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int col = 0; col < numOfCols; col++) {
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //make text not clip on small buttons
                button.setPadding(0,0,0,0);

                final int finalCol = col;
                final int finalRow = row;

                button.setOnClickListener(view -> {
                    //set up the Dragon Buttons
                    if (isInDragonList(finalCol, finalRow)) {
                        gridButtonClicked(finalCol, finalRow);
                    } else { //set up the non-Dragon Buttons
                        if (!ifHasBeenRevealed(finalCol, finalRow)) {
                            int numOfHiddenMines = scan(finalCol, finalRow);
                            button.setText("" + numOfHiddenMines);

                        }
                    } {
                        updateGameStatus();
                        storeGameStatusToSharePreferences();
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private void gridButtonClicked(int col, int row) {
        Button button = buttons [row][col];
        removeDragon(col, row);
        updateRowAndColRevealedBut(col, row);

        // Lock Button Sizes: before scaling the buttons
        lockButtonSizes();

        //Scale Image to button
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dragon_button);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    /** this function is used when the button was not revealed*/
    private int scan(int col, int row) {
        sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
        scansUsed++;
        revealedSet.add(row * numOfCols + col);
        int countOfMines = 0;
        for (int i = 0; i < numOfCols; i++) {
            if(isInDragonList(i, row)){
                countOfMines++;
            }
        }
        for (int i = 0; i < numOfRows; i++) {
            if(isInDragonList(col, i)){
                countOfMines++;
            }
        }
        return countOfMines;
    }

    @SuppressLint("SetTextI18n")
    private void updateRowAndColRevealedBut(int col, int row) {
        // updating game board row
        for (int i = 0; i < numOfCols; i++){
            if(ifHasBeenRevealed(i, row)){
                buttons[row][i].setText("" + scan2(i, row));
            }
        }
        // updating game board column
        for(int i = 0; i < numOfRows; i++){
            if(ifHasBeenRevealed(col, i)){
                buttons[i][col].setText("" + scan2(col, i));
            }
        }
    }

    private void removeDragon(int col, int row) {
        int location = row* numOfCols + col;
        sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
        if (dragonLocationSet.contains(location)) {
            dragonLocationSet.remove(location);
            numOfRevealedDragons++;
            if (numOfRevealedDragons == targetNumOfDragons){
                // redraw the table
                for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
                    for (int colIndex = 0; colIndex < numOfCols; colIndex++) {
                        buttons[rowIndex][colIndex].setText("0");
                    }
                }

                updateNumOfGamesPlayed();
                updateBestScore();
                cleanupAfterGameFinished();

                FragmentManager manager = getSupportFragmentManager();
                MessageFragment dialog = new MessageFragment();
                dialog.show(manager, "MessageDialog");
                Log.i("TAG", "Just showed the dialog");
            }
        }
    }

    private void cleanupAfterGameFinished() {
        revealedSet.clear();
        setupDragons();
    }

    private void updateBestScore() {
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);
        String key = String.format("%d%d%d", numOfRows, numOfCols, targetNumOfDragons) ;
        SharedPreferences.Editor editor = preferences.edit();

        int prevBestScore = getBestScore();
        int newBestScore = prevBestScore == 0 ? scansUsed : Math.min(scansUsed, prevBestScore);

        editor.putInt(key, newBestScore);
        editor.apply();
    }

    private void updateNumOfGamesPlayed() {
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(NUMBER_OF_GAMES_PLAYED, preferences.getInt(NUMBER_OF_GAMES_PLAYED, 0) + 1);
        editor.apply();
    }



    private int scan2(int col, int row) {
        int countOfMines = 0;
        for (int i = 0; i < numOfCols; i++) {
            if(isInDragonList(i, row)){
                countOfMines++;
            }
        }
        for (int i = 0; i < numOfRows; i++) {
            if(isInDragonList(col, i)){
                countOfMines++;
            }
        }
        return countOfMines;
    }

    private boolean ifHasBeenRevealed(int col, int row) {
        return revealedSet.contains(row* numOfCols + col);
    }

    private boolean isInDragonList(int col, int row) {
        return dragonLocationSet.contains(row * numOfCols + col);
    }

    private void lockButtonSizes() {
        for (int row = 0; row < numOfRows; row++){
            for (int col = 0; col < numOfCols; col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, GameActivity.class);
    }

    private void setBackgroundImage(){
        ImageView myImageView = findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.chinese_new_year1);
    }


    // perform shared preferences to remember the game state
    private void storeGameStatusToSharePreferences() {
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(NUM_OF_ROWS, numOfRows);
        editor.putInt(NUM_OF_COLS, numOfCols);
        editor.putInt(NUM_OF_DRAGONS, targetNumOfDragons);
        editor.putInt(NUM_OF_REVEALED_DRAGONS, numOfRevealedDragons);
        editor.putInt(SCANS_USED, scansUsed);

        StringBuilder dragonLocationStr = new StringBuilder();
        Integer[] dragonLocationArr = dragonLocationSet.toArray(new Integer[0]);
        for(int i = 0; i < dragonLocationSet.size(); i++) {
            dragonLocationStr.append(dragonLocationArr[i]).append(",");
        }

        editor.putString(DRAGON_LOCATIONS_LIST, dragonLocationStr.toString());

        StringBuilder revealedListStr = new StringBuilder();
        Integer[] revealedsetArr = revealedSet.toArray(new Integer[0]);
        for (Integer integer : revealedsetArr) {
            revealedListStr.append(integer).append(",");
        }

        editor.putString(REVEALED_LIST, revealedListStr.toString());
        editor.apply();
    }

    private int getBestScore() {
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);

        String key = String.format("%d%d%d",numOfRows, numOfCols, targetNumOfDragons) ;
        return preferences.getInt(key, 0);
    }


    private void setUpDataFromSharedPreference() {
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);

        numOfRows = preferences.getInt(NUM_OF_ROWS, 0);
        numOfCols = preferences.getInt(NUM_OF_COLS, 0);
        targetNumOfDragons = preferences.getInt(NUM_OF_DRAGONS, 0);
        numOfRevealedDragons = preferences.getInt(NUM_OF_REVEALED_DRAGONS, 0);
        scansUsed = preferences.getInt(SCANS_USED, 0);
        String[] revealedsetArr = preferences.getString(REVEALED_LIST, "").split(",");
        String[] dragonLocationArr = preferences.getString(DRAGON_LOCATIONS_LIST, "").split(",");


        revealedSet.clear();
        if (!revealedsetArr[0].equals("")) {
            for (String str : revealedsetArr) {
                revealedSet.add(Integer.parseInt(str));
            }
        }
        dragonLocationSet.clear();
        if (!dragonLocationArr[0].equals("")) {
            for (String str : dragonLocationArr) {
                dragonLocationSet.add(Integer.parseInt(str));
            }
        }
    }

}
