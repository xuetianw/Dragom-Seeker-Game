package ca.cmpt276.as3.GameModel;

/**
 * DragonSeekerGame class is a class that is for the
 * game model of this project. It is a Java class that
 * will allow the user to store in information and be
 * able to receive information later from this class.
 */
public class DragonSeekerGame {
    private int row;
    private int col;
    private int numDragons;
    private int numberOfGamesPlayed = 0;
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
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getNumDragons() {
        return numDragons;
    }
    public void setNumDragons(int numDragons) {
        this.numDragons = numDragons;
    }
    public int getNumberOfGamesPlayed() {
        return numberOfGamesPlayed;
    }
    public void setNumberOfGamesPlayed(int numberOfGamesPlayed) {
        this.numberOfGamesPlayed = numberOfGamesPlayed;
    }
}
