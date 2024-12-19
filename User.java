import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class User {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void JadwalTiket(ActionEvent event) throws IOException {
        // Memuat file FXML untuk halaman Jadwal Tiket
        root = FXMLLoader.load(getClass().getResource("/views/JadwalTiket.fxml"));
        navigateToPage(event);
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
        // Memuat file FXML untuk halaman Login (LogOut)
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        navigateToPage(event);
    }

    @FXML
    void PengaturanAkun(ActionEvent event) throws IOException {
        // Memuat file FXML untuk halaman Pengaturan Akun
        root = FXMLLoader.load(getClass().getResource("/views/PengaturanAkun.fxml"));
        navigateToPage(event);
    }

    @FXML
    void PesanTiket(ActionEvent event) throws IOException {
        // Memuat file FXML untuk halaman Pesan Tiket
        root = FXMLLoader.load(getClass().getResource("ReservasiPage.fxml"));
        navigateToPage(event);
    }

    // Fungsi bantu untuk navigasi antar halaman
    private void navigateToPage(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Mengambil stage saat ini
        scene = new Scene(root); // Membuat scene baru dengan root yang di-load
        stage.setScene(scene); // Mengatur scene baru ke stage
        stage.show(); // Menampilkan stage
    }
}
