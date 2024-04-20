package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame implements Serializable{
    transient ConfigPanel configPanel;
    transient ControlPanel controlPanel;
    transient DrawingPanel canvas;

    public MainFrame(){
        super("My Drawing Application");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        pack();
    }

    public void exportImageToPNG(File file) {
        canvas.exportImageToPNG(file);
    }
    public void saveGame(String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(canvas);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public void loadGame(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            canvas = (DrawingPanel) inputStream.readObject();
            System.out.println("Game loaded successfully!");
            canvas.repaint(); // Repaint the loaded state
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}

