package patryk.game.of.life.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int X_DIM;
    public static int Y_DIM;
    private final int CELL_DIM = 15;

    private List<List<Cell>> cells;

    public Board(int x, int y) {
        X_DIM = x;
        Y_DIM = y;
        initializeGridPane();
    }

    private void initializeGridPane() {
        cells = new ArrayList<>();
        for (int i = 0; i < X_DIM; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < Y_DIM; j++) {
                Cell cell = new Cell(CELL_DIM, CELL_DIM);
                row.add(cell);
            }
            cells.add(row);
        }
    }
    public List<List<Cell>> getCells() {
        return cells;
    }
}
