package patryk.game.of.life;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import patryk.game.of.life.controller.Game;
import patryk.game.of.life.controller.MenuController;
import patryk.game.of.life.model.Board;


public class Main extends Application {
    private final Board board;
    private final Game game;
    private final MenuController menuController;

    public Main() {
        board = new Board(50, 40);
        game = new Game(board);
        menuController = new MenuController(game);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Pobranie widoku
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/main.fxml"));
        loader.setController(menuController);
        Pane root = loader.load();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setPadding(new Insets(10));

        //Pobranie komorke z Board i wstawienie do GridPane
        for (int i = 0; i < Board.X_DIM; i++) {
            for (int j = 0; j < Board.Y_DIM; j++) {
                gridPane.add(board.getCells().get(i).get(j), i, j);
            }
        }

        /*MenuController menuController = loader.getController();
        menuController.setGame(game);*/
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Game of life, version 0.01");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
