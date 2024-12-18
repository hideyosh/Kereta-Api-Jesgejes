import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://192.168.0.7:3306/KERETA?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Lintang112233";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}

