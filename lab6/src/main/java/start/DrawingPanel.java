package start;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
@Setter
@Getter
public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;
    int canvasWidth = 400, canvasHeight = 400;
    int boardWidth, boardHeight;
    int cellWidth, cellHeight;
    int padX, padY;
    int stoneSize;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init(frame.configPanel);
    }

    final void init(ConfigPanel configPanel) {
        rows =  configPanel.getRows();
        cols =  configPanel.getCols();

        padX = stoneSize + 10;
        padY = stoneSize + 10;
        cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        boardWidth = (cols - 1) * cellWidth;
        boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
    }

    private void paintGrid(Graphics2D g) {
        stoneSize=(cellHeight+cellWidth)/4;
        g.setColor(Color.DARK_GRAY);
        // horizontal lines
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
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                if(row!=rows&&col!=cols)
                    g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }
}