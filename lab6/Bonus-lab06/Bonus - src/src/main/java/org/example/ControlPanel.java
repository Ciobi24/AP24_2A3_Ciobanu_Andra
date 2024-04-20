package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ControlPanel extends JPanel{
    transient final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton exportBtn = new JButton("Export");

    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        setLayout(new GridLayout(1, 4));

        add(exitBtn);
        add(saveBtn);
        add(loadBtn);
        add(exportBtn);

        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
        loadBtn.addActionListener(this::loadGame);
        exportBtn.addActionListener(this::exportImage);
    }

    private void exitGame(ActionEvent e){
        frame.dispose();
    }

    private void exportImage(ActionEvent e) {
        // Create a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        // Show the save dialog
        int result = fileChooser.showSaveDialog(this);

        // If the user selects a file and clicks Save
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();
            // Get the file path
            String filePath = selectedFile.getAbsolutePath();

            // Append ".png" extension if not present
            if (!filePath.toLowerCase().endsWith(".png")) {
                filePath += ".png";
            }

            // Export the image to the selected file
            frame.exportImageToPNG(new File(filePath));
        }
    }

    private void saveGame(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".ser")) {
                filePath += ".ser";
            }

            frame.saveGame(filePath);
        }
    }

    private void loadGame(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            frame.loadGame(selectedFile.getAbsolutePath());
        }
    }

}
