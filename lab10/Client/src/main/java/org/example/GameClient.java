package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GameClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        if (out != null) {
            out.println(command);
            out.flush();  // Ensure the command is sent immediately
        }
    }

    public String receiveResponse() {
        try {
            if (in != null) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line).append("\n");
                    // If a specific end-of-response marker is needed, we can check for it here
                    if (line.trim().isEmpty()) { // Assuming a blank line indicates the end of the response
                        break;
                    }
                }
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String address = "localhost";
        int port = 8100;
        GameClient client = new GameClient(address, port);

        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
            String command;
            while (true) {
                System.out.print("Enter command: ");
                command = keyboard.readLine();
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                client.sendCommand(command);
                String response = client.receiveResponse();
                System.out.println(response);
                if (response != null && response.equalsIgnoreCase("Server stopped")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
