package start;

import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JPanel {
    final MainFrame frame;
    JLabel player;

    public ConsolePanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());
        player = new JLabel();
        changePlayer(1);
        add(player);
        //frame.pack();
    }
    public void changePlayer(int player)
    {
        this.player.setText("Player "+player);
        this.player.setForeground(frame.culori[player - 1]);
    }
}