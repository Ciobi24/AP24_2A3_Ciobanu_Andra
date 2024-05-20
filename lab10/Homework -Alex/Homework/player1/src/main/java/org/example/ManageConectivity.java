package org.example;

import java.util.Scanner;

public class ManageConectivity {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("---- Welcome to BattleShip game ----");
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.println("Welcome " + name);
        boolean isHost = false;
        while (true) {
            System.out.print("Would you like to create or join to a game? (Enter 'create' or 'join'): ");
            String type = scan.nextLine();
            if (type.equals("create")) {
                isHost = true;
                break;
            } else if (type.equals("join")) {
                break;
            } else {
                System.out.println("Error - please enter a valid option");
            }
        }

        if (isHost) {
            new Server(name);
        } else {
            System.out.print("Please enter the information for the game to connect (ip:port): ");
            String[] temp = scan.nextLine().split(":");
            String ip = temp[0];
            int port = Integer.parseInt(temp[1]);
            new Client(ip, port, name);
        }


        scan.close();
    }
}