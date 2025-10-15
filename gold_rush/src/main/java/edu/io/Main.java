package edu.io;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Board board = new Board();

        board.placeToken(0, 1, new Token("💰︎"));
        board.placeToken(1, 1, new Token("💰︎"));

        Player player = new Player("Bartek", 2, 2);
        player.placeOnBoard(board);

        board.display();

        System.out.println("Gracz rusza sie w lewo:");
        player.move(-1, 0, board);
        board.display();

        System.out.println("Gracz rusza sie w gore:");
        player.move(0, -1, board);
        board.display();

        System.out.println("Gracz rusza sie w lewo:");
        player.move(-1, 0, board);
        board.display();
    }
}
