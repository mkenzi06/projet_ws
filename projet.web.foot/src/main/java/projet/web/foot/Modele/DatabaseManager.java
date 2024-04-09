package projet.web.foot.Modele;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() throws ClassNotFoundException {
        try {
            // Etape 1: Connexion au serveur de base de données MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connexion au serveur de base de données réussie");
            
            // Etape 2: Connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servicewebapi?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            System.out.println("Connexion à la base de données réussie");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la connexion à la base de données: " + e.getMessage());
        }
    }

    // Méthode pour obtenir la connexion à la base de données
    public Connection connectBd() {
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connectBd(); // Vous pouvez ignorer cette ligne si vous ne voulez pas exécuter de requêtes ici
    }
}
