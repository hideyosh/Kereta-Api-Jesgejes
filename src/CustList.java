import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustList {

    @FXML
    private TableColumn<Customer, String> colNo;

    @FXML
    private TableColumn<Customer, String> colNama;

    @FXML
    private TableColumn<Customer, String> colPassword;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Customer> tableCust;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up table columns
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        // Load data from database
        loadCustomerData();
    }

    private void loadCustomerData() {
        try (Connection conn = Database.getConnection()) {
            System.out.println("Database connection successful");

            // Query untuk hanya mengambil data dengan role 'customers'
            String query = "SELECT username, password FROM users WHERE role = 'customers'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            int noCounter = 1; // Untuk mengisi kolom `no`

            while (rs.next()) {
                // Debugging data
                System.out.println("Fetching data: " + rs.getString("username") + ", " + rs.getString("password"));

                customerList.add(new Customer(
                        String.valueOf(noCounter++),     // Kolom `no` diisi angka urut
                        rs.getString("username"),        // Kolom `nama` diisi dengan `username`
                        rs.getString("password")         // Kolom `password`
                ));
            }

            // Check if customerList is populated
            if (customerList.isEmpty()) {
                System.out.println("No customers found in the database.");
            }

            tableCust.setItems(customerList);

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    @FXML
    void search_cust(ActionEvent event) {
        String searchKeyword = searchField.getText().toLowerCase();
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();

        for (Customer customer : customerList) {
            if (customer.getNama().toLowerCase().contains(searchKeyword)) {
                filteredList.add(customer);
            }
        }

        tableCust.setItems(filteredList);
    }

    @FXML
    void editbutton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCust.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root, 1920, 1080); // Set ukuran scene ke 1920x1080
            stage.setScene(scene);
            stage.setTitle("Edit Customer");
            stage.setFullScreen(true); // Aktifkan layar penuh
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
