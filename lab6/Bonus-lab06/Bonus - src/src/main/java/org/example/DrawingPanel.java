package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.awt.Color.BLUE;
import static java.awt.Color.blue;

public class DrawingPanel extends JPanel implements Serializable{
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 500, canvasHeight = 500;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize = 20;

    int[][] adjacency;
    List <Stone> stones, blueStones, redStones;
    int playerTurn;

    public int addStone(int x, int y, int index){
        for(Stone stone : stones){
            if(stone.getX() == x && stone.getY() == y){
                return stone.getIndex();
            }
        }
        stones.add(new Stone(index, x, y, 0));
        return -1;
    }

    private void manageStone(Graphics g, Color color, int x, int y, Stone stone, List<Stone> colStones){
        g.setColor(color);
        g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
        playerTurn *= (-1);
        stone.setColor(-1);
        blueStones.clear();
        redStones.clear();

          redStones.add(stone);
          blueStones.add(stone);
          stones.remove(stone);
    }

    private void checkWinner(Color color, List<Stone> colStones){
        boolean gameOver = true;

        for(Stone selectedRed : colStones){
            for(Stone unassignedStone : stones){
                if(adjacency[selectedRed.getIndex()][unassignedStone.getIndex()] == 1){
                    gameOver = false;
                    System.out.println("evaluated moves and game goes on");
                    break;
                }
            }
        }
        if(gameOver){
            if(color == Color.RED){
                System.out.println("RED WINS!");
                JOptionPane.showMessageDialog(null, "RED WINS!");
            }
            else{
                System.out.println("BLUE WINS!");
                JOptionPane.showMessageDialog(null, "BLUE WINS!");
            }

            System.exit(0);
        }
    }

    public void performAIMove() {
        if (playerTurn != -1) {
            return; // AI's turn only when playerTurn is 1 (Blue's turn)
        }

        // Select the specific stone, in this case the only one... in the past I had another idea, but I changed
        Stone selectedStone = stones.get(new Random().nextInt(stones.size()));

        // Find available adjacent positions for the red stone
        if(redStones.isEmpty()){
            manageStone(getGraphics(), Color.RED, selectedStone.getX(), selectedStone.getY(), selectedStone, redStones);
        }
        else {
            while(true){
                for (Stone stone : redStones) {
                    if (adjacency[selectedStone.getIndex()][stone.getIndex()] == 1) {
                        manageStone(getGraphics(), Color.RED, selectedStone.getX(), selectedStone.getY(), selectedStone, redStones);
                        return;
                    }
                }
                selectedStone = stones.get(new Random().nextInt(stones.size()));
            }
        }
    }

    public DrawingPanel(MainFrame mainFrame) {
        this.frame = mainFrame;
        init(frame.configPanel.getRows(), frame.configPanel.getCols());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                int mouseX = e.getX();
                int mouseY = e.getY();

                for (Stone selectedStone : stones) {
                    int x = selectedStone.getX();
                    int y = selectedStone.getY();

                    double distance = Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));
                    if (distance <= (double) stoneSize / 2) {
                        //map of pair coordinates?
                        Graphics g = getGraphics();

                        if(blueStones.isEmpty()){
                            manageStone(g, BLUE, x, y, selectedStone, blueStones);
                            checkWinner(Color.RED, blueStones);
                            performAIMove(); // Call the AI move after the player's move
                            checkWinner(BLUE, redStones);
                            return;
                        }

                        for (Stone stone : blueStones) {
                            if (adjacency[selectedStone.getIndex()][stone.getIndex()] == 1) {
                                manageStone(g, BLUE, x, y, selectedStone, blueStones);
                                checkWinner(Color.RED, blueStones);
                                performAIMove(); // Call the AI move after the player's move
                                checkWinner(BLUE, redStones);
                                return;
                            }
                        }
                        break;
                    }
                }
                System.out.println("That isn't a stone");
            }
        });
    }

    final void init(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);

        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
    }
    private void paintGrid(Graphics2D g) {
        int stoneIndex = 0;
        adjacency = new int[rows * cols][rows * cols];
        stones = new ArrayList<>();
        redStones = new ArrayList<>();
        blueStones = new ArrayList<>();
        playerTurn = 1;

        g.setColor(Color.BLACK);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }

        for(int row = 0; row < rows; ++row){
            for(int col = 0; col < cols; ++col){
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                if(-1 == addStone(x, y, stoneIndex)){
                    stoneIndex++;
                }
                g.setColor(Color.BLACK);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }

        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;

                int downIndex, upIndex, leftIndex, rightIndex, currentIndex = addStone(x, y, stoneIndex);

                double chance = Math.random();
                if(chance < 0.3){//right stick
                    if(col < cols-1){
                        int x2 = padX + (col+1)*cellWidth;
                        g.setStroke(new BasicStroke(6.0F));
                        g.drawLine(x, y, x2, y);
                        g.setStroke(new BasicStroke(1));

                        rightIndex = addStone(x2, y, stoneIndex);
                        if(rightIndex == -1){
                            rightIndex=stoneIndex;
                            stoneIndex++;
                        }
                        adjacency[rightIndex][currentIndex] = adjacency[currentIndex][rightIndex] = 1;
                    }
                }

                chance = Math.random();
                if(chance < 0.3){//left stick
                    if(col > 0){
                        int x2 = padX + (col-1)*cellWidth;
                        g.setStroke(new BasicStroke(6.0F));
                        g.drawLine(x2, y, x, y);
                        g.setStroke(new BasicStroke(1));

                        leftIndex = addStone(x2, y, stoneIndex);
                        if(leftIndex == -1) {
                            leftIndex = stoneIndex;

                            stoneIndex++;
                        }
                        adjacency[leftIndex][currentIndex] = adjacency[currentIndex][leftIndex] = 1;
                    }
                }

                chance = Math.random();
                if(chance < 0.3){//down stick
                    if(row < rows-1){
                        int y2 = padY + (row+1)*cellHeight;
                        g.setStroke(new BasicStroke(6.0F));
                        g.drawLine(x, y, x, y2);
                        g.setStroke(new BasicStroke(1));

                        downIndex = addStone(x, y2, stoneIndex);
                        if(downIndex == -1){
                            downIndex=stoneIndex;
                            stoneIndex++;
                        }
                        adjacency[downIndex][currentIndex] = adjacency[currentIndex][downIndex] = 1;
                    }
                }

                chance = Math.random();
                if(chance < 0.3){//up stick
                    if(row > 0){
                        int y2 = padY + (row-1)*cellHeight;
                        g.setStroke(new BasicStroke(6.0F));
                        g.drawLine(x, y2, x, y);
                        g.setStroke(new BasicStroke(1));

                        upIndex = addStone(x, y2, stoneIndex);
                        if(upIndex == -1){
                            upIndex =stoneIndex;
                            stoneIndex++;
                        }
                        adjacency[upIndex][currentIndex] = adjacency[currentIndex][upIndex] = 1;
                    }
                }

            }
        }
    }

    public void exportImageToPNG(File file) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        List<Stone> copyRedStones = new ArrayList<>(redStones);
        List<Stone> copyBlueStones = new ArrayList<>(blueStones);

        paintComponent(g);

        for (Stone stone : copyRedStones) {
            int x = stone.getX();
            int y = stone.getY();
            g.setColor(Color.RED);
            g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
        }

        for (Stone stone : copyBlueStones) {
            int x = stone.getX();
            int y = stone.getY();
            g.setColor(BLUE);
            g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
        }

        g.dispose();
        try {
            ImageIO.write(image, "PNG", file);
            System.out.println("Image exported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error exporting image: " + e.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

}

