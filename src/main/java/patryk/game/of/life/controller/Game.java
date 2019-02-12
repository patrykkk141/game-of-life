package patryk.game.of.life.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import patryk.game.of.life.model.Cell;
import patryk.game.of.life.model.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private boolean isPlaying = false;

    public Game(Board board) {
        this.board = board;
        setMouseEvent();
    }

    private void setMouseEvent() {
        List<List<Cell>> cells = board.getCells();
        for (List<Cell> x : cells)
            for (Cell cell : x)
                cell.addEventHandler(MouseEvent.MOUSE_CLICKED, changeCellState);
    }

    private void changeState(Cell cell) {
        if (cell.isAlive())
            cell.kill();
        else cell.revive();
    }

    private EventHandler<MouseEvent> changeCellState = e -> {
        changeState((Cell) e.getSource());
    };

    private int getNumberOfAliveNeighbour(Cell cell, int x_pkt, int y_pkt) {
        int result = 0;

        for (int i = x_pkt - 1, c1 = 0; c1 < 3; c1++, i++) {
            if (i < 0)
                i = Board.X_DIM - 1;
            else if (i > Board.X_DIM - 1)
                i = 0;

            for (int j = y_pkt - 1, c2 = 0; c2 < 3; c2++, j++) {
                if (j < 0)
                    j = Board.Y_DIM - 1;
                else if (j > Board.Y_DIM - 1)
                    j = 0;

                if (i == x_pkt && j == y_pkt)
                    continue;

                if (board.getCells().get(i).get(j).isAlive())
                    result++;
            }
        }
        return result;
    }

    void play() {
        isPlaying = true;
        Runnable task = () -> getNewGeneration(500);

        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    void stop() {
        isPlaying = false;
    }

    private void getNewGeneration(int delayInMillis) {
        while (isPlaying) {
            List<Cell> changeStateCells = new ArrayList<>();
            for (int i = 0; i < Board.X_DIM; i++) {
                for (int j = 0; j < Board.Y_DIM; j++) {
                    Cell cell = board.getCells().get(i).get(j);
                    int aliveNeighbours = getNumberOfAliveNeighbour(cell, i, j);
                    if (!cell.isAlive() && aliveNeighbours == 3)
                        changeStateCells.add(cell);
                    if (cell.isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3))
                        changeStateCells.add(cell);
                }
            }
            try {
                Platform.runLater(() -> showNewGeneration(changeStateCells));
                Thread.sleep(delayInMillis);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showNewGeneration(List<Cell> cells) {
        for (Cell x : cells)
            changeState(x);
    }
}
