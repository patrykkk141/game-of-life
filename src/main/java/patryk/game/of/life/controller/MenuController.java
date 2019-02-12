package patryk.game.of.life.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    private GameController gameController;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    void start(ActionEvent event){
        gameController.play();
    }

    @FXML
    void stop(ActionEvent event) {

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
