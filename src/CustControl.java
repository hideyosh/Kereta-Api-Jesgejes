import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

public class CustControl {

    @FXML
    private Button Jadwal;

    @FXML
    private Button Logout;

    @FXML
    private Button Pengaturan;

    @FXML
    private Button Pesan;

    @FXML
    void JadwalHandle(ActionEvent event) {
        navigateToDashboard(event, "JadwalTiket.fxml");
    }

    @FXML
    void LogoutHandle(ActionEvent event) {
        navigateToDashboard(event, "LoginPage.fxml"); // Kembali ke halaman login/awal
    }

    @FXML
    void PengaturanHandle(ActionEvent event) {
        navigateToDashboard(event, "pengaturan.fxml");
    }

    @FXML
    void PesanHandle(ActionEvent event) {
        navigateToDashboard(event, "pesan.fxml");
    }

    private void navigateToDashboard(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
