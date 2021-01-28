package com.frog.board;

public class CellCoordinates {
    private int row;
    private int col;

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public CellCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public CellCoordinates(){
        super();
    }

    public void setRow(int row) {
        this.row = row;
    }
}
