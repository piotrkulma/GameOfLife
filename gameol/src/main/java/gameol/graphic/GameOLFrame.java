package gameol.graphic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Piotr Kulma  on 16.08.14.
 */
public class GameOLFrame extends JFrame {
    private GameOLPanel drawPanel;

    public GameOLFrame() {
        super("Game of life BETA");
        init();
    }

    private void init() {
        initFrame();
        initDrawPanel();
    }

    private void initFrame() {
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initDrawPanel() {
        drawPanel = new GameOLPanel();
        this.getContentPane().add(drawPanel, BorderLayout.CENTER);
    }
}
