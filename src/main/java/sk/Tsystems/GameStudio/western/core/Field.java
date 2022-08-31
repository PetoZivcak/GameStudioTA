package sk.Tsystems.GameStudio.western.core;


import sk.Tsystems.GameStudio.minesweeper.core.Clue;

import java.util.Random;

public class Field {
    private sk.Tsystems.GameStudio.western.core.GameState state = GameState.PLAYING;
    private int nrOfRow;
    private int nrOfCol;
    private int nrOfEnemies;

    long startMillis;

    private Tile[][] tiles;
    public Field() {
    }

    public Field(int nrOfRow, int nrOfCol, int nrOfEnemies) {
        this.nrOfRow = nrOfRow;
        this.nrOfCol = nrOfCol;
        this.nrOfEnemies = nrOfEnemies;
      tiles = new Tile[nrOfRow][nrOfCol];
      generate();
      fillWithHostages();

    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getNrOfRow() {
        return nrOfRow;
    }

    public void setNrOfRow(int nrOfRow) {
        this.nrOfRow = nrOfRow;
    }

    public int getNrOfCol() {
        return nrOfCol;
    }

    public void setNrOfCol(int nrOfCol) {
        this.nrOfCol = nrOfCol;
    }

    public int getNrOfEnemies() {
        return nrOfEnemies;
    }

    public void setNrOfEnemies(int nrOfEnemies) {
        this.nrOfEnemies = nrOfEnemies;
    }

    public long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void generate() {
        int presentEnemies = 0;
        int randomRow = 0;
        int randomColumn = 0;
        int fieldSize = (this.nrOfRow) * (this.nrOfCol);
        if (this.nrOfEnemies <= fieldSize) {
            while (this.nrOfEnemies > presentEnemies) {
                Random r = new Random();
                randomRow = r.nextInt(this.nrOfCol);
                randomColumn = r.nextInt(this.nrOfCol);
                if (tiles[randomRow][randomColumn] == null) {
                    tiles[randomRow][randomColumn] = new Enemy();
                    tiles[randomRow][randomColumn].setIdentificator("ENEMY");
                    presentEnemies++;
                }

            }

        } else {
            tiles[randomRow][randomColumn].setIdentificator("HOSTAGE");
            this.nrOfEnemies = 0;


        }

    }

    private boolean isSolved() {
        int nrOfFiredEnemies = 0;
        int countTiles = (this.nrOfRow) * (this.nrOfCol);


        for (int i = 0; i < this.nrOfRow; i++) {
            for (int j = 0; j < this.nrOfCol; j++) {
                if (tiles[i][j].getState().equals(Tile.State.FIRED) && tiles[i][j].getIdentificator().equals("ENEMY")) {

                    nrOfFiredEnemies++;
                }
            }

        }

        if (nrOfFiredEnemies == this.nrOfEnemies) {

            return true;
        }
        return false; // throw new UnsupportedOperationException("Method isSolved not yet implemented");
    }

    private int nrOfkilledHostages() {
        int nrOfFiredHostages = 0;
        int countTiles = (this.nrOfRow) * (this.nrOfCol);


        for (int i = 0; i < this.nrOfRow; i++) {
            for (int j = 0; j < this.nrOfCol; j++) {
                if (tiles[i][j].getState().equals(Tile.State.FIRED) && tiles[i][j].getIdentificator().equals("HOSTAGE")) {

                    nrOfFiredHostages++;
                }
            }

        }


        return nrOfFiredHostages; // throw new UnsupportedOperationException("Method isSolved not yet implemented");
    }
    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }
    public void fireTile(int row, int column) {
        if (state == GameState.PLAYING) {
            Tile tile=getTile(row,column);
            if (tile.getState() == Tile.State.OPEN) {
                tile.setState(Tile.State.FIRED);
                if(tile.getIdentificator()=="HOSTAGE"){
                    state=GameState.FAILED;
                }
                if(isSolved()){
                    state=GameState.SOLVED;
                }
            }
        }
    }
    public int getPlayTimeInMillis(){
        return (int) ((System.currentTimeMillis() - startMillis));
    }
    public int getScore() {
        return 5000 - getPlayTimeInMillis();
    }
    private void fillWithHostages() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Hostage();
                    tiles[i][j].setIdentificator("HOSTAGE");
                }


            }

        }

    }
    public void setAllTilesClosed(){
        for (int nrRow = 0; nrRow < nrOfRow; nrRow++) {
            for (int nrCol = 0; nrCol < nrOfCol; nrCol++) {
                this.tiles[nrRow][nrCol].setState(Tile.State.CLOSED);
            }

        }
    }
    public void setAllTilesOpened(Field field){
        for (int nrRow = 0; nrRow < nrOfRow; nrRow++) {
            for (int nrCol = 0; nrCol < nrOfCol; nrCol++) {
               field.getTile(nrRow,nrCol).setState(Tile.State.OPEN);
            }

        }
        startMillis = System.currentTimeMillis();

    }

}

