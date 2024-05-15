package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
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