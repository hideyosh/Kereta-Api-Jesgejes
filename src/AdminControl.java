import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminControl {

    @FXML
    private Button Logout;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void LogoutHandle(ActionEvent event) {
        try {
            // Muat file FXML untuk halaman LoginPage.fxml
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1920, 1080); // Set ukuran scene ke 1920x1080
            stage.setScene(scene);
            stage.setFullScreen(true); // Aktifkan layar penuh
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Tambahkan penanganan kesalahan sesuai kebutuhan
        }
    }
    @FXML
    void aturcustbutton(ActionEvent event) {
        try {
            // Muat file FXML untuk halaman aturcust.fxml
            root = FXMLLoader.load(getClass().getResource("ListCust.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1920, 1080); // Set ukuran scene ke 1920x1080
            stage.setScene(scene);
            stage.setFullScreen(true); // Aktifkan layar penuh
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Tambahkan penanganan kesalahan sesuai kebutuhan
        }
    }

    @FXML
    void aturjadwalbutton(ActionEvent event) {
        try {
            // Muat file FXML untuk halaman aturjadwal.fxml
            root = FXMLLoader.load(getClass().getResource("ListJadwal.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1920, 1080); // Set ukuran scene ke 1920x1080
            stage.setScene(scene);
            stage.setFullScreen(true); // Aktifkan layar penuh
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Tambahkan penanganan kesalahan sesuai kebutuhan
        }
    }
}
