package patryk.game.of.life.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;


public class MenuController {
    private final Game game;

    public MenuController(Game game) {
        this.game = game;
    }

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private ChoiceBox<?> pattern;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label aliveCells;

    @FXML
    private Label deadCells;

    @FXML
    private Label generation;

    @FXML
    void start(ActionEvent event) {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        game.play();
    }

    @FXML
    void stop(ActionEvent event) {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        game.stop();
    }

    @FXML
    void initialize() {
        stopButton.setDisable(true);
        speedSlider.setMin(0);
        speedSlider.setMax(900);
        speedSlider.decrement();
        speedSlider.valueProperty().addListener((observableValue, number, t1) -> {
            game.setSpeed(Math.round((double) number));
        });

        generation.textProperty().bind(game.generationCounterProperty().asString());
        aliveCells.textProperty().bind(game.aliveCellsProperty().asString());
        deadCells.textProperty().bind(game.deadCellsProperty().asString());
    }
}
