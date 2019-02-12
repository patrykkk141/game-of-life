package patryk.game.of.life.view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import patryk.game.of.life.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int X_DIM;
    public static int Y_DIM;
    private final int CELL_DIM = 15;

    private GridPane gridPane;
    private List<List<Cell>> cells;

    public Board(int x, int y) {
        X_DIM = x;
        Y_DIM = y;
        initializeGridPane();
    }


    private void initializeGridPane() {
        //ustawienia gridpane'a
        gridPane = new GridPane();
        gridPane.setVgap(2);
        gridPane.setHgap(2);
        gridPane.setPadding(new Insets(10));

        cells = new ArrayList<>();

        for (int i = 0; i < X_DIM; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < Y_DIM; j++) {
                Cell cell = new Cell(CELL_DIM, CELL_DIM);
                gridPane.add(cell, i, j);
                row.add(cell);
            }
            cells.add(row);
        }
    }


    public GridPane getBoard() {
        return gridPane;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }
}
