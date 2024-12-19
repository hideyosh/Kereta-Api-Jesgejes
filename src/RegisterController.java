import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Label backLabel;

    @FXML
    private Label statusLabel;

    @FXML
public void initialize() {
    if (backLabel == null) {
        System.out.println("backLabel belum terhubung ke FXML!");
    } else {
        System.out.println("backLabel berhasil diinisialisasi.");
    }
}

    @FXML
    void handleRegister(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (validateInputs(username, password, confirmPassword)) {
            try (Connection connection = Database.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO users (username, password, role) VALUES (?, ?, ?)")) {

                statement.setString(1, username);
                statement.setString(2, password); // Menyimpan password asli
                statement.setString(3, "Customer");

                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    setStatus("Registrasi berhasil!", "-fx-text-fill: green;");
                } else {
                    setStatus("Registrasi gagal, coba lagi.", "-fx-text-fill: red;");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                setStatus("Terjadi kesalahan pada database.", "-fx-text-fill: red;");
            }
        }
    }

    @FXML
    void handleBack(MouseEvent event) {
    try {
        // Muat halaman login
        Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginScene = new Scene(loginRoot);

        // Dapatkan stage dari event source
        Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();

        // Set scene baru dan tampilkan
        stage.setScene(loginScene);
        stage.setTitle("Login Page");
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Gagal memuat halaman Login!");
    }
}


    private boolean validateInputs(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            setStatus("Semua field harus diisi!", "-fx-text-fill: red;");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            setStatus("Password dan Konfirmasi Password tidak cocok!", "-fx-text-fill: red;");
            return false;
        }
        if (password.length() < 8) {
            setStatus("Password harus minimal 8 karakter!", "-fx-text-fill: red;");
            return false;
        }
        return true;
    }

    private void setStatus(String message, String style) {
        statusLabel.setText(message);
        statusLabel.setStyle(style);
    }
}
