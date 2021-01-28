package pieces;

import com.frog.board.CellCoordinates;

import java.awt.*;

public abstract class Piece {
    final int marginTop = 50;
    final int marginLeft = 50;
    final int cellSize = 100;

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    int col;
    int row;

    public Team getBelongsToTeam() {
        return belongsToTeam;
    }

    public Team belongsToTeam;

    protected Piece(Team belongsToTeam, int row, int col){
        this.belongsToTeam = belongsToTeam;
        this.row = row;
        this.col = col;
    }


    public void setCoordinates(CellCoordinates cell){
        setRow(cell.getRow());
        setCol(cell.getCol());
    }

    public abstract void render(Graphics gr);
}
