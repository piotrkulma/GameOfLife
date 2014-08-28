package gameol.graphic;

import gameol.simulation.GameOLConfig;
import gameol.matrix.CellState;
import gameol.utils.LifeMatrixUtils;
import gameol.matrix.Cell;
import gameol.matrix.LifeMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class GameOLPanel extends JPanel implements MatrixRedrawer, MouseListener, MouseMotionListener {
    private boolean initialized;
    private boolean mouseButtonDown;

    private Image offscreen;
    private LifeMatrix matrix;

    public GameOLPanel() {
        mouseButtonDown = false;
        initialized = false;
        matrix = null;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(matrix != null) {
            if(!initialized) {
                GameOLConfig.DRAWING_SCALE_X = this.getWidth() / matrix.getSize();
                GameOLConfig.DRAWING_SCALE_Y = this.getHeight() / matrix.getSize();
            }

            drawCells(g);
        }
    }

    public void redraw(LifeMatrix matrix) {
        this.matrix = matrix;
        this.repaint();
    }

    public void redraw() {
        this.repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        setMatrixCellByRowPosition(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setMatrixCellByRowPosition(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

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
            g2d.drawRect(px * GameOLConfig.DRAWING_SCALE_X, py * GameOLConfig.DRAWING_SCALE_Y, GameOLConfig.DRAWING_SCALE_X, GameOLConfig.DRAWING_SCALE_Y);
        } else if(cell.getState() == CellState.ALIVE) {
            if(GameOLConfig.AGING_COLOURS_ENABLED) {
                g2d.setColor(LifeMatrixUtils.getAgeColor(cell.getAge()));
            } else {
                g2d.setColor(Color.black);
            }

            g2d.fillRect(px * GameOLConfig.DRAWING_SCALE_X, py * GameOLConfig.DRAWING_SCALE_Y, GameOLConfig.DRAWING_SCALE_X, GameOLConfig.DRAWING_SCALE_Y);
        }
    }

    private void setMatrixCellByRowPosition(int rawX, int rawY) {
        int px = (rawX) / GameOLConfig.DRAWING_SCALE_X;
        int py = (rawY) / GameOLConfig.DRAWING_SCALE_Y;
        matrix.setCellAt(px, py, CellState.ALIVE);
        redraw(matrix);
    }
}
