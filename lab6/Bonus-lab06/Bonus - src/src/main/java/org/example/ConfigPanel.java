package org.example;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    private JLabel label;
    private JSpinner rowSpinner, colSpinner;
    private JButton newGame;
    private int rows, cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        label = new JLabel("Grid size: ");
        rowSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        colSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        newGame = new JButton("New Game");
        newGame.addActionListener(e -> {
            int readRows = (int) rowSpinner.getValue();
            int readCols = (int) colSpinner.getValue();

            rows = readRows;
            cols = readCols;

            frame.canvas.init(rows, cols);
            frame.canvas.repaint();
        });


        add(label);
        add(rowSpinner);
        add(colSpinner);
        add(newGame);
    }

}
