import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Memuat file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();

        // Mengatur judul dan scene
        primaryStage.setTitle("Login UI");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }
}
