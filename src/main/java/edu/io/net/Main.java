package edu.io.net;

import edu.io.net.GameServerConnector;
import edu.io.net.SocketConnector;
import edu.io.net.command.Handshake;
import edu.io.net.command.JoinGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var gsc = new GameServerConnector("tcp://localhost:1313", new SocketConnector());
        gsc.connect();

        if (!gsc.isConnected()) {
            System.err.println("Could not connect to server");
            return;
        }

        System.out.println("Connected via TCP. Verifying protocol...");

        gsc.issueCommand(new Handshake.Cmd("1.1.17"), res -> {
            System.out.println("Handshake successful! Protocol version matches.");

            Scanner in = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = in.nextLine();

            gsc.issueCommand(new JoinGame.Cmd(name), joinRes -> {
                System.out.println("Join request sent for player: " + name);
                System.out.println("SERVER ACCEPTED \nYou are now in the game!");
                System.out.println("---------------------------------------------");
            });
        });

        System.out.println("Client started. Waiting for server updates...");
        System.out.println("(Press Stop button in IntelliJ to exit)");

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}