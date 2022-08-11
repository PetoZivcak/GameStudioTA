package sk.Tsystems.GameStudio.minesweeper.core;

import java.util.Random;

/**
 * Field represents playing field and game logic.
 */
public class Field {
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;
    private long startMillis;

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    private final int rowCount;

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private  int mineCount;

    /**
     * Game state.
     */
    private GameState state = GameState.PLAYING;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];


        //generate the field content
        generate();
        fillWithClues();


    }


    /**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
        Tile tile = tiles[row][column];

        if (tile.getState() == Tile.State.CLOSED) {
            tile.setState(Tile.State.OPEN);
            if (tile instanceof Clue) {
                this.openTile(row, column);
            }
            if (tile instanceof Clue && ((Clue) tile).getValue() == 0) {
                openAdjacentMines(row, column);
            }
            if (tile instanceof Mine) {
                state = GameState.FAILED;
                return;
            }

            if (isSolved()) {
                state = GameState.SOLVED;
                return;
            }
            if (tile.getState()==Tile.State.MARKED){tile.setState(Tile.State.MARKED);}
        }
    }

    /**
     * Marks tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void markTile(int row, int column) {

        Tile currentTile = getTile(row, column);

        if (currentTile.getState() == Tile.State.CLOSED) {
            currentTile.setState(Tile.State.MARKED);
        } else {
            if (currentTile.getState() == Tile.State.MARKED) {
                currentTile.setState(Tile.State.CLOSED);
            }
        }

        //throw new UnsupportedOperationException("Method markTile not yet implemented");
    }

    /**
     * Generates playing field.
     */
    private void generate() {
        int plantedMine = 0;
        int randomRow = 0;
        int randomColumn = 0;
        int fieldSize=(this.rowCount)*(this.columnCount);
        if (this.mineCount <= fieldSize) {
            while (this.mineCount > plantedMine) {
                Random r = new Random();
                randomRow = r.nextInt(this.rowCount);
                randomColumn = r.nextInt(this.columnCount);
                if (tiles[randomRow][randomColumn] == null) {
                    tiles[randomRow][randomColumn] = new Mine();
                    plantedMine++;
                }

            }

//        } else {
//            for (int i = 0; i < this.rowCount; i++) {
//                for (int j = 0; j < this.columnCount; j++) {
//                    tiles[i][j] = null;
//
//                }
//
//
//            }
        }else {

            this.mineCount=0;


        }
        startMillis = System.currentTimeMillis();
    }
//public void printTiles(){
//    for (int i = 0; i < this.rowCount; i++) {
//        for (int j = 0; j < this.columnCount; j++) {
//            if(tiles[i][j]instanceof Mine){
//                System.out.print("x ");
//            }
//            if(tiles[i][j]instanceof Clue){
//                System.out.print(((Clue) tiles[i][j]).getValue()+" ");
//            }
//
//
//
//        }
//        System.out.println();
//
//    }
//
//}

    /**
     * Add clue tiles with value.
     */
    private void fillWithClues() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Clue(countAdjacentMines(i, j));
                }


            }

        }

    }


    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    private boolean isSolved() {
        int openCount = 0;
        int countTiles = (this.columnCount) * (this.rowCount);


        for (int i = 0; i < this.rowCount; i++) {
            for (int j = 0; j < this.columnCount; j++) {
                if (tiles[i][j].getState() == Tile.State.OPEN) {

                    openCount++;
                }
            }

        }

        if (countTiles - openCount == this.mineCount) {

            return true;
        }
        return false; // throw new UnsupportedOperationException("Method isSolved not yet implemented");
    }


    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) {
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    private void openAdjacentMines(int row, int column) {


        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < rowCount) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < columnCount) {
                        if (tiles[actRow][actColumn] instanceof Clue) {
                            openTile(actRow, actColumn);
                        }
                    }
                }
            }
        }


    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getMineCount() {
        return mineCount;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    public GameState getState() {
        return state;
    }

    public int getPlayTimeInSeconds(){
        return (int) ((System.currentTimeMillis() - startMillis)/1000);
    }



    public int getScore() {
        return rowCount * columnCount * 10 - getPlayTimeInSeconds();
    }




//        void markTile(int row, int column) {
//        Tile currentTile = getTile(row, column);
//
//        if (currentTile.getState() == Tile.State.CLOSED) {
//           //DOPRACUJ
//        }
//    }
}