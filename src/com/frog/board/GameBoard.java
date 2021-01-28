package com.frog.board;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JFrame {
    ArrayList<Piece> pieces = new ArrayList<Piece>();

    final int cellSize = 100;

    private CellCoordinates generateWarrior(){
        int row;
        int col;
        Random rand = new Random();
        int position = rand.nextInt(4);
        if(rand.nextInt(2) == 0){
            row = 2;
            if (position < 2) {
                col = position;
            } else {
                col = position + 1;
            }
        } else {
            col = 2;
            if (position < 2) {
                row = position;
            } else {
                row = position + 1;
            }
        }
        return new CellCoordinates(row, col);
    }
    public GameBoard() {
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Graphics gr = this.getGraphics();

        // Print Pieces
        Leader leaderYellow = new Leader(Team.YELLOW, 0, 4);
        pieces.add(leaderYellow);

        //leaderYellow.render(gr);
        Leader leaderGreen = new Leader(Team.GREEN, 4, 0);
        pieces.add(leaderGreen);
        //leaderGreen.render(gr);

        for (int i = 0; i < 4; i++) {
            Guard guardYellow = new Guard(Team.YELLOW, i, 0, i);
            pieces.add(guardYellow);
            //guardYellow.render(gr);
            Guard guardGreen = new Guard(Team.GREEN, i, 4, i + 1);
            pieces.add(guardGreen);
            //guardGreen.render(gr);
        }

        //Generate Warriors
        Warrior war1 = new Warrior(generateWarrior());
        pieces.add(war1);
        Warrior war2 = new Warrior(generateWarrior());
        pieces.add(war2);
    }

    @Override
    public void paint(Graphics gr) {
        super.paint(gr);

        // Print board
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                GameCell cell = new GameCell(row, col, cellSize, gr);
                cell.render(gr);
            }
        }

        // Print pieces
        for (Piece p : pieces){
            p.render(gr);
        }

        // Print center
        int x = 50 + 2 * 100 + 50 - 25;
        int y = x;
        gr.setColor(Color.gray);
        gr.fillOval(x, y, 50, 50);
    }

    public Piece getPiece(int row, int col) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getRow() == row && pieces.get(i).getCol() == col) {
                return pieces.get(i);
            }
        }
        return null;
    }

    private void destroyPiece(CellCoordinates coordinates){
        for (int i = 0; i < pieces.size(); i++){
            if (pieces.get(i).getRow() == coordinates.getRow() && pieces.get(i).getCol() == coordinates.getCol()){
                pieces.remove(i);
                break;
            }
        }
    }

    public void move(int fromRow, int fromCol, Direction dir) {
        Piece p = getPiece(fromRow, fromCol);

        int row = fromRow;

        int col = fromCol;

        // calculate next coordinates
        while(!shouldStop(getNext(p.getRow(), p.getCol(), dir))){
            CellCoordinates next = getNext(p.getRow(), p.getCol(), dir);
            if (getPiece(next.getRow(), next.getCol()).getBelongsToTeam() == Team.NEUTRAL){
                destroyPiece(new CellCoordinates(p.getRow(), p.getCol()));
            }
            p.setCoordinates(getNext(p.getRow(), p.getCol(), dir));
        }

        paint(this.getGraphics());
    }

    private CellCoordinates getNext(int row, int col, Direction dir) {
        CellCoordinates coordinates = new CellCoordinates(row, col);
        if (dir == Direction.UP || dir == Direction.UP_LEFT || dir == Direction.UP_RIGHT)
            coordinates.setRow(row - 1);
        else if (dir == Direction.DOWN || dir == Direction.DOWN_LEFT || dir == Direction.RIGHT_DOWN) {
            coordinates.setRow(row + 1);
        }


        if (dir == Direction.RIGHT || dir == Direction.RIGHT_DOWN || dir == Direction.UP_RIGHT) {
            coordinates.setCol(col + 1);
        } else if (dir == Direction.LEFT || dir == Direction.DOWN_LEFT || dir == Direction.UP_LEFT) {
            coordinates.setCol(col - 1);
        }
        return coordinates;
    }

    private boolean shouldStop(CellCoordinates cell){
        if(cell.getRow() < 0 || cell.getRow() >= 5 ||
                cell.getCol() < 0 || cell.getCol() >=5){
            return true;
        }

        for(Piece p : pieces){
            if (p.getRow() == cell.getRow() && p.getCol() == cell.getCol()){
                return true;
            }
        }

        return false;
    }

}
