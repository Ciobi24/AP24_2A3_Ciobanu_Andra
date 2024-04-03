package start;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class MainFrame
        extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // create the components
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        // Arrange the components in the container (frame)
        // JFrame uses a BorderLayout by default
        add(configPanel, BorderLayout.NORTH); // This is BorderLayout.NORTH
        add(canvas, BorderLayout.CENTER); // This is BorderLayout.CENTER
        add(controlPanel, BorderLayout.SOUTH); // This is BorderLayout.SOUTH
        // Invoke the layout manager
        pack();
        setLocationRelativeTo(null);
    }

}