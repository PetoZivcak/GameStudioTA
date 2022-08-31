package sk.Tsystems.GameStudio.western.core;

public abstract class Tile {
    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }
private  int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private String identificator;
    private State state = State.CLOSED;

    /** Tile states. */
    public enum State {
        /** Open tile. */
        OPEN,
        /** Closed tile. */
        CLOSED,
        FIRED,

    }

    /** Tile state. */


    /**
     * Returns current state of this tile.
     * @return current state of this tile
     */
    public sk.Tsystems.GameStudio.western.core.Tile.State getState() {
        return state;
    }

    /**
     * Sets current current state of this tile.
     * @param state current state of this tile
     */
    public void setState(sk.Tsystems.GameStudio.western.core.Tile.State state) {
        this.state = state;
    }
}
