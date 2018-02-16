package ca.cmpt276.as3.GameModel;

/**
 * Created by wu on 2/11/2018.
 */

public class DragonSeekerGame {
    private int row;
    private int col;
    private int numDragons;

    private static DragonSeekerGame instance;
    public DragonSeekerGame() {}

    public static DragonSeekerGame getInstance(){
        if(instance == null){
            instance = new DragonSeekerGame();
        }
        return instance;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNumDragons() {
        return numDragons;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setNumDragons(int numDragons) {
        this.numDragons = numDragons;
    }
}
