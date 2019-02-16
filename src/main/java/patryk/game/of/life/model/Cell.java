package patryk.game.of.life.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

    private static final String ALIVE_COLOR = "#757575";
    private static final String DEAD_COLOR = "#e0e0e0";

    public Cell(double v, double v1) {
        super(v, v1, Paint.valueOf(DEAD_COLOR));
        isAlive = false;
    }

    private Boolean isAlive;

    public Boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
        this.setFill(Paint.valueOf(DEAD_COLOR));
    }

    public void revive() {
        isAlive = true;
        this.setFill(Paint.valueOf(ALIVE_COLOR));
    }
}
