package gameol.utils;

import gameol.matrix.CellState;
import gameol.matrix.Cell;
import gameol.matrix.LifeMatrix;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class LifeMatrixUtils {
    public static int countNeighbors(Cell cell, LifeMatrix matrix) {
        int neighbors = 0;

        int px = cell.getPx();
        int py = cell.getPy();
        int size = matrix.getSize();

        if(isCellAlive(getValueIfExists(px, py-1, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px + 1, py - 1, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px + 1, py, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px + 1, py + 1, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px, py + 1, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px - 1, py + 1, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px - 1, py, matrix))) {
            neighbors++;
        }

        if(isCellAlive(getValueIfExists(px - 1, py - 1, matrix))) {
            neighbors++;
        }

        return neighbors;
    }

    public static CellState getValueIfExists(int px, int py, LifeMatrix matrix) {
        if(px >= 0 && px < matrix.getSize() && py >=0 && py < matrix.getSize() ) {
            return matrix.getCellAt(px, py).getState();
        }

        return null;
    }

    public static boolean isCellAlive(CellState state) {
        if(state != null && state == CellState.ALIVE) {
            return true;
        }

        return false;
    }

    public static void recountNeighbors(LifeMatrix matrix) {
        for(int i=0; i<matrix.getSize(); i++) {
            for(int j=0; j<matrix.getSize(); j++) {
                Cell cell = matrix.getCellAt(i, j);

                cell.setNeighbors(LifeMatrixUtils.countNeighbors(cell, matrix));
            }
        }
    }

    public static void resurrectOrKill(LifeMatrix matrix) {
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
}
