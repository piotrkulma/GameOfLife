package gameol.graphic;

import gameol.simulation.GameOLConfig;
import gameol.simulation.LifeSimulation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Piotr Kulma  on 16.08.14.
 */
public class GameOLFrame extends JFrame {
    private GameOLPanel drawPanel;
    private LifeSimulation simulation;

    public GameOLFrame() {
        super("Game of life BETA");
        init();
    }

    private void init() {
        initFrame();
        initDrawPanel();
        initSimulation();
    }

    private void initFrame() {
        this.setSize(GameOLConfig.WINDOW_WIDTH, GameOLConfig.WINDOW_WIDTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initDrawPanel() {
        drawPanel = new GameOLPanel();
        this.getContentPane().add(drawPanel, BorderLayout.CENTER);
    }

    private void initSimulation() {
        simulation = new LifeSimulation(drawPanel);
    }
}
