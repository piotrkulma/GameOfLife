package gameol.graphic;

import gameol.simulation.GameOLConfig;
import gameol.matrix.CellState;
import gameol.utils.LifeMatrixUtils;
import gameol.matrix.Cell;
import gameol.matrix.LifeMatrix;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class GameOLPanel extends JPanel implements MatrixRedrawer {
    private Image offscreen;
    private LifeMatrix matrix;

    public GameOLPanel() {
        matrix = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(matrix != null) {
            drawCells(g);
        }
    }

    @Override
    public void redraw(LifeMatrix matrix) {
        this.matrix = matrix;
        this.repaint();
    }

    private void drawCells(Graphics g) {
        offscreen = createImage(GameOLConfig.WINDOW_WIDTH, GameOLConfig.WINDOW_HEIGHT);
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
            g2d.drawRect(px * GameOLConfig.DRAWING_SCALE, py * GameOLConfig.DRAWING_SCALE, GameOLConfig.DRAWING_SCALE, GameOLConfig.DRAWING_SCALE);
        } else if(cell.getState() == CellState.ALIVE) {
            if(GameOLConfig.AGING_COLOURS_ENABLED) {
                g2d.setColor(LifeMatrixUtils.getAgeColor(cell.getAge()));
            } else {
                g2d.setColor(Color.black);
            }

            g2d.fillRect(px * GameOLConfig.DRAWING_SCALE, py * GameOLConfig.DRAWING_SCALE, GameOLConfig.DRAWING_SCALE, GameOLConfig.DRAWING_SCALE);
        }
    }
}
