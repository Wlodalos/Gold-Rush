package edu.io;

import edu.io.player.Player;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();
    }
}