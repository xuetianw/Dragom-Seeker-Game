package ca.cmpt276.as3.GameModel;

/**
 * DragonSeekerGame class is a class that is for the
 * game model of this project. It is a Java class that
 * will allow the user to store in information and be
 * able to receive information later from this class.
 */
public class DragonSeekerGame {
    private int numOfRows;
    private int numOfColumns;
    private int targetNumOfDragons;
    private static DragonSeekerGame instance;

    private DragonSeekerGame() {
    }

    public static DragonSeekerGame getInstance() {
        if (instance == null) {
            instance = new DragonSeekerGame();
        }
        return instance;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public void setNumOfColumns(int numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    public int getTargetNumOfDragons() {
        return targetNumOfDragons;
    }

    public void setTargetNumOfDragons(int targetNumOfDragons) {
        this.targetNumOfDragons = targetNumOfDragons;
    }
}
