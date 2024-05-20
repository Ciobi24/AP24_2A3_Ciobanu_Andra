package org.example;

import java.net.*;
import java.io.*;

public class Client extends Player {
    private String ip;
    private int port;
    private int myTime;      // Remaining time for the client player
    private int oppTime;     // Remaining time for the opponent player
    private final int totalTime = 400; // 400 seconds

    public Client(String ip, int port, String name) {
        super(name, true);
        this.ip = ip;
        this.port = port;
        this.myTime = totalTime;
        this.oppTime = totalTime;

        try {
            this.socket = new Socket(this.ip, this.port);
            updatePlayerDataStreams();
            out.writeUTF(name); //sending name to server
            this.oppName = in.readUTF(); //get name from server
            System.out.println("You have connected to challenge " + oppName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime;
        long endTime;
        int moveTime;

        while (!isOver) {
            if (myTurn) {
                startTime = System.currentTimeMillis(); // Record the start time
                sendMove();
                endTime = System.currentTimeMillis(); // Record the end time
                moveTime = (int) ((endTime - startTime) / 1000); // Calculate move time in seconds
                myTime -= moveTime; // Decrement the player's remaining time
                game.board.printBothBoards(game.oppBoard);
                System.out.println("Your remaining time: " + myTime + " seconds");
            } else {
                startTime = System.currentTimeMillis(); // the start time
                receiveMove();
                endTime = System.currentTimeMillis(); // the end time
                moveTime = (int) ((endTime - startTime) / 1000); // Calculate move time in seconds
                oppTime -= moveTime; // Decrement the remaining time for opponent
                game.board.printBothBoards(game.oppBoard);
                System.out.println(oppName + "'s remaining time: " + oppTime + " seconds");
            }
        }

        if (this.winner) {
            System.out.println("Congrats! You WOn!");
        } else {
            System.out.println(this.oppName + " Wins. Better luck next time");
        }

        closeConnection();
    }
}
