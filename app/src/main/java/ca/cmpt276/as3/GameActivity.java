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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import ca.cmpt276.as3.GameModel.*;
import ca.cmpt276.as3.model.R;

/**
 * This class is responsible for allowing the user
 * to play the game.
 */
public class GameActivity extends AppCompatActivity {
    public static final String AppStates = "Game";
    public static final String NUM_OF_ROWS = "Rows";
    public static final String NUM_OF_COL = "Col";
    public static final String NUM_OF_DRAGONS = "Dragons";
    public static final String NUM_OF_REVEALED_DRAGONS = "RevealedDragons";
    public static final String NUM_OF_ROWS1 = "numRows";
    public static final String DRAGON_COUNT = "dragCount";
    public static final String SCANS_USED = "scanUsed";
    public static final String REVEALED_LIST = "reveaList";
    public static final String DRAGON_LOCATION_LIST = "dragonLionList";
    public static final String BEST_SCORE = "best score";
    public static final String NUMBER_OF_GAMES_PLAYED = "number of games played";
    private static int numOfRows;
    private static int numOfCol;
    private static int numOfDragons;
    private static int numOfRevealedDragons;
    private static int dragonCount;
    private static int scansUsed;
    private String TAG = "OrientationDemo";

    Button buttons[][] ;
    ArrayList<Integer> dragonLocationList = new ArrayList();;
    ArrayList<Integer> revealedList = new ArrayList<>();
    TextView numberOfMineTV;
    TextView numOfScansusedTV;
    SoundPool sounds;
    int sExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.e(TAG, "Running onCreate()!");  // test

        dragonCount = 0;
        scansUsed = 0;
        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        sExplosion = sounds.load(getApplicationContext(), R.raw.scansound, 1);

        numOfRevealedDragons = 0;
        updateUI();
        numOfRows = DragonSeekerGame.getInstance().getRow();
        numOfCol = DragonSeekerGame.getInstance().getCol();
        numOfDragons = DragonSeekerGame.getInstance().getNumDragons();

        setupGragons();

