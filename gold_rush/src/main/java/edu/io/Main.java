package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Board board = new Board();
        PlayerToken player = new PlayerToken(board);

        board.placeToken(1, 1, new GoldToken());
        board.placeToken(5, 5, new GoldToken());

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            board.display();
            System.out.print("Wprowadź ruch (W/A/S/D) lub Q aby zakończyć: ");
            command = scanner.nextLine().toUpperCase();

            if (command.equals("Q")) {
                break;
            }

            try {
                switch (command) {
                    case "W": player.move(PlayerToken.Move.UP); break;
                    case "A": player.move(PlayerToken.Move.LEFT); break;
                    case "S": player.move(PlayerToken.Move.DOWN); break;
                    case "D": player.move(PlayerToken.Move.RIGHT); break;
                    default: System.out.println("Nieprawidłowa komenda!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Koniec gry.");
    }
}