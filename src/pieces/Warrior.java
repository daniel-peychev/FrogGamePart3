package pieces;
import com.frog.board.CellCoordinates;

import java.util.Random;

import java.awt.*;

public class Warrior extends Piece {
    final int innerRadius = 30;
    final int outerRadius = 40;

    public Warrior(CellCoordinates coordinates){
        super(Team.NEUTRAL, coordinates.getRow(), coordinates.getCol());
    }

    @Override
    public void render(Graphics gr) {
        int x = marginLeft + col * cellSize + cellSize / 2 - outerRadius/2;
        int y = marginTop + row * cellSize + cellSize / 2 - outerRadius/2;

        gr.setColor(Color.red);

        gr.drawOval(x, y, outerRadius, outerRadius);

    }
}