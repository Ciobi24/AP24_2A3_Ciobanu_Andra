package start;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton saveImgBtn = new JButton("Save Image");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        //gbl.setConstraints(exitBtn,gbc);
        this.add(this.exitBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(this.saveBtn, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(this.loadBtn, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(this.saveImgBtn, gbc);

        setLayout(gbl);

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        saveBtn.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          ObjectMapper objectMapper = new ObjectMapper();
                                          objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                                          try {
                                              objectMapper.writeValue(new File(frame.path), frame.joc);
                                              //System.out.println(objectMapper.writeValueAsString(catalog));
                                          } catch (IOException q) {
                                              q.printStackTrace();
                                          }
                                      }
                                  }
        );
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    frame.joc = objectMapper.readValue(new File(frame.path), GameState.class);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                frame.repaint();
            }
        });
        saveImgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveImage();
            }
        });
    }

    void saveImage() {
        BufferedImage buff = new BufferedImage(frame.joc.getCanvasWidth(), frame.joc.getCanvasHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = buff.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, frame.joc.getCanvasWidth(), frame.joc.getCanvasHeight());
        this.frame.canvasPanel.paintComponent(g);
        g.dispose();
        File file = new File("joc.png");
        try {
            ImageIO.write(buff, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}