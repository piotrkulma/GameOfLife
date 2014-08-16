package gameol.utils;

import gameol.matrix.CellState;
import gameol.matrix.Cell;
import gameol.matrix.LifeMatrix;

import java.awt.*;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class LifeMatrixUtils {
    public static int countNeighbors(Cell cell, LifeMatrix matrix) {
        int neighbors = 0;

        int px = cell.getPx();
        int py = cell.getPy();

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
        if(px < 0) {
            px = matrix.getSize() - 1;
        } else if(px >= matrix.getSize()) {
            px = 0;
        }

        if(py < 0) {
            py = matrix.getSize() - 1;
        } else if(py >= matrix.getSize()) {
            py = 0;
        }

        //if(px >= 0 && px < matrix.getSize() && py >=0 && py < matrix.getSize() ) {
            return matrix.getCellAt(px, py).getState();
        //}

       // return null;
    }

    public static boolean isCellAlive(CellState state) {
        if(state != null && state == CellState.ALIVE) {
            return true;
        }

        return false;
    }

    public static Color getAgeColor(int age) {
        if(age == 0) {
            return Color.red;
        } else if(age == 1) {
            return Color.green;
        } else if(age == 2) {
            return Color.blue;
        } else if(age == 3) {
            return Color.yellow;
        }
        return Color.black;
    }
}
