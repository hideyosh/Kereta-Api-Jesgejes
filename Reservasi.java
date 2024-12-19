import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Reservasi {

    @FXML
    private ComboBox<String> comboBrangkat; // ComboBox untuk stasiun awal
    @FXML
    private ComboBox<String> comboTujuan;   // ComboBox untuk stasiun tujuan
    @FXML
    private TextField txtPenumpang;         // TextField untuk nama penumpang
    @FXML
    private Label lblJadwal;                // Label untuk menampilkan jadwal

    // Daftar stasiun contoh
    private final String[] stasiun = {
        "Stasiun Jakarta Kota", "Stasiun Gambir", "Stasiun Bandung", 
        "Stasiun Surabaya", "Stasiun Yogyakarta"
    };

    @FXML
    void Brangkat(ActionEvent event) {
        // Mengisi daftar stasiun awal ke comboBrangkat
        comboBrangkat.getItems().clear();
        for (String s : stasiun) {
            comboBrangkat.getItems().add(s);
        }
    }

    @FXML
    void Tujuan(ActionEvent event) {
        // Mengisi daftar stasiun tujuan ke comboTujuan
        comboTujuan.getItems().clear();
        for (String s : stasiun) {
            comboTujuan.getItems().add(s);
        }
    }

    @FXML
    void Penumpang(ActionEvent event) {
        // Menangkap input nama penumpang
        String namaPenumpang = txtPenumpang.getText();
        System.out.println("Nama Penumpang: " + namaPenumpang);
    }

    @FXML
    void Jadwal(ActionEvent event) {
        // Menampilkan jadwal berdasarkan input stasiun awal dan tujuan
        String brangkat = comboBrangkat.getValue();
        String tujuan = comboTujuan.getValue();
        String namaPenumpang = txtPenumpang.getText();

        if (brangkat == null || tujuan == null || namaPenumpang.isEmpty()) {
            lblJadwal.setText("Mohon lengkapi data stasiun dan nama penumpang!");
        } else {
            lblJadwal.setText("Penumpang: " + namaPenumpang + "\n"
                    + "Berangkat: " + brangkat + "\n"
                    + "Tujuan: " + tujuan + "\n"
                    + "Jadwal: 08:00 - 15:00 WIB");
        }
    }
}
