package patryk.game.of.life.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class MenuController {
    private Game game;

    public MenuController() {
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
    private void start(ActionEvent event){
        startButton.setDisable(true);
        stopButton.setDisable(false);
        game.play();
    }

    @FXML
    private void stop(ActionEvent event) {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        game.stop();
    }

    @FXML
    private void initialize() {
        stopButton.setDisable(true);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
