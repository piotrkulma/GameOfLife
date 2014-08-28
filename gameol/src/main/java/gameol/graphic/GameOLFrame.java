package gameol.graphic;

import gameol.simulation.GameOLConfig;
import gameol.simulation.LifeSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Piotr Kulma  on 16.08.14.
 */
public class GameOLFrame extends JFrame {
    private GameOLPanel drawPanel;
    private LifeSimulation simulation;

    private JPanel buttonPanel;
    private JButton actionButton;
    private JButton clearButton;

    public GameOLFrame() {
        super("Game of life BETA");
        init();
    }

    private void init() {
        initFrame();
        initDrawPanel();
        initButtons();
        initSimulation();
    }

    private void initFrame() {
        this.setSize(GameOLConfig.WINDOW_WIDTH, GameOLConfig.WINDOW_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initButtons() {
        buttonPanel = new JPanel();

        actionButton = new JButton("Start");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simulation.isActive()) {
                    simulation.pauseSimulation();
                    actionButton.setText("Start");
                } else {
                    simulation.startSimulation();
                    actionButton.setText("Stop");
                }
            }
        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulation.clearAndPauseSimulation();
                drawPanel.redraw();
                actionButton.setText("Start");
            }
        });

        buttonPanel.add(actionButton);
        buttonPanel.add(clearButton);

        this.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    }

    private void initDrawPanel() {
        drawPanel = new GameOLPanel();
        this.getContentPane().add(drawPanel, BorderLayout.CENTER);
    }

    private void initSimulation() {
        simulation = new LifeSimulation(drawPanel);
    }
}
