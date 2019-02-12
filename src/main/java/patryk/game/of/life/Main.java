package patryk.game.of.life;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import patryk.game.of.life.controller.GameController;
import patryk.game.of.life.controller.MenuController;
import patryk.game.of.life.view.Board;


public class Main extends Application {
    private final Board board;
    private final GameController gameController;

    public Main() {
        board = new Board(50, 40);
        gameController = new GameController(board);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Przygotowanie oraz wyswietlenie glownego widoku aplikacji
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/main.fxml"));

        Pane root = loader.load();
        MenuController menuController = loader.getController();
        menuController.setGameController(gameController);
        root.getChildren().add(board.getBoard());

        Scene scene = new Scene(root);

        primaryStage.setTitle("Game of life, version 0.01");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
