package sk.Tsystems.GameStudio.minesweeper.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.Tsystems.GameStudio.minesweeper.core.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldTest {

    private Random randomGenerator = new Random();
    private Field field;
    private int rowCount;
    private int columnCount;
    private int minesCount;


    @BeforeEach//dolezite, lebo zakazdym zainiciuje a neostavaju povodne zmeny pri otvarani a zatvarani
    public void initTest() {

//        Minesweeper m = new Minesweeper();
//        m.getPlayingSeconds() // volam metodu objektu m
//        Minesweeper.getInstance() // volam staticku metodu triedy Minesweeper
        rowCount = randomGenerator.nextInt(10) + 5;
        columnCount = rowCount;
        minesCount = Math.max(1, randomGenerator.nextInt(rowCount * columnCount));
        field = new Field(rowCount, columnCount, minesCount);
    }

    @Test
    public void checkMinesCount() {
        int minesCounter = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (field.getTile(row, column) instanceof Mine) {
                    minesCounter++;
                }
            }
        }

        assertEquals(minesCount, minesCounter, "Field was initialized incorrectly - " +
                "a different amount of mines was counted in the field than amount given in the constructor.");
    }

    @Test
    public void checkMarkTile() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                field.markTile(i, j);
                assertTrue(field.getTile(i, j).getState() == Tile.State.MARKED);
                field.markTile(i, j);
                assertTrue(field.getTile(i, j).getState() == Tile.State.CLOSED);

            }

        }
    }

    @Test
    public void checkFieldInitialization() {
        int myrowCount = rowCount;
        int myColumnCount = columnCount;
        boolean istTrue = false;

        assertTrue(columnCount == field.getColumnCount());
        assertEquals(columnCount, field.getRowCount());

        //if (columnCount==field.getColumnCount()){
        //   istTrue=true;

        // }
        //  if (columnCount==field.getRowCount()){
//
        // }
        // assertTrue(istTrue);


    }


    @Test
    public void fieldWithTooManyMines() {
        Field fieldWithTooManyMines = null;
        int higherMineCount = rowCount * columnCount + randomGenerator.nextInt(10) + 1;
        try {
            fieldWithTooManyMines = new Field(rowCount, columnCount, higherMineCount);
        } catch (Exception e) {
            // field with more mines than tiles should not be created - it may fail on exception
        }
        assertTrue((fieldWithTooManyMines == null) || (fieldWithTooManyMines.getMineCount() <= (rowCount * columnCount)));
    }

    @Test
    public void checkOpenClue() {
        int myRow = 0;
        int myColumn = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (field.getTile(i, j) instanceof Clue && ((Clue) field.getTile(i, j)).getValue() > 0) {
                    myRow = i;
                    myColumn = j;
                }


            }

        }
        field.openTile(myRow, myColumn);
        int countOpen = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (field.getTile(i, j).getState() == Tile.State.OPEN) {
                    countOpen++;
                }

            }

        }
        assertEquals(1, countOpen);
        assertEquals(field.getState(), GameState.PLAYING);

        int myRow2 = 0;
        int myColumn2 = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (field.getTile(i, j) instanceof Clue && ((Clue) field.getTile(i, j)).getValue() == 0) {
                    myRow2 = i;
                    myColumn2 = j;
                }


            }

        }
        field.openTile(myRow2, myColumn2);
        int myChecker = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (field.getTile(i, j).getState() == Tile.State.OPEN && field.getTile(i, j) instanceof Clue) {
                    myChecker = 1;
                } else {
                    myChecker = 2;
                }

            }

        }
        assertEquals(1, myChecker);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (field.getTile(i, j).getState() == Tile.State.CLOSED) {
                    field.markTile(i, j);
                    field.openTile(i, j);
                    assertTrue(field.getTile(i, j).getState() == Tile.State.MARKED);

                }

            }

        }

    }

    // ... dalsie testy
}