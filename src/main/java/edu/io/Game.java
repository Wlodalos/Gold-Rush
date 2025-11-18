package edu.io;

import edu.io.player.Player;
import edu.io.token.*;
import java.util.Scanner;
import java.util.Objects;

public class Game {
    private final Board board;
    private Player player;

    public Game() {
        this.board = new Board();
        board.placeToken(3, 3, new GoldToken(5.0));
        board.placeToken(6, 1, new GoldToken());
        board.placeToken(2, 2, new PickaxeToken());
        board.placeToken(5, 5, new AnvilToken());
        board.placeToken(4, 4, new WaterToken());
    }

    public void join(Player player) {
        this.player = Objects.requireNonNull(player);
        PlayerToken playerToken = new PlayerToken(player, board);
        player.assignToken(playerToken);

        player.vitals.setOnDeathHandler(() -> {
            System.out.println("Gracz umarł z odwodnienia!");
        });
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;
        PlayerToken playerToken = player.token();

        while (true) {
            board.display();
            System.out.println("Złoto: " + player.gold.amount() + " | Woda: " + player.vitals.hydration() + "%");
            System.out.print("Ruch (W/A/S/D) lub Q: ");
            command = scanner.nextLine().toUpperCase();

            if (command.equals("Q")) break;

            try {
                switch (command) {
                    case "W": playerToken.move(PlayerToken.Move.UP); break;
                    case "A": playerToken.move(PlayerToken.Move.LEFT); break;
                    case "S": playerToken.move(PlayerToken.Move.DOWN); break;
                    case "D": playerToken.move(PlayerToken.Move.RIGHT); break;
                    default: System.out.println("Nieprawidłowa komenda!");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Błąd: " + e.getMessage());
                if (e.getMessage().contains("dead")) {
                }
            }
        }
        scanner.close();
        System.out.println("Koniec gry.");
    }
}