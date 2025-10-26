package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import java.util.Scanner;

public class Game {
    private final Board board;
    private Player player;

    public Game() {
        this.board = new Board();
        board.placeToken(3, 3, new GoldToken(5.0));
        board.placeToken(6, 1, new GoldToken());
    }

    public void join(Player player) {
        this.player = player;
        PlayerToken playerToken = new PlayerToken(player, board);
        player.assignToken(playerToken);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;
        PlayerToken playerToken = player.token();

        while (true) {
            board.display();
            System.out.println("Posiadane złoto: " + player.gold());
            System.out.print("Wprowadź ruch (W/A/S/D) lub Q aby zakończyć: ");
            command = scanner.nextLine().toUpperCase();

            if (command.equals("Q")) {
                break;
            }

            try {
                switch (command) {
                    case "W": playerToken.move(PlayerToken.Move.UP); break;
                    case "A": playerToken.move(PlayerToken.Move.LEFT); break;
                    case "S": playerToken.move(PlayerToken.Move.DOWN); break;
                    case "D": playerToken.move(PlayerToken.Move.RIGHT); break;
                    default: System.out.println("Nieprawidłowa komenda!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("Błąd: " + e.getMessage());
                break;
            }
        }
        scanner.close();
        System.out.println("Koniec gry. Zebrano łącznie: " + player.gold() + " złota.");
    }
}