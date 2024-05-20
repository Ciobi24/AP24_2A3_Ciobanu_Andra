package org.example;
import java.util.Arrays;
import java.util.Random;

public class BattleshipGame {
    private char[][] playerBoard;
    private char[][] opponentBoard;
    private char[][] opponentVisibleBoard;
    private static final int BOARD_SIZE = 10;
    private static final char EMPTY = '~';
    private static final char SHIP = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'O';
    private boolean gameStarted = false;
    private boolean gameOver = false;
    private int playerMovesLeft = 30;
    private int opponentMovesLeft = 30;
    private Random random;

    public BattleshipGame() {
        playerBoard = new char[BOARD_SIZE][BOARD_SIZE];
        opponentBoard = new char[BOARD_SIZE][BOARD_SIZE];
        opponentVisibleBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(playerBoard[i], EMPTY);
            Arrays.fill(opponentBoard[i], EMPTY);
            Arrays.fill(opponentVisibleBoard[i], EMPTY);
        }
        random = new Random();
        generateRandomBoard(playerBoard);
        generateRandomBoard(opponentBoard);
    }

    private void generateRandomBoard(char[][] board) {
        int[] shipSizes = {5, 4, 3, 3, 2}; // Sizes of ships
        for (int size : shipSizes) {
            placeRandomShip(board, size);
        }
    }

    private boolean placeRandomShip(char[][] board, int size) {
        boolean placed = false;
        while (!placed) {
            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);
            boolean horizontal = random.nextBoolean();
            placed = placeShip(board, x, y, size, horizontal);
        }
        return placed;
    }

    private boolean placeShip(char[][] board, int x, int y, int size, boolean horizontal) {
        if (horizontal) {
            if (x + size > BOARD_SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (board[x + i][y] != EMPTY) return false;
            }
            for (int i = 0; i < size; i++) {
                board[x + i][y] = SHIP;
            }
        } else {
            if (y + size > BOARD_SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (board[x][y + i] != EMPTY) return false;
            }
            for (int i = 0; i < size; i++) {
                board[x][y + i] = SHIP;
            }
        }
        return true;
    }

    public String makeMove(int x, int y) {
        if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
            return "Move out of bounds";
        }
        if (gameOver) {
            return "Game over. No more moves allowed.";
        }
        if (opponentBoard[x][y] == SHIP) {
            opponentBoard[x][y] = HIT;
            opponentVisibleBoard[x][y] = HIT;
            if (allShipsSunk(opponentBoard)) {
                gameOver = true;
                return "Hit! You sank all ships. You win!";
            }
            return "Hit!";
        } else {
            opponentBoard[x][y] = MISS;
            opponentVisibleBoard[x][y] = MISS;
            playerMovesLeft--;
            if (playerMovesLeft <= 0) {
                gameOver = true;
                return "Miss! Out of moves. Game over.";
            }
            return "Miss!";
        }
    }

    public boolean allShipsSunk(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == SHIP) return false;
            }
        }
        return true;
    }

    public void startGame() {
        gameStarted = true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public String getPlayerBoard() {
        return boardToString(playerBoard);
    }

    public String getOpponentVisibleBoard() {
        return boardToString(opponentVisibleBoard);
    }

    private String boardToString(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            for (char cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
