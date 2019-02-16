package patryk.game.of.life.controller;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import patryk.game.of.life.model.Cell;
import patryk.game.of.life.model.Board;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private boolean isPlaying = false;
    private IntegerProperty aliveCells;
    private IntegerProperty deadCells;
    private IntegerProperty generationCounter;
    private LongProperty speed;

    public Game(Board board) {
        this.board = board;

        aliveCells = new SimpleIntegerProperty(this, "aliveCells", 0);
        deadCells = new SimpleIntegerProperty(this, "deadCells", Board.X_DIM * Board.Y_DIM);
        generationCounter = new SimpleIntegerProperty(this, "generationCounter", 0);
        speed = new SimpleLongProperty(this, "speed", 0);

        setMouseEvent();
    }

    private void setMouseEvent() {
        List<List<Cell>> cells = board.getCells();
        for (List<Cell> x : cells)
            for (Cell cell : x)
                cell.addEventHandler(MouseEvent.MOUSE_CLICKED, changeCellState);
    }

    private void changeState(Cell cell) {
        if (cell.isAlive()) {
            cell.kill();
            updateCellsCounters(aliveCells.getValue() - 1, deadCells.getValue() + 1);
        } else {
            cell.revive();
            updateCellsCounters(aliveCells.getValue() + 1, deadCells.getValue() - 1);
        }

    }

    private void updateCellsCounters(int alive, int dead) {
        aliveCells.setValue(alive);
        deadCells.setValue(dead);
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
        Runnable task = this::getNewGeneration;

        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    void stop() {
        isPlaying = false;
    }

    private void getNewGeneration() {
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
                Platform.runLater(() -> {
                    showNewGeneration(changeStateCells);
                    generationCounter.setValue(generationCounter.getValue() + 1);
                });
                Thread.sleep(1000 - speed.getValue());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showNewGeneration(List<Cell> cells) {
        for (Cell x : cells)
            changeState(x);
    }

    IntegerProperty generationCounterProperty() {
        return generationCounter;
    }

    IntegerProperty aliveCellsProperty() {
        return aliveCells;
    }

    IntegerProperty deadCellsProperty() {
        return deadCells;
    }

    void setSpeed(long speed) {
        this.speed.set(speed);
    }
}
