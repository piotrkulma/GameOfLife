package gameol.matrix;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class Cell {
    private int age;
    private int neighbors;
    private int px;
    private int py;

    private CellState state;

    public Cell(int px, int py) {
        this.px = px;
        this.py = py;

        initCell();
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    private void initCell() {
        this.age = 0;
        this.neighbors = 0;
        state = CellState.DEAD;
    }
 }
