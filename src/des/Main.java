package des;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/mainWindows.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Security Application");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 700, 425));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
