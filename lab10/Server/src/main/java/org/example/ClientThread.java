package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private GameServer server;
    private BattleshipGame currentGame;

    public ClientThread(Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received: " + request);
                String response = handleRequest(request);
                out.println(response);
                out.flush();  // Ensure the response is sent immediately

                if (request.equalsIgnoreCase("stop")) {
                    server.stop();
                    out.println("Server stopped");
                    out.flush();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String request) {
        String[] parts = request.split(" ");
        String command = parts[0];

        switch (command) {
            case "create":
                int gameId = server.createGame();
                return "Game created with ID: " + gameId;

            case "join":
                if (parts.length < 2) {
                    return "Error: Missing game ID";
                }
                gameId = Integer.parseInt(parts[1]);
                currentGame = server.joinGame(gameId);
                if (currentGame == null) {
                    return "Error: Game not found";
                }
                return "Joined game with ID: " + gameId;

            case "start":
                if (currentGame == null) {
                    return "Error: Not joined in any game";
                }
                currentGame.startGame();
                return "Game started";

            case "attack":
                if (parts.length < 3) {
                    return "Error: Missing attack coordinates";
                }
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                if (currentGame == null) {
                    return "Error: Not joined in any game";
                }
                if (!currentGame.isGameStarted()) {
                    return "Error: Game not started yet";
                }
                String moveResult = currentGame.makeMove(x, y);
                String playerBoard = currentGame.getPlayerBoard();
                String opponentVisibleBoard = currentGame.getOpponentVisibleBoard();
                return moveResult + "\nYour Board:\n" + playerBoard + "\nOpponent's Visible Board:\n" + opponentVisibleBoard;

            default:
                return "Unknown command";
        }
    }
}
