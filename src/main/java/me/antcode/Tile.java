package me.antcode;

public class Tile{

    /**
     * Value of Piece being either 'X', 'O', or '-'
     */
    private String value;

    //row position on board
    private final int row;

    //column position on board
    private final int col;

    //Spot value on board
    private final int spot;

    public Tile(String value, int row, int col, int spot) {
        this.value = value;
        this.row = row;
        this.col = col;
        this.spot = spot;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getSpot() {
        return spot;
    }
}
