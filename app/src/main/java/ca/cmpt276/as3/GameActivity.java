package ca.cmpt276.as3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import ca.cmpt276.as3.GameModel.*;
import ca.cmpt276.as3.model.R;

/**
 * This class is responsible for allowing the user
 * to play the game.
 */
public class GameActivity extends AppCompatActivity {
    private static int numOfRows;
    private static int numOfCol;
    private static int numOfMines;
    private static int numOfRevealedMines;
    private String TAG = "OrientationDemo";
    int mineCount = 0;

    Button buttons[][] ;
    ArrayList<Integer> mineLocationList = new ArrayList();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.e(TAG, "Running onCreate()!");  // test

        numOfRevealedMines = 0;
        TextView numberOfMineTV = (TextView) findViewById(R.id.numOfRevealBomb);
        TextView numOfScansusedTV = (TextView) findViewById(R.id.numOfScans);
        numOfScansusedTV.setText("Scans used :" );



        numOfRows = MineSeekerGame.getInstance().getRow();
        numOfCol = MineSeekerGame.getInstance().getCol();
        numOfMines = MineSeekerGame.getInstance().getNumOfMine();

        addMineLocations();

        buttons = new Button[numOfRows][numOfCol];
        populateButtons();
        setBackgroundImage();
        numberOfMineTV.setText("Mine Num Total: "+ numOfMines + "\nMine Revealed: "+ numOfRevealedMines);

    }

    private void addMineLocations() {
        while (mineCount != numOfMines){
            Random rand = new Random();
            int randomRowLocation = rand.nextInt(numOfRows);
            int randomColumLocation = rand.nextInt(numOfCol);
            int ramdomLocationNum = randomRowLocation*numOfCol+ randomColumLocation;
            if (mineLocationList.isEmpty()){
                mineLocationList.add(ramdomLocationNum);
                mineCount = 1;
            } else {
                boolean found = false;
                for (int i= 0; i < mineLocationList.size(); i++){
                    if (mineLocationList.get(i) == ramdomLocationNum){
                        found = true;
                    }
                }
                if (!found){
                    mineLocationList.add(ramdomLocationNum);
                    mineCount++;
                }
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
                int location = row*numOfCol + col;
                if(checkIfinMineList(col, row)){
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            gridButtonClicked(finalCol, finalRow);
                        }
                    });
                } else {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int numOfHiddenMines = scanForHiddenMines(finalCol, finalRow);
                            button.setText(""+numOfHiddenMines);
                        }
                    });
                }


                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        Toast.makeText(this, "Button clicked: " + col + "," + row,
                Toast.LENGTH_SHORT).show();
        Button button = buttons [row][col];

        if (checkIfinMineList(col, row)){
            removeMine(col, row);
        } else {
            int numOfMinesLeftFoundInScan = scanForHiddenMines(col, row);
            button.setText("" + numOfMinesLeftFoundInScan);
        }


        TextView numOfRevealBomb = (TextView) findViewById(R.id.numOfRevealBomb);
        numOfRevealBomb.setText("Mine Num Total: "+ numOfMines + "\nMine Revealed: "+ numOfRevealedMines);

        // Lock Button Sizes: before scaling the buttons
        lockButtonSizes();


        //Scale Image to button
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bomb_image1);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));

    }

    private void removeMine(int col, int row) {
        int location = row*numOfCol + col;
        for(int i=0; i <mineLocationList.size(); i++) {
            if (mineLocationList.get(i) == location){
                mineLocationList.remove(i);
                mineCount--;
                numOfRevealedMines++;
            }
        }
    }

    private int scanForHiddenMines(int col, int row) {
        int countOfMines = 0;
        for (int i=0; i<numOfCol; i++) {
            if(checkIfinMineList(i, row)){
                countOfMines++;
            }
        }
        for (int i=0; i<numOfRows; i++) {
            if(checkIfinMineList(col, i)){
                countOfMines++;
            }
        }
        return countOfMines;
    }

    private boolean checkIfinMineList(int col, int row) {
        int location = row*numOfCol + col;
        for(int i=0; i <mineLocationList.size(); i++) {
            if (mineLocationList.get(i) == location){
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
        myImageView.setImageResource(R.drawable.background_image3);
    }
}
