package org.example;

import java.util.Scanner;
public class Game {
    public BattleBoard board;
    public BattleBoard oppBoard;

    public Game(){
        board = new BattleBoard();
        oppBoard = new BattleBoard();
        Scanner scan = new Scanner(System.in);
        System.out.println("Here will be your generated board: \n");
        board.shipRandom();
        System.out.println("Your Board: ");
        board.printBoard();
    }

    public boolean gameOver(){
        if (board.remainingShips == 0){
            return true;
        }
        return false;
    }
}
