package gameol.graphic;

import gameol.matrix.CellState;
import gameol.utils.GlobalTimer;
import gameol.LifeFrameAction;
import gameol.utils.LifeMatrixUtils;
import gameol.matrix.Cell;
import gameol.matrix.LifeMatrix;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class GameOLPanel extends JPanel implements LifeFrameAction {
    private int scale = 8;
    private int matrixSize = 30;
    private int timerInterval = 100;

    private Image offscreen;

    private LifeMatrix matrix;
    private GlobalTimer timer;

    public GameOLPanel() {

        initMatrix();
        initTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        drawCells(g);
    }

    @Override
    public void performFrame() {
        LifeMatrixUtils.recountNeighbors(matrix);
        LifeMatrixUtils.resurrectOrKill(matrix);

        this.repaint();
    }

    private void drawCells(Graphics g) {
        offscreen = createImage(400, 400);
        Graphics2D g2d = (Graphics2D) offscreen.getGraphics();

        for(int i=0; i<matrix.getSize(); i++) {
            for(int j=0; j<matrix.getSize(); j++) {
                Cell cell = matrix.getCellAt(i, j);
                drawCell(cell, i, j, g2d);
            }
        }
        g.drawImage(offscreen, 0, 0, this);
    }

    private void drawCell(Cell cell, int px, int py, Graphics g2d) {
        if(cell.getState() == CellState.DEAD) {
            g2d.setColor(Color.black);
            g2d.drawRect(px * scale, py * scale, scale, scale);
        } else if(cell.getState() == CellState.ALIVE) {
            if(cell.getAge() == 0) {
                g2d.setColor(Color.red);
            } else if(cell.getAge() == 1) {
                g2d.setColor(Color.green);
            } else if(cell.getAge() == 2) {
                g2d.setColor(Color.blue);
            } else if(cell.getAge() == 3) {
                g2d.setColor(Color.yellow);
            } else {
                g2d.setColor(Color.black);
            }

            g2d.fillRect(px * scale, py * scale, scale, scale);
        }
    }

    //TODO jakieś wczytywanie, czy coś
    private void initMatrix() {
        matrix = new LifeMatrix(matrixSize);
        matrix.setCellAt(10, 9, CellState.ALIVE);
        matrix.setCellAt(10, 10, CellState.ALIVE);
        matrix.setCellAt(10, 11, CellState.ALIVE);
        matrix.setCellAt(9, 11, CellState.ALIVE);
        matrix.setCellAt(8, 10, CellState.ALIVE);
    }

    private void initTimer() {
        timer = new GlobalTimer(timerInterval, this);
        timer.start();
    }
}
