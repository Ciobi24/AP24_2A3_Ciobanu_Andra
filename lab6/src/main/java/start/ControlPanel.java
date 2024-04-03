package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel
        extends JPanel {
    final MainFrame frame;
    JButton exitBtn;
    JButton loadButton;
    JButton saveButton;
 
    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    } 
    private void init() {
//change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add all buttons
         exitBtn = new JButton("Exit");
        loadButton= new JButton("Load");
         saveButton= new JButton("Save");

        add(exitBtn);
        add(loadButton);
        add(saveButton);

        //configure listeners for all buttons
        exitBtn.addActionListener(
                this::exitGame);
        loadButton.addActionListener(
                this::loadGame);
        saveButton.addActionListener(this::saveGame);

        Dimension buttonSize = new Dimension(30, 25);
        exitBtn.setPreferredSize(buttonSize);
        loadButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);

    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    private void loadGame(ActionEvent e) {}
    private void saveGame(ActionEvent e) {}

}