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

        gsc.issueCommand(new Handshake.Cmd("1.1.17"), res -> {
            System.out.println(res);
        });

        var in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        var name = in.nextLine();

        gsc.issueCommand(new JoinGame.Cmd(name), res -> {
            System.out.println(res);
        });

        new Scanner(System.in).nextLine();
    }
}