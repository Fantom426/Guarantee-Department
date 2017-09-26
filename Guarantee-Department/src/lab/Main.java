package lab;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lab.datalayer.Database;
import lab.util.FXUtils;

public class Main extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent home = FXUtils.loadView("/view/home.fxml", primaryStage);
        primaryStage.setTitle("Гарантийный отдел");
        primaryStage.setScene(new Scene(home, 900, 650));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Database.cleanUp();
            }
        });
        mainStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}