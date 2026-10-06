
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "080305Lins.";
    
    private static final String URL = "jdbc:mysql://localhost:3306/uc11"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Driver do MySQL não encontrado. Verifique o .jar em Libraries.\n" + erro.getMessage());
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }
    
}
