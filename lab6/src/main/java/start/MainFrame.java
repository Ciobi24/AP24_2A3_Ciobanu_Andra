package start;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvasPanel;
    ConsolePanel consolePanel;

    String path;
    GameState joc;
    Color culori[];

    public MainFrame() {
        super("My Drawing Application");
        path = "./joc.json";
        culori = new Color[2];
        culori[0] = Color.PINK;
        culori[1] = Color.CYAN;
        init();

    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        configPanel = new ConfigPanel(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(configPanel, gbc);
        joc = new GameState(configPanel.getRows(), configPanel.getCols());

        consolePanel = new ConsolePanel(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(consolePanel, gbc);

        canvasPanel = new DrawingPanel(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(canvasPanel, gbc);

        controlPanel = new ControlPanel(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(controlPanel, gbc);

        //invoke the layout manager
        pack();
        setLocationRelativeTo(null);

    }

    public Color getColor(int culoare) {
        return culori[culoare - 1];
    }
}