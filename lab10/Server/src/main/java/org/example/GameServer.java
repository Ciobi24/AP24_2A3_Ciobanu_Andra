package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class GameServer {
    private ServerSocket serverSocket;
    private Map<Integer, BattleshipGame> games;
    private int nextGameId = 1;

    public GameServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            games = new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Server started...");
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected...");
                new Thread(new ClientThread(clientSocket, this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int createGame() {
        BattleshipGame game = new BattleshipGame();
        int gameId = nextGameId++;
        games.put(gameId, game);
        return gameId;
    }

    public BattleshipGame joinGame(int gameId) {
        return games.get(gameId);
    }

    public void stop() {
        try {
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
