package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    final void init() {
        setPreferredSize(new Dimension(frame.joc.getCanvasWidth(), frame.joc.getCanvasHeight()));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawStone(e.getX(), e.getY());
                repaint();
            }
            //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawStone(int x, int y) {
        for (int row = 0; row < frame.joc.getRows(); row++) {
            for (int col = 0; col < frame.joc.getCols(); col++) {
                int d = frame.joc.getStoneSize() / 2;
                int xc = frame.joc.getPadX() + col * frame.joc.getCellWidth();
                int yc = frame.joc.getPadX() + row * frame.joc.getCellHeight();
                if (xc - d < x && x < xc + d && yc - d < y && y < yc + d) {
                    if (frame.joc.touchStick(row, col)) {
                        if (frame.joc.isFreeStone(row, col)) {
                            if (frame.joc.isNear(row, col)) {
                                {
                                    frame.joc.addStone(row, col);
                                    frame.joc.setLasStoneX(row);
                                    frame.joc.setLasStoney(col);

                                    if (frame.joc.isFinish()) {
                                        frame.consolePanel.player.setText("### Player " + frame.joc.getPlayer() + " win! ###");
                                        frame.consolePanel.player.setForeground(frame.culori[frame.joc.getPlayer() - 1]);
                                    } else {
                                        frame.joc.player = frame.joc.player % 2 + 1;
                                        frame.consolePanel.changePlayer(frame.joc.getPlayer());
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, frame.joc.getCanvasWidth(), frame.joc.getCanvasHeight());
        paintGrid(g);
        paintSticks(g);
        paintStones(g);
        circleLastStone(g);
    }

    private void circleLastStone(Graphics2D g) {
        if (this.frame.joc.lasStoneX != -1 && this.frame.joc.lasStoneY != -1)
            g.setColor(Color.MAGENTA);

        int x = frame.joc.getPadX() + this.frame.joc.lasStoneX * frame.joc.getCellWidth();
        int y = frame.joc.getPadX() + this.frame.joc.lasStoneY * frame.joc.getCellHeight();
        g.setStroke(new BasicStroke(3));
        g.drawOval(y - frame.joc.getStoneSize() / 2, x - frame.joc.getStoneSize() / 2, frame.joc.getStoneSize(), frame.joc.getStoneSize());
        g.setStroke(new BasicStroke(1));
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //lines
        for (int row = 0; row < frame.joc.getRows(); row++) {
            int x1 = frame.joc.getPadX();
            int y1 = frame.joc.getPadY() + row * frame.joc.getCellHeight();
            int x2 = frame.joc.getPadX() + frame.joc.getBoardWidth();
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }
        for (int col = 0; col < frame.joc.getCols(); col++) {
            int x1 = frame.joc.getPadX() + col * frame.joc.getCellWidth();
            int y1 = frame.joc.getPadY();
            int x2 = x1;
            int y2 = frame.joc.getPadY() + frame.joc.getBoardHeight();
            g.drawLine(x1, y1, x2, y2);
        }
        //intersections
        for (int row = 0; row < frame.joc.getRows(); row++) {
            for (int col = 0; col < frame.joc.getCols(); col++) {
                int x = frame.joc.getPadX() + col * frame.joc.getCellWidth();
                int y = frame.joc.getPadX() + row * frame.joc.getCellHeight();
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - frame.joc.getStoneSize() / 2, y - frame.joc.getStoneSize() / 2, frame.joc.getStoneSize(), frame.joc.getStoneSize());
            }
        }
    }

    private void paintSticks(Graphics2D g) {
        for (int i = 0; i < 2 * frame.joc.getRows(); i++)
            for (int j = 0; j < 2 * frame.joc.getCols(); j++)
                if (frame.joc.getStick(i, j) != 0) {
                    int x = frame.joc.getPadX() + (j / 2) * frame.joc.getCellWidth();
                    int y = frame.joc.getPadY() + (i / 2) * frame.joc.getCellHeight();
                    g.setColor(Color.BLACK);
                    if (i % 2 == 1)//verticale
                        g.fillRect(x - 3, y, 6, frame.joc.getCellHeight());
                    else g.fillRect(x, y - 3, frame.joc.getCellWidth(), 6);
                }
    }

    private void paintStones(Graphics2D g) {
        for (int row = 0; row < frame.joc.getRows(); row++) {
            for (int col = 0; col < frame.joc.getCols(); col++) {
                int culoare = frame.joc.getStone(row, col);
                if (culoare != 0) {
                    g.setColor(frame.getColor(culoare));
                    int x = frame.joc.getPadX() + col * frame.joc.getCellWidth();
                    int y = frame.joc.getPadX() + row * frame.joc.getCellHeight();

                    g.fillOval(x - frame.joc.getStoneSize() / 2, y - frame.joc.getStoneSize() / 2, frame.joc.getStoneSize(), frame.joc.getStoneSize());
                }
            }
        }
    }

}