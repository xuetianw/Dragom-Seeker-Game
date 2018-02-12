package ca.cmpt276.as3.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.model.GameModel.MineSeekerGame;

/**
 * Options class is responsible for showing the board size
 * and the number of mines that user want to use to play
 * the game.
 */

public class OptionsActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Log.e(TAG, "Running onCreate()!");      // test
        setBoardSize();
        setNumMines();
        setupPrintButton();
        setBackgroundImage();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, OptionsActivity.class);
    }

    private void setupPrintButton() {
        Button btn1 = (Button) findViewById(R.id.printSelectedID);
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
                    Toast.makeText(OptionsActivity.this, "please select game size ", Toast.LENGTH_SHORT).show();
                }


                // setting up the number of mines:
                RadioGroup minesNumGroup = (RadioGroup) findViewById(R.id.radio_group_install_mines);
                int idOfSelectedMineNum = minesNumGroup.getCheckedRadioButtonId();
                RadioButton minesNumradioButton = findViewById(idOfSelectedMineNum);
                String messageMine = null;
                try{
                    messageMine = minesNumradioButton.getText().toString();
                }catch (Exception e){
                    Toast.makeText(OptionsActivity.this, "please select num of mines", Toast.LENGTH_SHORT).show();
                }

                if(messageMine != null && messageBoard != null){
                    switch (messageMine){
                        case "6 mines":
                            MineSeekerGame.getInstance().setNumOfMine(6);
                            break;
                        case "10 mines":
                            MineSeekerGame.getInstance().setNumOfMine(10);
                            break;
                        case "15 mines":
                            MineSeekerGame.getInstance().setNumOfMine(15);
                            break;
                        case "20 mines":
                            MineSeekerGame.getInstance().setNumOfMine(20);
                        default:
                    }
                    switch (messageBoard) {
                        case "4 rows by 6 columns":
                            MineSeekerGame.getInstance().setRow(4);
                            MineSeekerGame.getInstance().setCol(6);
                            break;
                        case "5 rows by 10 columns":
                            MineSeekerGame.getInstance().setRow(5);
                            MineSeekerGame.getInstance().setCol(10);
                            break;
                        case "6 rows by 15 columns":
                            MineSeekerGame.getInstance().setRow(6);
                            MineSeekerGame.getInstance().setCol(15);
                            break;
                    }
                }

                Toast.makeText(OptionsActivity.this, "Selected button's text is: "
                        + idOfSelectedBoardSize + " and " + idOfSelectedMineNum, Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")        // necessary?
    private void setBoardSize(){
        RadioGroup group =(RadioGroup) findViewById(R.id.radio_group_install_boardSize);
        int [] BoardRowArray = getResources().getIntArray(R.array.number_of_rows);
        int [] BoardColArray = getResources().getIntArray(R.array.number_of_columns);

        // create the buttons:
        for(int i = 0; i < BoardRowArray.length; i++){
            final int boardRow = BoardRowArray[i];
            final int boardCol = BoardColArray[i];
            RadioButton button = new RadioButton(this);
            button.setText(boardRow + " rows by " + boardCol + " columns");

            // TODO: Set on-click callbacks
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, "You clicked on " + boardRow + " rows by " + boardCol + " cols!", Toast.LENGTH_SHORT)
                            .show();
                }
            });

            // Add to radio group
            group.addView(button);
        }
    }

    @SuppressLint("SetTextI18n")        // necessary?
    private void setNumMines(){
        RadioGroup group =(RadioGroup) findViewById(R.id.radio_group_install_mines);
        int [] numMinesArray = getResources().getIntArray(R.array.number_of_mines);
        for(int i = 0; i < numMinesArray.length; i++){
            final int numMines = numMinesArray[i];
            RadioButton button = new RadioButton(this);
            button.setText(numMines + " mines");

            // TODO: Set on-click callbacks
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, "You clicked on " + numMines + " mines!", Toast.LENGTH_SHORT)
                            .show();
                }
            });

            // Add to radio group
            group.addView(button);
        }
    }

    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.background_image3);
    }
}
