import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label registerButton;  // Menggunakan Label untuk register

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    private TextField usernameField;

    @FXML
    private Label statusLabel;

    

    
    /**
     * Inisialisasi awal untuk ChoiceBox dan komponen lainnya.
     */
    @FXML
    public void initialize() {
        // Tambahkan opsi untuk ChoiceBox
        roleChoiceBox.getItems().addAll("Admin", "Customer");
        roleChoiceBox.setValue("Customer"); // Atur default ke Customer


        // Menambahkan event listener untuk ChoiceBox
        roleChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Role yang dipilih: " + newValue);  // Menampilkan role yang dipilih
        });

        // Menggunakan MouseEvent untuk register
        registerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleRegister(event);  // Menangani klik pada Label
            }
        });
    }

    /**
     * Handle action saat tombol Login ditekan.
     */
    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleChoiceBox.getValue();

        LoginService loginService = new LoginService();
        if (loginService.validateUser(username, password, role)) {
            try {
                // Pilih file FXML berdasarkan role
                String dashboardFXML = role.equals("Admin") ? "AdminDashboard.fxml" : "CustomerDashboard.fxml";

                FXMLLoader loader = new FXMLLoader(getClass().getResource(dashboardFXML));
                Parent dashboardRoot = loader.load();

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(dashboardRoot));
                stage.setTitle(role + " Dashboard");
            } catch (Exception e) {
                e.printStackTrace();
                statusLabel.setText("Error loading dashboard: " + e.getMessage());
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            statusLabel.setText("Login gagal! Periksa username, password, atau role Anda.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Handle action saat tombol Register ditekan.
     */
    @FXML
    void handleRegister(MouseEvent event) {
    try {
        // Muat halaman Register
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        Parent registerRoot = loader.load();

        // Pastikan registerButton memiliki Scene
        if (registerButton.getScene() == null) {
            System.out.println("Scene untuk registerButton tidak ditemukan!");
            return;
        }

        Stage stage = (Stage) registerButton.getScene().getWindow();
        if (stage == null) {
            System.out.println("Stage tidak ditemukan!");
            return;
        }

        // Set scene baru
        stage.setScene(new Scene(registerRoot));
        stage.setTitle("Register Page");
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        statusLabel.setText("Error navigating to Register page: " + e.getMessage());
        statusLabel.setStyle("-fx-text-fill: red;");
    }
}


}
