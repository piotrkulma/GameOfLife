package gameol.simulation;

import gameol.graphic.MatrixRedrawer;
import gameol.matrix.Cell;
import gameol.matrix.CellState;
import gameol.matrix.LifeMatrix;
import gameol.utils.LifeMatrixUtils;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class LifeSimulation implements LifeFrameAction {
    private LifeMatrix matrix;
    private GlobalTimer timer;
    private MatrixRedrawer redraw;

    private boolean active;
    private boolean activated;

    //TODO
    private int pattern_gilder[][] =
            {
                    {0, 0, 1},
                    {1, 0, 1},
                    {0, 1, 1}
            };

    public LifeSimulation(MatrixRedrawer redraw) {
        this.redraw = redraw;
        this.active = false;
        this.activated = false;

        initMatrix();
        initTimer();
    }

    @Override
    public void performFrame() {
        countNeighbors();
        setCells();

        redraw.redraw(matrix);
    }

    public void startSimulation() {
        active = true;

        if(activated) {
            this.timer.wakeup();
        } else {
            this.timer.start();
            activated = true;
        }
    }

    public void pauseSimulation() {
        active = false;
        this.timer.pause();
    }

    public void clearAndPauseSimulation() {
        pauseSimulation();
        matrix.clearMatrix();
    }

    public boolean isActive() {
        return active;
    }

    private void countNeighbors() {
        for(int i=0; i<matrix.getSize(); i++) {
            for(int j=0; j<matrix.getSize(); j++) {
                Cell cell = matrix.getCellAt(i, j);

                cell.setNeighbors(LifeMatrixUtils.countNeighbors(cell, matrix));
            }
        }
    }

    private void setCells() {
        int neighbors = 0;

        for(int i=0; i<matrix.getSize(); i++) {
            for(int j=0; j<matrix.getSize(); j++) {
                Cell cell = matrix.getCellAt(i, j);
                neighbors = cell.getNeighbors();

                if(cell.getState() == CellState.ALIVE) {
                    if(neighbors < 2) {
                        cell.setState(CellState.DEAD);
                    } else if(neighbors > 3) {
                        cell.setState(CellState.DEAD);
                    } else {
                        cell.setAge(cell.getAge() + 1);
                    }
                } else {
                    if(neighbors == 3) {
                        cell.setState(CellState.ALIVE);
                        cell.setAge(0);
                    }
                }
            }
        }
    }

    //TODO jakieś wczytywanie, czy coś
    private void initMatrix() {
        matrix = new LifeMatrix(GameOLConfig.MATRIX_SIZE);
        matrix.setCellAt(10, 9, CellState.ALIVE);
        matrix.setCellAt(10, 10, CellState.ALIVE);
        matrix.setCellAt(10, 11, CellState.ALIVE);
        matrix.setCellAt(9, 11, CellState.ALIVE);
        matrix.setCellAt(8, 10, CellState.ALIVE);
    }

    private void initTimer() {
        timer = new GlobalTimer(GameOLConfig.TIMER_INTERVAL, this);
        //timer.start();
    }
}
