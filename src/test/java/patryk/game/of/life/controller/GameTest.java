package patryk.game.of.life.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import patryk.game.of.life.controller.Game;
import patryk.game.of.life.model.Board;
import patryk.game.of.life.model.Cell;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    private Game game;
    Board board;

    @Before
    public void setup() {
        board = new Board(10, 10);
        game = new Game(board);
    }

    @Test
    public void testUpdateCellsCountersAtKill() {
        int aliveBefore = game.aliveCellsProperty().get();
        int deadBefore = game.deadCellsProperty().get();
        game.updateCellCountersAtKill();

        Assert.assertEquals(aliveBefore - 1, game.aliveCellsProperty().get());
        Assert.assertEquals(deadBefore + 1, game.deadCellsProperty().get());
    }

    @Test
    public void testUpdateCellCounterAtRevive() {
        int aliveBefore = game.aliveCellsProperty().get();
        int deadBefore = game.deadCellsProperty().get();
        game.updateCellCountersAtRevive();


        Assert.assertEquals(aliveBefore + 1, game.aliveCellsProperty().get());
        Assert.assertEquals(deadBefore - 1, game.deadCellsProperty().get());
    }

    @Test
    public void isAliveNeighboursReturnsAllNeighbours() {
        Board b = new Board(5, 5);
        Game g = new Game(b);
        List<List<Cell>> cells = b.getCells();

        cells.get(0).get(0).revive();
        cells.get(0).get(1).revive();
        cells.get(0).get(2).revive();
        cells.get(1).get(0).revive();
        cells.get(1).get(2).revive();
        cells.get(2).get(0).revive();
        cells.get(2).get(1).revive();
        cells.get(2).get(2).revive();

        Assert.assertEquals(8, g.getNumberOfAliveNeighbour(1, 1));
    }

    @Test
    public void isAliveNeighboursWorkWithCoordinates0x0() {
        Board b = new Board(5, 5);
        Game g = new Game(b);
        List<List<Cell>> cells = b.getCells();

        cells.get(0).get(1).revive();
        cells.get(0).get(4).revive();
        cells.get(1).get(0).revive();
        cells.get(1).get(1).revive();
        cells.get(1).get(4).revive();
        cells.get(4).get(0).revive();
        cells.get(4).get(1).revive();
        cells.get(4).get(4).revive();

        Assert.assertEquals(8, g.getNumberOfAliveNeighbour(0, 0));
    }

}
