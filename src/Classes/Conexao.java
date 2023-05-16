package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public /*final*/ class Conexao {

    public static Connection conector() {
        
        java.sql.Connection conexao = null;

        /*final*/String driver = "com.mysql.jdbc.Driver";

        /*final*/String url = "jdbc:mysql://localhost:3306/smansys";
        /*final*/String user = "root";
        /*final*/String password = "";
        
        try {
            
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
