package ca.cmpt276.as3.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setBoardSize();
        setNumMines();
        setupPrintButton();
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
                RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_install_boardSize);
                int idOfSelected = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(idOfSelected);
                String messageBoard = radioButton.getText().toString();

                // setting up the number of mines:
                group = (RadioGroup) findViewById(R.id.radio_group_install_mines);
                idOfSelected = group.getCheckedRadioButtonId();
                radioButton = findViewById(idOfSelected);
                String messageMine = radioButton.getText().toString();

                Toast.makeText(OptionsActivity.this, "Selected button's text is: "
                        + messageBoard + " and " + messageMine, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")        // necessary?
    private void setBoardSize(){
        RadioGroup group =(RadioGroup) findViewById(R.id.radio_group_install_boardSize);
        int [] BoardRowArray = getResources().getIntArray(R.array.board_size);

        // create the buttons:
        for(int i = 0; i < BoardRowArray.length; i++){
            final int boardRow = BoardRowArray[i];
            RadioButton button = new RadioButton(this);
            button.setText(boardRow + " " + getString(R.string.board_size));

            // TODO: Set on-click callbacks
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, "You clicked " + boardRow, Toast.LENGTH_SHORT)
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
            button.setText(numMines + " " + getString(R.string.number_of_mines));

            // TODO: Set on-click callbacks
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(OptionsActivity.this, "You clicked " + numMines, Toast.LENGTH_SHORT)
                            .show();
                }
            });

            // Add to radio group
            group.addView(button);
        }
    }
}
