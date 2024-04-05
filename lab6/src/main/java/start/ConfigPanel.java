package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinnerR;
    JSpinner spinnerC;
    JButton buttonGenerare;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());
        //create the label and the spinner
        label = new JLabel("Grid size:");
        spinnerR = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerC = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        buttonGenerare = new JButton("Generare Harta");
        buttonGenerare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.joc = new GameState(getRows(), getCols());
                frame.consolePanel.changePlayer(1);
                frame.repaint();
            }
        });
        add(label); //JPanel uses FlowLayout by default
        add(spinnerR);
        add(spinnerC);
        add(buttonGenerare);
        //frame.pack();
    }

    public int getRows() {
        return (int) spinnerR.getValue();
    }

    public int getCols() {
        return (int) spinnerC.getValue();
    }
}