        buttons = new Button[numOfRows][numOfCol];
        populateButtons();
        setBackgroundImage();
        setupTextview();


    }

    private void setupTextview() {
        TextView numberOfTimesPlayedtv = (TextView) findViewById(R.id.numberofgamesid);
        numberOfTimesPlayedtv.setText("number of times played: "+ DragonSeekerGame.getInstance().getNumberOfGamesPlayed());

        TextView bestScoretv = (TextView) findViewById(R.id.bestscoreid);

        if(DragonSeekerGame.getInstance().getBestScore() != 0){
            bestScoretv.setText("best score: "+ DragonSeekerGame.getInstance().getBestScore());
        } else {
            bestScoretv.setText("best score: ");
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        numOfScansusedTV = (TextView) findViewById(R.id.numOfScans);
        numOfScansusedTV.setText(">> Scans used :" + scansUsed);
        numOfScansusedTV.setTypeface(Typeface.DEFAULT_BOLD);
        numOfScansusedTV.setTextColor(Color.BLUE);

        numberOfMineTV = (TextView) findViewById(R.id.numOfRevealDragon);
        numberOfMineTV.setText(">> Dragon Num Total: " + numOfDragons + "\n>> Dragon Revealed: " + numOfRevealedDragons);
        numberOfMineTV.setTextColor(Color.BLACK);
    }

    private void setupGragons() {
        while (dragonCount != numOfDragons){
            Random rand = new Random();
            int randomRowLocation = rand.nextInt(numOfRows);
            int randomColLocation = rand.nextInt(numOfCol);
            int randomLocationNum = randomRowLocation*numOfCol+ randomColLocation;

            boolean found = false;
            for (int i = 0; i < dragonLocationList.size(); i++){
                if (dragonLocationList.get(i) == randomLocationNum){
                    found = true;
                }
            }
            if (!found){
                dragonLocationList.add(randomLocationNum);
                dragonCount++;
            }
        }
    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int row = 0; row < numOfRows; row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);

            for (int col = 0; col < numOfCol; col++){
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //make text not clip on small buttons
                button.setPadding(0,0,0,0);

                final int finalCol = col;
                final int finalRow = row;
                //set up the Dragon Buttons
                if(isInMineList(col, row)){
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gridButtonClicked(finalCol, finalRow);
                            updateUI();
                        }
                    });
                } else { //set up the non-Dragon Buttons
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ifHasRevealed(finalCol,finalRow)){

                            } else {
                                int numOfHiddenMines = scan(finalCol, finalRow);
                                button.setText(""+numOfHiddenMines);
                                updateUI();
                            }
                        }
                    });
                }

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }



    private void gridButtonClicked(int col, int row) {
        Button button = buttons [row][col];

        if (isInMineList(col, row)){
            removeMine(col, row);
            updateRowAndColRevealedBut(col, row);
        } else {
            if(ifHasRevealed(col,row)){

            }else {
                int numOfMinesInRowAndColum = scan(col, row);
                button.setText("" + numOfMinesInRowAndColum);
            }
        }

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

    @SuppressLint("SetTextI18n")
    private void updateRowAndColRevealedBut(int col, int row) {
        //updating its row
        for (int i=0; i<numOfCol; i++){
            if(ifHasRevealed(i, row)){
                buttons[row][i].setText(""+ scan2(i,row));
            }
        }
        //updating its coloum
        for(int i = 0; i< numOfRows; i++){
            if(ifHasRevealed(col, i)){
                buttons[i][col].setText(""+ scan2(col,i));
            }
        }
    }

    private void removeMine(int col, int row) {
        int location = row*numOfCol + col;
        sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
        for(int i = 0; i < dragonLocationList.size(); i++) {
            if (dragonLocationList.get(i) == location){
                dragonLocationList.remove(i);
                dragonCount--;
                numOfRevealedDragons++;
                if (numOfRevealedDragons == numOfDragons){
                    //redraw table
                    for (int roww = 0; roww < numOfRows; roww++) {
                        for (int coll = 0; coll < numOfCol; coll++) {
                            buttons[roww][coll].setText("0");
                        }
                    }

                    getState();

                    DragonSeekerGame.getInstance().setNumberOfGamesPlayed(DragonSeekerGame.getInstance().getNumberOfGamesPlayed()+1);

                    storeGameStatuestoSharePreferences();

                    FragmentManager manager = getSupportFragmentManager();
                    MessageFragment dialog = new MessageFragment();
                    dialog.show(manager, "MessageDialog");
                    Log.i("TAG", "Just showed the dialog");

                }
            }
        }
    }



    private int scan(int col, int row) {
        sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
        scansUsed++;
        revealedList.add(row*numOfCol+ col);
        int countOfMines = 0;
        for (int i=0; i<numOfCol; i++) {
            if(isInMineList(i, row)){
                countOfMines++;
            }
        }
        for (int i=0; i<numOfRows; i++) {
            if(isInMineList(col, i)){
                countOfMines++;
            }
        }
        return countOfMines;
    }

    private int scan2(int col, int row) {
        int countOfMines = 0;
        for (int i=0; i<numOfCol; i++) {
            if(isInMineList(i, row)){
                countOfMines++;
            }
        }
        for (int i=0; i<numOfRows; i++) {
            if(isInMineList(col, i)){
                countOfMines++;
            }
        }
        return countOfMines;
    }

    private boolean ifHasRevealed(int col, int row) {
        int location = row*numOfCol + col;
        for (int i = 0; i< revealedList.size(); i++ ){
            if (revealedList.get(i) == location){
                return true;
            }
        }
        return false;
    }

    private boolean isInMineList(int col, int row) {
        int location = row*numOfCol + col;
        for(int i = 0; i < dragonLocationList.size(); i++) {
            if (dragonLocationList.get(i) == location){
                return true;
            }
        }
        return false;
    }

    private void lockButtonSizes() {
        for (int row = 0; row < numOfRows; row++){
            for (int col = 0; col < numOfCol; col++){
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
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.chinese_new_year1);
    }


    private void storeGameStatuestoSharePreferences() {
        SharedPreferences preferences = getSharedPreferences(AppStates, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(NUM_OF_ROWS, numOfRows);
        editor.putInt(NUM_OF_COL, numOfCol);
        editor.putInt(NUM_OF_DRAGONS, numOfDragons);
        editor.putInt(NUM_OF_REVEALED_DRAGONS, numOfRevealedDragons);
        editor.putInt(NUM_OF_ROWS1, numOfRows);
        editor.putInt(DRAGON_COUNT, dragonCount);
        editor.putInt(SCANS_USED, scansUsed);

        editor.putInt(BEST_SCORE, DragonSeekerGame.getInstance().getBestScore());
        editor.putInt(NUMBER_OF_GAMES_PLAYED, DragonSeekerGame.getInstance().getNumberOfGamesPlayed());


        StringBuilder dragonLocationstr = new StringBuilder();
        for(int i = 0; i< dragonLocationList.size(); i++) {
            dragonLocationstr.append(dragonLocationList.get(i)).append(",");
        }

        editor.putString(DRAGON_LOCATION_LIST, dragonLocationstr.toString());


        StringBuilder revealedListstr = new StringBuilder();
        for(int i = 0; i< revealedList.size(); i++) {
            revealedListstr.append(revealedList.get(i)).append(",");
        }

        editor.putString(REVEALED_LIST, revealedListstr.toString());

        editor.commit();
    }

    private static int getPreviousBestCore(Context context){
        SharedPreferences preferences = context.getSharedPreferences(AppStates, MODE_PRIVATE);
        return preferences.getInt(BEST_SCORE,0);
    }

    private static int getPreviousNumberOfGamePlayed(Context context){
        SharedPreferences preferences = context.getSharedPreferences(AppStates, MODE_PRIVATE);
        return preferences.getInt(NUMBER_OF_GAMES_PLAYED,0);
    }

    private void getState() {
        int previousBestcore = getPreviousBestCore(getApplicationContext());
        if(previousBestcore == 0) {
            DragonSeekerGame.getInstance().setBestScore(scansUsed);
        } else if ( scansUsed < previousBestcore) {
            DragonSeekerGame.getInstance().setBestScore(scansUsed);
        }
    }



}
