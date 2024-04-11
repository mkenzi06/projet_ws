package projet.web.foot.Modele;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * La classe DatabaseManager gère la connexion à une base de données MySQL.
 * Elle utilise le pilote JDBC MySQL pour établir la connexion et fournit une méthode pour obtenir cette connexion.
 */
public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connexion au serveur de base de données réussie");
            
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servicewebapi?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            System.out.println("Connexion à la base de données réussie");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la connexion à la base de données: " + e.getMessage());
        }
    }
    /**
     * Renvoie la connexion à la base de données.
     *
     * @return La connexion à la base de données.
     */
    public Connection connectBd() {
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connectBd(); // juste pour debug et voir si le serveur se connecte a la BD
    }
}
