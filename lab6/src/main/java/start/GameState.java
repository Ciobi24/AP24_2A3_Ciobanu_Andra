package start;


import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonAutoDetect(creatorVisibility = ANY, fieldVisibility = ANY)
public class GameState implements Serializable {
    public int canvasWidth = 400, canvasHeight = 400;
    int padX, padY;
    int rows, cols;
    int stoneSize = 20;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int tabla[][];

    int player;
    int lasStoneX;
    int lasStoneY;
    private GameAI gameAI;

    public GameState(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        tabla = new int[2 * rows][2 * cols];

        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        this.player = 1;
        lasStoneX = -1;
        lasStoneY = -1;
        randomSticks();
    }
    public List<Move> getAvailableMoves() {
        List<Move> availableMoves = new ArrayList<>();

        // Check the positions adjacent to the last stone placed
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int newRow = lasStoneX + direction[0];
            int newCol = lasStoneY + direction[1];

            // If the position is valid and not already occupied by a stone, it's a valid move
            if (isValidPosition(newRow, newCol) && isFreeStone(newRow, newCol)) {
                availableMoves.add(new Move(newRow, newCol));
            }
        }

        return availableMoves;
    }

    private void randomSticks() {
        //int nrBete = Math.random()*(max-min)+min;
        int nrBete = 2 * rows * cols + 10;
        int i = rows, j = cols;
        if (i % 2 == j % 2)
            j++;
        while (nrBete != 0) {
            int poz = (int) (Math.random() * 119) % 6;
            int x = 0, y = 1;
            if (poz == 0) {
                x = i - 1;
                y = j - 1;
            }
            if (poz == 1) {
                x = i + 1;
                y = j - 1;
            }
            if (poz == 2) {
                x = i - 1;
                y = j + 1;
            }
            if (poz == 3) {
                x = i + 1;
                y = j + 1;
            }
            if (i % 2 == 0 && j % 2 == 1)//orizontal
            {
                if (poz == 4) {
                    x = i;
                    y = j - 2;
                }
                if (poz == 5) {
                    x = i;
                    y = j + 2;
                }
            } else {
                if (poz == 4) {
                    x = i - 2;
                    y = j;
                }
                if (poz == 5) {
                    x = i + 2;
                    y = j;
                }
            }
            if (x >= 0 && x < 2 * rows - 1 && y >= 0 && y < 2 * cols - 1) {
                tabla[x][y] = 1;
                nrBete--;
                i = x;
                j = y;
            }
        }
    }

    @JsonCreator
    @ConstructorProperties({"canvasWidth", "canvasHeight", "padX", "padY", "rows", "cols", "stoneSize", "boardWidth", "boardHeight", "cellWidth", "cellHeight", "tabla", "player", "lasStoneX", "lasStoney"})
    //public main.GameState(int canvasWidth, int canvasHeight, int padX, int padY, int rows, int cols, int stoneSize, int boardWidth, int boardHeight, int cellWidth, int cellHeight, int[][] tabla, Color[] culori) {
    public GameState(int canvasWidth, int canvasHeight, int padX, int padY, int rows, int cols, int stoneSize, int boardWidth, int boardHeight, int cellWidth, int cellHeight, int[][] tabla, int player, int lasStoneX, int lasStoney) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.padX = padX;
        this.padY = padY;
        this.rows = rows;
        this.cols = cols;
        this.stoneSize = stoneSize;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.tabla = tabla;
        this.player = player;
        this.lasStoneX = lasStoneX;
        this.lasStoneY = lasStoney;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int[][] getTabla() {
        return tabla;
    }

    public void setTabla(int[][] tabla) {
        this.tabla = tabla;
    }

    public int getPadX() {
        return padX;
    }

    public void setPadX(int padX) {
        this.padX = padX;
    }

    public int getPadY() {
        return padY;
    }

    public void setPadY(int padY) {
        this.padY = padY;
    }

    public int getStoneSize() {
        return stoneSize;
    }

    public void setStoneSize(int stoneSize) {
        this.stoneSize = stoneSize;
    }

    public int getStone(int row, int col) {
        return tabla[row * 2][col * 2];
    }

    public int getStick(int i, int j) {
        if (i % 2 != j % 2) {
            return tabla[i][j];
        }
        return 0;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getLasStoneX() {
        return lasStoneX;
    }

    public void setLasStoneX(int lasStoneX) {
        this.lasStoneX = lasStoneX;
    }

    public int getLasStoney() {
        return lasStoneY;
    }

    public void setLasStoney(int lasStoney) {
        this.lasStoneY = lasStoney;
    }

    public boolean isFreeStone(int row, int col) {
        if (tabla[row * 2][col * 2] == 0)
            return true;
        return false;
    }

    public void addStone(int row, int col) {
        tabla[row * 2][col * 2] = player;
    }

    public boolean touchStick(int row, int col) {
        row = row * 2;
        col = col * 2;
        int x, y;

        x = Math.max(row - 1, 0);
        y = col;
        if (tabla[x][y] == 1)
            return true;
        x = Math.min(row + 1, 2 * rows);
        y = col;
        if (tabla[x][y] == 1)
            return true;
        x = row;
        y = Math.max(col - 1, 0);
        if (tabla[x][y] == 1)
            return true;
        x = row;
        y = Math.min(col + 1, 2 * cols);
        if (tabla[x][y] == 1)
            return true;

        return false;
    }

    public boolean isNear(int row, int col) {

        if (lasStoneX == -1 && lasStoneY == -1)
            return true;
        if (row * 2 == lasStoneX * 2 && col * 2 == lasStoneY * 2 - 2 && tabla[row + lasStoneX][col + lasStoneY] == 1)
            return true;
        if (row * 2 == lasStoneX * 2 && col * 2 == lasStoneY * 2 + 2 && tabla[row + lasStoneX][col + lasStoneY] == 1)
            return true;
        if (row * 2 == lasStoneX * 2 - 2 && col * 2 == lasStoneY * 2 && tabla[row + lasStoneX][col + lasStoneY] == 1)
            return true;
        if (row * 2 == lasStoneX * 2 + 2 && col * 2 == lasStoneY * 2 && tabla[row + lasStoneX][col + lasStoneY] == 1)
            return true;
        return false;
    }

    public boolean isValidPosition(int x, int y) {
        if (x < 0 || y < 0 || x >= rows || y >= cols)
            return false;
        return true;
    }
    @JsonIgnore
    public boolean isFinish() {
        int x, y;
        x = lasStoneX - 1;
        y = lasStoneY;
        if (isValidPosition(x, y))
            if (isFreeStone(x, y) && touchStick(x, y) && isNear(x, y))
                return false;
        x = lasStoneX + 1;
        y = lasStoneY;
        if (isValidPosition(x, y))
            if (isFreeStone(x, y) && touchStick(x, y) && isNear(x, y))
                return false;
        x = lasStoneX;
        y = lasStoneY - 1;
        if (isValidPosition(x, y))
            if (isFreeStone(x, y) && touchStick(x, y) && isNear(x, y))
                return false;
        x = lasStoneX;
        y = lasStoneY + 1;
        if (isValidPosition(x, y))
            if (isFreeStone(x, y) && touchStick(x, y) && isNear(x, y))
                return false;
        return true;
    }
}