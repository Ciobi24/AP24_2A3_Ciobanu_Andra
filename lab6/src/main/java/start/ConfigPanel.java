package start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel
        extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner rowsSpinner;
    JSpinner colsSpinner;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        label = new JLabel("Grid size:");
         rowsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
         colsSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
         //create spinners for rows and cols, and the button
        JButton newGameButton = new JButton("Create");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getCanvas().setRows((int) rowsSpinner.getValue()+1);
                frame.getCanvas().setCols((int) colsSpinner.getValue()+1);
                frame.getCanvas().init(frame.getConfigPanel());
                frame.getCanvas().repaint();
            }
        });
        add(label); //JPanel uses FlowLayout by default
        add(rowsSpinner);
        add(colsSpinner);
        add(newGameButton);
    }
    public int getRows() {
        return (int) rowsSpinner.getValue()+1;
    }

    public int getCols() {
        return (int) colsSpinner.getValue()+1;
    }
}

