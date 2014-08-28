package gameol.matrix;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class LifeMatrix {
    private int size;

    private Cell[][] matrix;

    public LifeMatrix(int size) {
        this.size = size;
        initMatrix();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell getCellAt(int x, int y) {
        return matrix[x][y];
    }

    public void setCellAt(int x, int y, CellState value) {
        matrix[x][y].setState(value);
    }

    public void clearMatrix() {
        fillMatrix(CellState.DEAD);
    }

    private void initMatrix() {
        matrix = new Cell[size][size];

        fillMatrix(CellState.DEAD);
    }

    private void fillMatrix(CellState value) {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                matrix[i][j] = new Cell(i, j);
                matrix[i][j].setState(value);
            }
        }
    }
}
