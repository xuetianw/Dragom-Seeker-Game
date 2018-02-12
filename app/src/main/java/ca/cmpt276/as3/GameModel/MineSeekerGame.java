package ca.cmpt276.as3.GameModel;

/**
 * Created by wu on 2/11/2018.
 */

public class MineSeekerGame {
    int row;
    int col;
    int numOfMine;

    private static MineSeekerGame instance;
    public MineSeekerGame() {
    }

    public static MineSeekerGame getInstance(){
        if(instance == null){
            instance = new MineSeekerGame();
        }
        return instance;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNumOfMine() {
        return numOfMine;
    }


    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setNumOfMine(int numOfMine) {
        this.numOfMine = numOfMine;
    }
}
