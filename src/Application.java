
import javax.swing.*;

import com.frog.board.Direction;
import com.frog.board.GameBoard;
import pieces.Team;


import java.awt.*;
import java.util.Scanner;

public class Application {
    static Team turn;

    static void switchTeamTurn() {
        if (turn == Team.GREEN) {
            turn = Team.YELLOW;
        } else {
            turn = Team.GREEN;
        }
    }

    static Direction convertDirection(String direction) {
        direction = direction.toUpperCase();
        switch (direction) {
            case "U":
                return Direction.UP;
            case "UR":
            case "RU": {
                return Direction.UP_RIGHT;
            }
            case "R":
                return Direction.RIGHT;
            case "RD":
            case "DR": {
                return Direction.RIGHT_DOWN;
            }
            case "D":
                return Direction.DOWN;
            case "LD":
            case "DL":
                return Direction.DOWN_LEFT;

            case "L":
                return Direction.LEFT;
            case "UL":
            case "LU":
                return Direction.UP_LEFT;
            default:
                return null;
        }
    }

    public static class MyFrame extends JFrame {
        public MyFrame(){
            this.setSize(400,200);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setTitle("Прозорец");
            JLabel label1 = new JLabel("Край на играта! Опитай пак.");
            add(label1);
        }
    }
    public static void main(String[] args) {
        // Green is first
        turn = Team.GREEN;
        GameBoard gameBoard = new GameBoard();
        Scanner scan = new Scanner(System.in);
        //ModelWindow
        MyFrame board = new MyFrame();


        while (true) {
            int col;
            int row;
            Direction dir;
            System.out.println(turn.name() + " team's turn");
            System.out.println("Choose row: ");
            row = scan.nextInt();
            System.out.println("Choose column: ");
            col = scan.nextInt();
            // Skip new line
            scan.nextLine();
            System.out.println("Choose direction: ");
            dir = convertDirection(scan.nextLine());

            gameBoard.move(row, col, dir);
        }

        //gameBoard.move(0, 0, Direction.DOWN);
        //gameBoard.move(0, 2, Direction.DOWN);
        //gameBoard.move(4, 3, Direction.UP);

    }
}
